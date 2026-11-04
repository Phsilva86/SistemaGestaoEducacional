public class Main {
    public static void main(String[] args) {

        System.out.println("Desenvolvido por: Paulo Henrique Oliveira Silva");
        System.out.println("=== Fase 2 - Estrutura Academica ===");
        System.out.println();

        // Criando o curso e o professor
        Curso curso = new Curso("Analise e Desenvolvimento de Sistemas", "ADS01", 2000);
        Professor professor = new Professor("Carlos Mendes", "Programacao", "RF-00123");

        // Criando tres alunos do mesmo curso
        Aluno aluno1 = new Aluno("Maria Souza", "2024001", curso);
        Aluno aluno2 = new Aluno("Joao Pereira", "2024002", curso);
        Aluno aluno3 = new Aluno("Ana Lima", "2024003", curso);

        // Criando a turma: ela nasce com a lista de alunos vazia
        Turma turma = new Turma("T001", professor, curso);

        System.out.println("Turma criada, sem alunos:");
        turma.exibirResumo();
        System.out.println();

        // Matriculando os alunos
        turma.adicionarAluno(aluno1);
        turma.adicionarAluno(aluno2);
        turma.adicionarAluno(aluno3);

        System.out.println("Depois de matricular tres alunos:");
        turma.exibirResumo();
        System.out.println();

        // Removendo um aluno
        turma.removerAluno(aluno2);

        System.out.println("Depois de remover o aluno Joao Pereira:");
        turma.exibirResumo();
    }
}