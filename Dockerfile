### BUILD image
FROM maven:3.5-jdk-8 as builder

# copy the pom.xml file and save dependencies to ofline for a faster build time.
ADD ./pom.xml ./pom.xml

# save the dependency jars to offline
RUN mvn dependency:go-offline -B

# copy the source folder to the docker
COPY ./src ./src

# build the jar
RUN mvn -Dmaven.test.skip=true package

# wait for other services to run before running this container.
VOLUME /tmp
FROM openjdk:8u171-jre-alpine

WORKDIR  /myapp

# RUN apk add --no-cache bash
RUN apk update && apk add bash
COPY . /wait-for-it.sh/wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# rename the jar file to app.jar
COPY --from=builder /target/*.jar app.jar

# --chown=builder /target/blog-service-0.0.1.jar app.jar

# reconfirm the app.jar
RUN sh -c 'touch /app.jar'

ENV JAVA_OPTS=""
# run the jar file
ENTRYPOINT ["java","$JAVA_OPTS","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","/app.jar"]
