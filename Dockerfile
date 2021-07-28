FROM gradle:7.1.1-jdk11 AS base
WORKDIR /opt/hello-spring-boot
COPY ./ ./
RUN ./gradlew assemble

FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /opt/hello-spring-boot
COPY ./ ./
COPY --from=base /opt/hello-spring-boot/build/libs/demo-0.0.1-SNAPSHOT.jar ./
CMD java -jar demo-0.0.1-SNAPSHOT.jar ./