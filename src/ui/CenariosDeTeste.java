package ui;

import model.Aluno;
import model.Curso;
import model.CursoEAD;
import model.CursoPresencial;
import model.Professor;
import service.AlunoService;
import service.AutenticacaoService;
import service.AvaliacaoService;
import service.CursoService;
import service.ProfessorService;
import service.TurmaService;

public class CenariosDeTeste {

    private AlunoService alunoService;
    private ProfessorService professorService;
    private CursoService cursoService;
    private TurmaService turmaService;
    private AvaliacaoService avaliacaoService;
    private AutenticacaoService autenticacaoService;

    private boolean jaExecutado = false;
    private int total = 0;
    private int comportamentoEsperado = 0;

    public CenariosDeTeste(AlunoService alunoService, ProfessorService professorService,
            CursoService cursoService, TurmaService turmaService,
            AvaliacaoService avaliacaoService, AutenticacaoService autenticacaoService) {
        this.alunoService = alunoService;
        this.professorService = professorService;
        this.cursoService = cursoService;
        this.turmaService = turmaService;
        this.avaliacaoService = avaliacaoService;
        this.autenticacaoService = autenticacaoService;
    }

    public void executar() {
        if (jaExecutado) {
            System.out.println("Os cenarios de teste ja foram executados nesta sessao.");
            System.out.println();
            return;
        }
        jaExecutado = true;

        System.out.println("===== CENARIOS DE TESTE =====");
        System.out.println();

        // Dados usados nos testes (codigos com prefixo TST para nao se misturar com os demais)
        Curso curso1 = new CursoPresencial("Curso de Teste 1", "TST-C1", 800, "Sala T1");
        Curso curso2 = new CursoEAD("Curso de Teste 2", "TST-C2", 600, "Plataforma T2");
        Professor professor = new Professor("Professor Teste", "prof.teste", "senha123", "Testes", "TST-RF");
        Aluno aluno1 = new Aluno("Aluno Teste 1", "aluno1.teste", "senha111", "TST-001", curso1);
        Aluno aluno2 = new Aluno("Aluno Teste 2", "aluno2.teste", "senha222", "TST-002", curso2);

        System.out.println("--- Cenarios de SUCESSO ---");
        esperarSucesso("Cadastrar curso valido (presencial)", () -> cursoService.cadastrar(curso1));
        esperarSucesso("Cadastrar curso valido (EAD)", () -> cursoService.cadastrar(curso2));
        esperarSucesso("Cadastrar professor valido", () -> professorService.cadastrar(professor));
        esperarSucesso("Cadastrar aluno valido (curso 1)", () -> alunoService.cadastrar(aluno1));
        esperarSucesso("Cadastrar aluno valido (curso 2)", () -> alunoService.cadastrar(aluno2));
        esperarSucesso("Criar turma com professor e curso existentes",
                () -> turmaService.criarTurma("TST-T1", "TST-RF", "TST-C1"));
        esperarSucesso("Matricular aluno do curso da turma",
                () -> turmaService.matricularAluno("TST-T1", "TST-001"));
        esperarSucesso("Registrar avaliacao com nota valida (8.5)",
                () -> avaliacaoService.registrar("TST-T1", "TST-001", "Prova de Teste", 8.5));
        esperarSucesso("Login com credenciais corretas", () -> exigirLogin("aluno1.teste", "senha111"));
        System.out.println();

        System.out.println("--- Cenarios de FALHA (devem ser recusados) ---");
        esperarFalha("Cadastrar aluno com matricula repetida",
                () -> alunoService.cadastrar(new Aluno("Outro Aluno", "outro", "senha000", "TST-001", curso1)));
        esperarFalha("Cadastrar curso com carga horaria zero",
                () -> cursoService.cadastrar(new Curso("Curso Invalido", "TST-C3", 0)));
        esperarFalha("Criar turma com codigo repetido",
                () -> turmaService.criarTurma("TST-T1", "TST-RF", "TST-C1"));
        esperarFalha("Criar turma com professor inexistente",
                () -> turmaService.criarTurma("TST-T2", "RF-INEXISTENTE", "TST-C1"));
        esperarFalha("Matricular o mesmo aluno duas vezes",
                () -> turmaService.matricularAluno("TST-T1", "TST-001"));
        esperarFalha("Matricular aluno de outro curso",
                () -> turmaService.matricularAluno("TST-T1", "TST-002"));
        esperarFalha("Registrar avaliacao com nota 11",
                () -> avaliacaoService.registrar("TST-T1", "TST-001", "Prova com nota invalida", 11));
        esperarFalha("Registrar avaliacao duplicada",
                () -> avaliacaoService.registrar("TST-T1", "TST-001", "Prova de Teste", 9));
        esperarFalha("Avaliar aluno que nao esta na turma",
                () -> avaliacaoService.registrar("TST-T1", "TST-002", "Prova de Teste", 7));
        esperarFalha("Login com senha errada", () -> exigirLogin("aluno1.teste", "errada"));
        System.out.println();

        System.out.println("Resumo: " + comportamentoEsperado + " de " + total
                + " cenarios se comportaram como esperado.");
        System.out.println("Os dados de teste (codigos TST-...) permanecem no sistema e aparecem nos relatorios.");
        System.out.println();
    }

    // A tarefa deve funcionar: se o servico lancar um aviso, e um problema
    private void esperarSucesso(String titulo, Runnable acao) {
        total++;
        try {
            acao.run();
            comportamentoEsperado++;
            System.out.println("[OK]        " + titulo);
        } catch (IllegalArgumentException e) {
            System.out.println("[PROBLEMA]  " + titulo + " -> " + e.getMessage());
        }
    }

    // A tarefa deve ser recusada: se passar sem aviso, e um problema
    private void esperarFalha(String titulo, Runnable acao) {
        total++;
        try {
            acao.run();
            System.out.println("[PROBLEMA]  " + titulo + " -> deveria ter sido recusado");
        } catch (IllegalArgumentException e) {
            comportamentoEsperado++;
            System.out.println("[RECUSADO]  " + titulo + " -> " + e.getMessage());
        }
    }

    // Transforma login invalido em aviso, para os testes tratarem como os demais
    private void exigirLogin(String login, String senha) {
        if (autenticacaoService.entrar(login, senha) == null) {
            throw new IllegalArgumentException("Login ou senha invalidos.");
        }
    }

}