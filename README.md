# WaCoDiS Message Dashboard
Spring-boot based application and Angular frontend that listens on RabbitMQ to receive
all messages within the WaCoDiS system.

**Table of Content**  
1. [WaCoDiS Project Information](#wacodis-project-information)
2. [Overview](#overview) 
3. [Installation / Building Information](#installation--building-information)
4. [Deployment](#deployment)
5. [Developer Information](#developer-information)
6. [Contact](#contact)
7. [Credits and Contributing Organizations](#credits-and-contributing-organizations)

## WaCoDiS Project Information
<p align="center">
  <img src="https://raw.githubusercontent.com/WaCoDiS/apis-and-workflows/master/misc/logos/wacodis.png" width="200">
</p>
Climate changes and the ongoing intensification of agriculture effect in increased material inputs in watercourses and dams.
Thus, water industry associations, suppliers and municipalities face new challenges. To ensure an efficient and environmentally
friendly water supply for the future, adjustments on changing conditions are necessary. Hence, the research project WaCoDiS
aims to geo-locate and quantify material outputs from agricultural areas and to optimize models for sediment and material
inputs (nutrient contamination) into watercourses and dams. Therefore, approaches for combining heterogeneous data sources,
existing interoperable web based information systems and innovative domain oriented models will be explored.

### Architecture Overview
For a detailed overview about the WaCoDiS system architecture please visit the 
**[WaCoDiS Core Engine](https://github.com/WaCoDiS/core-engine)** repository.  

## Overview  
WaCoDiS Message Dashboard is a web based application that aims to monitor the message flow between all components of
the WaCoDiS system. Therefore, a Spring Boot backend application listens on RabbitMQ for message exchanges of certain topics.
The backend application provides the received messages via WebSocket to an Angular-based UI that visualizes the message
flow user-friendly manner.

### Modules
WaCoDiS Message Dashboard comprises a backend component as well as a frontend component:
* __WaCoDiS Message Dashboard Backend__
A Spring Boot backend application listens on RabbitMQ for message exchanges of certain topics. It forwards received messages
directly to a WebSocket and provides a REST-based API for fetching archived messages. 
* __WaCoDiS Message Dashboard UI__
The Angular based frontend provides a user-friendly view on the message flow. It connects to the WebSocket of the backend
component to receive messages without any delay. Furthermore, it supports the discovery of archived messages.

### Technologies
* __Java__  
WaCoDiS Message Dashboard has been tested with Oracle JDK 8 and OpenJDK 8. Unless stated otherwise later Java versions
can be used as well.
* __Maven__  
The backend component uses the build-management tool [Apache Maven](https://maven.apache.org/)
* __npm__
To install the frontend dependencies, [npm](https://www.npmjs.com/) ist required.
* __Spring Boot__  
The backend component is built with the [Spring Boot](https://spring.io/projects/spring-boot) 
framework. Therefore, it is not necessary to deploy WaCoDiS Message Dashboard manually with a web server.  
* __Spring Cloud__  
[Spring Cloud](https://spring.io/projects/spring-cloud) is used for exploiting some ready-to-use features for distributed
systems. In particular, [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream) is used for subscribing to
asynchronous messages within the WaCoDiS system.
* __Spring Websocket__
The backend utilizes [Spring WebSocket](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket)
capabilities for providing messages on a WebSocket to the frontend. 
* __RabbitMQ__  
RabbitMQ serves as a message broker for message exchanges between WaCoDiS components which are monitored by WaCoDiS
Message Dashboard. Since RabbitMQ is not part of this project it [must be deployed separately](#dependencies).
* __Angular__:
The frontend component is implemented by the use of the web framework [Angular](https://angular.io/).

## Installation / Building Information
### Build from Source
In order to build WaCoDiS Message Dashboard from source _Java Development Kit_ (JDK) must be available. Data Access 
is tested with Oracle JDK 8 and OpenJDK 8. Unless stated otherwise later JDK versions can be used. In addition [Apache Maven](https://maven.apache.org/), 
[npm](https://www.npmjs.com/) and [Angular](https://angular.io/) must be available for building the application.  

For convenience, the project comes with a Maven profile which facilitates building and bundling the Angular application
as well as the Spring Boot backend as a single application. Run `mvn clean install -Pbundle-ui` to execute the build profile.

If you rather wish to execute the single build steps on your own, follow the instructions below:
1. Install all dependencies for the frontend component via npm by running `npm install` from the _./ui_ folder.
2. Start the build with `ng build --base-href` either using the production flag `--prod` or not.
3. Copy the content from _./ui/dist_ into _./src/main/resources/static_.
4. Make sure the `spring.resources.static-locations` which is defined in the _application.yml_ points to the appropriate
location for your static web content.
5. Run `mvn clean install` to build the whole application
6. Inside _./target_ you will find the bundled _*.jar_ file. 

### Build with Docker
The repository contains a Dockerfile for building a Docker image. Just run `docker build -t wacodis/message-dashboard.`
from the project root to build the image.

The [deployment section](#run-with-docker) provides helpful information for running the application as Docker container.

### Configuration
The backend fetches configurations from [WaCoDiS Config Server](https://github.com/WaCoDiS/config-server). If config server
is not available, configuration values located at *src/main/resources/application.yml* within are applied instead.   

#### Parameters
WaCoDiS Message Dashboard is a Spring Boot application and provides an _application.yml_ for configuration purpose.
A documentation for common application properties can be found at https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html

In addition, some configuration parameters relate to different Spring Cloud components, i.e. [Spring Cloud Stream](https://docs.spring.io/spring-cloud-stream/docs/current/reference/html/)
and [Spring Cloud Config](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/).

To get started the most relevant configuration parameters are described below:
* `spring.resources.static-locations`: Directory that contains the static web resources for the frontend
* `spring.cloud.config.url`: URL that points to WaCoDiS Config Server
* `spring.rabbitmq.host`: Name of the node running a RabbitMQ instance
* `spring.rabbitmq.port`: RabbitMQ port to connect to
* `spring.rabbitmq.username`: Username for RabbitMQ connections
* `spring.rabbitmq.password`: Password for RabbitMQ connectio
* `spring.cloud.stream.bindings.jobs-new.destination`: Topic used to listen for WaCoDiS Job creation messages
* `spring.cloud.stream.bindings.jobs-status.destination`: Topic used to listen for WaCoDiS Job status changed messages
* `spring.cloud.stream.bindings.jobs-deletion.destination`: Topic used to listen for WaCoDiS Job deletion messages
* `spring.cloud.stream.bindings.tools-execute.destination`: Topic used to listen for tool execution messages
* `spring.cloud.stream.bindings.tools-finished.destination`: Topic used to listen for tool finished messages
* `spring.cloud.stream.bindings.tools-failure.destination`: Topic used to listen for tool failure messages
* `spring.cloud.stream.bindings.data-available.destination`: Topic used to listen for published DataEnvelope messages
* `spring.cloud.stream.bindings.data-accessible.destination`: Topic used to listen for accessible DataEnvelope messages

In addition, you have to adopt the environment files inside the _src/environments/_ folder to your runtime environment
specific needs for  running the frontend properly:
* `production`: Indicates whether the environment file should be used in production mode or not
* `api`: WebSocket URL to connect to
* `archiveUrl`: URL for consuming archived messages
* `topics`: List of topics to consume from the WebSocket
 
## Deployment
### Dependencies
WaCoDiS Message Dashboard requires a running RabbitMQ instance for consuming messages. For starting a RabbitMQ instance
as Docker container a _docker-compose.yml_ is provided at _./docker/rabbitmq_.

### Run with Maven
Just start the application by running `mvn spring-boot:run` from the root of project. Make sure you have installed all 
dependencies with `mvn clean install` from the project root and bundled the frontend like described in the 
[Build from Source section](#build-from-source).

### Run with Docker
For convenience, a _docker-compose.yml_ is provided for running the Metadata Connector as Docker container. Just, run 
`docker-compose up` from the project root. The latest Docker image will be fetched from [Docker Hub](https://hub.docker.com/r/wacodis/message-dashboard).
The _docker-compose.yml_ also contains the most important configuration parameters as environment variables. Feel free
to adapt the parameters for your needs.

### Run Frontend on Development Server
If you wish to run the frontend standalone without the Spring Boot backend for development purpose, run `ng serve --open`
for a dev server and opening a web browser window displaying the UI on http://localhost:4200.

## User Guide
WaCoDiS Message Dashboard is part of the great microservice-oriented WaCoDiS System with the scope to monitor the message
flow between the main components. For this purpose it consumes asynchronous messages from a RabbitMQ message broker.
For development or testing purposes you can publish messages in accordance to the [OpenAPI schema definitions](https://github.com/WaCoDiS/apis-and-workflows/tree/master/openapi)
manually via AMQP. A lightweight AMQP publishing client can be found at https://github.com/WaCoDiS/Tools.

## Developer Information
This section contains information for developers.

### Developer Guidelines
WaCoDiS Message Dashboard consumes various messages via AMQP. You will find the required model classes inside the
`de.wacodis.messagedashboard.model` package. If the underlying schema of the messages changes, you need to update the
model classes accordingly. 
1. We strongly recommend generating the new model classes from an [OpenAPI definition](https://github.com/WaCoDiS/apis-and-workflows/tree/master/openapi).
If not already done, first enhance the model schemas within the OpenAPI document.  
2. The project provides Maven profiles for automatically generating model classes from an OpenAPI document. The
_generate-models_ profile generates the models from a Maven artifact you first have to create for the [OpenAPI definition](https://github.com/WaCoDiS/apis-and-workflows/tree/master/openapi)
project. In contrast, by using the _download-generate-models_ profile there is no need to create the artifact in beforehand,
since the execution of this profile will download the latest OpenAPI definitions and then creates the models on top of it. 
You can trigger the profiles by respectively running `mvn clean compile -Pgenerate-models` and `mvn clean compile -Pdownload-generate-models`.
3. If you need to listen for additional topics you have to adjust the `StreamBinder` and `StreamChannels` classes within
the `de.wacodis.messagedashboard` package as well as the `application.yml` and `environment.ts` files accordingly.

### How to contribute
Feel free to implement missing features by creating a pull request. For any feature requests or found bugs, we kindly
ask you to create an issue. 

### Branching
The master branch provides sources for stable builds. The develop branch represents the latest (maybe unstable) state of
development.

### License and Third Party Lib POM Plugins
TODO

## Contributing Developers
|    Name   |   Organization    |    GitHub    |
| :-------------: |:-------------:| :-----:|
| Matthes Rieke | 52° North GmbH | [matthesrieke](https://github.com/matthesrieke) |
| Sebastian Drost | 52° North GmbH | [SebaDro](https://github.com/SebaDro) |
| Arne Vogt | 52° North GmbH | [arnevogt](https://github.com/arnevogt) |

## Contact
The WaCoDiS project is maintained by [52°North GmbH](https://52north.org/). If you have any questions about this or any
other repository related to WaCoDiS, please contact wacodis-info@52north.org.

## Credits and Contributing Organizations
- Department of Geodesy, Bochum University of Applied Sciences, Bochum
- 52° North Initiative for Geospatial Open Source Software GmbH, Münster
- Wupperverband, Wuppertal
- EFTAS Fernerkundung Technologietransfer GmbH, Münster

The research project WaCoDiS is funded by the BMVI as part of the [mFund programme](https://www.bmvi.de/DE/Themen/Digitales/mFund/Ueberblick/ueberblick.html)  
<p align="center">
  <img src="https://raw.githubusercontent.com/WaCoDiS/apis-and-workflows/master/misc/logos/mfund.jpg" height="100">
  <img src="https://raw.githubusercontent.com/WaCoDiS/apis-and-workflows/master/misc/logos/bmvi.jpg" height="100">
</p>