Backend metadata portal
=============================

Backend container application of the data portal. This backend application is used with [this frontend application](https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/theia-ozcar-ui). The application is desribed here: https://theia-ozcar.gricad-pages.univ-grenoble-alpes.fr/documentations/docs/dataportal-doc/index.html

The Kubernetes deployment file is versionned here: https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/config-repo/-/blob/master/base/deployment-metadata-portal.yml

### Environement variables

The following env variables need to be set:
- MONGODB_URL
- KEYCLOAK_RESOURCE
- KEYCLOAK_CREDENTIALS_SECRET

### Ports
- 8080
