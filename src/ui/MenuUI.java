package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Aluno;
import model.Curso;
import model.Professor;
import model.Relatorio;
import service.AlunoService;
import service.CursoService;
import service.ProfessorService;

public class MenuUI {

    private AlunoService alunoService;
    private ProfessorService professorService;
    private CursoService cursoService;

    public MenuUI(AlunoService alunoService, ProfessorService professorService, CursoService cursoService) {
        this.alunoService = alunoService;
        this.professorService = professorService;
        this.cursoService = cursoService;
    }

    // Executa o menu ate o usuario escolher sair
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

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
                    for (Aluno a : alunoService.listar()) {
                        a.gerarRelatorio();
                        System.out.println();
                    }
                    break;
                case "2":
                    for (Professor p : professorService.listar()) {
                        p.gerarRelatorio();
                        System.out.println();
                    }
                    break;
                case "3":
                    for (Curso c : cursoService.listar()) {
                        c.gerarRelatorio();
                        System.out.println();
                    }
                    break;
                case "4":
                    List<Relatorio> todos = new ArrayList<>();
                    todos.addAll(alunoService.listar());
                    todos.addAll(professorService.listar());
                    todos.addAll(cursoService.listar());
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