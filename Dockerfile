FROM openjdk:11-jre-slim-buster
COPY --from=build "build/libs/*.jar" "/app/app.jar"
WORKDIR /app/
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080
