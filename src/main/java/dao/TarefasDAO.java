package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Tarefas;


public class TarefasDAO {

    // CREATE - INSERIR TAREFA
    public int inserir(Tarefas tarefas) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int idGerado = -1;
        String sql = "INSERT INTO tarefa (id_aluno, tarefa, dt_criacao, dt_entrega) VALUES (?, ?, ?, ?) RETURNING id";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, tarefas.getIdAluno());
            pst.setString(2,tarefas.getTarefa());
            pst.setDate(3, Date.valueOf(tarefas.getDtCriacao()));
            pst.setDate(4, Date.valueOf(tarefas.getDtEntrega()));


            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idGerado = rs.getInt("id");
                tarefas.setId(idGerado);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return idGerado; // Retorna o ID gerado ou -1 se falhar
    }

    // READ - BUSCAR TAREFA PELO ID
    public Tarefas buscarPorId(int id) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        Tarefas tarefas  = null;
        String sql = "SELECT * FROM tarefa WHERE id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                tarefas = new Tarefas(
                        rs.getInt("id"),
                        rs.getInt("id_aluno"),
                        rs.getString("tarefa"),
                        rs.getDate("dt_criacao").toLocalDate(),
                        rs.getDate("dt_entrega").toLocalDate()
                );
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return tarefas; // retorna null se não encontrar
    }

    // READ - LISTAR TODAS AS TAREFAS DO ALUNO
    public List<Tarefas> listarIdAluno(int idAluno) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Tarefas> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa where id_aluno = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idAluno);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tarefas.add(new Tarefas(
                        rs.getInt("id"),
                        rs.getInt("id_aluno"),
                        rs.getString("tarefa"),
                        rs.getDate("dt_criacao").toLocalDate(),
                        rs.getDate("dt_entrega").toLocalDate()

                ));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return tarefas;
    }

    // READ - LISTAR TODOS AS TAREFAS
    public List<Tarefas> listar() {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Tarefas> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tarefas.add(new Tarefas(
                        rs.getInt("id"),
                        rs.getInt("id_aluno"),
                        rs.getString("tarefa"),
                        rs.getDate("dt_criacao").toLocalDate(),
                        rs.getDate("dt_entrega").toLocalDate()

                ));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return tarefas;
    }

    // UPDATE - ATUALIZAR TAREFA
    public int atualizar(Tarefas tarefas) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "UPDATE tarefa SET tarefa = ?, dt_entrega = ?WHERE id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tarefas.getTarefa());
            pst.setDate(2, Date.valueOf(tarefas.getDtEntrega()));
            pst.setInt(3, tarefas.getId());

            retorno = pst.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno; // retorna número de linhas alteradas ou -1 em caso de erro
    }

    // DELETE - DELETAR TAREFA
    public int deletar(int id) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "DELETE FROM tarefa WHERE id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            retorno = pst.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno; // retorna número de linhas deletadas ou -1 se falhar
    }
}
