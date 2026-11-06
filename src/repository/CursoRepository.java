package repository;

import java.util.ArrayList;
import java.util.List;

import model.Curso;

public class CursoRepository {

    private List<Curso> cursos = new ArrayList<>();

    public void salvar(Curso curso) {
        cursos.add(curso);
    }

    public List<Curso> listarTodos() {
        return new ArrayList<>(cursos);
    }

    // Procura um curso pelo codigo. Devolve null se nao encontrar
    public Curso buscarPorCodigo(String codigo) {
        for (Curso c : cursos) {
            if (c.getCodigo().equals(codigo)) {
                return c;
            }
        }
        return null;
    }

}