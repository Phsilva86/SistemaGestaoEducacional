package model;

public class CursoEAD extends Curso {

    private String plataformaVirtual;

    public CursoEAD(String nome, String codigo, int cargaHoraria, String plataformaVirtual) {
        super(nome, codigo, cargaHoraria);
        this.plataformaVirtual = plataformaVirtual;
    }

    public String getPlataformaVirtual() {
        return plataformaVirtual;
    }

    @Override
    public void detalharCurso() {
        System.out.println("Tipo: Curso EAD");
        super.detalharCurso();
        System.out.println("Plataforma virtual: " + plataformaVirtual);
    }

}