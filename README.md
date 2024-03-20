Donations Service API Definitions
---

Prerequisites
---
- Java 17, because Gradle does not support Java 21 yet.
- Docker
- Maven
- npm
- Angular 17

#Build
## Build API artifacts

```shell
cd donations-api
./build-api-artifacts.sh
```
This builds versioned [API definition](donations-api/definitions/donations-svc.v1.openapi.yaml), html documentation for the API,
server side stubs library jar, and typescript library for SPA client.

All sides use type-safe language, so the integrity of of client/server
communication is checked by compiler and code completion makes development more productive.

For the purpose of take home project documentation is committed to the repository.
[donations-svc-api-docs-1.0.0.html](https://htmlpreview.github.io/?https://github.com/kgignatyev/digital-seattle/blob/main/donations-api/out/donations-svc-api-docs-1.0.0.html)

## Build and run service

in a different terminal run
```shell
cd donations-sboot-service
mvn  spring-boot:run
```

## Build and run UI

in a different terminal run
```shell
cd donations-ui
npm install
npm run start
```

# Development

Changes to UI are immediately applied and relected by the running UI

Changes to service hot applied either by running build in a separate terminal, or restarting service
```shell
cd donations-sboot-service
mvn  package
```
**Note**: for faster build ***mvnd*** can be used



## Poking around

On start some [test data is generated](donations-sboot-service/src/main/kotlin/com/kgignatyev/donations/service/services/TestDataSvc.kt) and stored, so the UI 
can be used to look at the data. 
![UI](https://github.com/kgignatyev/digital-seattle/blob/main/donations-ui/docs/ui.png)




