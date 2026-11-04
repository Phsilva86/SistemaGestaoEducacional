public abstract class Usuario {

    private String nome;
    private String login;
    private String senha;

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    // Confere login e senha. So as classes filhas podem usar este metodo
    protected boolean credenciaisConferem(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

}