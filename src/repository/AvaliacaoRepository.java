package repository;

import java.util.ArrayList;
import java.util.List;

import model.Avaliacao;

public class AvaliacaoRepository {

    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public void salvar(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    public List<Avaliacao> listarTodos() {
        return new ArrayList<>(avaliacoes);
    }

    // Confere se ja existe uma avaliacao com a mesma descricao para o mesmo aluno na mesma turma
    public boolean existe(String descricao, String matricula, String codigoTurma) {
        for (Avaliacao a : avaliacoes) {
            if (a.getDescricao().equals(descricao)
                    && a.getAluno().getMatricula().equals(matricula)
                    && a.getTurma().getCodigo().equals(codigoTurma)) {
                return true;
            }
        }
        return false;
    }

}