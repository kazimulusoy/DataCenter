# The Data Center Application

This application is based on Spring Boot. Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications.

## Open EndPoints

Application is running on http://localhost:8789/ and easly can be changed with application.properties.

Open Api Specs can be reached on:

http://localhost:8789/api-docs

http://localhost:8789/swagger-ui.html

racks

- GET: /api/v1/datacenters/racks/{rackId}/cabelings
- GET: /api/v1/datacenters/racks/
- DELETE: /api/v1/datacenters/racks/{rackId}/units/{unitId}/nodes/{nodeId}/cards/{cardId}
- PUT: /api/v1/datacenters/racks/{rackId}/units/{unitId}/nodes/{nodeId}
- POST: /api/v1/datacenters/racks/{rackId}/units/{unitId}/nodes/{nodeId}/cards
- GET: /api/v1/datacenters/racks/{rackId}
- POST: /api/v1/datacenters/racks/{rackId}/units/{unitId}/nodes

datacenter

- GET: /api/v1/datacenters/
