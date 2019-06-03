FROM openjdk:8-jre-alpine

ENTRYPOINT ["/usr/bin/java", "-jar", "/tmp/app/app.jar", "-Dspring.data.mongodb.uri=mongodb://mongo/test"]

ADD target/lib           /tmp/app/lib
ARG JAR_FILE
ADD target/${JAR_FILE} /tmp/app/app.jar