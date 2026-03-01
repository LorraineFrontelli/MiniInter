package model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Tarefas {

    // ATRIBUTOS
    private int id;
    private int idAluno;
    private String tarefa;
    private LocalDate dtCriacao;
    private LocalDate dtEntrega;

    // CONSTRUTOR VAZIO

    public Tarefas() {
    }

    // CONSTRUTOR SEM ID (para novos registros)

    public Tarefas(int idAluno, String tarefa, LocalDate dtCriacao,  LocalDate dtEntrega) {
        this.idAluno = idAluno;
        this.tarefa = tarefa;
        this.dtCriacao = dtCriacao;
        this.dtEntrega = dtEntrega;
    }

    // CONSTRUTOR COMPLETO (quando for carregar do BD)

    public Tarefas(int id, int idAluno, String tarefa, LocalDate dtCriacao, LocalDate dtEntrega) {
        this.id = id;
        this.idAluno = idAluno;
        this.tarefa = tarefa;
        this.dtCriacao = dtCriacao;
        this.dtEntrega = dtEntrega;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public String getTarefa() {
        return tarefa;
    }

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }

    public LocalDate getDtEntrega() {
        return dtEntrega;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setDtCriacao(LocalDate dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public void setDtEntrega(LocalDate dtEntrega) {
        this.dtEntrega = dtEntrega;
    }

    // TO STRING

    @Override
    public String toString() {
        return "Tarefas{" +
                "id=" + id +
                ", idAluno=" + idAluno +
                ", tarefa='" + tarefa + '\'' +
                ", dtCriacao=" + dtCriacao +
                ", dtEntrega=" + dtEntrega +
                '}';
    }
}
