
### Geonetwork Client
#### Overview
It is solely a API Client. 
This module is a dependency to the *ETL* module

This module exposes a few methods to be used when communicating with GN
The methods evolve around CRUD concepts : publish, get, search, delete.
The interface exposed is `GeonetworkClient.java`

It's input/output are simple primitive objects such as Strings (for XML payload), integers... and the like

The choice have been made to not expose fully defined objects, and to keep business logic at the minimum here.
Think of it as an utility for communicating with the API, nothing more.

The workflow logic, and complex (de)serialization, would be concentrated in the **ETL** module for example. 

#### Generated client
Due to the complexity of the GN API, and according to the official doc, I decided to use a **codegen** to generate the skeleton of this API client.
see `pom.xml` for details on the procedure.
The generated code evolves around Spring own REST client, so we keep an homogenous structure.

### Docker setup
See Docker-compose or kubernetes configuration in *config-repo* gitlab project.

### Database setup
When using **geonetwork** images, one can setup the **database properties** where data will be stored.
This can be done with environment variables :
 `GEONETWORK_DB_USERNAME`
 `GEONETWORK_DB_PASSWORD`
 `GEONETWORK_DB_NAME`
 `GEONETWORK_DB_HOST`
 `GEONETWORK_DB_PORT`
 `GEONETWORK_DB_CONNECTION_PROPERTIES`
 
 GN will read those values via `webinf/config-db/jdbc.properties` and feed them to the JDBC driver.
 
### API issues
The spec of GN 4.0.1 is not correct. it has quite a lot of bugs that needs to be corrected by hand
before being fed to a code generator (openAPI codegen)
Otherwise, the code would not compile because of this 
[issue](https://github.com/geonetwork/core-geonetwork/issues/5171).

Also, most signatures of endpoint are not correct, due to a misunderstanding of the OpenAPI 3 spec.
All of this need to be corrected by hand in a patch file, located in `src/main/resources`

Only then, the codegen can produce some reasonably useable output.
Probably few people use the codegen approach. 

### Testing
Testing the code can be done using test classes, mostly *IT classes , which means IntegrationTest .
This naming makes it possible for maven to execute those tests in the `verify` phase of the lifecycle, for when we will have a CI chain.

In the meantime, these can be run via IntelliJ, with a local or remote GN. check `application.properties` located in `src/test/resources` for configuration.
 