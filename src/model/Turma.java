package model;

import java.util.ArrayList;
import java.util.List;

public class Turma {

    private String codigo;
    private Professor professor;
    private Curso curso;
    private List<Aluno> listaAlunos;

    public Turma(String codigo, Professor professor, Curso curso) {
        this.codigo = codigo;
        this.professor = professor;
        this.curso = curso;
        this.listaAlunos = new ArrayList<>();
    }

    // Adiciona um aluno na turma
    public void adicionarAluno(Aluno aluno) {
        listaAlunos.add(aluno);
    }

    // Remove um aluno da turma
    public void removerAluno(Aluno aluno) {
        listaAlunos.remove(aluno);
    }

        // Mostra o resumo da turma na tela
    public void exibirResumo() {
        System.out.println("--- Resumo da Turma ---");
        System.out.println("Codigo: " + codigo);
        System.out.println("Professor: " + professor.getNome());
        System.out.println("Curso: " + curso.getNome());
        System.out.println("Alunos matriculados: " + listaAlunos.size());
    }
}