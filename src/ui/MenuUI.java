package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Aluno;
import model.Curso;
import model.CursoEAD;
import model.CursoPresencial;
import model.Professor;
import model.Relatorio;
import model.Turma;
import model.Usuario;
import service.AlunoService;
import service.AutenticacaoService;
import service.AvaliacaoService;
import service.CursoService;
import service.ProfessorService;
import service.TurmaService;

public class MenuUI {

    private AlunoService alunoService;
    private ProfessorService professorService;
    private CursoService cursoService;
    private TurmaService turmaService;
    private AvaliacaoService avaliacaoService;
    private AutenticacaoService autenticacaoService;
    private CenariosDeTeste cenarios;
    private Scanner scanner = new Scanner(System.in);

    public MenuUI(AlunoService alunoService, ProfessorService professorService, CursoService cursoService,
            TurmaService turmaService, AvaliacaoService avaliacaoService,
            AutenticacaoService autenticacaoService, CenariosDeTeste cenarios) {
        this.alunoService = alunoService;
        this.professorService = professorService;
        this.cursoService = cursoService;
        this.turmaService = turmaService;
        this.avaliacaoService = avaliacaoService;
        this.autenticacaoService = autenticacaoService;
        this.cenarios = cenarios;
    }

    // ===== MENU PRINCIPAL =====

    public void iniciar() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("========== SISTEMA DE GESTAO EDUCACIONAL ==========");
            System.out.println("1 - Cadastros (alunos, professores e cursos)");
            System.out.println("2 - Turmas (criar, matricular e remover alunos)");
            System.out.println("3 - Registrar avaliacao");
            System.out.println("4 - Relatorios");
            System.out.println("5 - Login");
            System.out.println("6 - Executar cenarios de teste");
            System.out.println("0 - Sair");
            String opcao = ler("Escolha uma opcao");
            System.out.println();

            // Qualquer recusa de um servico cai neste catch e volta ao menu
            try {
                switch (opcao) {
                    case "1":
                        menuCadastros();
                        break;
                    case "2":
                        menuTurmas();
                        break;
                    case "3":
                        registrarAvaliacao();
                        break;
                    case "4":
                        menuRelatorios();
                        break;
                    case "5":
                        fazerLogin();
                        break;
                    case "6":
                        cenarios.executar();
                        break;
                    case "0":
                        continuar = false;
                        System.out.println("Encerrando o sistema. Ate logo!");
                        break;
                    default:
                        System.out.println("Opcao invalida. Tente novamente.");
                        System.out.println();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Operacao recusada: " + e.getMessage());
                System.out.println();
            }
        }

