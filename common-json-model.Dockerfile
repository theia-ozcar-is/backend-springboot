FROM maven:3.6.3-jdk-11 as build_pivot_json_schema

WORKDIR /
COPY . .
RUN mvn --batch-mode compile -am -pl common/common-models && cd common/common-models/ && mvn exec:java

#Serve the static content using nginx
FROM nginx:1.19-alpine

RUN rm /usr/share/nginx/html/index.html
COPY config-schema-json/* /usr/share/nginx/html/
COPY --from=build_pivot_json_schema /common/common-models/pivotSchema.json /usr/share/nginx/html/pivotSchema.json
COPY --from=build_pivot_json_schema /common/common-models/pivotSchemaDraft7.json /usr/share/nginx/html/pivotSchemaDraft7.json
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
