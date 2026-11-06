public class Professor extends Usuario implements Autenticacao, Relatorio {

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

    // Cumpre o contrato Relatorio
    @Override
    public void gerarRelatorio() {
        System.out.println("===== RELATORIO DE PROFESSOR =====");
        System.out.println("Nome: " + getNome());
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Registro: " + registro);
        System.out.println("==================================");
    }

}