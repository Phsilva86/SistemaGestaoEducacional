import java.util.ArrayList;
import java.util.List;

import model.Administrador;
import model.Aluno;
import model.Curso;
import model.CursoEAD;
import model.CursoPresencial;
import model.Professor;
import repository.AlunoRepository;
import repository.AvaliacaoRepository;
import repository.CursoRepository;
import repository.ProfessorRepository;
import repository.TurmaRepository;
import service.AlunoService;
import service.AutenticacaoService;
import service.AvaliacaoService;
import service.CursoService;
import service.ProfessorService;
import service.TurmaService;
import ui.CenariosDeTeste;
import ui.MenuUI;

public class Main {
    public static void main(String[] args) {

        System.out.println("Desenvolvido por: Paulo Henrique Oliveira Silva");
        System.out.println("=== Fase Final - Sistema Educacional Consolidado ===");
        System.out.println();

        // 1. Camada repository: os armazens
        AlunoRepository alunoRepository = new AlunoRepository();
        ProfessorRepository professorRepository = new ProfessorRepository();
        CursoRepository cursoRepository = new CursoRepository();
        TurmaRepository turmaRepository = new TurmaRepository();
        AvaliacaoRepository avaliacaoRepository = new AvaliacaoRepository();

        // 2. Camada service: cada servico recebe o seu repositorio (e os servicos de que depende)
        AlunoService alunoService = new AlunoService(alunoRepository);
        ProfessorService professorService = new ProfessorService(professorRepository);
        CursoService cursoService = new CursoService(cursoRepository);
        TurmaService turmaService = new TurmaService(turmaRepository, professorService, cursoService, alunoService);
        AvaliacaoService avaliacaoService = new AvaliacaoService(avaliacaoRepository, turmaService, alunoService);

        // 3. Administrador do sistema e servico de login
        List<Administrador> administradores = new ArrayList<>();
        administradores.add(new Administrador("Fernanda Rocha", "fernanda", "admin789", "Coordenadora"));
        AutenticacaoService autenticacaoService = new AutenticacaoService(alunoService, professorService, administradores);

        // 4. Dados iniciais, cadastrados pelos servicos (as regras valem aqui)
        Curso presencial = new CursoPresencial("Analise e Desenvolvimento de Sistemas", "ADS01", 2000, "Sala 12");
        Curso ead = new CursoEAD("Ciencia de Dados", "CDD01", 1800, "Moodle");
        cursoService.cadastrar(presencial);
        cursoService.cadastrar(ead);

        professorService.cadastrar(new Professor("Carlos Mendes", "carlos", "prof456", "Programacao", "RF-00123"));
        professorService.cadastrar(new Professor("Patricia Alves", "patricia", "prof789", "Banco de Dados", "RF-00456"));

        alunoService.cadastrar(new Aluno("Maria Souza", "maria", "aluno123", "2024001", presencial));
        alunoService.cadastrar(new Aluno("Joao Pereira", "joao", "aluno456", "2024002", ead));

        turmaService.criarTurma("T001", "RF-00123", "ADS01");
        turmaService.matricularAluno("T001", "2024001");

        // 5. Camada ui: cenarios de teste e menu recebem os servicos
        CenariosDeTeste cenarios = new CenariosDeTeste(alunoService, professorService, cursoService,
                turmaService, avaliacaoService, autenticacaoService);
        MenuUI menu = new MenuUI(alunoService, professorService, cursoService,
                turmaService, avaliacaoService, autenticacaoService, cenarios);
        menu.iniciar();
    }
}