#!/usr/bin/perl

# Lib for getting the date in microseconds
use POSIX qw(strftime);
use Time::HiRes qw(time gettimeofday);

use File::Copy qw(cp);
use File::Copy qw(mv);

# mail sending
use MIME::Lite;

#
# Send back reply to client for a given status.
#

sub reply {
    local ($status, $message) = @_;
    local ($remuser, $remhost, $logline) = ();

    print("Status: $status\n");
    print("Content-Type: text/html\n\n");

    if ($status == 201) {
        print("Content Accepted\n");
    }
    elsif ($status == 400) {
        print("An error occurred publishing this file ($message).\n");
    }
    elsif ($status == 500) {
        print("An unexpected error occurred ($message).\n");
    }
    # Note: status 204 and 201 gives have content part

    # Create a simple log
    $remuser = $ENV{'REMOTE_USER'} || "-";
    $remhost = $ENV{'REMOTE_HOST'} || $ENV{'REMOTE_ADDR'} || "-";

    $logline = "$remhost $remuser $filename status $status";
    $logline .= " ($message)" if ($status == 400);
    &log($logline);
    exit(0);
}

sub log {
    local ($msg) = @_;
    open(LOG, ">> $putlog") || return;
    print LOG "$msg\n";
    close(LOG);
}


# routine that will send a mail with all the files given as parameter attached
sub sendMailWithLogs {
    my @args = @_;
    my $obsName = shift(@args);

    my $to = 'portail-ozcar-theia-infra-admin@univ-grenoble-alpes.fr';
    my $from = 'veronique.chaffard@univ-grenoble-alpes.fr';
    my $body = 'various technical logfiles about the import of ' . $obsName;

    my @slicedHostname = split("-", `hostname -s`); # dev-import-module
    my $platform = $slicedHostname[0];              # dev

    my $subject = 'import Theia/OZCAR on ' . $platform . " - " . $obsName;

    # set mail sending program (default to "sendmai" which we dont want)
    MIME::Lite->send("sendmail", "/usr/bin/msmtp -t ");


    # get the success boolean ...err... int
    my $isSuccess = shift(@args);
    if ($isSuccess != 0) {
        $subject .= " SUCCESSFUL";
    }
    else {
        $subject .= " FAILED";
    }

    # construct the basic message
    my $msg = MIME::Lite->new(
        From    => $from,
        To      => $to,
        Data    => $body,
        Subject => $subject
    ) or die "Error creating multipart container: $!\n";

    # add each log file as an attachment
    foreach my $logFile (@args) {
        if (-e $logFile) {
            $msg->attach(
                Type => 'text/plain',
                Path => $logFile,
            );
        }
    }

    $msg->send;
    print "Email Sent Successfully\n"; #  hopefully. no die or anything
}


# Very simple PUT handler. Read the Apache Week article before attempting
# to use this script. You are responsible for ensure that this script is
# used securely.

# A simple log file, must be writable by the user that this program runs as.
# Should not be within the document tree.
$putlog = "/tmp/put1.log";

# Check we are using PUT method
if ($ENV{'REQUEST_METHOD'} ne "PUT") {&reply(400, "Request method is not PUT");}

# Note: should also check we are an authentication user by checking
# REMOTE_USER

# Check we got a destination filename
$filename = $ENV{'PATH_TRANSLATED'};
if (!$filename) {&reply(400, "No PATH_TRANSLATED");}

# Check we got some content
$clength = $ENV{'CONTENT_LENGTH'};
if (!$clength) {&reply(400, "Content-Length missing or zero ($clength)");}

# Read the content itself
$toread = $clength;
$content = "";
while ($toread > 0) {
    $nread = read(STDIN, $data, $clength);
    &reply(400, "Error reading content") if !defined($nread);
    $toread -= $nread;
    $content = $data;
}

