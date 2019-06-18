# Distribution Tracing

Requirements to compile and run the app:
- Maven
- AdoptJDK 11

Run the following to build:
```
mvn clean install
```

Run the following to start the app:
```
java -jar ./target/<name_of_the_app>.jar
```

Build docker image:
```
mvn clean package docker:build
```

Run docker image:
```
docker run distributed-tracing
```