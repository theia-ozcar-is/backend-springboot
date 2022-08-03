Import metadata service
=======================

A container application used by data producer to deposit data and metadata in Theia/OZCAR information system. The workflow of a producer data deposit using this application is described : https://theia-ozcar.gricad-pages.univ-grenoble-alpes.fr/documentations/docs/import-doc/index.html

The Kubernetes deployment file is versionned here: https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/config-repo/-/blob/master/base/deployment-import-module.yml

### Volumes - reserved persitent volume

The data and metadata are deposited under personnal repository that can be created using [mkAll bash script](https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/backend-springboot/-/tree/master/import-module-scripts/create-data-deposit/mkall). Those scripts are now included in the application image under `/create_data_deposit`. To recreate producer data deposit folders using existing passwords you need to mount the "password_en_clair" file to `create_data_deposit/prod.pwd` file. For Kubernetes deployment, this volume is mounted using secrets.
The data deposit repository structure must be created on a persistent volume that will be mounted in the container. This persistent volume must be mounted to the container application (apache server) such as:

- /my/host/volume:/var/www/html

### Configuration files

The following configuration files need to be mounted. All configuration files are under https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/config-repo/-/tree/master/base/configs for consistency reasons.

- configs/import-module/apache/virtualhost.conf:/etc/apache2/sites-available/000-default.conf
- configs/import-module/msmtp/msmtprc:/etc/msmtprc

### Environement variables

The following env variables need to be set:
- FUSEKI_SPARQL_ENDPOINT
- FUSEKI_SPARQL_USER
- FUSEKI_SPARQL_PASSWORD
- JSON_SCHEMA_URL
- MONGODB_URL
- RESTCOUNTRIES_API_URL
- OPENELEVATION_API_URL
- KOPPENCLIMATES_API_URL
- RABBITMQ_HOST
- RABBITMQ_PASSWORD
- RABBITMQ_PORT

**!! Warning: these env vars need to be copied to the Apache virtualHost using PassEnv directive. Otherwise, they won't visible for the [put.psgi pERL script](https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/backend-springboot/-/tree/master/import-module-scripts/put.psgi) and the  into the perl script at boot time, and the java application won't be able to launch properly.**

### Ports
- 80



