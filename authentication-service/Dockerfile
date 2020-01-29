FROM openjdk:11
WORKDIR usr/src

ADD ./target/authentication-service-0.0.1-SNAPSHOT.jar /usr/src/authentication-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar", "authentication-service-0.0.1-SNAPSHOT.jar"]