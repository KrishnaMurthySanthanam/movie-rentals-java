# Refactoring Java

The code creates an information slip about movie rentals.
Rewrite and improve the code after your own liking.

Think: you are responsible for the solution, this is a solution you will have to put your name on.


## Handing in the assignment

Reason how you have been thinking and the decisions you took. 
You can hand in the result any way you feel (git patch, pull-request or ZIP-file).
Note: the Git history must be included.


##  Getting Started

### Prerequisites
- Java 24+
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
mvn exec:java -Dexec.mainClass="com.krishna.movierental.Main"
```
###  Package the application
```bash
mvn package
```
###  Run the packaged application
```bash
java -cp target/movie-rentals-1.0-SNAPSHOT.jar com.krishna.movierental.Main
``` 
---

## Log
Logging is configured via Logback, using the logback.xml file located in:
src/main/resources/logback.xml
 

