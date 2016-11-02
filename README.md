### Notes:
1. I extended date model(Json). There could be two additional optional fields("category", "imported"). See [Data Model Examples]
2. Application is Spring Boot application and packed as a standalone JAR file.
   It runs on port 8080. Example "http://localhost:8080/tax"
3. Hope I have understood/implemented task correctly. If there are some concerns I could try to rework application.

### How to run:
1. *mvn spring-boot:run*
2. *java -jar here-task-1.0-SNAPSHOT.jar*

### Rest API description:
* **/tax**,  POST: Accepts array of:
```javascript
 {
    "description": string, mandatory;
    "count": integer, mandatory;
 	"unitPrice": double, mandatory;
 	"category": string, optinal, one of "FOOD", "BOOKS", "MEDICAL";
 	"imported": boolean, optional
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
* *debug=true* - to run with Debug loging level
* *try.to.infer=true* - to run application with 'infer' functionality enabled

### Data Model Examples:
See json files in [./src/test/resources/json/](./src/test/resources/json/)

### Frameworks/Tools Used:
* Spring Core/Boot/MVC/Test;
* JUnit, Mockito;
* Maven;
* Chrome Postman plugin for REST testing;