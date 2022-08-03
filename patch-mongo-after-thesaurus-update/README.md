Populate geonetwork service
===========================

A container application used to mongodb database collection after a modification in the thesaurus. The modification includes change in variable category hierarchy, change in variable broader categories, change in category of variable and variable simplifiedLabel and prefLabel.


populate cataloging service with ISO19115 file descripbing datasets of a data producer. The job is described here: https://theia-ozcar.gricad-pages.univ-grenoble-alpes.fr/documentations/docs/jobs-doc/index.html
The Kubernetes deployment file is versionned here: https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/config-repo/-/blob/master/base/jobs/patch-mongo-after-thesaurus-modification.yml

Environement variables
------------------------
The following env variables need to be set:

MONGODB_URL
RABBITMQ_HOST
RABBITMQ_PORT
RABBITMQ_PASSWORD
RABBITMQ_USER
FUSEKI_SPARQL_ENDPOINT
FUSEKI_SPARQL_USER
FUSEKI_SPARQL_PASSWORD

Ports
------

8080
