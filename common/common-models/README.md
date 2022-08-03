JSON schema repository
======================

[Application](https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/backend-springboot/-/blob/master/common/common-models/src/main/java/fr/theialand/insitu/dataportal/model/application/JsonSchemaGeneratorApplication.java) used to build [json schema](https://json-schema.org/) according to the Java classes used in backend-springboot applications. This json schema are then published under https://in-situ.theia-land.fr/json-schema/ and are used to validate the json file containing the metadata sent by data producers.

The json-schema are packaged in a container image using the [gitlab-CI](https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/backend-springboot/-/blob/master/.commonModels-gitlab-ci.yml) and [the following Dockerfile](https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/backend-springboot/-/blob/master/common-json-model.Dockerfile). The shcema are then deployed on the Kubernetes cluster using the following deployment file: https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/config-repo/-/blob/master/base/deployment-json-schema.yml
