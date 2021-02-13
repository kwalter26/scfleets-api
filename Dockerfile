ARG BUILD_DIR=/build/
ARG RUN_DIR=/app/
ARG JAR_FILE=build/libs/*.jar

FROM openjdk:11-jdk-buster AS build
ARG BUILD_DIR
ARG RUN_DIR
ARG JAR_FILE
COPY  . /build/
WORKDIR /build/
RUN chmod 777 ./gradlew
RUN ./gradlew build --no-daemon

FROM openjdk:11-jre-slim-buster
ARG BUILD_DIR
ARG RUN_DIR
ARG JAR_FILE
COPY --from=build "/build/build/libs/*.jar" "/app/app.jar"
WORKDIR /app/
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080
