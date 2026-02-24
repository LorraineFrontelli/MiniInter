package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Telefone;

public class TelefoneDAO {

    // CREATE - INSERIR TELEFONE
    public int inserir(Telefone telefone) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int idGerado = -1;
        String sql = "INSERT INTO telefone (id_aluno, nome, numero, tipo) VALUES (?, ?, ?, ?) RETURNING id";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, telefone.getIdAluno());
            pst.setString(2,telefone.getNome());
            pst.setInt(3, telefone.getNumero());
            pst.setString(4, telefone.getTipo());

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idGerado = rs.getInt("id");
                telefone.setId(idGerado);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return idGerado; // Retorna o ID gerado ou -1 se falhar
    }

    // READ - BUSCAR TELEFONE PELO ID 
    public Telefone buscarPorId(int id) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        Telefone telefone = null;
        String sql = "SELECT * FROM telefone WHERE id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                telefone = new Telefone(
                        rs.getInt("id"),
                        rs.getInt("id_aluno"),
                        rs.getString("nome"),
                        rs.getInt("numero"),
                        rs.getString("tipo")
                );
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return telefone; // retorna null se não encontrar
    }

    // READ - LISTAR TODOS OS TELEFONES DO ALUNO
    public List<Telefone> listarIdAluno(int idAluno) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Telefone> telefones = new ArrayList<>();
        String sql = "SELECT * FROM telefone where id_aluno = ?";
        
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idAluno);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                telefones.add(new Telefone(
                        rs.getInt("id"),
                        rs.getInt("id_aluno"),
                        rs.getString("nome"),
                        rs.getInt("numero"),
                        rs.getString("tipo")

                ));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return telefones;
    }

    // READ - LISTAR TODOS OS TELEFONES
    public List<Telefone> listar() {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Telefone> telefones = new ArrayList<>();
        String sql = "SELECT * FROM telefone";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                telefones.add(new Telefone(
                        rs.getInt("id"),
                        rs.getInt("id_aluno"),
                        rs.getString("nome"),
                        rs.getInt("numero"),
                        rs.getString("tipo")

                ));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return telefones;
    }

    // UPDATE - ATUALIZAR TELEFONE
    public int atualizar(Telefone telefone) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "UPDATE telefone SET nome = ?, numero = ?, tipo = ? WHERE id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, telefone.getNome());
            pst.setInt(2, telefone.getNumero());
            pst.setString(3, telefone.getTipo());
            pst.setInt(4, telefone.getId());

            retorno = pst.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno; // retorna número de linhas alteradas ou -1 em caso de erro
    }

    // DELETE - DELETAR TELEFONE
    public int deletar(int id) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "DELETE FROM telefone WHERE id = ?";

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
