ARG BUILD_DIR=/home/gradle/src/
ARG RUN_DIR=/app/
ARG JAR_FILE=build/libs/*.jar

FROM openjdk:11-jdk-buster AS build
ARG BUILD_DIR
ARG RUN_DIR
ARG JAR_FILE
COPY  . ${BUILD_DIR}
WORKDIR ${BUILD_DIR}
RUN ./gradlew build --no-daemon

FROM openjdk:11-jdk-buster
ARG BUILD_DIR
ARG RUN_DIR
ARG JAR_FILE
COPY --from=build "${BUILD_DIR}${JAR_FILE}" "${RUN_DIR}app.jar"
WORKDIR ${RUN_DIR}
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080
