FROM gradle:jdk-21-and-22-alpine AS build
WORKDIR /transaction-log-demo
COPY build.gradle .
COPY settings.gradle .
COPY gradle ./gradle
RUN gradle dependencies --no-daemon
COPY src ./src
RUN gradle build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine
COPY --from=build ./transaction-log-demo/build/libs/*.jar transaction-log-demo.jar
ENTRYPOINT ["java", "-jar", "transaction-log-demo.jar"]