# Write it out 
# Note: doesn't check the location of the file, whether it already
# exists, whether it is a special file, directory or link. Does not
# set the access permissions. Does not handle subdirectories that
# need creating.
open(OUT, "> $filename") || &reply(400, "Cannot write to $filename");
print OUT $content;
close(OUT);

# Run import script
my $path = '/var/www/html';
my @filenamesplit = split('/', $filename);
my $obsName = @filenamesplit[4];

my $jarFile = '/import-module-scripts/import-metadata-service.jar';

# Data deposit directory
my $dataDir = $path . '/' . $obsName . '/new';
my $previousDataDir = $path . '/' . $obsName . '/previous';

# Get the date of the day in microseconds
my ($epochsec, $microsec) = gettimeofday();
my $date = strftime("%Y-%m-%d_%H-%M-%S", localtime($epochsec)) . '.' . sprintf("%06.0f", $microsec);

# tmp directory for unzipping file and trash unvalid file
my $realTmpDir = $path . '/' . $obsName . '/tmp';
my $tmpDir = $path . '/' . $obsName . '/tmp' . '/' . $date;
my $unzipDir = $tmpDir . '/unzip';
my $trashDir = $tmpDir . '/trash';


# log file name
my $logDir = $tmpDir;
my $logWorkflow = $logDir . '/log_workflow.log';
#my $logErrorFolder = $logDir. '/error_folder.log';
my $logJava = $logDir . '/log_java.log';
my $logUser = $unzipDir . '/validation-json.log';

my $tmpStr1 = "\n###### $date \n";

my @a = `cd $dataDir;ls -w 1`;

