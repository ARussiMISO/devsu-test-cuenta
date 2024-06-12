FROM openjdk:17 AS build

ARG VERSION=latest
ARG JAR_PATH=./build/libs/*.jar

VOLUME /tmp
WORKDIR /
ADD . .
RUN microdnf install findutils
RUN ./gradlew --stacktrace clean test build
RUN mv /${JAR_PATH} app.jar

FROM openjdk:17
WORKDIR /

COPY --from=build /app.jar /

ENV VERSION=$VERSION
ENV JAVA_OPTS="-Dspring.profiles.active=${ENVIRONMENT}"

EXPOSE 8080

ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=${ENVIRONMENT} /app.jar"]