# 🏦 Bancária API

API REST para gerenciamento de clientes, contas bancárias e transações financeiras, desenvolvida com Java e Spring Boot.
 
---

## 📋 Sobre o Projeto

Este projeto simula o back-end de um sistema bancário, permitindo o cadastro de clientes, abertura de contas e realização de operações financeiras como depósitos, saques e transferências entre contas.
 
---

##  Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA / Hibernate**
- **Spring Validation (Bean Validation)**
- **MySQL** (banco de dados relacional)
- **Lombok** (redução de boilerplate)
- **Maven** (gerenciador de dependências)

## Em desenvolvimento
- Autenticação com Spring Security
---

##  Funcionalidades

### Clientes
- ✅ Cadastro de cliente com endereço
- ✅ Listagem de clientes (ordenada por nome)
- ✅ Atualização parcial de dados
- ✅ Remoção de cliente
### Contas
- ✅ Abertura de conta vinculada a um cliente
- ✅ Status da conta via enum (`ATIVO`, `INATIVO`, `BLOQUEADO`)
- ✅ Número de conta gerado automaticamente com UUID
### Transações
- ✅ Depósito
- ✅ Saque (com validação de saldo)
- ✅ Transferência entre contas (com prevenção de deadlock)
- ✅ Extrato de transações por conta
### Boas Práticas Aplicadas
- ✅ DTOs de entrada e saída separados (entidades nunca expostas diretamente)
- ✅ DTOs distintos para criação e atualização de endereço
- ✅ Tratamento global de exceções com `@RestControllerAdvice`
- ✅ Respostas HTTP semânticas (201, 204, 404, 422)
- ✅ Injeção de dependência por construtor em todo o projeto
---

## 📁 Estrutura do Projeto

```
src/main/java/com/bancaria/api/
├── customer/
│   ├── dto/
│   │   ├── RegistrationData.java
│   │   ├── DataUpdate.java
│   │   ├── DataAddressCreate.java
│   │   ├── DataAddressUpdate.java
│   │   ├── CustomerResponse.java
│   │   └── AddressResponse.java
│   ├── Customer.java
│   ├── Address.java
│   ├── CustomerController.java
│   └── CustomerRepository.java
├── account/
│   ├── dto/
│   │   └── AccountResponse.java
│   ├── Account.java
│   ├── AccountStatus.java
│   ├── AccountController.java
│   ├── AccountService.java
│   └── AccountRepository.java
├── transaction/
│   ├── dto/
│   │   ├── TransactionRequest.java
│   │   └── TransactionResponse.java
│   ├── Transaction.java
│   ├── TransactionType.java
│   ├── TransactionController.java
│   ├── TransactionService.java
│   └── TransactionRepository.java
└── exception/
    ├── GlobalExceptionHandler.java
    ├── CustomerNotFoundException.java
    ├── AccountNotFoundException.java
    ├── AccountBlockedException.java
    └── InsufficientBalanceException.java
```
 
---

##  Como Rodar o Projeto

### Pré-requisitos
- Java 17+
- Maven
- MySQL rodando localmente
### 1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/bancaria-api.git
cd bancaria-api
```

### 2. Configure o banco de dados

Crie um banco de dados MySQL:
```sql
CREATE DATABASE bancaria;
```

Renomeie o arquivo de exemplo e preencha com suas credenciais:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bancaria
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
```

### 3. Execute a aplicação
```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`
 
---

## 📡 Endpoints

### Clientes — `/customers`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/customers` | Cadastra um novo cliente |
| GET | `/customers` | Lista todos os clientes |
| PUT | `/customers/{id}` | Atualiza dados do cliente |
| DELETE | `/customers/{id}` | Remove um cliente |

### Contas — `/accounts`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/accounts/{customerId}` | Abre uma conta para o cliente |

### Transações — `/transactions`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/transactions` | Realiza depósito, saque ou transferência |
| GET | `/transactions/account/{accountId}` | Retorna o extrato da conta |
 
---

## 📨 Exemplos de Requisição

### Criar cliente
```json
POST /customers
{
  "name": "João Silva",
  "email": "joao@email.com",
  "phone": "81999998888",
  "cpf": "12345678901",
  "address": {
    "logradouro": "Rua das Flores",
    "numero": "123",
    "complemento": "Apto 2",
    "bairro": "Centro",
    "cidade": "Recife",
    "cep": "50010000",
    "uf": "PE"
  }
}
```

### Realizar transferência
```json
POST /transactions
{
  "type": "TRANSFERENCIA",
  "amount": 500.00,
  "sourceAccountId": 1,
  "targetAccountId": 2,
  "description": "Pagamento de aluguel"
}
```
 
---

## 🛡️ Tratamento de Erros

A API retorna respostas de erro padronizadas:

```json
{
  "status": 404,
  "message": "Cliente não encontrado com id: 5"
}
```

| Código | Situação |
|--------|----------|
| 400 | Dados inválidos na requisição |
| 404 | Cliente ou conta não encontrada |
| 422 | Saldo insuficiente ou conta bloqueada |
 
---

##  Autor

Feito por **wanderson luiz** — entre em contato pelo [LinkedIn](https://www.linkedin.com/in/wanderson-luiz-239719398/)