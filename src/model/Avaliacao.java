package model;

public class Avaliacao {

    private double nota;
    private String descricao;
    private Aluno aluno;
    private Turma turma;

    public Avaliacao(String descricao, Aluno aluno, Turma turma) {
        this.descricao = descricao;
        this.aluno = aluno;
        this.turma = turma;
        this.nota = 0;
    }

    public double getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    // Unico caminho para alterar a nota: so aceita valores de 0 a 10
    public void atribuirNota(double valor) {
        if (valor >= 0 && valor <= 10) {
            this.nota = valor;
            System.out.println("Nota " + valor + " atribuida com sucesso.");
        } else {
            System.out.println("Nota invalida: " + valor + ". Informe um valor entre 0 e 10.");
        }
    }

}