# Simulador de Investimentos - CAIXA

Este é o projeto para o Desafio Técnico Back-end da CAIXA, construído seguindo os padrões da **Clean Architecture**, além de contar com a stack **Java 21 + Quarkus + SQLite**.

> 💡 **Para o Avaliador:** O projeto foi configurado com Docker para poupar o seu tempo. Você **não precisa** ter Java, Maven ou SQLite instalados em sua máquina para rodar ou testar o projeto. **Basta ter o Docker!**

---

## 🚀 Como Rodar o Projeto (A Forma Mais Fácil)

O projeto possui um `Dockerfile` multi-stage que compila o código fonte e gera a imagem de execução de forma totalmente isolada.

Abra seu terminal na raiz do projeto e execute:

```bash
docker compose up --build
```

*(Ou `docker-compose up --build` dependendo da sua versão do Docker).*

Pronto! A API estará rodando em `http://localhost:8080`.
*(Na primeira vez pode levar alguns minutos para o Docker baixar as camadas do Maven e compilar).*

---

## 💻 Testando a API (Copiar e Colar)

Abra outro terminal e rode os `curl` abaixo para validar imediatamente o sistema:

### 1. Criar uma Simulação (POST)

```bash
curl -X POST http://localhost:8080/simulacoes \
  -H "Content-Type: application/json" \
  -d '{
    "clienteId": 12345,
    "valor": 10000.00,
    "prazoMeses": 12,
    "tipoProduto": "CDB"
  }'
```

*Obs: A tabela de produtos é populada automaticamente com opções de `CDB`, `LCA` e `LCI` no momento em que a aplicação sobe (Migration via Flyway).*

### 2. Buscar o Histórico de Simulações (GET)

```bash
curl -X GET "http://localhost:8080/simulacoes?clienteId=12345"
```

---

## 🧪 Como Rodar os Testes Automatizados (Opcional)

A cobertura de testes do projeto já ultrapassa os **80%** exigidos (alcançando ~90%). 

Caso você tenha **Java 21 e Maven** instalados na sua máquina e deseje rodar os testes localmente para validar a cobertura e as asserções:

```bash
mvn clean test
```

---

## 🏗 Arquitetura e Decisões Técnicas

O projeto foi organizado em camadas concêntricas (Clean Architecture) priorizando a independência do framework:

- `domain`: O coração do negócio (Modelos nativos, Value Objects como `ResultadoSimulacao`, Exceptions de domínio). Sem anotações de Quarkus ou Banco de Dados.
- `application`: Casos de Uso (`CriarSimulacaoUseCase`, `BuscarHistoricoUseCase`). Orquestra a lógica de negócio usando interfaces.
- `infrastructure`: Onde a magia da tecnologia acontece (Banco de Dados SQLite, Implementações de Repositórios com Panache, Mappers com MapStruct, Migrations com Flyway).
- `presentation`: A borda da aplicação (Controllers que recebem as requisições REST, DTOs de Request/Response, Exception Mappers globais).

## ✨ Extras (Bônus Implementados)

Conforme sugerido no desafio, adicionei:
- **Dockerfile** simplificado multi-stage na raiz do repositório.
- **Docker Compose** para 1-click run.
- Histórico completo no Git refletindo as camadas do desenvolvimento.

Se chegou até aqui, muito obrigado por avaliar meu código!
