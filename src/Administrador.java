public class Administrador extends Usuario implements Autenticacao {

    private String cargo;

    public Administrador(String nome, String login, String senha, String cargo) {
        super(nome, login, senha);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return credenciaisConferem(login, senha);
    }

}