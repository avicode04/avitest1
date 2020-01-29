FROM openjdk:11

ADD ./target/order-service-0.0.1-SNAPSHOT.jar /usr/src/order-service-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "order-service-0.0.1-SNAPSHOT.jar"]