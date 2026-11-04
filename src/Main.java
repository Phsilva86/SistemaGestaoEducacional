public class Main {
    public static void main(String[] args) {

        System.out.println("Desenvolvido por: Paulo Henrique Oliveira Silva");
        System.out.println("=== Fase 1 - Modelagem Inicial ===");
        System.out.println();

        // Criando um objeto Curso
        Curso curso = new Curso("Analise e Desenvolvimento de Sistemas", "ADS01", 2000);

        // Criando um objeto Professor
        Professor professor = new Professor("Carlos Mendes", "Programacao", "RF-00123");

        // Criando um objeto Aluno, entregando o curso que ele faz
        Aluno aluno = new Aluno("Maria Souza", "2024001", curso);

        // Mostrando os dados do curso
        System.out.println("--- Curso ---");
        System.out.println("Nome: " + curso.getNome());
        System.out.println("Codigo: " + curso.getCodigo());
        System.out.println("Carga horaria: " + curso.getCargaHoraria() + " horas");
        System.out.println();

        // Mostrando os dados do professor
        System.out.println("--- Professor ---");
        System.out.println("Nome: " + professor.getNome());
        System.out.println("Especialidade: " + professor.getEspecialidade());
        System.out.println("Registro: " + professor.getRegistro());
        System.out.println();

        // Mostrando os dados do aluno
        System.out.println("--- Aluno ---");
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Matricula: " + aluno.getMatricula());
        System.out.println("Curso: " + aluno.getCurso().getNome());
    }
}