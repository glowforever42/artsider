# JDK11 기반으로 도커 이미지 생성
FROM openjdk:11-jdk

ARG JAR_FILE=build/libs/artSider-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

# 8080 포트 사용
EXPOSE 8080
