# ---- Base Node for UI build ----
FROM node:13.8 AS base-ui

# prepare the source files for build
RUN mkdir /tmp/ui

# only copy package.json, so we don't reinstall deps for every code change
COPY ./ui/package.json ./ui/package-lock.json /tmp/ui/
# npm also allowed to run as root
RUN cd /tmp/ui && npm install --unsafe-perm

RUN npm install -g @angular/cli@7.3.8

COPY ./ui/ /tmp/ui
RUN cd /tmp/ui && ng build --prod --base-href /message-dashboard/

# ---- Base maven ----
FROM maven:3.5-jdk-8-slim AS base

# prepare the source files for build
RUN mkdir /tmp/message-dashboard
COPY . /tmp/message-dashboard

# copy the compiled UI
COPY --from=base-ui /tmp/ui/dist/* /tmp/message-dashboard/src/main/resources/static

# run maven
RUN cd /tmp/message-dashboard && mvn clean install -DskipTests=true

# find the JAR file and move it
RUN bash -c 'find /tmp/message-dashboard/target -maxdepth 1 -size +1048576c | grep message-dashboard | xargs -I{} mv {} /app.jar'

# now the runnable image
FROM adoptopenjdk/openjdk8:alpine

# copy over the dist from the base build image
COPY --from=base /app.jar /app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

EXPOSE 8080
