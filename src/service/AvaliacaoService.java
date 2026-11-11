package service;

import java.util.List;

import model.Aluno;
import model.Avaliacao;
import model.Turma;
import repository.AvaliacaoRepository;

public class AvaliacaoService {

    private AvaliacaoRepository repository;
    private TurmaService turmaService;
    private AlunoService alunoService;

    public AvaliacaoService(AvaliacaoRepository repository, TurmaService turmaService, AlunoService alunoService) {
        this.repository = repository;
        this.turmaService = turmaService;
        this.alunoService = alunoService;
    }

    // Regras: dados validos, aluno matriculado na turma, nota de 0 a 10 e sem duplicidade
    public void registrar(String codigoTurma, String matricula, String descricao, double nota) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descricao da avaliacao e obrigatoria.");
        }
        Turma turma = turmaService.buscarPorCodigo(codigoTurma);
        if (turma == null) {
            throw new IllegalArgumentException("Turma nao encontrada: " + codigoTurma + ".");
        }
        Aluno aluno = alunoService.buscarPorMatricula(matricula);
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno nao encontrado: " + matricula + ".");
        }
        if (!turmaService.alunoEstaNaTurma(turma, matricula)) {
            throw new IllegalArgumentException("O aluno " + aluno.getNome() + " nao esta matriculado na turma " + codigoTurma + ".");
        }
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Nota invalida: " + nota + ". Informe um valor entre 0 e 10.");
        }
        if (repository.existe(descricao, matricula, codigoTurma)) {
            throw new IllegalArgumentException("Ja existe a avaliacao \"" + descricao + "\" para este aluno nesta turma.");
        }

        Avaliacao avaliacao = new Avaliacao(descricao, aluno, turma);
        avaliacao.atribuirNota(nota);
        repository.salvar(avaliacao);
    }

    public List<Avaliacao> listar() {
        return repository.listarTodos();
    }

}