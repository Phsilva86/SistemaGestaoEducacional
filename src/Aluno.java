public class Aluno extends Usuario implements Autenticacao {

    private String matricula;
    private Curso curso;

    public Aluno(String nome, String login, String senha, String matricula, Curso curso) {
        super(nome, login, senha);
        this.matricula = matricula;
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return credenciaisConferem(login, senha);
    }

}