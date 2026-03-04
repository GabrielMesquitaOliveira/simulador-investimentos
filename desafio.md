# Desafio Técnico Back-end
## API de Simulação de Investimentos

---

## 1. Contexto

A CAIXA está evoluindo sua plataforma digital de investimentos. Seu desafio é construir uma API que simula investimentos com base em produtos parametrizados em banco de dados, persistindo o histórico de simulações.

---

## 2. Objetivo

Entregar uma aplicação back-end capaz de: receber uma solicitação de simulação via JSON, validar e selecionar um produto elegível a partir do banco, calcular o valor final, persistir a simulação e disponibilizar o histórico via endpoint.

---

## 3. Tecnologias Permitidas

Escolha uma das opções abaixo:

- **Java 21 + Quarkus** (REST, JPA/Hibernate) + SQLite
- **.NET 9+** (ASP.NET Web API, Entity Framework Core) + SQLite

---

## 4. Requisitos Obrigatórios (MVP)

### 4.1 Banco de Dados

Crie um banco SQLite com as tabelas `produtos` e `simulacoes`. É obrigatório incluir seed de dados (migrations, scripts SQL ou inicialização automática).

**Tabela `produtos` (campos mínimos):**

| Campo | Descrição |
|---|---|
| `id` | Identificador único |
| `nome` | Nome do produto |
| `tipoProduto` | CDB, LCI, LCA, etc. |
| `rentabilidadeAnual` | Ex: `0.12` |
| `risco` | Baixo, Médio, Alto |
| `prazoMinMeses` / `prazoMaxMeses` | Prazo mínimo e máximo em meses |
| `valorMin` / `valorMax` | Valor mínimo e máximo |

**Tabela `simulacoes` (campos mínimos):**

| Campo | Descrição |
|---|---|
| `id` | Identificador único |
| `clienteId` | ID do cliente |
| `produtoNome` | Nome do produto |
| `tipoProduto` | Tipo do produto |
| `valorInvestido` | Valor investido |
| `prazoMeses` | Prazo em meses |
| `rentabilidadeAplicada` | Rentabilidade aplicada |
| `valorFinal` | Valor final calculado |
| `dataSimulacao` | Data da simulação |

---

### 4.2 Endpoint – Criar Simulação

**`POST /simulacoes`**

**Request (exemplo):**

```json
{
  "clienteId": 123,
  "valor": 10000.00,
  "prazoMeses": 12,
  "tipoProduto": "CDB"
}
```

**Regras:**

- Validar campos obrigatórios e valores positivos
- Buscar produto compatível no banco
- Se não houver produto elegível, retornar HTTP `422`

**Cálculo sugerido:**

```
valorFinal = valor * (1 + rentabilidadeAnual / 12) ^ prazoMeses
```

**Response (exemplo):**

```json
{
  "produtoValidado": {
    "id": 1,
    "nome": "CDB Caixa 2026",
    "tipo": "CDB",
    "rentabilidade": 0.12,
    "risco": "Baixo"
  },
  "resultadoSimulacao": {
    "valorFinal": 11200.00,
    "prazoMeses": 12
  },
  "dataSimulacao": "2026-02-23T14:00:00Z"
}
```

---

### 4.3 Endpoint – Histórico

**`GET /simulacoes?clienteId=123`**

```json
[
  {
    "id": 1,
    "clienteId": 123,
    "produto": "CDB Caixa 2026",
    "valorInvestido": 10000.00,
    "valorFinal": 11200.00,
    "prazoMeses": 12,
    "dataSimulacao": "2026-02-23T14:00:00Z"
  }
]
```

---

### 4.4 Testes

Incluir pelo menos **2 testes automatizados**:

- Teste do cálculo
- Teste do endpoint `POST /simulacoes`

---

## 5. Entrega

- Código
- README com instruções de execução e testes

---

## 6. Bônus (opcional)

- Dockerfile
- Autenticação JWT simples
- Endpoint de agregação
- Logs estruturados

---

## 7. Critérios de Avaliação

- Funcionamento da API
- Organização e clareza do código
- Validações e tratamento de erros
- Cobertura de testes unitários em **80% ou mais**

---

## 8. Observações Finais

O objetivo do desafio é avaliar fundamentos de desenvolvimento back-end. Mantenha a solução simples, funcional e bem documentada.