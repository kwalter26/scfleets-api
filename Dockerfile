FROM openjdk:11-jdk-buster AS build
COPY  . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew build --no-daemon

FROM openjdk:11-jdk-buster
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
