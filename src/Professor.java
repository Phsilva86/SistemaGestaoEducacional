public class Professor extends Usuario implements Autenticacao {

    private String especialidade;
    private String registro;

    public Professor(String nome, String login, String senha, String especialidade, String registro) {
        super(nome, login, senha);
        this.especialidade = especialidade;
        this.registro = registro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getRegistro() {
        return registro;
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return credenciaisConferem(login, senha);
    }

}