FROM maven:3.6.3-jdk-11

WORKDIR /mdts-asset-api

COPY . .

RUN mvn clean install -DskipTests

CMD mvn spring-boot:run