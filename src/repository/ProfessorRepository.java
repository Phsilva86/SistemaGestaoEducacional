package repository;

import java.util.ArrayList;
import java.util.List;

import model.Professor;

public class ProfessorRepository {

    private List<Professor> professores = new ArrayList<>();

    public void salvar(Professor professor) {
        professores.add(professor);
    }

    public List<Professor> listarTodos() {
        return new ArrayList<>(professores);
    }

    // Procura um professor pelo registro. Devolve null se nao encontrar
    public Professor buscarPorRegistro(String registro) {
        for (Professor p : professores) {
            if (p.getRegistro().equals(registro)) {
                return p;
            }
        }
        return null;
    }

}