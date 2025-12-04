Fernando de Souza Tomasi, Isadora Barriquel, Lais Kaminski Casagrande

Tema: Finanças e Investimentos
Titulo: Sistema para gestão financeira pessoal

A API permite o gerenciamento de transações financeiras (receitas e despesas), agrupadas por contas e categorias, com validações de saldo, envio automático de relatórios mensais e regras de negócio consistentes.

## Descrição do Projeto

O objetivo do projeto é fornecer uma API robusta para o gerenciamento financeiro pessoal, permitindo:

- Cadastro de usuários
- Criação de contas e categorias
- Registro de transações (receitas/despesas)
- Controle automático de saldo
- Filtragem e paginação
- Envio mensal automático de relatórios por e-mail

## Descrição do Problema

Muitas pessoas têm dificuldade em registrar gastos e receitas, e acabam perdendo controle financeiro.  
Este projeto resolve esse problema permitindo:

* Registro de todas as movimentações  
* Organização por contas e categorias  
* Evita gastos quando não há saldo  
* Relatórios mensais (e-mail)  


## Tecnologias Utilizadas

| Tecnologia | Função |
|-----------|--------|
| Java 17 | Linguagem principal |
| Spring Boot | Framework principal |
| Spring JPA | Persistência de dados |
| PostgreSQL | Banco de dados |
| JavaMailSender | Envio de e-mails |
| IntelliJ IDEA | IDE |
| GitHub | Versionamento |

## Arquitetura REST (Camadas)

- Controller – Recebe requisições HTTP
- Service – Regras de negócio
- Repository – Persistência no banco
- DTOs – Entrada e saída de dados
- Mapper – Conversão entre modelo e DTO

## Limitações do Projeto

- Não possui autenticação JWT (pode ser adicionada futuramente)
- Interface de front-end não implementada (somente API)
- Não possui Swagger (documentação somente no README)

## Entidades

### Usuário
- id
- nome
- email
- senhaHash
- resumoMensal (boolean)
- criadoEm
- atualizadoEm

### Conta
- id
- usuarioId
- nome
- tipo
- saldo

### Categoria
- id
- usuarioId
- nome
- tipo

### Transação
- id
- usuarioId
- contaId
- categoriaId
- tipo (receita/despesa)
- valor
- data
- descricao


## Principais Regras de Negócio

* Não permite cadastrar e-mail duplicado  
* Receita aumenta saldo  
* Despesa diminui saldo  
* Não permite transação se o saldo for insuficiente  
* Não permite excluir conta usada em transações  
* Relatório mensal gerado automaticamente via e-mail

## Carta-Desafio: Envio de Relatório Mensal

Todo dia 1º às 08:00, o sistema dispara relatórios consolidados por categoria para usuários que habilitaram essa opção.

Exemplo:

