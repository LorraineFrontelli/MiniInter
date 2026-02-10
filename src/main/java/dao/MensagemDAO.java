package dao;

import conexao.Conexao;
import model.Mensagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensagemDAO {

    // CREATE - INSERIR MENSAGEM
    public int inserir(Mensagem mensagem) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int idGerado = -1;
        String sql = "INSERT INTO mensagem (id_professor, id_aluno, mensagem, dt_mensagem) VALUES (?, ?, ?, ?) RETURNING id";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, mensagem.getIdProfessor());
            pst.setInt(2, mensagem.getIdAluno());
            pst.setString(3, mensagem.getMensagem());
            pst.setTimestamp(4, Timestamp.valueOf(mensagem.getMensagem()));

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idGerado = rs.getInt("id");
                mensagem.setId(idGerado);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return idGerado; // Retorna o ID gerado ou -1 se falhar
    }

    // READ - BUSCAR MENSAGEM PELO ID
    public Mensagem buscarPorId(int id) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        Mensagem mensagem = null;
        String sql = "SELECT * FROM mensagem WHERE id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                mensagem = new Mensagem(
                        rs.getInt("id"),
                        rs.getInt("id_professor"),
                        rs.getInt("id_aluno"),
                        rs.getString("mensagem"),
                        rs.getTimestamp("dt_mensagem")
                );
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return mensagem; // retorna null se não encontrar
    }

    // READ - BUSCAR MENSAGEM PELO ID DO PROFESSOR
    public Mensagem buscarPorIdProfessor(int idProfessor) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        Mensagem mensagem = null;
        String sql = "SELECT * FROM mensagem WHERE id_professor = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idProfessor);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                mensagem = new Mensagem(
                        rs.getInt("id"),
                        rs.getInt("id_professor"),
                        rs.getInt("id_aluno"),
                        rs.getString("mensagem"),
                        rs.getTimestamp("dt_mensagem")
                );
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return mensagem; // retorna null se não encontrar
    }

    // READ - BUSCAR MENSAGEM PELO ID DO ALUNO
    public Mensagem buscarPorIdAluno(int idAluno) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        Mensagem mensagem = null;
        String sql = "SELECT * FROM mensagem WHERE id_aluno = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idAluno);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                mensagem = new Mensagem(
                        rs.getInt("id"),
                        rs.getInt("id_professor"),
                        rs.getInt("id_aluno"),
                        rs.getString("mensagem"),
                        rs.getTimestamp("dt_mensagem")
                );
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return mensagem; // retorna null se não encontrar
    }

    // READ - LISTAR TODAS AS MENSAGENS
    public List<Mensagem> listar() {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT * FROM mensagem";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                mensagens.add(new Mensagem(
                        rs.getInt("id"),
                        rs.getInt("id_professor"),
                        rs.getInt("id_aluno"),
                        rs.getString("mensagem"),
                        rs.getTimestamp("dt_mensagem")
                ));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return mensagens;
    }

    // UPDATE - ATUALIZAR MENSAGEM
    public int atualizar(Mensagem mensagem) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "UPDATE mensagem SET mensagem = ?, dt_mensagem = ? WHERE id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, mensagem.getMensagem());
            pst.setTimestamp(2, Timestamp.valueOf(mensagem.getMensagem()));
            pst.setInt(3, mensagem.getId());

            retorno = pst.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno; // retorna número de linhas alteradas ou -1 em caso de erro
    }

    // DELETE - DELETAR MENSAGEM
    public int deletar(int id) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "DELETE FROM mensagem WHERE id = ?";

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
