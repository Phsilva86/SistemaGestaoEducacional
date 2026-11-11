# Sistema de Gestão Educacional (SGE)

Sistema de gestão educacional orientado a objetos, desenvolvido em **Java** como projeto da disciplina *Imersão Profissional Aplicando Orientação a Objetos*. O cenário é o da EduConnect, uma startup fictícia de tecnologia educacional que fornece sistemas para universidades e escolas.

O projeto foi construído em fases, simulando o ciclo real de desenvolvimento, e o histórico de commits registra cada etapa.

## Funcionalidades

- Cadastro de alunos, professores e cursos (presenciais e EAD)
- Criação de turmas, com matrícula e remoção de alunos
- Registro de avaliações, com notas de 0 a 10 protegidas por encapsulamento
- Relatórios individuais e gerais, gerados de forma polimórfica
- Login com perfis de aluno, professor e administrador
- Cenários de teste automáticos de sucesso e de falha
- Menu interativo no terminal

## Conceitos de orientação a objetos aplicados

| Fase | Conceito |
| --- | --- |
| 1 | Classes e objetos |
| 2 | Associações e relacionamentos (turmas, listas de alunos) |
| 3 | Encapsulamento (nota privada, validada em `atribuirNota`) |
| 4 | Herança e polimorfismo (`CursoPresencial` e `CursoEAD`) |
| 5 | Interfaces e abstração (`Autenticacao` e a classe abstrata `Usuario`) |
| 6 | Polimorfismo e laços (`Relatorio` e menu de relatórios) |
| 7 | Arquitetura em camadas (`model`, `repository`, `service` e `ui`) |
| Final | Consolidação: todas as funcionalidades em um único sistema |

## Estrutura do projeto

```
src/
├── Main.java            # integra as camadas
├── model/               # entidades (Aluno, Professor, Curso, Turma, Avaliacao...)
├── repository/          # armazenamento temporário em listas
├── service/             # regras de negócio
└── ui/                  # menu interativo e cenários de teste
```

- **model:** as entidades e os contratos (interfaces) do sistema.
- **repository:** guarda e busca os objetos em memória.
- **service:** valida as regras antes de gravar (matrícula única, nota entre 0 e 10, aluno do curso da turma, entre outras).
- **ui:** conversa com o usuário e só fala com os serviços.

## Como executar

Requisitos: **JDK 17 ou superior** (desenvolvido com o JDK 25).

No terminal, dentro da pasta do projeto:

```
javac -d out -sourcepath src src/Main.java
java -cp out Main
```

Também é possível abrir a pasta no **Visual Studio Code**, com o *Extension Pack for Java*, e usar o botão **Run** no arquivo `Main.java`.

Os dados ficam apenas na memória: ao encerrar o programa, tudo é descartado.

## Cenários de teste

A opção **6** do menu executa cenários automáticos. Cada cenário de sucesso deve funcionar, e cada cenário de falha deve ser recusado pelas regras de negócio, por exemplo:

- matrícula de aluno repetida
- curso com carga horária zero
- aluno matriculado duas vezes na mesma turma
- aluno de outro curso na turma
- nota fora do intervalo de 0 a 10
- login com senha errada

## Autor

Paulo Henrique Oliveira Silva