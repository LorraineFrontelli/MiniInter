package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Mensagem;

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
            pst.setTimestamp(4, mensagem.getDataMensagem());


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

    // READ - LISTAR TODO O HISTORICO DE CHAT ENTRE PROFESSOR E ALUNO
    public List<Mensagem> listarHistoricoAlunoProfessor(int idAluno, int idProfessor) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT * FROM mensagem WHERE id_professor = ? AND id_aluno = ? ORDER BY dt_mensagem";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idProfessor);
            pst.setInt(2, idAluno);
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
            pst.setTimestamp(2, mensagem.getDataMensagem());
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
