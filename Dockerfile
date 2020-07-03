FROM openjdk:8
EXPOSE 8080
ADD target/incognito-myth.jar incognito-myth.jar
ENTRYPOINT ["java","-jar","/incognito-myth.jar"]