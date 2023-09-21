package br.spendwise.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.spendwise.model.domain.Receita;
import br.spendwise.model.domain.Usuario;

public class ReceitaDAO {
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS receitas(id integer PRIMARY KEY, descricao varchar(40), FOREIGN KEY(id) REFERENCES orcamentos(id))";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Receita receitas) {
        OrcamentoDAO odao = new OrcamentoDAO();
        odao.inserir(receitas);
        String sql = "INSERT INTO receitas(id,descricao) values (?,?)";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setInt(1, receitas.getId());
            stmt.setString(2, receitas.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Integer id) {
        String sql = "DELETE FROM receitas WHERE id=?";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Receita receitas) {
        String sql = "UPDATE receitas SET descricao=? WHERE id=?";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setString(1, receitas.getDescricao());
            stmt.setInt(2, receitas.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Receita> listar() {
        ArrayList<Receita> receita = new ArrayList<>();
        String sql = "SELECT r.*, o.* FROM receitas r INNER JOIN orcamentos o ON r.id = o.id";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioDAO udao = new UsuarioDAO();
                Usuario u = udao.pesquisarPorId(rs.getInt("idusuario"));
                Receita r = new Receita(rs.getInt("id"), rs.getDouble("valor"), rs.getString("data"), u, rs.getString("tipo"), rs.getString("descricao"));
                receita.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receita;
    }

    public Receita pesquisarPorId(Integer id) {
        Receita receitas = null;
        String sql = "SELECT r.*, o.* FROM receitas r INNER JOIN orcamentos o ON r.id = o.id WHERE r.id = ?";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioDAO udao = new UsuarioDAO();
                Usuario u = udao.pesquisarPorId(rs.getInt("idusuario"));
                receitas = new Receita(rs.getInt("id"), rs.getDouble("valor"), rs.getString("data"), u, rs.getString("tipo"), rs.getString("descricao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receitas;
    }
}
