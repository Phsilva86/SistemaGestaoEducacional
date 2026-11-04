import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Desenvolvido por: Paulo Henrique Oliveira Silva");
        System.out.println("=== Fase 4 - Heranca e Polimorfismo ===");
        System.out.println();

        // Criando um curso de cada tipo
        Curso cursoGeral = new Curso("Introducao a Computacao", "INT01", 60);
        CursoPresencial presencial = new CursoPresencial("Analise e Desenvolvimento de Sistemas", "ADS01", 2000, "Sala 12");
        CursoEAD ead = new CursoEAD("Ciencia de Dados", "CDD01", 1800, "Moodle");

        // Uma lista de Curso aceita os tres tipos
        List<Curso> cursos = new ArrayList<>();
        cursos.add(cursoGeral);
        cursos.add(presencial);
        cursos.add(ead);

        // Polimorfismo: o mesmo comando, um resultado diferente para cada tipo
        for (Curso c : cursos) {
            c.detalharCurso();
            System.out.println();
        }

        // Um aluno pode se matricular em qualquer tipo de curso
        Aluno aluno = new Aluno("Maria Souza", "2024001", ead);
        System.out.println("O aluno " + aluno.getNome() + " cursa:");
        aluno.getCurso().detalharCurso();
    }
}