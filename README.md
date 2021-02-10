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
In order to build the WaCoDiS Metadata Connector from source _Java Development Kit_ (JDK) must be available. Data Access 
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
* `spring.rabbitmq.host`: Name of the node running a RabbitMQ instance
* `spring.rabbitmq.port`: RabbitMQ port to connect to
* `spring.rabbitmq.username`: Username for RabbitMQ connections
* `spring.rabbitmq.password`: Password for RabbitMQ connectio
* `spring.cloud.stream.bindings.input-data-envelope.destination`: Topic used to listen for published DataEnvelope messages
* `dataaccess.uri`: URL that points to a WaCoDiS DataAccess API instance
 
## Deployment
### Dependencies
TODO

### Run with Maven
Just start the application by running `mvn spring-boot:run` from the root of project. Make sure you have installed all 
dependencies with `mvn clean install` from the project root and bundled the frontend like described in the 
[Build from Source section](#build-from-source).

### Run with Docker
TODO

### Run frontend on development server
Run `ng serve --open` for a dev server and opening a web browser window displaying the UI on http://localhost:4200.

## User Guide
TODO

## Developer Information
This section contains information for developers.

### How to Contribute
TODO

### Pending developments
TODO

### Branching
The master branch provides sources for stable builds. The develop branch represents the latest (maybe unstable) state of
development.

### License and Third Party Lib POM Plugins
[optional]

## Contact
|    Name   |   Organization    |    Mail    |
| :-------------: |:-------------:| :-----:|
| Sebastian Drost | Bochum University of Applied Sciences | sebastian.drost@hs-bochum.de |
| Arne Vogt | Bochum University of Applied Sciences | arne.vogt@hs-bochum.de |
| Andreas Wytzisk  | Bochum University of Applied Sciences | andreas.wytzisk@hs-bochum.de |
| Matthes Rieke | 52° North GmbH | m.rieke@52north.org |

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