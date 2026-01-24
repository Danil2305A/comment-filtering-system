# comment-filtering-system
[Задание из курса Stepik "Java. Базовый курс"](https://stepik.org/lesson/14513/step/9?unit=4147)

## Getting Started
### Prerequisites
* OpenJDK 25
* Gradle 9.1.0 (or use Gradle wrapper)
* Docker
### Build app
```bash
./gradlew jar
```
### Run app
```bash
java -jar build/libs/comment-filtering-system-1.0-SNAPSHOT.jar
```
### Build & run tests
```bash
./gradlew test
```
## You can also use Docker
### Build image
```bash
docker build -t my-app .
```
### Create & run container
```bash
docker run --name my-app -it my-app:latest
```

