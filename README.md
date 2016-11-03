### Notes:
1. Application is Spring Boot application and packed as a standalone JAR file.
   It runs on port 8080. Example "http://localhost:8080/tax"
2. I extended data model(Json). There could be two additional optional fields in Goods("taxFree": boolean, "imported": boolean). This should not affect functionality, rather it provides API to specify explicitly whether goods is tax-free/imported.

### How to build:
*mvn clean install*
### How to run(2 options):
1. *mvn spring-boot:run*
2. *java -jar here-task-1.0-SNAPSHOT.jar*

### Rest API description:
* **/tax**,  POST: Accepts array of:
```javascript
 {
    "description": string, mandatory;
    "count": integer, mandatory;
 	"unitPrice": double, mandatory;
 	"taxFree": boolean, optinal;
 	"imported": boolean, optional;
 }
```
Returns:
```javascript
 {
    "salesTax": string;
 }
```

### Config file:
Application has [application.properties](./src/main/resources/application.properties) config file.
Configurable parameters:
* *logging.file=here.log* - log file name;
* *tax.free.key.words* - space/comma/semi-column separated list of *'key words'* for tax-tree goods lookup;
* *imported.key.words* - space/comma/semi-column separated list of *'key words'* for imported goods lookup;

### Data Model Examples:
See json files in [./src/test/resources/json/](./src/test/resources/json/)

### Frameworks/Tools Used:
* Spring Core/Boot/MVC/Test;
* JUnit, Mockito;
* Logback logger
* Maven;
* Chrome Postman plugin for REST testing;