package repository;

import java.util.ArrayList;
import java.util.List;

import model.Turma;

public class TurmaRepository {

    private List<Turma> turmas = new ArrayList<>();

    public void salvar(Turma turma) {
        turmas.add(turma);
    }

    public List<Turma> listarTodos() {
        return new ArrayList<>(turmas);
    }

    // Procura uma turma pelo codigo. Devolve null se nao encontrar
    public Turma buscarPorCodigo(String codigo) {
        for (Turma t : turmas) {
            if (t.getCodigo().equals(codigo)) {
                return t;
            }
        }
        return null;
    }

}