package model;

import java.util.ArrayList;
import java.util.List;

public class Turma implements Relatorio {

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

    public String getCodigo() {
        return codigo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Curso getCurso() {
        return curso;
    }

    // Devolve uma copia da lista, para ninguem mexer na original por fora
    public List<Aluno> getListaAlunos() {
        return new ArrayList<>(listaAlunos);
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

    // Cumpre o contrato Relatorio
    @Override
    public void gerarRelatorio() {
        System.out.println("===== RELATORIO DE TURMA =====");
        System.out.println("Codigo: " + codigo);
        System.out.println("Professor: " + professor.getNome());
        System.out.println("Curso: " + curso.getNome());
        System.out.println("Alunos matriculados: " + listaAlunos.size());
        for (Aluno a : listaAlunos) {
            System.out.println(" - " + a.getNome() + " (matricula " + a.getMatricula() + ")");
        }
        System.out.println("==============================");
    }

}