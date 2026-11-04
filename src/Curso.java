public class Curso {

    private String nome;
    private String codigo;
    private int cargaHoraria;

    public Curso(String nome, String codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    // Getters: permitem ler os dados privados
    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

        // Mostra as informacoes do curso. As subclasses vao sobrescrever este metodo
    public void detalharCurso() {
        System.out.println("Curso: " + nome);
        System.out.println("Codigo: " + codigo);
        System.out.println("Carga horaria: " + cargaHoraria + " horas");
    }
}