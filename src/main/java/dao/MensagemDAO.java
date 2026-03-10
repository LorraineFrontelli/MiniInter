package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import conexao.Conexao;
import model.Mensagem;

public class MensagemDAO {

    // CREATE - INSERIR MENSAGEM
    public int inserir(Mensagem mensagem) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int idGerado = -1;
        String sql = "INSERT INTO chat (id_remetente, tipo_remetente, id_destinatario, tipo_destinatario, mensagem, dt_mensagem, lida) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, mensagem.getIdRemetente());
            pst.setString(2, mensagem.getTipoRemetente());
            pst.setInt(3, mensagem.getIdDestinatario());
            pst.setString(4, mensagem.getTipoDestinatario());
            pst.setString(5, mensagem.getMensagem());
            pst.setTimestamp(6, mensagem.getDataMensagem());
            pst.setBoolean(7, mensagem.getLida());


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

    // READ - LISTAR TODO O HISTORICO DE CHAT DO USUARIO
    public List<Mensagem> listarHistorico(int idUsuario, String tipoUsuario) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT " +
                "CASE WHEN m.id_remetente = ? AND m.tipo_remetente = ? THEN m.id_destinatario ELSE m.id_remetente END AS id_contato, " +
                "CASE WHEN m.id_remetente = ? AND m.tipo_remetente = ? THEN m.tipo_destinatario ELSE m.tipo_remetente END AS tipo_contato, " +
                "CASE WHEN m.id_remetente = ? AND m.tipo_remetente = ? THEN " +
                "  CASE WHEN m.tipo_destinatario = 'PROFESSOR' THEN p2.nome " +
                "       WHEN m.tipo_destinatario = 'ALUNO' THEN a2.nome " +
                "       WHEN m.tipo_destinatario = 'ADMIN' THEN 'ADMIN_' || adm2.id::text END " +
                "ELSE " +
                "  CASE WHEN m.tipo_remetente = 'PROFESSOR' THEN p.nome " +
                "       WHEN m.tipo_remetente = 'ALUNO' THEN a.nome " +
                "       WHEN m.tipo_remetente = 'ADMIN' THEN 'ADMIN_' || adm.id::text END " +
                "END AS nome_contato, " +
                "BOOL_OR(m.lida = false AND m.id_destinatario = ? AND m.tipo_destinatario = ?) AS tem_nao_lidas, " +
                "MAX(m.dt_mensagem) AS ultima_mensagem " +
                "FROM chat m " +
                "LEFT JOIN professor p ON m.tipo_remetente = 'PROFESSOR' AND m.id_remetente = p.id " +
                "LEFT JOIN aluno a ON m.tipo_remetente = 'ALUNO' AND m.id_remetente = a.matricula " +
                "LEFT JOIN admin adm ON m.tipo_remetente = 'ADMIN' AND m.id_remetente = adm.id " +
                "LEFT JOIN professor p2 ON m.tipo_destinatario = 'PROFESSOR' AND m.id_destinatario = p2.id " +
                "LEFT JOIN aluno a2 ON m.tipo_destinatario = 'ALUNO' AND m.id_destinatario = a2.matricula " +
                "LEFT JOIN admin adm2 ON m.tipo_destinatario = 'ADMIN' AND m.id_destinatario = adm2.id " +
                "WHERE (m.id_destinatario = ? AND m.tipo_destinatario = ?) OR (m.id_remetente = ? AND m.tipo_remetente = ?) " +
                "GROUP BY id_contato, tipo_contato, nome_contato " +
                "ORDER BY ultima_mensagem DESC;";
        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, idUsuario);
            pst.setString(2, tipoUsuario);
            pst.setInt(3, idUsuario);
            pst.setString(4, tipoUsuario);
            pst.setInt(5, idUsuario);
            pst.setString(6, tipoUsuario);
            pst.setInt(7, idUsuario);
            pst.setString(8, tipoUsuario);
            pst.setInt(9, idUsuario);
            pst.setString(10, tipoUsuario);
            pst.setInt(11, idUsuario);
            pst.setString(12, tipoUsuario);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                mensagens.add(new Mensagem(
                        rs.getInt("id_contato"),
                        rs.getString("tipo_contato"),
                        idUsuario,
                        tipoUsuario,
                        rs.getString("nome_contato"),
                        rs.getTimestamp("ultima_mensagem"),
                        rs.getBoolean("tem_nao_lidas")
                ));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return mensagens;
    }


    // READ - LISTAR TODO O HISTORICO DE CHAT DO USUARIO
    public List<Mensagem> listarHistoricoRecente(int idUsuario, String tipoUsuario, int limite) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "WITH conversas AS ( " +
                "SELECT " +
                "CASE WHEN m.id_remetente = ? AND m.tipo_remetente = ? THEN m.id_destinatario ELSE m.id_remetente END AS id_contato, " +
                "CASE WHEN m.id_remetente = ? AND m.tipo_remetente = ? THEN m.tipo_destinatario ELSE m.tipo_remetente END AS tipo_contato, " +
                "CASE WHEN m.id_remetente = ? AND m.tipo_remetente = ? THEN " +
                "  CASE WHEN m.tipo_destinatario = 'PROFESSOR' THEN p2.nome " +
                "       WHEN m.tipo_destinatario = 'ALUNO' THEN a2.nome " +
                "       WHEN m.tipo_destinatario = 'ADMIN' THEN 'ADMIN_' || adm2.id::text END " +
                "ELSE " +
                "  CASE WHEN m.tipo_remetente = 'PROFESSOR' THEN p.nome " +
                "       WHEN m.tipo_remetente = 'ALUNO' THEN a.nome " +
                "        WHEN m.tipo_remetente = 'ADMIN' THEN 'ADMIN_' || adm.id::text END " +
                "END AS nome_contato, " +
                "BOOL_OR(m.lida = false AND m.id_destinatario = ? AND m.tipo_destinatario = ?) AS tem_nao_lidas, " +
                "MAX(m.dt_mensagem) AS ultima_mensagem " +
                "FROM chat m " +
                "LEFT JOIN professor p ON m.tipo_remetente = 'PROFESSOR' AND m.id_remetente = p.id " +
                "LEFT JOIN aluno a ON m.tipo_remetente = 'ALUNO' AND m.id_remetente = a.matricula " +
                "LEFT JOIN admin adm ON m.tipo_remetente = 'ADMIN' AND m.id_remetente = adm.id " +
                "LEFT JOIN professor p2 ON m.tipo_destinatario = 'PROFESSOR' AND m.id_destinatario = p2.id " +
                "LEFT JOIN aluno a2 ON m.tipo_destinatario = 'ALUNO' AND m.id_destinatario = a2.matricula " +
                "LEFT JOIN admin adm2 ON m.tipo_destinatario = 'ADMIN' AND m.id_destinatario = adm2.id " +
                "WHERE (m.id_destinatario = ? AND m.tipo_destinatario = ?) OR (m.id_remetente = ? AND m.tipo_remetente = ?) " +
                "GROUP BY id_contato, tipo_contato, nome_contato " +
                ") " +
                "SELECT c.*, " +
                "(SELECT m2.mensagem FROM chat m2 " +
                " WHERE (m2.id_remetente = ? AND m2.tipo_remetente = ? AND m2.id_destinatario = c.id_contato AND m2.tipo_destinatario = c.tipo_contato) " +
                "    OR (m2.id_remetente = c.id_contato AND m2.tipo_remetente = c.tipo_contato AND m2.id_destinatario = ? AND m2.tipo_destinatario = ?) " +
                " ORDER BY m2.dt_mensagem DESC LIMIT 1) AS ultima_mensagem_texto " +
                "FROM conversas c " +
                "ORDER BY c.ultima_mensagem DESC " +
                "LIMIT ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, idUsuario);
            pst.setString(2, tipoUsuario);
            pst.setInt(3, idUsuario);
            pst.setString(4, tipoUsuario);
            pst.setInt(5, idUsuario);
            pst.setString(6, tipoUsuario);
            pst.setInt(7, idUsuario);
            pst.setString(8, tipoUsuario);
            pst.setInt(9, idUsuario);
            pst.setString(10, tipoUsuario);
            pst.setInt(11, idUsuario);
            pst.setString(12, tipoUsuario);
            pst.setInt(13, idUsuario);
            pst.setString(14, tipoUsuario);
            pst.setInt(15, idUsuario);
            pst.setString(16, tipoUsuario);
            pst.setInt(17, limite);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Mensagem conversa = new Mensagem(
                        rs.getInt("id_contato"),
                        rs.getString("tipo_contato"),
                        idUsuario,
                        tipoUsuario,
                        rs.getString("nome_contato"),
                        rs.getTimestamp("ultima_mensagem"),
                        rs.getBoolean("tem_nao_lidas")
                );
                conversa.setMensagem(rs.getString("ultima_mensagem_texto"));
                mensagens.add(conversa);

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return mensagens;
    }

    // READ - LISTAR POR NOME O HISTORICO DE CHAT DO USUARIO
    public List<Mensagem> listarPorNome(int idUsuario, String tipoUsuario, String nomeFiltro) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT " +
                "CASE WHEN m.id_remetente = ? AND m.tipo_remetente = ? THEN m.id_destinatario ELSE m.id_remetente END AS id_contato, " +
                "CASE WHEN m.id_remetente = ? AND m.tipo_remetente = ? THEN m.tipo_destinatario ELSE m.tipo_remetente END AS tipo_contato, " +
                "CASE WHEN m.id_remetente = ? AND m.tipo_remetente = ? THEN " +
                "  CASE WHEN m.tipo_destinatario = 'PROFESSOR' THEN p2.nome " +
                "       WHEN m.tipo_destinatario = 'ALUNO' THEN a2.nome " +
                "       WHEN m.tipo_destinatario = 'ADMIN' THEN 'ADMIN_' || adm2.id::text END " +
                "ELSE " +
                "  CASE WHEN m.tipo_remetente = 'PROFESSOR' THEN p.nome " +
                "       WHEN m.tipo_remetente = 'ALUNO' THEN a.nome " +
                "       WHEN m.tipo_remetente = 'ADMIN' THEN 'ADMIN_' || adm.id::text END " +
                "END AS nome_contato, " +
                "BOOL_OR(m.lida = false AND m.id_destinatario = ? AND m.tipo_destinatario = ?) AS tem_nao_lidas, " +
                "MAX(m.dt_mensagem) AS ultima_mensagem " +
                "FROM chat m " +
                "LEFT JOIN professor p ON m.tipo_remetente = 'PROFESSOR' AND m.id_remetente = p.id " +
                "LEFT JOIN aluno a ON m.tipo_remetente = 'ALUNO' AND m.id_remetente = a.matricula " +
                "LEFT JOIN admin adm ON m.tipo_remetente = 'ADMIN' AND m.id_remetente = adm.id " +
                "LEFT JOIN professor p2 ON m.tipo_destinatario = 'PROFESSOR' AND m.id_destinatario = p2.id " +
                "LEFT JOIN aluno a2 ON m.tipo_destinatario = 'ALUNO' AND m.id_destinatario = a2.matricula " +
                "LEFT JOIN admin adm2 ON m.tipo_destinatario = 'ADMIN' AND m.id_destinatario = adm2.id " +
                "WHERE ((m.id_destinatario = ? AND m.tipo_destinatario = ?) OR (m.id_remetente = ? AND m.tipo_remetente = ?)) " +
                "AND (CASE WHEN m.id_remetente = ? AND m.tipo_remetente = ? THEN " +
                "  CASE WHEN m.tipo_destinatario = 'PROFESSOR' THEN p2.nome WHEN m.tipo_destinatario = 'ALUNO' THEN a2.nome WHEN m.tipo_destinatario = 'ADMIN' THEN adm2.login END " +
                "ELSE " +
                "  CASE WHEN m.tipo_remetente = 'PROFESSOR' THEN p.nome WHEN m.tipo_remetente = 'ALUNO' THEN a.nome WHEN m.tipo_remetente = 'ADMIN' THEN adm.login END " +
                "END) ILIKE ? "+
                "GROUP BY id_contato, tipo_contato, nome_contato " +
                "ORDER BY ultima_mensagem DESC;";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            pst.setString(2, tipoUsuario);

            pst.setInt(3, idUsuario);
            pst.setString(4, tipoUsuario);

            pst.setInt(5, idUsuario);
            pst.setString(6, tipoUsuario);

            pst.setInt(7, idUsuario);
            pst.setString(8, tipoUsuario);

            pst.setInt(9, idUsuario);
            pst.setString(10, tipoUsuario);

            pst.setInt(11, idUsuario);
            pst.setString(12, tipoUsuario);

            pst.setInt(13, idUsuario);
            pst.setString(14, tipoUsuario);

            pst.setString(15, "%" + nomeFiltro + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                mensagens.add(new Mensagem(
                        rs.getInt("id_contato"),
                        rs.getString("tipo_contato"),
                        idUsuario,
                        tipoUsuario,
                        rs.getString("nome_contato"),
                        rs.getTimestamp("ultima_mensagem"),
                        rs.getBoolean("tem_nao_lidas")
                ));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return mensagens;
    }


    // READ - LISTAR UMA CONVERSA DO USUARIO
    public List<Mensagem> listarConversa(int idUsuario, String tipoUsuario, int idUsuario2, String tipoUsuario2) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT m.id, m.id_destinatario, m.tipo_destinatario, m.id_remetente, m.tipo_remetente, m.mensagem, m.dt_mensagem, m.lida, " +
                "CASE " +
                "  WHEN m.tipo_remetente = 'PROFESSOR' THEN p.nome " +
                "  WHEN m.tipo_remetente = 'ALUNO' THEN a.nome " +
                "  WHEN m.tipo_remetente = 'ADMIN' THEN 'ADMIN_' || adm.id::text END " +
                "  AS nome_remetente " +
                "FROM chat m " +
                "LEFT JOIN professor p ON m.tipo_remetente = 'PROFESSOR' AND m.id_remetente = p.id " +
                "LEFT JOIN aluno a ON m.tipo_remetente = 'ALUNO' AND m.id_remetente = a.matricula " +
                "LEFT JOIN admin adm ON m.tipo_remetente = 'ADMIN' AND m.id_remetente = adm.id " +
                "WHERE (m.id_remetente = ? AND m.tipo_remetente = ? AND m.id_destinatario = ? AND m.tipo_destinatario = ?) " +
                "   OR (m.id_remetente = ? AND m.tipo_remetente = ? AND m.id_destinatario = ? AND m.tipo_destinatario = ?) " +
                "ORDER BY m.dt_mensagem ASC;";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            pst.setString(2, tipoUsuario);
            pst.setInt(3, idUsuario2);
            pst.setString(4, tipoUsuario2);

            pst.setInt(5, idUsuario2);
            pst.setString(6, tipoUsuario2);
            pst.setInt(7, idUsuario);
            pst.setString(8, tipoUsuario);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                mensagens.add(new Mensagem(
                        rs.getInt("id"),
                        rs.getInt("id_destinatario"),
                        rs.getString("tipo_destinatario"),
                        rs.getInt("id_remetente"),
                        rs.getString("tipo_remetente"),
                        rs.getString("mensagem"),
                        rs.getTimestamp("dt_mensagem"),
                        rs.getBoolean("lida"),
                        rs.getString("nome_remetente")
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
        String sql = "SELECT * FROM chat";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                mensagens.add(new Mensagem(
                        rs.getInt("id"),
                        rs.getInt("id_destinatario"),
                        rs.getString("tipo_destinatario"),
                        rs.getInt("id_remetente"),
                        rs.getString("tipo_remetente"),
                        rs.getTimestamp("dt_mensagem"),
                        rs.getString("mensagem"),
                        rs.getBoolean("lida")
                ));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return mensagens;
    }

    public List<Map<String, Object>> listarTodosUsuarios(int idAtual, String tipoAtual) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Map<String, Object>> usuarios = new ArrayList<>();

        String sql = " SELECT * FROM ( SELECT matricula AS id, nome, 'ALUNO' AS tipo FROM aluno UNION ALL SELECT id, nome, 'PROFESSOR' AS tipo FROM professor UNION ALL SELECT id, 'ADMIN_' || id AS nome, 'ADMIN' AS tipo FROM admin) AS todos ORDER BY 2 ";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // ignora o próprio usuário logado
                if (rs.getInt("id") == idAtual && rs.getString("tipo").equals(tipoAtual)) continue;

                Map<String, Object> u = new HashMap<>();
                u.put("id",   rs.getInt("id"));
                u.put("nome", rs.getString("nome"));
                u.put("tipo", rs.getString("tipo"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return usuarios;
    }

    // UPDATE - ATUALIZAR MENSAGEM
    public int atualizar(Mensagem mensagem) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "UPDATE chat SET mensagem = ?, dt_mensagem = ?, lida = ? WHERE id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, mensagem.getMensagem());
            pst.setTimestamp(2, mensagem.getDataMensagem());
            pst.setBoolean(3, mensagem.getLida());
            pst.setInt(4, mensagem.getId());

            retorno = pst.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno; // retorna número de linhas alteradas ou -1 em caso de erro
    }

    // UPDATE - MARCAR MENSAGENS COMO LIDAS
    public int atualizarLidas(int idRemetente, String tipoRemetente, int idDestinatario, String tipoDestinatario) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "UPDATE chat SET lida = true WHERE id_remetente = ? AND tipo_remetente = ? AND id_destinatario = ? AND tipo_destinatario = ? AND lida = false";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idRemetente);
            pst.setString(2, tipoRemetente);
            pst.setInt(3, idDestinatario);
            pst.setString(4, tipoDestinatario);

            retorno = pst.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

    // DELETE - DELETAR MENSAGEM
    public int deletar(int id) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "DELETE FROM chat WHERE id = ?";

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
