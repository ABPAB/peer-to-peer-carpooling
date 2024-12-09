# Use the Gradle image for ARM architecture
ARG BUILD_HOME=/rideservice
FROM --platform=linux/arm64 gradle:jdk17 as build-image

ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
WORKDIR $APP_HOME

# Copy the gradle build files into the proper working directory
COPY --chown=gradle:gradle build.gradle settings.gradle $APP_HOME/
COPY --chown=gradle:gradle src/ $APP_HOME/src/

# Ensure the gradle wrapper (if used) is copied too
COPY --chown=gradle:gradle gradlew $APP_HOME/
COPY --chown=gradle:gradle gradle/ $APP_HOME/gradle/

RUN gradle --no-daemon build

# Use the Debian-based OpenJDK image for ARM architecture
FROM --platform=linux/arm64 eclipse-temurin:17-jdk

ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME

# Copy the jar file from the build image to the runtime container
COPY --from=build-image $APP_HOME/build/libs/rideservice-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
