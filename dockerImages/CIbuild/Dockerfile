FROM docker:18.06.3-ce-git

RUN apk update -y && apk upgrade && apk add --no-cache nss && apk add --no-cache openjdk8
RUN DOCKER_HOST=unix:///var/run/docker-real.sock && apk add maven -y
