FROM openjdk:17-jdk

WORKDIR /app

COPY build/libs/harudoyak-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]