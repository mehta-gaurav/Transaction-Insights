FROM openjdk:17-jdk-slim

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests

RUN mv target/*.jar insighits.jar
RUN ls -la
RUN pwd
ENTRYPOINT ["java", "-jar", "insighits.jar"]

EXPOSE 8080