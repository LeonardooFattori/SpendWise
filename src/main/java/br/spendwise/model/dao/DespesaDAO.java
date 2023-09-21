package br.spendwise.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.spendwise.model.domain.Despesa;
import br.spendwise.model.domain.Usuario;

public class DespesaDAO {
    public void criarTabela(){
        String sql = "CREATE TABLE IF NOT EXISTS despesas(id integer PRIMARY KEY, categoria varchar(40), FOREIGN KEY(id) REFERENCES orcamentos(id))";
        try(PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void inserir(Despesa despesas) {
        OrcamentoDAO odao = new OrcamentoDAO();
        odao.inserir(despesas);
        String sql = "INSERT INTO despesas(id,categoria) values (?,?)";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setInt(1, despesas.getId());
            stmt.setString(2, despesas.getCategoria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Integer Id) {
        String sql = "DELETE FROM despesas WHERE Id=?";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setInt(1, Id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Despesa despesas) {
        String sql = "UPDATE despesas SET categoria=? WHERE Id=?";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setString(1, despesas.getCategoria());
            stmt.setInt(2, despesas.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Despesa> listar() {
        ArrayList<Despesa> despesas = new ArrayList<>();
        String sql = "SELECT d.*, o.* FROM despesas d INNER JOIN orcamentos o ON d.id = o.id";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioDAO udao = new UsuarioDAO();
                Usuario u = udao.pesquisarPorId(rs.getInt("idusuario"));
                Despesa d = new Despesa(rs.getInt("id"), rs.getDouble("valor"), rs.getString("data"), u, rs.getString("tipo"), rs.getString("categoria"));
                despesas.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesas;
    }

    public Despesa pesquisarPorId(Integer Id) {
        Despesa despesa = null;
        String sql = "SELECT d.*, o.* FROM despesas d INNER JOIN orcamentos o ON d.id = o.id WHERE d.id = ?";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setInt(1, Id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioDAO udao = new UsuarioDAO();
                Usuario u = udao.pesquisarPorId(rs.getInt("idusuario"));
                despesa = new Despesa(rs.getInt("id"), rs.getDouble("valor"), rs.getString("data"), u, rs.getString("tipo"), rs.getString("categoria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesa;
    }
}
