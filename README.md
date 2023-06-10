
# Interactive Quiz App API

The Interactive Quiz App API is a powerful and versatile application programming interface designed to provide a seamless experience for creating, managing, and interacting with quizzes. This API allows developers to build robust quiz applications that enable users to create quizzes, participate in quizzes, and view their performance through a user-friendly dashboard.


## Features

- User Creation
- Quiz Creation
- Quiz Participation
- Security and Authentication


## Screenshots

![user create]([https://drive.google.com/file/d/1XbBbUMPnPjsqglJ50TECMXgFnrPNQLWr/view?usp=drive_link](https://drive.google.com/file/d/1cgQapPOXiUgvrS7WJunfgkXQuvhLyU2e/view?usp=drive_link))
![question api](https://drive.google.com/file/d/1XbBbUMPnPjsqglJ50TECMXgFnrPNQLWr/view?usp=drive_link)
![question api](https://drive.google.com/file/d/1cgQapPOXiUgvrS7WJunfgkXQuvhLyU2e/view?usp=drive_link)


## Requirments

- JDK 17

- maven 
- mysql database


## Run Locally

Create Database mb

```bash
  create database mb;
```
Clone the project
```bash
  git clone https://github.com/Manubk/quiz-app.git
```

Go to the project directory

```bash
  cd quiz-app
```
Maven Clean and Package

```bash
  mvn clean package
```

Change Directory

```bash
  cd target
```
Start the program

```bash
  java -jar quizapp-0.0.1-SNAPSHOT.jar
```
Pragram Access

```bash
  http://localhost:9001
```

## Deployment
For Docker  / from root directory

```bash
 docker-compose up
```
Access

```bash
 http://<mechainIP>:9001/<endpoints>
```


## Documentation

Swagger Documentation

```bash
http://<IpAdress>:9001/swagger-ui/index.html
```
## Credentials

- UserName : demouser
- Password : pass



## Authors

- [@ManuBk](https://www.github.com/manubk)

