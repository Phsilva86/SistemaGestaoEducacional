package repository;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;

public class AlunoRepository {

    private List<Aluno> alunos = new ArrayList<>();

    // Guarda um aluno na lista
    public void salvar(Aluno aluno) {
        alunos.add(aluno);
    }

    // Devolve uma copia da lista, para ninguem mexer na lista original
    public List<Aluno> listarTodos() {
        return new ArrayList<>(alunos);
    }

    // Procura um aluno pela matricula. Devolve null se nao encontrar
    public Aluno buscarPorMatricula(String matricula) {
        for (Aluno a : alunos) {
            if (a.getMatricula().equals(matricula)) {
                return a;
            }
        }
        return null;
    }

}