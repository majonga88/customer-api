# Customer API

## Running the application in dev mode

You can run your application in dev mode:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Access to swagger-ui - Non-dev mode

You can access by default to Swagger webpage for querying the api after launch the application.


