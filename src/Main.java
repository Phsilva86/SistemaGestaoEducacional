import model.Aluno;
import model.Curso;
import model.CursoEAD;
import model.CursoPresencial;
import model.Professor;
import repository.AlunoRepository;
import repository.CursoRepository;
import repository.ProfessorRepository;
import service.AlunoService;
import service.CursoService;
import service.ProfessorService;
import ui.MenuUI;

public class Main {
    public static void main(String[] args) {

        System.out.println("Desenvolvido por: Paulo Henrique Oliveira Silva");
        System.out.println("=== Fase 7 - Arquitetura em Camadas ===");
        System.out.println();

        // 1. Camada repository: os armazens
        AlunoRepository alunoRepository = new AlunoRepository();
        ProfessorRepository professorRepository = new ProfessorRepository();
        CursoRepository cursoRepository = new CursoRepository();

        // 2. Camada service: cada servico recebe o seu repositorio
        AlunoService alunoService = new AlunoService(alunoRepository);
        ProfessorService professorService = new ProfessorService(professorRepository);
        CursoService cursoService = new CursoService(cursoRepository);

        // 3. Dados de exemplo, cadastrados pelos servicos (as regras valem aqui)
        Curso presencial = new CursoPresencial("Analise e Desenvolvimento de Sistemas", "ADS01", 2000, "Sala 12");
        Curso ead = new CursoEAD("Ciencia de Dados", "CDD01", 1800, "Moodle");
        cursoService.cadastrar(presencial);
        cursoService.cadastrar(ead);

        alunoService.cadastrar(new Aluno("Maria Souza", "maria", "aluno123", "2024001", presencial));
        alunoService.cadastrar(new Aluno("Joao Pereira", "joao", "aluno456", "2024002", ead));

        professorService.cadastrar(new Professor("Carlos Mendes", "carlos", "prof456", "Programacao", "RF-00123"));
        professorService.cadastrar(new Professor("Patricia Alves", "patricia", "prof789", "Banco de Dados", "RF-00456"));

        // 4. Teste de falha: matricula repetida deve ser recusada pelo servico
        try {
            alunoService.cadastrar(new Aluno("Outro Aluno", "outro", "senha000", "2024001", presencial));
        } catch (IllegalArgumentException e) {
            System.out.println("Cadastro recusado: " + e.getMessage());
        }
        System.out.println();

        // 5. Camada ui: o menu recebe os servicos e entra em funcionamento
        MenuUI menu = new MenuUI(alunoService, professorService, cursoService);
        menu.iniciar();
    }
}