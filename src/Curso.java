public class Curso implements Relatorio {

    private String nome;
    private String codigo;
    private int cargaHoraria;

    public Curso(String nome, String codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    // Mostra as informacoes do curso. As subclasses sobrescrevem este metodo
    public void detalharCurso() {
        System.out.println("Curso: " + nome);
        System.out.println("Codigo: " + codigo);
        System.out.println("Carga horaria: " + cargaHoraria + " horas");
    }

    // Cumpre o contrato Relatorio
    @Override
    public void gerarRelatorio() {
        System.out.println("===== RELATORIO DE CURSO =====");
        detalharCurso();
        System.out.println("==============================");
    }

}