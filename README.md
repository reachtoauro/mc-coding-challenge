#Code Challenge

## Introduction
This document outlines the details as well as best practices in developing spring boot based REST API application which determines if two cities are connected based on origin and destination user inputs. This project also includes 
 > *Swagger API*
 > *JUnit* Test Cases
 > *100%* code coverage through Eclemma
 > *0 Sonar* Bugs, Vulnerabilities and Code smells
 > *Functional Test cases*
 > *Monitoring* metrics (API statistics)


## Prerequisites
Minimun requirements to run this project
> 1. *Java* 8 or above
> 2. *Maven* 
 
## Design Solution

> The solution is implemnted using Graph data structure where each vertex represents a city, and each edge represents a connection between two cities.
> The graph is unidirected as per one of the given examples where origin was Boston and destination Philadelphia
> Loading the given connected cities from the given file to crate a Graph using Adjacency Map
> Breadth First Traversal mechanism to identify if a path exists between two cities inside a Utility


## Implementation
This application is a *maven* project with implementations for below.

> 1. Spring Boot & Spring REST API
> 2. Swagger Specification
> 3. Unit test cases & ATDD
> 4. Actuator for health, info and monitoring metrics 

## Coding
> The project is created using the Spring Initializer at https://start.spring.io, specifying a Maven Project using Java 8, Spring Boot version 2.3.5, and packaged as a JAR in Spring Tool Suite(STS)
> Created a resource(controller) annotated with @RestController for Spring MVC.
> For Logging I used logback(spring variation) and SL4J(used as façade for commonly used logging frameworks in our case logback)
> As per best practice, I placed the city.txt resource data file into the java resources folder, where we can read it using the ResourceLoaderAware implementation.

## Installation & Run
  
 1. To install and run goto the directory in which you want to install the project.
clone the github project via URL

```git
git clone https://github.com/reachtoauro/mc-coding-challenge.git

```
 2. do  a maven clean install to build the source code
```maven
mvn clean install

```
3. The UNIT testsuite can be executed separately by
```maven
mvn test

```

4. Run the application using maven Spring Boot plugin
```maven
mvn spring-boot:run 
```
 
## API Testing
The API can be tested using an REST API testing tools such as POSTMAN, command line or browser
 
```
Browser:

http://localhost:8080/connected?origin=boston&destination=newark

OR

POSTMAN or Command line

curl -X GET 'http://localhost:8080/connected?origin=Boston&destination=Albany'

```
## Swagger UI

```
http://localhost:8080/swagger-ui.html
```
## API monitoring/metrics

```
health url
http://localhost:8081/actuator/health

metrics
http://localhost:8081/actuator/metrics

info
http://localhost:8081/actuator/info

loggers
http://localhost:8081/actuator/loggers

thread dump
http://localhost:8081/actuator/threaddump

All available Actuator endpoints can be accessed here
http://localhost:8081/actuator/

```

## Future Scope
> Detailed ATDD test cases can be implemented using Cucumber and feature files
> Usage of logback makes it easy to configure logs based on profiles
> Also it can be leveraged to generate JSON logs which can be used for analytics purposes and using async logger features in logback
> More logging profiles can be introduced
> Authentication layer can be implemented using Spring Security or JWT
> Performance Monitoring can be implemented using New Relic
> The application can be easily dockerized and deployed in Elastic Beanstalk, ECS or leveraging EKS in AWS