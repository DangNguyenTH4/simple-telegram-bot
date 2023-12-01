FROM openjdk:17
COPY ./target/*.jar /app/sunapp.jar
ENTRYPOINT java -jar /app/sunapp.jar
