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

