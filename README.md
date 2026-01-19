# 🎮 Jogo da Forca - Java Edition

Um jogo da forca interativo desenvolvido em Java com sistema de autenticação de usuários e gerenciamento de perfil, utilizando arquivo de texto como banco de dados local. Este é um projeto para o início de meu desenvolvimento em java, aprendendo o básico, como encapsulamento, manipulação de arquivos, programação orientada a objetos...

## 📋 Características

- **🔐 Sistema de Autenticação**: Login e criação de usuários.
- **👤 Gerenciamento de Perfil**: Cada jogador possui seu próprio perfil.
- **🎯 Jogabilidade Intuitiva**: Interface de linha de comando amigável.
- **💾 Persistência de Dados**: Armazenamento local em arquivo de texto (sem uso de banco de dados).
- **📊 Histórico de Vitórias**: Rastreamento do desempenho do jogador.

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java
- **Armazenamento**: Arquivo de Texto Local (.txt)

## 📁 Estrutura do Projeto

```
Forca/
├── src/
│   ├── Main.java                 # Ponto de entrada da aplicação
│   ├── game/
│   │   ├── ForcaGame.java        # Lógica principal do jogo
│   │   └── Word.java             # Classe para gerenciar palavras
│   └── Users/
│       ├── User.java             # Modelo de usuário
│       └── DatabaseUser.java     # Gerenciamento de usuários
├── database/
│   └── users.txt                 # Armazenamento local de usuários
└── README.md
```

## 🚀 Como Executar

### Pré-requisitos
- Java 8 ou superior instalado
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code com extensões Java)

### Passos

1. **Clone ou baixe o projeto**
   ```bash
   cd Forca
   ```

2. **Compile o projeto**
   ```bash
   javac src/*.java src/game/*.java src/Users/*.java
   ```

3. **Execute a aplicação**
   ```bash
   java -cp src Main
   ```

## 🎮 Como Jogar

1. **Início**: Ao iniciar o programa, você será saudado e questionado sobre sua situação
2. **Novo Usuário**: Se não possui conta, escolha "Não" para criar uma nova
3. **Login**: Se já possui conta, escolha "Sim" e insira suas credenciais
4. **Jogo**: 
   - Você tem 6 tentativas para acertar a palavra
   - Digite uma letra por vez
   - Letras já utilizadas são rastreadas
   - Ganhe adivinando a palavra completa
   - Perca se errar 6 vezes

## 📝 Formato do Banco de Dados Local

Os dados dos usuários são armazenados em `src/database/users.txt` no seguinte formato:

**Exemplo:**
```
id,username,password
1,joao_silva,senha123
2,maria_santos,outrasenha
```

> **Nota**: Não é recomendado editar este arquivo manualmente durante a execução do programa.

## 🔍 Detalhes Técnicos

### Classes Principais

- **Main.java**: Gerencia o fluxo principal da aplicação, incluindo autenticação e menu de opções.
- **ForcaGame.java**: Implementa a lógica do jogo, gerenciando tentativas, letras reveladas e contagem de vitórias.
- **Word.java**: Gerencia a palavra secreta e sua revelação gradual.
- **User.java**: Modelo de dados para representar um usuário.
- **DatabaseUser.java**: Responsável pela leitura, escrita e validação de dados de usuários.

### Fluxo de Autenticação

```
Início
  ↓
Possui Usuário?
  ├─→ Sim → Login (Username + Password)
  │   ├─→ Válido → Carrega Perfil
  │   └─→ Inválido → Tenta Novamente
  └─→ Não → Criar Novo Usuário
       ↓
    Inserir Dados
       ↓
    Salvar em database/users.txt
       ↓
    Iniciar Jogo
```

## 📈 Possíveis Melhorias Futuras

- [ ] Interface gráfica (Swing/JavaFX)
- [ ] Banco de dados relacional (MySQL, PostgreSQL)
- [ ] Modo multiplayer
- [ ] API REST para integração

## 📄 Licença

Este projeto é de código aberto e está disponível para fins educacionais.

### 👨‍💻 Autor: Leonardo Reis Ferraz

*Desenvolvido como projeto de aprendizado em Java.*

---

**Versão**: 1.0  
**Última Atualização**: 19 de Janeiro de 2026