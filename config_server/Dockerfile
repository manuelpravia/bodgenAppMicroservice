FROM openjdk:17-jdk-alpine
RUN addgroup -S mpraviap && adduser -S admin -G mpraviap
USER admin:mpraviap
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ADD target/${JAR_FILE} app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
