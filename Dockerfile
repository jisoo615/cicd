FROM openjdk:17-jdk

WORKDIR /app
# 기존 jar 파일 삭제 (필요 시)
RUN rm -rf /app/*.jar
# 프로젝트 빌드
RUN ./gradlew clean build

COPY build/libs/harudoyak-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]