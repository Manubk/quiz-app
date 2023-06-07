FROM openjdk:21

COPY target/quizapp-0.0.1-SNAPSHOT.jar quiz-app.jar

ENTRYPOINT [ "java","-jar","quiz-app.jar" ]

