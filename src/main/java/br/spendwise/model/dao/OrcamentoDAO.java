package br.spendwise.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.spendwise.model.domain.Orcamento;
import br.spendwise.model.domain.Usuario;

public class OrcamentoDAO {
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS orcamentos(id integer PRIMARY KEY, valor float, data varchar(20), idusuario integer, tipo varchar(20), FOREIGN KEY(idusuario) REFERENCES usuarios(id))";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Orcamento orcamentos) {
        String sql = "INSERT INTO orcamentos(id,valor,data,idusuario,tipo) values (?,?,?,?,?)";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setInt(1, orcamentos.getId());
            stmt.setDouble(2, orcamentos.getValor());
            stmt.setString(3, orcamentos.getData());
            stmt.setInt(4, orcamentos.getUsuario().getId());
            stmt.setString(5, orcamentos.getTipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Integer id) {
        String sql = "DELETE FROM orcamentos WHERE id=?";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Orcamento orcamentos) {
        String sql = "UPDATE orcamentos SET valor=?, data=? WHERE id=?";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setDouble(1, orcamentos.getValor());
            stmt.setString(2, orcamentos.getData());
            stmt.setInt(3, orcamentos.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Orcamento> listar() {
        ArrayList<Orcamento> orcamento = new ArrayList<>();
        String sql = "SELECT * FROM orcamentos";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioDAO udao = new UsuarioDAO();
                Usuario u = udao.pesquisarPorId(rs.getInt("idusuario"));
                Orcamento o = new Orcamento(rs.getInt("id"), rs.getDouble("valor"), rs.getString("data"), u, rs.getString("tipo"));
                orcamento.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orcamento;
    }

    public Orcamento pesquisarPorId(Integer id) {
        Orcamento orcamentos = null;
        String sql = "SELECT * FROM orcamentos WHERE id=?";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioDAO udao = new UsuarioDAO();
                Usuario u = udao.pesquisarPorId(rs.getInt("idusuario"));
                orcamentos = new Orcamento(rs.getInt("id"), rs.getDouble("valor"), rs.getString("data"), u, rs.getString("tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orcamentos;
    }
}
