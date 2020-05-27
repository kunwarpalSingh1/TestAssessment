FROM openjdk:11-jdk as builder
WORKDIR /workspace/app
COPY pom.xml .
COPY mvnw /workspace/app  
COPY .mvn .mvn 
COPY src ./src  
RUN ./mvnw package -DskipTests

FROM openjdk:11-jre
WORKDIR /app
COPY --from=builder /workspace/app/target .
EXPOSE 8890
ENTRYPOINT ["java","-jar", "./MoviesCategory-0.0.1-SNAPSHOT.jar"]