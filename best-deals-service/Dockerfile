FROM openjdk:11

ADD ./target/best-deals-service-0.0.1-SNAPSHOT.jar /usr/src/best-deals-service-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "best-deals-service-0.0.1-SNAPSHOT.jar"]