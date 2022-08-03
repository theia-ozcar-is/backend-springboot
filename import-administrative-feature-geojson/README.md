Import administrative feature geojson
=======================================

One time job container application of the data portal. This application is used to populate the collection "administrative features" of the MongoDB database. This collection is used to enrich observation documents from the data producer with administrative features. This jobs need to be run each time a new country need to be added or updated to the administrative features. The geojson file of the country needs to be added to the /resources/administrative-geojson before to start the job. Such geojson file can be downloaded from  https://osm-boundaries.com/ (admin levels from 2 to 6, including national water, simplification: Very little (0.001) et Almost nothing (0.0001) for France).

The Kubernetes deployment file is versionned here: https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/config-repo/-/blob/master/base/jobs/import-administrative-feature-geojson.yml

### Environement variables

The following env variables need to be set:
- MONGODB_URL
- FUSEKI_SPARQL_ENDPOINT
- FUSEKI_SPARQL_USER
- FUSEKI_SPARQL_PASSWORD

### Ports
- 8080
