FROM openjdk:11

ADD ./target/seller-profile-service-0.0.1-SNAPSHOT.jar /usr/src/seller-profile-service-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "seller-profile-service-0.0.1-SNAPSHOT.jar"]