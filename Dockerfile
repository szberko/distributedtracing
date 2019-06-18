FROM adoptopenjdk/openjdk11:latest
MAINTAINER Me

RUN mkdir /opt/app
COPY target/distributed-tracing-1.0-SNAPSHOT.jar /opt/app
WORKDIR /opt/app
CMD ["java", "-jar", "distributed-tracing-1.0-SNAPSHOT.jar"]