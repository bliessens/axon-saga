## To use this sample project

* Before starting, just run the Spring Boot application once. This will
create the HSQL database in folder `build/axon-db`
* In IntelliJ: create HSQL datasource with
  * path: `/full/path/to/projectroot/build`
  * Database: `axon-db`
  * User: `SA`
  * blank password
  * MAKE SURE YOU CLOSE THE DATABASE connection before RUNNING the Spring Boot project !!!
  * MAKE SURE YOU CLOSE THE DATABASE connection before RUNNING the Spring Boot project !!!
  * MAKE SURE YOU CLOSE THE DATABASE connection before RUNNING the Spring Boot project !!!

* Create **your** Spring Boot run configuratiuon with **your** spring profile enabled

* You **must** create following Spring Beans:
  * a separate `XStreamSerializer` bean, if not you'll
    get a Json serializer which is tougher to play with.
  * an `AnnotatedSagaManager`
  * an `AnnotatedSagaRepository`
  * a `JpaSagaStore`
  * a `JpaTokenStore`
  * last but not least, a `TrackingEventProcessor`

## Branches

* master: Spring Boot application with Axon, HSQLDB &JPA autoconfiguration
* solution: implemented saga with CommandGateway injected with ResourceInjector
* solution-sagaParamResolver: implemented saga with CommandGateway injected with SpringParameterResolverFactory
* starting-point: where to start implementing hte exercise, gradle modules can be generated for each participant

## To start the exercises

This repo is meant for classroom trainings whereby all participants
work on the same git branch but distinct project source files. 
Each participant works on his/her own 'project' individually.

1. Declare the workshop participants
   Open the root [build file](build.gradle) and declare the participant names in
   ```groovy 
   baselineNames = ["john", "mark", "eve"]
   ```
2. Generate the skeleton for each participant
   In the same `build.gradle`, run Gradle task 'generateBaseline'
   For each participant, a gradle project is generate with the same skeleton code.
   Commit all generate code. You are ready to start.