package model;

import java.util.Date;

public class Boletim {

    // ATRIBUTOS
    private int id;
    private int idProfessor;
    private int idAluno;
    private double nota1;
    private String descricao1;
    private double nota2;
    private String descricao2;
    private boolean aprovado;
    private String observacao;
    private Date dataLancamento;

    // CONSTRUTOR VAZIO
    public Boletim() {
    }

    // CONSTRUTOR SEM ID (para novos registros)
    public Boletim(int idProfessor, int idAluno, double nota1, String descricao1,
                   double nota2, String descricao2, boolean aprovado,
                   String observacao, Date dataLancamento) {
        this.idProfessor = idProfessor;
        this.idAluno = idAluno;
        this.nota1 = nota1;
        this.descricao1 = descricao1;
        this.nota2 = nota2;
        this.descricao2 = descricao2;
        this.aprovado = aprovado;
        this.observacao = observacao;
        this.dataLancamento = dataLancamento;
    }

    // CONSTRUTOR COMPLETO (quando for carregar do BD)
    public Boletim(int id, int idProfessor, int idAluno, double nota1, String descricao1,
                   double nota2, String descricao2, boolean aprovado,
                   String observacao, Date dataLancamento) {
        this.id = id;
        this.idProfessor = idProfessor;
        this.idAluno = idAluno;
        this.nota1 = nota1;
        this.descricao1 = descricao1;
        this.nota2 = nota2;
        this.descricao2 = descricao2;
        this.aprovado = aprovado;
        this.observacao = observacao;
        this.dataLancamento = dataLancamento;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public double getNota1() {
        return nota1;
    }

    public String getDescricao1() {
        return descricao1;
    }

    public double getNota2() {
        return nota2;
    }

    public String getDescricao2() {
        return descricao2;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public String getObservacao() {
        return observacao;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public void setDescricao1(String descricao1) {
        this.descricao1 = descricao1;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public void setDescricao2(String descricao2) {
        this.descricao2 = descricao2;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    // TO STRING
    @Override
    public String toString() {
        return "Boletim: " +
                "id=" + id +
                ", idAluno=" + idAluno +
                ", idProfessor=" + idProfessor +
                ", aprovado=" + aprovado;
    }
}
