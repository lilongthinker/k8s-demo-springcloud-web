#FROM maven:3-jdk-8-alpine AS build-env

#WORKDIR /app
#COPY . /app
#RUN mvn package  --settings ./setting/settings.xml-apache

FROM openjdk:8-jre
#COPY --from=build-env /app/target/*.jar /app.jar

WORKDIR /
COPY target/*.jar /app.jar

ENV JAVA_OPTS=""
ENV SERVER_PORT 8080

EXPOSE ${SERVER_PORT}

HEALTHCHECK --interval=10s --timeout=3s \
	CMD curl -v --fail http://localhost:${SERVER_PORT} || exit 1

# 如下方法进程号是1
ENTRYPOINT [ "/usr/local/openjdk-8/bin/java","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","$JAVA_OPTS","/app.jar" ]

# 造成 java进程非1号进程
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.se    curity.egd=file:/dev/./urandom -jar /app.jar" ,"-Dnetworkaddress.cache.ttl=0" ]


