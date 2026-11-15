💊 API de Controle de Medicamentos

    Sistema backend para organização da rotina de medicação de idosos com Alzheimer.

Este projeto foi desenvolvido como requisito avaliativo para a disciplina de Arquitetura de Software do curso de Análise e Desenvolvimento de Sistemas. Trata-se de um MVP (Produto Mínimo Viável) focado em solucionar um problema real de gestão familiar: a desorganização e o esquecimento de horários de medicação, garantindo mais segurança e qualidade de vida para o idoso e seus cuidadores.
🎯 O Problema e o Impacto Social

Cuidar de um idoso com Alzheimer exige uma rotina rígida de medicação. O esquecimento de horários, a confusão de dosagens e as doses duplicadas são riscos graves à saúde. Esta API foi projetada para ser o motor de um sistema que organiza esses dados, garantindo que os cuidadores tenham clareza sobre o que deve ser administrado.
🛠️ Tecnologias Utilizadas

    Java 21 (Linguagem base)

    Spring Boot 3.4.0 (Framework web)

    Spring Web (Criação da API REST)

    Bean Validation (Validação de dados de entrada)

    Maven (Gerenciamento de dependências)

    JUnit 5 (Testes unitários)

🏗️ Arquitetura do Projeto

O projeto segue o padrão Feature-Driven Development (FDD) combinado com a separação em camadas (MVC + Service). Essa escolha garante o baixo acoplamento e a manutenibilidade, permitindo que novas funcionalidades sejam adicionadas sem impactar o código existente.

    Controller: Porta de entrada HTTP, recebe as requisições e devolve as respostas.

    Service: O "cérebro" do sistema, contém as regras de negócio e a persistência em memória.

    Model (Entidade): Representa o objeto de domínio Medicamento.

    DTO (Data Transfer Object): Objeto de transferência que isola a API das entidades e aplica as validações de entrada.

    Exception Handler: Tratamento global de erros para padronizar as respostas JSON.

📌 Nota sobre a Persistência

Para garantir a agilidade e o foco nas regras de negócio neste MVP, não foi utilizado um banco de dados real. A persistência é feita em memória (utilizando List e AtomicLong do Java), o que significa que os dados são perdidos ao reiniciar o servidor. A arquitetura em camadas, no entanto, permite a substituição futura por um banco relacional (como PostgreSQL) com mínimo impacto no código.
📂 Estrutura de Pastas
text

src/main/java/com/gerenmedicamentos/controlemedicamentos/
├── controller/
│   ├── MedicamentoController.java
│   └── GlobalExceptionHandler.java
├── service/
│   └── MedicamentoService.java
├── dto/
│   └── MedicamentoDTO.java
├── model/
│   └── Medicamento.java
├── exception/
│   └── RegraDeNegocioException.java
└── ControleMedicamentosApplication.java

🚀 Como Executar o Projeto
Pré-requisitos

    Java 21 (JDK) instalado.

    Maven instalado (ou use o Maven Wrapper ./mvnw incluso no projeto).

Passo a passo

    Clone o repositório:
    bash

    git clone https://github.com/GabrielALPorto/Java.git
    cd Java

    Execute a aplicação:
    bash

    mvn spring-boot:run

    (Ou abra no VS Code/IntelliJ e execute a classe ControleMedicamentosApplication).

    A API estará disponível em: http://localhost:8080

Rodando os Testes

Para executar os testes unitários e validar as regras de negócio:
bash

mvn test

🔌 Endpoints da API
Método	Endpoint	Descrição	Status de Sucesso	Status de Erro
POST	/api/medicamentos	Cadastra um novo medicamento	201 (Created)	400 (Bad Request) / 409 (Conflict)
GET	/api/medicamentos	Lista todos os medicamentos ativos	200 (OK)	-
GET	/api/medicamentos/{id}	Busca um medicamento por ID	200 (OK)	404 (Not Found)
PUT	/api/medicamentos/{id}	Atualiza um medicamento existente	200 (OK)	404 (Not Found)
DELETE	/api/medicamentos/{id}	Inativa um medicamento (Soft Delete)	204 (No Content)	404 (Not Found)
📋 Exemplo de Requisição (POST)

Requisição (JSON):
json

{
  "nome": "Donepezila",
  "dosagem": "10mg",
  "descricao": "Tomar após o café da manhã",
  "horario": "08:00:00"
}

Resposta (201 Created):
json

{
  "id": 1,
  "nome": "Donepezila",
  "dosagem": "10mg",
  "descricao": "Tomar após o café da manhã",
  "horario": "08:00:00",
  "ativo": true
}

⚖️ Regras de Negócio Implementadas

    Validação de Entrada: Nome e dosagem não podem ser vazios; o horário é obrigatório.

    Deduplicação: O sistema impede o cadastro de dois medicamentos com o mesmo nome e dosagem.

    Soft Delete: A exclusão não apaga o registro da memória, apenas altera a flag ativo para false, preservando o histórico.

👨‍💻 Autor

Gabriel A. L. Porto

    Estudante de Análise e Desenvolvimento de Sistemas.

    Foco em desenvolvimento Backend e Infraestrutura.

Projeto desenvolvido para fins acadêmicos.