package model;

public class CursoPresencial extends Curso {

    private String salaDeAula;

    public CursoPresencial(String nome, String codigo, int cargaHoraria, String salaDeAula) {
        super(nome, codigo, cargaHoraria);
        this.salaDeAula = salaDeAula;
    }

    public String getSalaDeAula() {
        return salaDeAula;
    }

    @Override
    public void detalharCurso() {
        System.out.println("Tipo: Curso Presencial");
        super.detalharCurso();
        System.out.println("Sala de aula: " + salaDeAula);
    }

}