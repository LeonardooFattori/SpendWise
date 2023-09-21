package br.spendwise.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.spendwise.model.dao.OrcamentoDAO;
import br.spendwise.model.domain.Orcamento;
import br.spendwise.model.domain.Usuario;

@WebServlet(name = "orcamentoServlet", value = "/orcamentos")
public class OrcamentoServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        OrcamentoDAO dao = new OrcamentoDAO();
        dao.criarTabela();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        OrcamentoDAO dao = new OrcamentoDAO();
        dao.excluir(id);
        resp.sendRedirect("");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        Integer id = Integer.parseInt(req.getParameter("id"));
        Double valor = Double.parseDouble(req.getParameter("valor"));
        String data = req.getParameter("data");
        String tipo = req.getParameter("tipo");
        String enviar = req.getParameter("enviar");
        Usuario usuario = new Usuario();
        Orcamento o = new Orcamento(id, valor, data, usuario, tipo);
        OrcamentoDAO dao = new OrcamentoDAO();
        if (enviar.contains("Salvar")) {
            dao.inserir(o);
        } else {
            dao.alterar(o);
        }
        resp.sendRedirect("");
    }
}
