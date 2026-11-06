package model;

public class Aluno extends Usuario implements Autenticacao, Relatorio {

    private String matricula;
    private Curso curso;

    public Aluno(String nome, String login, String senha, String matricula, Curso curso) {
        super(nome, login, senha);
        this.matricula = matricula;
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return credenciaisConferem(login, senha);
    }

    // Cumpre o contrato Relatorio
    @Override
    public void gerarRelatorio() {
        System.out.println("===== RELATORIO DE ALUNO =====");
        System.out.println("Nome: " + getNome());
        System.out.println("Matricula: " + matricula);
        System.out.println("Curso: " + curso.getNome());
        System.out.println("Carga horaria do curso: " + curso.getCargaHoraria() + " horas");
        System.out.println("==============================");
    }

}