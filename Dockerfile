FROM openjdk:8
ADD target/incognito-myth.jar incognito-myth.jar
ENTRYPOINT ["java","-jar","/incognito-myth.jar"]