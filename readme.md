💊 API de Controle de Medicamentos

    Sistema backend para organização da rotina de medicação de idosos com Alzheimer.

Projeto desenvolvido como requisito avaliativo para a disciplina de Arquitetura de Software do curso de Análise e Desenvolvimento de Sistemas. Trata-se de um MVP focado em solucionar um problema real de gestão familiar: a desorganização e o esquecimento de horários de medicação, garantindo mais segurança e qualidade de vida para o idoso e seus cuidadores.
🛠️ Tecnologias Utilizadas

    Java 21

    Spring Boot 3.x

    Spring Web (API REST)

    Bean Validation (Validação de dados)

    Maven (Gerenciamento de dependências)

🏗️ Arquitetura do Projeto

O projeto segue o padrão de arquitetura em camadas, garantindo a separação de responsabilidades:

    Controller: Porta de entrada HTTP (a ser implementado).

    Service: Regras de negócio e persistência em memória.

    Model (Entidade): Objeto de domínio Medicamento.

    DTO (Data Transfer Object): Isola a API da entidade e aplica validações de entrada.

📌 Nota sobre a Persistência

Para garantir agilidade no MVP, não foi utilizado banco de dados real. A persistência é feita em memória (List e AtomicLong), o que significa que os dados são perdidos ao reiniciar o servidor. A arquitetura em camadas permite a substituição futura por um banco relacional (como PostgreSQL) com mínimo impacto.
📂 Estrutura de Pastas
text

src/main/java/com/gerenmedicamentos/controlemedicamentos/
├── model/
│   └── Medicamento.java       # Entidade de domínio
├── dto/
│   └── MedicamentoDTO.java    # Objeto de transferência e validações
├── service/
│   └── MedicamentoService.java # Regras de negócio e persistência
└── ControleMedicamentosApplication.java # Classe principal

🚀 Como Executar o Projeto
Pré-requisitos

    Java 21 (JDK) instalado.

    Maven instalado (ou use o Maven Wrapper ./mvnw incluso no projeto).

Passo a passo

    Clone o repositório:
    bash

    git clone https://github.com/GabrielALPorto/Java.git
    cd Java/medicamentos

    Execute a aplicação:
    bash

    ./mvnw spring-boot:run

    (Ou abra no VS Code/IntelliJ e execute a classe ControleMedicamentosApplication).

    A API estará disponível em: http://localhost:8080

👨‍💻 Autor

Gabriel A. L. Porto

    Estudante de Análise e Desenvolvimento de Sistemas.

    Foco em desenvolvimento Backend e Infraestrutura.
