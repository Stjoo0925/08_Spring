FROM openjdk:17
ARG JAR_FILE=build/libs/chap07-thymeleaf-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} chap07-thymeleaf-0.0.1-SNAPSHOT
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/chap07-thymeleaf-0.0.1-SNAPSHOT.jar"]