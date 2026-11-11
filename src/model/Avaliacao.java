package model;

public class Avaliacao implements Relatorio {

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

    // Cumpre o contrato Relatorio
    @Override
    public void gerarRelatorio() {
        System.out.println("===== RELATORIO DE AVALIACAO =====");
        System.out.println("Descricao: " + descricao);
        System.out.println("Aluno: " + aluno.getNome());
        System.out.println("Turma: " + turma.getCodigo());
        System.out.println("Nota: " + nota);
        System.out.println("==================================");
    }

}