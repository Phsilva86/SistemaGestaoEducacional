package service;

import java.util.List;

import model.Curso;
import repository.CursoRepository;

public class CursoService {

    private CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    // Regras: carga horaria positiva e codigo unico
    public void cadastrar(Curso curso) {
        if (curso.getCargaHoraria() <= 0) {
            throw new IllegalArgumentException("A carga horaria do curso deve ser maior que zero.");
        }
        if (repository.buscarPorCodigo(curso.getCodigo()) != null) {
            throw new IllegalArgumentException("Ja existe um curso com o codigo " + curso.getCodigo() + ".");
        }
        repository.salvar(curso);
    }

    public List<Curso> listar() {
        return repository.listarTodos();
    }

    public Curso buscarPorCodigo(String codigo) {
        return repository.buscarPorCodigo(codigo);
    }

}