######################################
# Base image for compiling container #
######################################
FROM maven:3.8.3-openjdk-17 as builder

# Define workdir where commands will be executed
WORKDIR /project

# Copy project dependencies
COPY pom.xml /project/

# Copy project code
COPY src /project/src

# Compile project
RUN mvn package -DskipTests

########################################
# Base image for application container #
########################################
FROM khipu/openjdk17-alpine

# Define work dir where JAR will be located
WORKDIR /usr/src/app/

# Copy JAR from compiling container
COPY --from=builder /project/target/*.jar /usr/src/app/

# Port
EXPOSE 8080

# Command executed with docker run
CMD [ "java", "-jar", "practica-books_monolith-0.0.1-SNAPSHOT.jar" ]