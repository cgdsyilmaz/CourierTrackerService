## CourierTrackerService - Design
I have designed courier tracker service and its project structure with the mind set of having a clean architecture and code.
To achieve this CourierTrackerService utilizes exception handling, chain of responsibility and publisher(observer) patterns.


## TECH STACK
    • Java 17
    • Spring Boot 2.6.3 (Please refer to the build.gradle file for the dependencies)
    • PostgreSQL
    • Lombok
    • Docker, Docker-Compose and Docker Hub
    • Gitkraken and GitLab

## How to use
Requires JDK17 Gradle Docker and Docker-compose to build and run. One can refer to the postman collection in order to get a knowledge about how to use the application.

Please add stores before testing. This is a very important step.

Luckily I have pushed the latest Docker image to the Docker Hub as a public container image. Hence, only docker compose run command below is sufficient.

    > docker-compose -f docker-compose.yaml up

## Improvement ideas
    • In order to meet time requirements I could not write any test code. I could have used TDD at the beginning in order to write tests.
    
    • I also could have made 3 seperate microservices in terms of high level design.
    One can apply seperation of concerns and develop Store, Courier, Location microservices.

    • Authentication and authorization could have been very useful for this project. 
    User with the role manager or supervisor can add or remove courier and stores.
