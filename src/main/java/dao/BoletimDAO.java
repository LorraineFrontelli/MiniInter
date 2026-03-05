package dao;

// imports da classe
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexao.Conexao;
import model.Boletim;

public class BoletimDAO {

    // CREATE - inserir boletim
    public int inserir(Boletim boletim) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        String sql = "INSERT INTO boletim (id_professor, id_aluno, nota_1, descricao_1, nota_2, descricao_2, aprovado, observacao, dt_criacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int retorno;

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, boletim.getIdProfessor());
            pst.setInt(2, boletim.getIdAluno());
            pst.setDouble(3, boletim.getNota1());
            pst.setString(4, boletim.getDescricao1());
            pst.setDouble(5, boletim.getNota2());
            pst.setString(6, boletim.getDescricao2());
            pst.setBoolean(7, boletim.isAprovado());
            pst.setString(8, boletim.getObservacao());
            pst.setDate(9, new java.sql.Date(boletim.getDataCriacao().getTime()));

            retorno = pst.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

    // READ - Buscar boletins por aluno
    public List<Boletim> buscarPorAluno(int idAluno) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Boletim> boletins = new ArrayList<>();
        String sql = "SELECT b.id, b.id_professor, b.id_aluno, b.nota_1, b.descricao_1, b.nota_2, b.descricao_2, b.aprovado, b.observacao, b.dt_criacao, p.materia, p.nome FROM boletim b JOIN professor p ON p.id = b.id_professor WHERE b.id_aluno = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idAluno);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                boletins.add(new Boletim(
                        rs.getInt("id"),
                        rs.getInt("id_professor"),
                        rs.getInt("id_aluno"),
                        rs.getDouble("nota_1"),
                        rs.getString("descricao_1"),
                        rs.getDouble("nota_2"),
                        rs.getString("descricao_2"),
                        rs.getBoolean("aprovado"),
                        rs.getString("observacao"),
                        rs.getDate("dt_criacao"),
                        rs.getString("materia"),
                        rs.getString("nome")
                ));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return boletins;
    }

    // READ - Buscar boletins pelo nome do aluno
    public List<Boletim> buscarPorNomeAluno(String nomeAluno) {

        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Boletim> boletins = new ArrayList<>();

        String sql = "SELECT b.id, b.id_professor, b.id_aluno, " +
                "b.nota_1, b.descricao_1, " +
                "b.nota_2, b.descricao_2, " +
                "b.aprovado, b.observacao, " +
                "b.dt_criacao, " +
                "p.materia, p.nome " +
                "FROM boletim b " +
                "JOIN professor p ON p.id = b.id_professor " +
                "JOIN aluno a ON a.id = b.id_aluno " +
                "WHERE a.nome LIKE ?";

        try {

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + nomeAluno + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                boletins.add(new Boletim(
                        rs.getInt("id"),
                        rs.getInt("id_professor"),
                        rs.getInt("id_aluno"),
                        rs.getDouble("nota_1"),
                        rs.getString("descricao_1"),
                        rs.getDouble("nota_2"),
                        rs.getString("descricao_2"),
                        rs.getBoolean("aprovado"),
                        rs.getString("observacao"),
                        rs.getDate("dt_criacao"),
                        rs.getString("materia"),
                        rs.getString("nome")
                ));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return boletins;
    }

    // READ - listar todos os boletins
    public List<Boletim> listar() {

        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Boletim> boletins = new ArrayList<>();

        String sql = "SELECT b.id, b.id_professor, b.id_aluno, " +
                "b.nota_1, b.descricao_1, " +
                "b.nota_2, b.descricao_2, " +
                "b.aprovado, b.observacao, " +
                "b.dt_criacao, " +
                "p.materia, p.nome " +
                "FROM boletim b " +
                "JOIN professor p ON p.id = b.id_professor";

        try {

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                boletins.add(new Boletim(
                        rs.getInt("id"),
                        rs.getInt("id_professor"),
                        rs.getInt("id_aluno"),
                        rs.getDouble("nota_1"),
                        rs.getString("descricao_1"),
                        rs.getDouble("nota_2"),
                        rs.getString("descricao_2"),
                        rs.getBoolean("aprovado"),
                        rs.getString("observacao"),
                        rs.getDate("dt_criacao"),
                        rs.getString("materia"),
                        rs.getString("nome")
                ));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return boletins;
    }


    // UPDATE - atualizar boletim
    public int atualizar(Boletim boletim) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;

        String sql = "UPDATE boletim SET id_professor=?, id_aluno=?, nota_1=?, descricao_1=?, nota_2=?, descricao_2=?, aprovado=?, observacao=?, dt_lancamento=? WHERE id=?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, boletim.getIdProfessor());
            pst.setInt(2, boletim.getIdAluno());
            pst.setDouble(3, boletim.getNota1());
            pst.setString(4, boletim.getDescricao1());
            pst.setDouble(5, boletim.getNota2());
            pst.setString(6, boletim.getDescricao2());
            pst.setBoolean(7, boletim.isAprovado());
            pst.setString(8, boletim.getObservacao());
            pst.setDate(9, new java.sql.Date(boletim.getDataCriacao().getTime()));
            pst.setInt(10, boletim.getId());

            retorno = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

    // DELETE - deletar boletim
    public int deletar(int id) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;

        String sql = "DELETE FROM boletim WHERE id=?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            retorno = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

}
