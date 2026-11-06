import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Desenvolvido por: Paulo Henrique Oliveira Silva");
        System.out.println("=== Fase 6 - Relatorios e Estatisticas ===");
        System.out.println();

        // Criando os cursos (um de cada tipo)
        Curso presencial = new CursoPresencial("Analise e Desenvolvimento de Sistemas", "ADS01", 2000, "Sala 12");
        Curso ead = new CursoEAD("Ciencia de Dados", "CDD01", 1800, "Moodle");

        // Criando alunos e professores
        Aluno aluno1 = new Aluno("Maria Souza", "maria", "aluno123", "2024001", presencial);
        Aluno aluno2 = new Aluno("Joao Pereira", "joao", "aluno456", "2024002", ead);
        Professor prof1 = new Professor("Carlos Mendes", "carlos", "prof456", "Programacao", "RF-00123");
        Professor prof2 = new Professor("Patricia Alves", "patricia", "prof789", "Banco de Dados", "RF-00456");

        // Listas de cada tipo
        List<Aluno> alunos = new ArrayList<>();
        alunos.add(aluno1);
        alunos.add(aluno2);

        List<Professor> professores = new ArrayList<>();
        professores.add(prof1);
        professores.add(prof2);

        List<Curso> cursos = new ArrayList<>();
        cursos.add(presencial);
        cursos.add(ead);

        // Uma lista que guarda qualquer coisa que gere relatorio (polimorfismo)
        List<Relatorio> todos = new ArrayList<>();
        todos.addAll(alunos);
        todos.addAll(professores);
        todos.addAll(cursos);

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        // Laco do menu: repete ate o usuario escolher sair
        while (continuar) {
            System.out.println("------ MENU DE RELATORIOS ------");
            System.out.println("1 - Relatorio de alunos");
            System.out.println("2 - Relatorio de professores");
            System.out.println("3 - Relatorio de cursos");
            System.out.println("4 - Todos os relatorios em sequencia");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            String opcao = scanner.nextLine();
            System.out.println();

            switch (opcao) {
                case "1":
                    for (Aluno a : alunos) {
                        a.gerarRelatorio();
                        System.out.println();
                    }
                    break;
                case "2":
                    for (Professor p : professores) {
                        p.gerarRelatorio();
                        System.out.println();
                    }
                    break;
                case "3":
                    for (Curso c : cursos) {
                        c.gerarRelatorio();
                        System.out.println();
                    }
                    break;
                case "4":
                    for (Relatorio r : todos) {
                        r.gerarRelatorio();
                        System.out.println();
                    }
                    break;
                case "0":
                    continuar = false;
                    System.out.println("Encerrando o sistema. Ate logo!");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    System.out.println();
            }
        }

        scanner.close();
    }
}