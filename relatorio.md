# Relatório Técnico

###### Disciplina: DIM0501 - Boas Práticas de Programação

###### Tema: Aplicação dos conceitos de boas práticas em cenário simulado

###### Semestre: 2026.

## 1. Identificação do Grupo

**Integrante 1: Lucas Silva de Oliveira**

**Linguagem utilizada: JAVA**

**Link do repositório Git: https://github.com/lucasilvas2/dim0501-boas-praticas-und-1**

## 2. Descrição do Sistema

Descreva brevemente:

```
● O que o sistema faz
● Qual era o objetivo do código original
● Em que contexto ele poderia ser utilizado
```
O sistema é capaz de cadastrar noticias e classificar elas como confiavel, duvidosa ou falsa.
Podendo adicionar classificação de forma manual ou automatica, onde um método faz a verificação do texto, identificando a presença de palavras-chave, como FONTE, URGENTE, !!!, e tamanho do texto. Essa aplicação pode ser utilizada em plataformas de postagem de noticias, redes sociais e mecanismos de busca para identificação prévia de possíveis fake news.
## 3. Problemas Identificados

Liste e explique os principais problemas encontrados no código original.

###### 3.1 Problemas de Legibilidade
    - Class e métodos com nomes que não definem bem sua função
    - Métodos com excessiva quantidade de if's, else's e validações, sendo necessário alta carga cognitiva para compreensão
    - Métodos com muitas linhas de código
    - Presença de `magic numbers` e `magic strings`
    - Comentários desnecessários e pouco explicativos

```java
// função que faz tudo
public static void f(String a, String b) {
        // adiciona coisa
        if (a != null && !a.equals("")) {
            D d = new D();
            d.t = a;

            if (b == null || b.equals("")) {
                d.c = "duvidosa";
            } else {
                d.c = b;
            }

            data.add(d);
        } else {
            System.out.println("erro");
        }
    }
```

###### 3.2 Problemas de Organização
    - Funções com múltiplas responsabilidades
    - Falta de separação em módulos
    - Código com alta complexidade ciclomática

###### 3.3 Código Duplicado ou Design Ruim
    - Linhas de código repetidas em métodos diferentes
    - Métodos com muitas linhas de código
    - If's aninhados
    - Métodos com muitas responsabilidades

###### 3.4 Falta de Validação
    - Problema na verificação de string vazia, um espaço em branco é considerado válido
        - if (a != null && !a.equals(""))
    - Sistema permite adicionar nóticias com qualquer classificação
    - Método não válida o parametro de entrada

###### 3.5 Problemas de Documentação
    - Comentários desnecessários
    - Comentários que não explicam o código
    - Comentários que explicam o óbvio

#### 4. Estratégias de Solução

Explique como os problemas foram resolvidos.

###### 4.1 Refatoração

```
● O que foi renomeado
● Como as funções foram reorganizadas
```
###### 4.2 Modularização

```
● Como o código foi dividido
● Quais módulos foram criados
```
###### 4.3 Programação Defensiva

```
● Quais validações foram adicionadas
● Como erros passaram a ser tratados
```

###### 4.4 Documentação

```
● O que foi documentado
● Que tipo de comentários foram utilizados
● O que foi incluído no README
```
#### 5. Exemplos de Melhoria (Antes vs Depois)

Apresente pelo menos **2 exemplos claros** de melhoria.

###### Exemplo 1

**Antes:**

<trecho original>

**Depois:**

<trecho refatorado>

**Explicação:**
Explique o que foi melhorado e por quê.

###### Exemplo 2

**Antes:**

<trecho original>

**Depois:**

<trecho refatorado>

**Explicação:**
Explique o que foi melhorado e por quê.

#### 6. Organização Final do Código

Descreva a estrutura final do projeto:

Exemplo:

projeto/
├── modelo
├── servico
├── interface
└── main

Explique brevemente o papel de cada parte.


### 7. Conclusão

Responda:

```
● O que o grupo aprendeu com o trabalho
● Quais foram as maiores dificuldades
● Como as boas práticas impactaram o código
```

