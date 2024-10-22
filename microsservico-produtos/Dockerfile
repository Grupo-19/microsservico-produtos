# Imagem base com JDK 17
FROM openjdk:17-jdk-alpine

# Diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR gerado
COPY target/fiap-tech-challenge-fase-4-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta da aplicação
EXPOSE 8090

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
