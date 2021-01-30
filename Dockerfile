ARG BUILD_DIR=/home/gradle/src/
ARG RUN_DIR=/app/
ARG JAR_FILE=build/libs/*.jar

FROM gradle:6.6.1-jdk11 AS build
ARG BUILD_DIR
ARG RUN_DIR
ARG JAR_FILE
COPY  . ${BUILD_DIR}
WORKDIR ${BUILD_DIR}
RUN chmod 777 ./gradlew
RUN ./gradlew build --no-daemon

FROM openjdk:11-jre-slim-buster
ARG BUILD_DIR
ARG RUN_DIR
ARG JAR_FILE
COPY --from=build "${BUILD_DIR}${JAR_FILE}" "${RUN_DIR}app.jar"
WORKDIR ${RUN_DIR}
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080
