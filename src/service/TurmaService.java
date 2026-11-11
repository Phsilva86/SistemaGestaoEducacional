package service;

import java.util.List;

import model.Aluno;
import model.Curso;
import model.Professor;
import model.Turma;
import repository.TurmaRepository;

public class TurmaService {

    private TurmaRepository repository;
    private ProfessorService professorService;
    private CursoService cursoService;
    private AlunoService alunoService;

    public TurmaService(TurmaRepository repository, ProfessorService professorService,
            CursoService cursoService, AlunoService alunoService) {
        this.repository = repository;
        this.professorService = professorService;
        this.cursoService = cursoService;
        this.alunoService = alunoService;
    }

    // Regras: codigo obrigatorio e unico; professor e curso precisam existir
    public void criarTurma(String codigo, String registroProfessor, String codigoCurso) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("O codigo da turma e obrigatorio.");
        }
        if (repository.buscarPorCodigo(codigo) != null) {
            throw new IllegalArgumentException("Ja existe uma turma com o codigo " + codigo + ".");
        }
        Professor professor = professorService.buscarPorRegistro(registroProfessor);
        if (professor == null) {
            throw new IllegalArgumentException("Professor nao encontrado: " + registroProfessor + ".");
        }
        Curso curso = cursoService.buscarPorCodigo(codigoCurso);
        if (curso == null) {
            throw new IllegalArgumentException("Curso nao encontrado: " + codigoCurso + ".");
        }
        repository.salvar(new Turma(codigo, professor, curso));
    }

    // Regras: turma e aluno existem, o aluno e do curso da turma e nao esta matriculado ainda
    public void matricularAluno(String codigoTurma, String matricula) {
        Turma turma = exigirTurma(codigoTurma);
        Aluno aluno = alunoService.buscarPorMatricula(matricula);
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno nao encontrado: " + matricula + ".");
        }
        if (!aluno.getCurso().getCodigo().equals(turma.getCurso().getCodigo())) {
            throw new IllegalArgumentException("O aluno " + aluno.getNome() + " nao pertence ao curso da turma.");
        }
        if (alunoEstaNaTurma(turma, matricula)) {
            throw new IllegalArgumentException("O aluno " + aluno.getNome() + " ja esta matriculado nesta turma.");
        }
        turma.adicionarAluno(aluno);
    }

    // Regras: turma existe e o aluno precisa estar matriculado nela
    public void removerAluno(String codigoTurma, String matricula) {
        Turma turma = exigirTurma(codigoTurma);
        for (Aluno a : turma.getListaAlunos()) {
            if (a.getMatricula().equals(matricula)) {
                turma.removerAluno(a);
                return;
            }
        }
        throw new IllegalArgumentException("O aluno " + matricula + " nao esta matriculado nesta turma.");
    }

    public boolean alunoEstaNaTurma(Turma turma, String matricula) {
        for (Aluno a : turma.getListaAlunos()) {
            if (a.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    public List<Turma> listar() {
        return repository.listarTodos();
    }

    public Turma buscarPorCodigo(String codigo) {
        return repository.buscarPorCodigo(codigo);
    }

    // Busca a turma ou interrompe com um aviso se ela nao existir
    private Turma exigirTurma(String codigo) {
        Turma turma = repository.buscarPorCodigo(codigo);
        if (turma == null) {
            throw new IllegalArgumentException("Turma nao encontrada: " + codigo + ".");
        }
        return turma;
    }

}