if (@a ne "") {
    #ne contient rien => fausse alerte
    # cree un lien latest pour faciliter le debug sur place
    `mkdir -p $tmpDir && ln -sfn $tmpDir $realTmpDir/latest`;

    foreach my $name (@a) {
        $hasZip = 0;
        $hasJson = 0;
        $validImport = 0;

        $tmpStr1 = $tmpStr1 . "\n ### File to process : $name\n";

        if (lc($name) =~ m/.zip\s/) {
            $hasZip = 1;
            chomp($name); #retire le crlf en fin de ligne

            @result = `mkdir -p $unzipDir 2>&1; chmod -R 755 $unzipDir 2>&1`;

            @result = `unzip -u -d $unzipDir $dataDir/$name 2>&1`;
            $unzipReturnCode = $?;

            $tmpStr1 = $tmpStr1 . "Zip file $name has been unzip in $unzipDir \n";
            #print ("Zip file $name has been unzip in $unzipDir \n");
            #print ("Unzip return command : @result\n");
            $tmpStr1 = $tmpStr1 . 'Unzip command output : ';
            foreach $result (@result) {
                $tmpStr1 = $tmpStr1 . "$result";
            }

            $tmpStr1 = $tmpStr1 . "Unzip command return code : $unzipReturnCode \n";

            if ($unzipReturnCode != 0) {
                $tmpStr1 = $tmpStr1 . "##############################################\n !!!! ERROR: Zip file is invalid !!!!!\n##############################################\n";
            }
            else {
                my @files = `cd $unzipDir;ls -w 1`;
                if (@files ne "") {
                    #ne contient rien => fausse alerte
                    foreach $fileName (@files) {
                        if (lc($fileName) =~ m/.json\s/) {
                            $hasJson = 1;
                        }
                    }
                    if ($hasJson == 1) {
                        # utf-8 needs to be forced, otherwise the JVM fall back to the host ... which is not utf
                        my @result2 = `java -Xmx512M -Dserver.port=8080 -Dfile.encoding=UTF-8 -jar $jarFile --prodDir=$unzipDir  >> $logJava 2>&1`;
                        $importReturncode = $?;

                        #$tmpStr1 = $tmpStr1. "Java import command output : @result2\n";
                        $tmpStr1 = $tmpStr1 . "\n Java import command return code : $importReturncode \n";

                        if ($importReturncode == 0) {
                            $validImport = 1;
                            $tmpStr1 = $tmpStr1 . "\n Import into database successfull \n";
                        }
                        else {
                            $tmpStr1 = $tmpStr1 . "##############################################\n !!!! ERROR: import into database failed !!!!!\n##############################################\n";
                        }
                    }
                    else {
                        $tmpStr1 = $tmpStr1 . "##############################################\n !!!! ERROR: No JSON file !!!!!\n##############################################\n";
                    }
                }
            }
        }
        else {
            $tmpStr1 = $tmpStr1 . "##############################################\n !!!! ERROR: is not a ZIP file !!!!!\n##############################################\n";
        }


        # In case of invalid files, moving them to trash dir
        if ($hasZip == 0 || $hasJson == 0 || $validImport == 0) {
            @result = `mkdir -p $trashDir 2>&1; chmod -R 755 $trashDir 2>&1`;

            $fileToMv = $dataDir . '/' . $name;

            chomp($fileToMv);

            # the mv function keeps the file permissions
            $result = mv($fileToMv, $trashDir);

            if ($result == 1) {
                $tmpStr1 = $tmpStr1 . "File $fileToMv has been move to $trashDir \n";
            }
            else {
                $tmpStr1 = $tmpStr1 . "WARNING: Problem moving file $fileToMv to $trashDir, reason: $! \n";
            }
        }

        # In case of sucessfull import, moving data file to a another dir to keep a history of deposit
        if ($validImport == 1) {

            $tmpStr1 = $tmpStr1 . "\n\n ## Archiving data file \n";

            $fileToMv = $dataDir . '/' . $name;
            chomp($fileToMv);

            # Copying zip file to tmpDir
            $dirDest = $tmpDir;

            $tmpStr1 = $tmpStr1 . "\n # Copying zip data file to tmp dir: \n";
            $result = cp($fileToMv, $dirDest);

            if ($result == 1) {
                $tmpStr1 = $tmpStr1 . "File $fileToMv has been copy to $dirDest \n";
            }
            else {
                $tmpStr1 = $tmpStr1 . "WARNING: Problem copying file $fileToMv to $dirDest , reason: $! \n";
            }

            # Moving Zip file to previous dir
            $dirDest = $previousDataDir;

            $tmpStr1 = $tmpStr1 . "\n # Moving zip data file to previous dir:\n";

            # First suppress existing files
            # List the content
            @files = glob("$dirDest/*.*");

            foreach $file (@files) {
                $tmpStr1 = $tmpStr1 . "Deleting files $file already contained in $dirDest \n";
                $result = unlink($file) or $tmpStr1 = $tmpStr1 . "WARNING : could not delete previous file $file, reason: $! \n";
            }

            # the mv function keeps the file permissions
            $result = mv($fileToMv, $dirDest);

            if ($result == 1) {
                $tmpStr1 = $tmpStr1 . "\nFile $fileToMv has been move to $dirDest \n";
            }
            else {
                $tmpStr1 = $tmpStr1 . "WARNING: Problem moving file $fileToMv to $dirDest , reason: $! \n";
            }
        }
    }

    # Create a log file for the worflow with all the messages
    @result = `mkdir -p $logDir 2>&1; chmod -R 755 $logDir 2>&1`;
    open($fh1, '>>', $logWorkflow) or die "Open file $logWorkflow : $!";
    print($fh1 $tmpStr1);
    close($fh1) or die "close failed: $!";

    print($tmpStr1); # Print to standard output to be passed to mail

    #Send email to the data producer
    sendMailWithLogs($obsName, $validImport, $logJava, $logWorkflow, $logUser);

    #Reply with specific http status
    #import failed
    if ($hasZip == 0 || $hasJson == 0 || $validImport == 0) {
        reply(400);
    } else {
        # Everything seemed to work
        # if content was created, not updated.
        if ($validImport == 1) {
            reply(201);
        }
    }

}
#should not be reached
reply(500);
exit(0);

