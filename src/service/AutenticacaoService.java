package service;

import java.util.ArrayList;
import java.util.List;

import model.Administrador;
import model.Aluno;
import model.Autenticacao;
import model.Professor;
import model.Usuario;

public class AutenticacaoService {

    private AlunoService alunoService;
    private ProfessorService professorService;
    private List<Administrador> administradores;

    public AutenticacaoService(AlunoService alunoService, ProfessorService professorService,
            List<Administrador> administradores) {
        this.alunoService = alunoService;
        this.professorService = professorService;
        this.administradores = administradores;
    }

    // Procura um usuario com esse login e senha. Devolve null se ninguem conferir
    public Usuario entrar(String login, String senha) {
        List<Usuario> todos = new ArrayList<>();
        todos.addAll(alunoService.listar());
        todos.addAll(professorService.listar());
        todos.addAll(administradores);

        for (Usuario u : todos) {
            if (u instanceof Autenticacao a && a.autenticar(login, senha)) {
                return u;
            }
        }
        return null;
    }

    // Diz qual e o perfil do usuario, para a ui mostrar
    public String perfilDe(Usuario usuario) {
        if (usuario instanceof Aluno) {
            return "Aluno";
        }
        if (usuario instanceof Professor) {
            return "Professor";
        }
        if (usuario instanceof Administrador) {
            return "Administrador";
        }
        return "Usuario";
    }

}