        scanner.close();
    }

    // ===== CADASTROS =====

    private void menuCadastros() {
        System.out.println("--- Cadastros ---");
        System.out.println("1 - Cadastrar aluno");
        System.out.println("2 - Cadastrar professor");
        System.out.println("3 - Cadastrar curso");
        String opcao = ler("Escolha uma opcao");
        System.out.println();

        switch (opcao) {
            case "1":
                cadastrarAluno();
                break;
            case "2":
                cadastrarProfessor();
                break;
            case "3":
                cadastrarCurso();
                break;
            default:
                System.out.println("Opcao invalida.");
                System.out.println();
        }
    }

    private void cadastrarAluno() {
        System.out.println("--- Cadastro de aluno ---");
        listarCursosDisponiveis();
        String nome = lerObrigatorio("Nome");
        String login = lerObrigatorio("Login");
        String senha = lerObrigatorio("Senha");
        String matricula = lerObrigatorio("Matricula");
        String codigoCurso = lerObrigatorio("Codigo do curso");

        Curso curso = cursoService.buscarPorCodigo(codigoCurso);
        if (curso == null) {
            throw new IllegalArgumentException("Curso nao encontrado: " + codigoCurso + ".");
        }

        alunoService.cadastrar(new Aluno(nome, login, senha, matricula, curso));
        System.out.println("Aluno cadastrado com sucesso.");
        System.out.println();
    }

    private void cadastrarProfessor() {
        System.out.println("--- Cadastro de professor ---");
        String nome = lerObrigatorio("Nome");
        String login = lerObrigatorio("Login");
        String senha = lerObrigatorio("Senha");
        String especialidade = lerObrigatorio("Especialidade");
        String registro = lerObrigatorio("Registro");

        professorService.cadastrar(new Professor(nome, login, senha, especialidade, registro));
        System.out.println("Professor cadastrado com sucesso.");
        System.out.println();
    }

    private void cadastrarCurso() {
        System.out.println("--- Cadastro de curso ---");
        System.out.println("1 - Presencial");
        System.out.println("2 - EAD");
        String tipo = lerObrigatorio("Tipo do curso");
        if (!tipo.equals("1") && !tipo.equals("2")) {
            throw new IllegalArgumentException("Tipo de curso invalido.");
        }

        String nome = lerObrigatorio("Nome");
        String codigo = lerObrigatorio("Codigo");
        int cargaHoraria = lerInteiro("Carga horaria (horas)");

        Curso curso;
        if (tipo.equals("1")) {
            String sala = lerObrigatorio("Sala de aula");
            curso = new CursoPresencial(nome, codigo, cargaHoraria, sala);
        } else {
            String plataforma = lerObrigatorio("Plataforma virtual");
            curso = new CursoEAD(nome, codigo, cargaHoraria, plataforma);
        }

        cursoService.cadastrar(curso);
        System.out.println("Curso cadastrado com sucesso.");
        System.out.println();
    }

    // ===== TURMAS =====

    private void menuTurmas() {
        System.out.println("--- Turmas ---");
        System.out.println("1 - Criar turma");
        System.out.println("2 - Matricular aluno");
        System.out.println("3 - Remover aluno da turma");
        String opcao = ler("Escolha uma opcao");
        System.out.println();

        switch (opcao) {
            case "1":
                criarTurma();
                break;
            case "2":
                matricularAluno();
                break;
            case "3":
                removerAluno();
                break;
            default:
                System.out.println("Opcao invalida.");
                System.out.println();
        }
    }

    private void criarTurma() {
        System.out.println("--- Criar turma ---");
        listarProfessoresDisponiveis();
        listarCursosDisponiveis();
        String codigo = lerObrigatorio("Codigo da nova turma");
        String registro = lerObrigatorio("Registro do professor");
        String codigoCurso = lerObrigatorio("Codigo do curso");

        turmaService.criarTurma(codigo, registro, codigoCurso);
        System.out.println("Turma criada com sucesso.");
        System.out.println();
    }

    private void matricularAluno() {
        System.out.println("--- Matricular aluno ---");
        listarTurmasDisponiveis();
        listarAlunosDisponiveis();
        String codigoTurma = lerObrigatorio("Codigo da turma");
        String matricula = lerObrigatorio("Matricula do aluno");

        turmaService.matricularAluno(codigoTurma, matricula);
        System.out.println("Aluno matriculado com sucesso.");
        System.out.println();
    }

    private void removerAluno() {
        System.out.println("--- Remover aluno da turma ---");
        listarTurmasDisponiveis();
        String codigoTurma = lerObrigatorio("Codigo da turma");
        String matricula = lerObrigatorio("Matricula do aluno");

        turmaService.removerAluno(codigoTurma, matricula);
        System.out.println("Aluno removido da turma com sucesso.");
        System.out.println();
    }

    // ===== AVALIACOES =====

    private void registrarAvaliacao() {
        System.out.println("--- Registrar avaliacao ---");
        listarTurmasDisponiveis();
        String codigoTurma = lerObrigatorio("Codigo da turma");
        String matricula = lerObrigatorio("Matricula do aluno");
        String descricao = lerObrigatorio("Descricao da avaliacao");
        double nota = lerDecimal("Nota (0 a 10)");

        avaliacaoService.registrar(codigoTurma, matricula, descricao, nota);
        System.out.println("Avaliacao registrada com sucesso.");
        System.out.println();
    }

    // ===== RELATORIOS =====

    private void menuRelatorios() {
        System.out.println("--- Relatorios ---");
        System.out.println("1 - Alunos");
        System.out.println("2 - Professores");
        System.out.println("3 - Cursos");
        System.out.println("4 - Turmas");
        System.out.println("5 - Avaliacoes");
        System.out.println("6 - Todos em sequencia");
        String opcao = ler("Escolha uma opcao");
        System.out.println();

        switch (opcao) {
            case "1":
                mostrar(alunoService.listar(), "Nenhum aluno cadastrado.");
                break;
            case "2":
                mostrar(professorService.listar(), "Nenhum professor cadastrado.");
                break;
            case "3":
                mostrar(cursoService.listar(), "Nenhum curso cadastrado.");
                break;
            case "4":
                mostrar(turmaService.listar(), "Nenhuma turma criada.");
                break;
            case "5":
                mostrar(avaliacaoService.listar(), "Nenhuma avaliacao registrada.");
                break;
            case "6":
                List<Relatorio> todos = new ArrayList<>();
                todos.addAll(alunoService.listar());
                todos.addAll(professorService.listar());
                todos.addAll(cursoService.listar());
                todos.addAll(turmaService.listar());
                todos.addAll(avaliacaoService.listar());
                mostrar(todos, "Nenhum dado cadastrado.");
                break;
            default:
                System.out.println("Opcao invalida.");
                System.out.println();
        }
    }

    // Mostra o relatorio de qualquer lista de objetos que assinaram o contrato Relatorio
    private void mostrar(List<? extends Relatorio> itens, String mensagemVazia) {
        if (itens.isEmpty()) {
            System.out.println(mensagemVazia);
            System.out.println();
            return;
        }
        for (Relatorio r : itens) {
            r.gerarRelatorio();
            System.out.println();
        }
    }

    // ===== LOGIN =====

    private void fazerLogin() {
        System.out.println("--- Login ---");
        String login = lerObrigatorio("Login");
        String senha = lerObrigatorio("Senha");

        Usuario usuario = autenticacaoService.entrar(login, senha);
        if (usuario == null) {
            System.out.println("Login ou senha invalidos.");
        } else {
            System.out.println("Bem-vindo(a), " + usuario.getNome()
                    + "! Perfil: " + autenticacaoService.perfilDe(usuario) + ".");
        }
        System.out.println();
    }

    // ===== LISTAS AUXILIARES (ajudam a escolher o que digitar) =====

    private void listarCursosDisponiveis() {
        System.out.println("Cursos disponiveis:");
        List<Curso> cursos = cursoService.listar();
        if (cursos.isEmpty()) {
            System.out.println("  (nenhum curso cadastrado)");
        }
        for (Curso c : cursos) {
            System.out.println("  " + c.getCodigo() + " - " + c.getNome());
        }
        System.out.println();
    }

    private void listarProfessoresDisponiveis() {
        System.out.println("Professores disponiveis:");
        List<Professor> professores = professorService.listar();
        if (professores.isEmpty()) {
            System.out.println("  (nenhum professor cadastrado)");
        }
        for (Professor p : professores) {
            System.out.println("  " + p.getRegistro() + " - " + p.getNome());
        }
        System.out.println();
    }

    private void listarAlunosDisponiveis() {
        System.out.println("Alunos disponiveis:");
        List<Aluno> alunos = alunoService.listar();
        if (alunos.isEmpty()) {
            System.out.println("  (nenhum aluno cadastrado)");
        }
        for (Aluno a : alunos) {
            System.out.println("  " + a.getMatricula() + " - " + a.getNome()
                    + " (curso " + a.getCurso().getCodigo() + ")");
        }
        System.out.println();
    }

    private void listarTurmasDisponiveis() {
        System.out.println("Turmas disponiveis:");
        List<Turma> turmas = turmaService.listar();
        if (turmas.isEmpty()) {
            System.out.println("  (nenhuma turma criada)");
        }
        for (Turma t : turmas) {
            System.out.println("  " + t.getCodigo() + " - curso " + t.getCurso().getCodigo()
                    + ", " + t.getListaAlunos().size() + " aluno(s)");
        }
        System.out.println();
    }

    // ===== LEITURA DE DADOS =====

    private String ler(String rotulo) {
        System.out.print(rotulo + ": ");
        return scanner.nextLine().trim();
    }

    private String lerObrigatorio(String rotulo) {
        String valor = ler(rotulo);
        if (valor.isEmpty()) {
            throw new IllegalArgumentException("O campo \"" + rotulo + "\" e obrigatorio.");
        }
        return valor;
    }

    private int lerInteiro(String rotulo) {
        String valor = lerObrigatorio(rotulo);
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo \"" + rotulo + "\" deve ser um numero inteiro.");
        }
    }

    private double lerDecimal(String rotulo) {
        String valor = lerObrigatorio(rotulo).replace(',', '.');
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo \"" + rotulo + "\" deve ser um numero.");
        }
    }

}