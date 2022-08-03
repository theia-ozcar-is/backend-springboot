Variable categories collection populate
=======================================

One time job container application of the data portal. This application is used to populate the collection "variable-categories" of the MongoDB database. This collection is used to avoid requesting the thesaurus each time the variable categories facet needs to be constructed in the data portal. This jobs need to be run each time a new categories is added to the terminologic service. 

The Kubernetes deployment file is versionned here: https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/config-repo/-/blob/master/base/jobs/variable-categories-collection-populate.yml

### Environement variables

The following env variables need to be set:
- MONGODB_URL
- FUSEKI_SPARQL_ENDPOINT
- FUSEKI_SPARQL_USER
- FUSEKI_SPARQL_PASSWORD

### Ports
- 8080
