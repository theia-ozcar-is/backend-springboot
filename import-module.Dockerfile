FROM maven:3.6.3-jdk-11 as build_import_module

RUN apt-get update && apt-get install -y patch
WORKDIR /
COPY . .
RUN ls
RUN mvn --batch-mode package -Dmaven.test.skip=true -pl import-metadata-service -am

FROM debian:buster

ENV APACHE_RUN_DIR=/var/run/apache2
ENV APACHE_LOCK_DIR=/var/run/apache2
ENV APACHE_PID_FILE=/var/run/apache2/apache2.pid
ENV APACHE_RUN_USER=www-data
ENV APACHE_RUN_GROUP=www-data
ENV APACHE_LOG_DIR=/tmp

RUN mkdir -p $APACHE_RUN_DIR && chown www-data:www-data $APACHE_RUN_DIR

# msmtp-* is the bash mail client
# build-essential contains make, which is needed by perl CPAN
RUN apt-get update && apt-get install -y --no-install-recommends \
    apache2 \
    unzip \
    openjdk-11-jre \
    gettext-base \
    msmtp \
    msmtp-mta \
    build-essential \
    curl \
    && setcap 'cap_net_bind_service=+ep' /usr/sbin/apache2

#enable dav module and in-situ.theia-land.fr site
RUN a2enmod cgid actions

# Install necessary Perl module for sendin mail w/ attachments
# install it in a in an non-interactive way
RUN PERL_MM_USE_DEFAULT=1 perl -MCPAN -e "install MIME::Lite"

# Copy the enstrypoint script
COPY import-module-scripts/entrypoint.sh /
RUN chmod +x /entrypoint.sh

## Copy the perl and bash script which manages the metadata import workflow
COPY import-module-scripts/ /import-module-scripts/
COPY import-module-scripts/put.psgi /usr/lib/cgi-bin/

# Copy springboot import module application
COPY --from=build_import_module import-metadata-service/target/import-metadata-service-0.0.1-SNAPSHOT.jar /import-module-scripts/import-metadata-service.jar

RUN chown -R www-data /import-module-scripts && chown -R www-data:www-data /var/www

USER www-data

#Expose port for http
EXPOSE 80

ENTRYPOINT ["/bin/bash","/entrypoint.sh"]
CMD ["/usr/sbin/apache2","-DFOREGROUND"]
