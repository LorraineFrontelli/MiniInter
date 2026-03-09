package model;


import java.sql.Timestamp;

public class Mensagem{

    // ATRIBUTOS
    private int id;
    private int idDestinatario;
    private String tipoDestinatario;
    private int idRemetente;
    private String tipoRemetente;
    private String mensagem;
    private Timestamp dataMensagem;
    private boolean lida;
    private String nome;
    private Timestamp dataUltimaMensagem;
    private boolean temNaoLidas;

    // CONSTRUTOR VAZIO

    public Mensagem() {
    }

    // CONSTRUTOR SEM ID (para novos registros)

    public Mensagem(int idDestinatario, String tipoDestinatario, int idRemetente, String tipoRemetente, Timestamp dataMensagem, String mensagem, boolean lida) {
        this.idDestinatario = idDestinatario;
        this.tipoDestinatario = tipoDestinatario;
        this.idRemetente = idRemetente;
        this.tipoRemetente = tipoRemetente;
        this.dataMensagem = dataMensagem;
        this.mensagem = mensagem;
        this.lida = lida;
    }

    // CONSTRUTOR COMPLETO

    public Mensagem(int id, int idDestinatario, String tipoDestinatario, int idRemetente, String tipoRemetente, Timestamp dataMensagem, String mensagem, boolean lida) {
        this.id = id;
        this.idDestinatario = idDestinatario;
        this.tipoDestinatario = tipoDestinatario;
        this.idRemetente = idRemetente;
        this.tipoRemetente = tipoRemetente;
        this.dataMensagem = dataMensagem;
        this.mensagem = mensagem;
        this.lida = lida;
    }


    // CONSTRUTOR COMPLETO COM NOME (quando for carregar do BD)

    public Mensagem(int id, int idDestinatario, String tipoDestinatario, int idRemetente, String tipoRemetente, String mensagem, Timestamp dataMensagem, boolean lida, String nome) {
        this.id = id;
        this.idDestinatario = idDestinatario;
        this.tipoDestinatario = tipoDestinatario;
        this.idRemetente = idRemetente;
        this.tipoRemetente = tipoRemetente;
        this.mensagem = mensagem;
        this.dataMensagem = dataMensagem;
        this.lida = lida;
        this.nome = nome;
    }

    // CONSTRUTOR INCOMPLETO (para mostrar conversas que o usuario esta)

    public Mensagem(int idDestinatario, String tipoDestinatario, int idRemetente, String tipoRemetente, String nome, Timestamp dataUltimaMensagem, boolean temNaoLidas) {
        this.idDestinatario = idDestinatario;
        this.tipoDestinatario = tipoDestinatario;
        this.idRemetente = idRemetente;
        this.tipoRemetente = tipoRemetente;
        this.nome = nome;
        this.dataUltimaMensagem = dataUltimaMensagem;
        this.temNaoLidas = temNaoLidas;
    }


    // GETTERS

    public int getId() {
        return id;
    }

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public String getTipoDestinatario() {
        return tipoDestinatario;
    }

    public int getIdRemetente() {
        return idRemetente;
    }

    public String getTipoRemetente() {
        return tipoRemetente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Timestamp getDataMensagem() {
        return dataMensagem;
    }

    public boolean getLida() {
        return lida;
    }

    public String getNome() {
        return nome;
    }

    public Timestamp getDataUltimaMensagem() {
        return dataUltimaMensagem;
    }

    public boolean getTemNaoLidas() {
        return temNaoLidas;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public void setTipoDestinatario(String tipoDestinatario) {
        this.tipoDestinatario = tipoDestinatario;
    }

    public void setIdRemetente(int idRemetente) {
        this.idRemetente = idRemetente;
    }

    public void setTipoRemetente(String tipoRemetente) {
        this.tipoRemetente = tipoRemetente;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setDataMensagem(Timestamp dataMensagem) {
        this.dataMensagem = dataMensagem;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataUltimaMensagem(Timestamp dataUltimaMensagem) {
        this.dataUltimaMensagem = dataUltimaMensagem;
    }

    public void setTemNaoLidas(boolean temNaoLidas) {
        this.temNaoLidas = temNaoLidas;
    }

    // TO STRING

    @Override
    public String toString() {
        return "Mensagem{" +
                "id=" + id +
                ", idDestinatario=" + idDestinatario +
                ", tipoDestinatario='" + tipoDestinatario + '\'' +
                ", idRemetente=" + idRemetente +
                ", tipoRemetente='" + tipoRemetente + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", dataMensagem=" + dataMensagem +
                ", lida=" + lida +
                '}';
    }
}