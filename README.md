# 📰 Classificador de Notícias (Detector de Fake News)

> Uma ferramenta de análise preliminar para auxiliar na identificação de conteúdos suspeitos.

Este projeto é uma ferramenta de linha de comando escrita em Java desenvolvida para fornecer um suporte inicial na verificação da confiabilidade de notícias. Utilizando uma lógica de pontuação baseada em padrões comuns encontrados em desinformações, o sistema serve como um filtro inicial para alertar o usuário sobre textos que podem ser **Duvidosos** ou **Falsos**.

---

## 🚀 O que o sistema faz?

- **Classificação Automática**: Analisa o texto em busca de sinais de alerta (falta de fontes, sensacionalismo, etc) e define um score de confiabilidade.
- **Entrada Manual**: Permite que você adicione notícias já classificadas por você.
- **Listagem Organizada**: Visualize todas as notícias processadas de forma clara e direta.
- **Interface Interativa**: Um menu simples e amigável no seu terminal.

---

## ⚙️ Algoritmo de Scoring e Heurísticas

A confiabilidade das notícias é calculada através de um sistema de pontuação (*score*) baseado em heurísticas de análise de texto. O motor de classificação avalia os seguintes critérios:

1. **Validação de Referência**: Verificação de metadados (`fonte`) no corpo do texto.
2. **Análise de Pontuação**: Detecção de padrões de sensacionalismo via uso excessivo de caracteres de exclamação.
3. **Análise de Palavras-Chave**: Identificação de termos alarmistas configurados no enum `PalavrasChave`.
4. **Restrição de Comprimento**: Validação de densidade de informação (mínimo de 10 caracteres).

O modelo de dados utiliza o `Score` para determinar a `Classificacao` final: **Confiavel**, **Duvidosa** ou **Falsa**.

---

## 🏗️ Organização do Projeto (Arquitetura)

Este projeto foi construído seguindo boas práticas de programação e separação de responsabilidades:

- 📂 **UI**: Gerencia toda a interação com o usuário.
- 📂 **Services**: Contém a lógica de negócio e orquestração.
- 📂 **Models**: Define o que é uma "Notícia" e como ela se comporta.
- 📂 **Repositories**: Responsável pelo armazenamento (atualmente em memória).
- 📂 **Infrastructure**: Implementações técnicas de entrada/saída (Console).
- 📂 **Interfaces**: Contratos que garantem flexibilidade ao sistema.

---

## 🛠️ Configuração e Execução (CLI)

### Pré-requisitos
- **Java Development Kit (JDK)**: Versão 8 ou superior.
  - [Download via Oracle](https://www.oracle.com/java/technologies/downloads/)
- **Git**: Para clonagem do repositório.
  - [Download Oficial do Git](https://git-scm.com/)

### Procedimento Passo a Passo
Execute os comandos abaixo no seu terminal para configurar e rodar o projeto:

1. **Clonagem do Repositório**:
   ```bash
   git clone https://github.com/seu-usuario/dim0501-boas-praticas-und-1.git
   ```
2. **Navegação**:
   ```bash
   cd dim0501-boas-praticas-und-1
   ```
3. **Compilação**:
   Utilize o compilador `javac` para gerar os bytecodes a partir do ponto de entrada:
   ```bash
   javac Main.java
   ```
4. **Execução**:
   Inicie a aplicação através da Java Virtual Machine (JVM):
   ```bash
   java Main
   ```

---

## 💻 Tecnologias
- **Linguagem**: Java
- **Versionamento**: Git

---
Feito com foco em boas práticas e clareza de código.
