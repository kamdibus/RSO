FROM openjdk:8-jre-alpine
FROM node:10-alpine

WORKDIR /app
COPY src/main/frontend/. /app
RUN npm install
RUN chmod a+x /app/node_modules/.bin/react-scripts
RUN npm run build

ENTRYPOINT ["/usr/bin/java", "-jar", "/tmp/app/app.jar"]

ADD target/lib           /tmp/app/lib
ARG JAR_FILE
ADD target/${JAR_FILE} /tmp/app/app.jar