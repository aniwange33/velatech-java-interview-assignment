FROM openjdk:8

MAINTAINER Tertese Amos <terteseamos@gmail.com>

EXPOSE 8000

ADD  target/velatech-interview-assignment.jar velatech-interview-assignment.jar

ENTRYPOINT ["java","-jar","velatech-interview-assignment.jar"]


