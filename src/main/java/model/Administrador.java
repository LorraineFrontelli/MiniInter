package model;

import java.util.HashSet;
import java.util.Set;

public class Administrador {

    // ATRIBUTOS
    private int id;
    private String login;
    private String senha;
    private Set<String> alunoCpf = new HashSet<>();

    // CONSTRUTOR VAZIO
    public Administrador() {
    }

    // CONSTRUTOR SEM ID (para novos registros)
    public Administrador(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    // CONSTRUTOR COMPLETO (quando for carregar do BD)
    public Administrador(int id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    // MÉTODOS CPF
    public void adicionarCpf(String cpf) {
        alunoCpf.add(cpf);
    }

    public boolean existeCpf(String cpf) {
        return alunoCpf.contains(cpf);
    }

    public Set<String> getAlunoCpf() {
        return alunoCpf;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // TO STRING
    @Override
    public String toString() {
        return "Administrador: " +
                "id=" + id +
                ", login='" + login + '\'' +
                ", quantidadeCpfs=" + alunoCpf.size();
    }
}
