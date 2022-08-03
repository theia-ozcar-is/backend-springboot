Populate geonetwork service
============================

A container application used to populate cataloging service with ISO19115 file descripbing datasets of a data producer. The workflow of populating the cataloging service is described : https://theia-ozcar.gricad-pages.univ-grenoble-alpes.fr/documentations/docs/catalogue-service-doc/index.html

The Kubernetes deployment file is versionned here: https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/config-repo/-/blob/master/base/deployment-geonetwork-standalone.yml

### Environement variables

The following env variables need to be set:

- MONGODB_URL
- RABBITMQ_HOST
- RABBITMQ_PASSWORD
- RABBITMQ_PORT
- GEONETWORK_HOST
- GEONETWORK_USER
- GEONETWORK_PASSWORD

### Ports
- 8080


