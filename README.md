# Movie Rentals

A Java application to creates an information slip about movie rentals.

##  Getting Started

### Prerequisites
- Java 21+
- Maven 3.8+

---
## Build and Run
###  Clean the project
```bash
mvn clean
```
###  Compile the project
```bash
mvn compile
```
###  Run the tests
```bash
mvn test
```
###  Run the application
```bash
mvn exec:java -Dexec.mainClass="com.etraveli.movierental.Main"
```
###  Package the application
```bash
mvn package
```
###  Run the packaged application
```bash
java -cp target/movie-rentals-1.0-SNAPSHOT.jar com.etraveli.movierental.Main
``` 
---

## Log
Logging is configured via Logback, using the logback.xml file located in:
src/main/resources/logback.xml
 

