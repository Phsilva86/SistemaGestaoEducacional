package service;

import java.util.List;

import model.Aluno;
import repository.AlunoRepository;

public class AlunoService {

    private AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    // Regras: nome obrigatorio e matricula unica
    public void cadastrar(Aluno aluno) {
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do aluno e obrigatorio.");
        }
        if (repository.buscarPorMatricula(aluno.getMatricula()) != null) {
            throw new IllegalArgumentException("Ja existe um aluno com a matricula " + aluno.getMatricula() + ".");
        }
        repository.salvar(aluno);
    }

    public List<Aluno> listar() {
        return repository.listarTodos();
    }

    public Aluno buscarPorMatricula(String matricula) {
        return repository.buscarPorMatricula(matricula);
    }

}