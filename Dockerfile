FROM mcr.microsoft.com/openjdk/21-jdk:latest AS builder

WORKDIR /app/

COPY pom.xml mvnw ./
COPY .mvn .mvn/

RUN ./mvnw clean package -DskipTests

FROM mcr.microsoft.com/openjdk/21-jre:latest

WORKDIR /app

# Copia o artefato compilado do estágio de build
COPY --from=builder /app/target/*.jar app.jar

# Define o comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]