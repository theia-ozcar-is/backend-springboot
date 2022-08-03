Backend association variable
=============================

Backend container application used to make the link between producer variable and theia/ozcar variable available [on the thesaurus](https://in-situ.theia-land.fr/skosmos/en/). This backend application is used with [this frontend application](https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/variable-association-ui). The workflow of a producer variable association is described : https://theia-ozcar.gricad-pages.univ-grenoble-alpes.fr/documentations/docs/association-application-doc/index.html

The Kubernetes deployment file is versionned here: https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/config-repo/-/blob/master/base/deployment-variable-association.yml

### Environement variables

The following env variables need to be set:
- SPARQL_ENDPOINT_URL
- JSON_SCHEMA_URL
- MONGODB_URL
- RABBITMQ_HOST
- RABBITMQ_PASSWORD
- RABBITMQ_PORT

### Ports
- 8080
