FROM gradle:7.4.3 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY .env /app/.env
COPY --from=build /home/gradle/src/build/libs/*.jar /app/bulk-billing.jar
ENTRYPOINT ["java","-jar","/app/bulk-billing.jar"]