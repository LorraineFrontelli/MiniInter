package model;

import java.util.Date;

public class Boletim {

    private int id;
    private int idProfessor;
    private int idAluno;
    private double nota1;
    private String descricao1;
    private double nota2;
    private String descricao2;
    private double media;
    private boolean aprovado;
    private String observacao;
    private Date dataLancamento;

    // Métodos construtores
    public Boletim() {}

    public Boletim(int id, int idProfessor, int idAluno, double nota1, String descricao1,
                   double nota2, String descricao2, double media, boolean aprovado,
                   String observacao, Date dataLancamento) {
        this.id = id;
        this.idProfessor = idProfessor;
        this.idAluno = idAluno;
        this.nota1 = nota1;
        this.descricao1 = descricao1;
        this.nota2 = nota2;
        this.descricao2 = descricao2;
        this.media = media;
        this.aprovado = aprovado;
        this.observacao = observacao;
        this.dataLancamento = dataLancamento;
    }

    // Métodos getters
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

    public double getMedia() {
        return media;
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

    // Métodos setters
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

    public void setMedia(double media) {
        this.media = media;
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

    // Método toString
    @Override
    public String toString() {
        return "Boletim ID: " + id +
                "\tAluno: " + idAluno +
                "\tProfessor: " + idProfessor +
                "\tMédia: " + media +
                "\tAprovado: " + aprovado;
    }
}
