public class Main {
    public static void main(String[] args) {

        System.out.println("Desenvolvido por: Paulo Henrique Oliveira Silva");
        System.out.println("=== Fase 5 - Autenticacao e Perfis ===");
        System.out.println();

        // O curso que o aluno vai cursar
        Curso curso = new Curso("Analise e Desenvolvimento de Sistemas", "ADS01", 2000);

        // Criando um usuario de cada perfil (cada um com login e senha)
        Aluno aluno = new Aluno("Maria Souza", "maria", "aluno123", "2024001", curso);
        Professor professor = new Professor("Carlos Mendes", "carlos", "prof456", "Programacao", "RF-00123");
        Administrador admin = new Administrador("Fernanda Rocha", "fernanda", "admin789", "Coordenadora");

        // Testando a autenticacao do aluno
        System.out.println("--- Aluno: " + aluno.getNome() + " ---");
        System.out.println("Login e senha corretos: " + aluno.autenticar("maria", "aluno123"));
        System.out.println("Senha errada: " + aluno.autenticar("maria", "senha-errada"));
        System.out.println();

        // Testando a autenticacao do professor
        System.out.println("--- Professor: " + professor.getNome() + " ---");
        System.out.println("Login e senha corretos: " + professor.autenticar("carlos", "prof456"));
        System.out.println("Login errado: " + professor.autenticar("outro", "prof456"));
        System.out.println();

        // Testando a autenticacao do administrador
        System.out.println("--- Administrador: " + admin.getNome() + " (" + admin.getCargo() + ") ---");
        System.out.println("Login e senha corretos: " + admin.autenticar("fernanda", "admin789"));
        System.out.println("Senha errada: " + admin.autenticar("fernanda", "123456"));
        System.out.println();

        // A interface funciona como tipo: qualquer perfil cabe em uma variavel Autenticacao
        System.out.println("--- Usando a interface como tipo ---");
        Autenticacao acesso = professor;
        System.out.println("Professor pela interface: " + acesso.autenticar("carlos", "prof456"));
        acesso = admin;
        System.out.println("Administrador pela interface: " + acesso.autenticar("fernanda", "admin789"));
    }
}