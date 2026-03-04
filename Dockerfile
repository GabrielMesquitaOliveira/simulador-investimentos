FROM maven:3.9.6-eclipse-temurin-21-jammy AS build

# Configuração e diretório de trabalho
WORKDIR /build
COPY pom.xml .

# Opcional mas melhora performance: baixar dependências prévias (cache)
RUN mvn dependency:go-offline

# Copia código e realiza o build real (ignorando testes para a imagem ficar menor/mais rapida caso ja tenham rodado fora)
COPY src ./src
RUN mvn clean package -DskipTests

# Stage de Execucao (Imagem Leve com JRE JMV Quarkus runtime)
# Referência: Imagem oficial enxuta recomendada para aplicações quarkus (Temurin 21)
FROM eclipse-temurin:21-jre-jammy

ENV LANGUAGE='en_US:en'
# Overriding the default JDBC URL to a writable directory for the non-root user
ENV QUARKUS_DATASOURCE_JDBC_URL='jdbc:sqlite:/tmp/simulacoes.db'

# Copiando apenas o build final do Quarkus
COPY --from=build --chown=1001:1001 /build/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build --chown=1001:1001 /build/target/quarkus-app/*.jar /deployments/
COPY --from=build --chown=1001:1001 /build/target/quarkus-app/app/ /deployments/app/
COPY --from=build --chown=1001:1001 /build/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 1001

ENTRYPOINT [ "java", "-jar", "/deployments/quarkus-run.jar" ]
