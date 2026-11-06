package service;

import java.util.List;

import model.Professor;
import repository.ProfessorRepository;

public class ProfessorService {

    private ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    // Regras: nome obrigatorio e registro unico
    public void cadastrar(Professor professor) {
        if (professor.getNome() == null || professor.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do professor e obrigatorio.");
        }
        if (repository.buscarPorRegistro(professor.getRegistro()) != null) {
            throw new IllegalArgumentException("Ja existe um professor com o registro " + professor.getRegistro() + ".");
        }
        repository.salvar(professor);
    }

    public List<Professor> listar() {
        return repository.listarTodos();
    }

    public Professor buscarPorRegistro(String registro) {
        return repository.buscarPorRegistro(registro);
    }

}