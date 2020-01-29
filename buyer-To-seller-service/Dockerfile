FROM openjdk:11

ADD ./target/buyer-To-seller-service-0.0.1-SNAPSHOT.jar /usr/src/buyer-To-seller-service-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "buyer-To-seller-service-0.0.1-SNAPSHOT.jar"]