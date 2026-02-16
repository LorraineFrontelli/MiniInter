package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Professor;

public class ProfessorDAO {

    // CREATE - INSERIR PROFESSOR
    public int inserir(Professor professor) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int idGerado = -1;
        String sql = "INSERT INTO professor (nome, dt_contratacao, email, senha, materia, usuario) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, professor.getNome());
            pst.setDate(2, Date.valueOf(professor.getDataContratacao()));
            pst.setString(3, professor.getEmail());
            pst.setString(4, professor.getSenha());
            pst.setString(5, professor.getMateria());
            pst.setString(6, professor.getUsuario());

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idGerado = rs.getInt("id");
                professor.setId(idGerado);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return idGerado; // Retorna o ID gerado ou -1 se falhar
    }

    // READ - BUSCAR PROFESSOR POR NOME
        public List<Professor> listarProfessorPorNome(String nome) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professor where nome ILIKE ? ";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + nome + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                professores.add(new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getTimestamp("dt_contratacao").toLocalDateTime().toLocalDate(),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("materia"),
                        rs.getString("usuario")
                ));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return professores;
    }

    // READ - LISTAR TODOS OS PROFESSORES
    public List<Professor> listar() {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professor";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                professores.add(new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getTimestamp("dt_contratacao").toLocalDateTime().toLocalDate(),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("materia"),
                        rs.getString("usuario")
                ));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return professores;
    }

    // UPDATE - ATUALIZAR PROFESSOR
    public int atualizar(Professor professor) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "UPDATE professor SET nome = ?, dt_contratacao = ?, email =?, senha = ?, materia =?, usuario=? WHERE id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, professor.getNome());
            pst.setDate(2, Date.valueOf(professor.getDataContratacao()));
            pst.setString(3, professor.getEmail());
            pst.setString(4, professor.getSenha());
            pst.setString(5, professor.getMateria());
            pst.setString(6, professor.getUsuario());
            pst.setInt(7, professor.getId());

            retorno = pst.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno; // retorna número de linhas alteradas ou -1 em caso de erro
    }

    // DELETE - DELETAR PROFESSOR
    public int deletar(int id) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "DELETE FROM professor WHERE id = ?";

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
