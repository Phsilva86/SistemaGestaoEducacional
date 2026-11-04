public class Main {
    public static void main(String[] args) {

        System.out.println("Desenvolvido por: Paulo Henrique Oliveira Silva");
        System.out.println("=== Fase 3 - Controle de Avaliacoes ===");
        System.out.println();

        // Preparando curso, professor, alunos e turma
        Curso curso = new Curso("Analise e Desenvolvimento de Sistemas", "ADS01", 2000);
        Professor professor = new Professor("Carlos Mendes", "Programacao", "RF-00123");
        Aluno aluno1 = new Aluno("Maria Souza", "2024001", curso);
        Aluno aluno2 = new Aluno("Joao Pereira", "2024002", curso);

        Turma turma = new Turma("T001", professor, curso);
        turma.adicionarAluno(aluno1);
        turma.adicionarAluno(aluno2);

        // Criando avaliacoes: cada uma ligada a um aluno e a uma turma
        Avaliacao prova1 = new Avaliacao("Prova 1 - Programacao", aluno1, turma);
        Avaliacao prova2 = new Avaliacao("Prova 1 - Programacao", aluno2, turma);

        System.out.println("--- Avaliacao de " + prova1.getAluno().getNome() + " ---");
        System.out.println("Descricao: " + prova1.getDescricao());
        System.out.println("Nota inicial: " + prova1.getNota());
        System.out.println();

        // Testando notas validas e invalidas
        System.out.println("Testando notas:");
        prova1.atribuirNota(8.5);   // valida
        prova1.atribuirNota(11);    // invalida: acima de 10
        prova1.atribuirNota(-2);    // invalida: abaixo de 0
        prova1.atribuirNota(10);    // valida: limite superior
        prova1.atribuirNota(0);     // valida: limite inferior
        prova1.atribuirNota(7);     // valida: nota final
        System.out.println();

        System.out.println("Nota final de " + prova1.getAluno().getNome() + ": " + prova1.getNota());
        System.out.println();

        // Segunda avaliacao, de outro aluno da mesma turma
        System.out.println("--- Avaliacao de " + prova2.getAluno().getNome() + " ---");
        prova2.atribuirNota(9.5);
        System.out.println("Nota final de " + prova2.getAluno().getNome() + ": " + prova2.getNota());
        System.out.println();

        // Mostrando a turma a que as avaliacoes pertencem
        prova1.getTurma().exibirResumo();
    }
}