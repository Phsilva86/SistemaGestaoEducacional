public class Professor {

    private String nome;
    private String especialidade;
    private String registro;

    public Professor(String nome, String especialidade, String registro) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.registro = registro;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getRegistro() {
        return registro;
    }

}