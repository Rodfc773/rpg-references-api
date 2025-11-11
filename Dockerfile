FROM eclipse-temurin:21 AS builder

WORKDIR /app/

COPY pom.xml mvnw ./
COPY .mvn .mvn/
COPY pom.xml ./pom.xml
COPY src ./src


RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o artefato compilado do estágio de build
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# Define o comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]