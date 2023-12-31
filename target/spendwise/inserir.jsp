<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.spendwise.model.dao.*,br.spendwise.model.domain.*"%>
<%@page import="java.util.*"%>

<%
    UsuarioDAO udao = new UsuarioDAO();
    ReceitaDAO rdao = new ReceitaDAO();
    DespesaDAO ddao = new DespesaDAO();
    ArrayList<Usuario> usuarios = udao.listar();
    Usuario u = null;
    Receita r = null;
    Despesa d = null;
    String botaoUsuario;
    String botaoReceita;
    String botaoDespesa;
    if(request.getParameter("idUsuario") != null){
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        u = udao.pesquisarPorId(id);
        botaoUsuario = "Alterar";
    }else{
        u = new Usuario();
        botaoUsuario = "Salvar";
    }
    if(request.getParameter("idReceita") != null){
        int id = Integer.parseInt(request.getParameter("idReceita"));
        r = rdao.pesquisarPorId(id);
        botaoReceita = "Alterar";
    }else{
        r = new Receita();
        botaoReceita = "Salvar";
    }
    if(request.getParameter("idDespesa") != null){
        int id = Integer.parseInt(request.getParameter("idDespesa"));
        d = ddao.pesquisarPorId(id);
        botaoDespesa = "Alterar";
    }else{
        d = new Despesa();
        botaoDespesa = "Salvar";
    }
%>

<!DOCTYPE html>
<html lang="pt=br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>SpendWise</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="assets/css/insert.css">
    <link rel="shortcut icon" href="assets/img/icons8-dinheiro-na-mão-48.png" type="image/x-icon" />
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
            <div class="container-fluid">
                <a class="navbar-brand text-black text_nav spend_text d-none d-md-block" href="apresentacao.html">SpendWise</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-chevron-down fa-sm text-black text_nav" style="color: #ffffff;"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav mx-auto">
                        <li class="nav-item">
                            <a class="nav-link text-black text_nav  " href="inserir.jsp">
                                <i class="fas fa-plus fa-lg"></i> Inserir
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-black  text_nav" href="visualizar.jsp">
                                <i class="fas fa-eye fa-lg"></i> Visualizar
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-black  text_nav" href="relatorio.html">
                                <i class="fas fa-chart-bar fa-lg"></i> Relatório
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <section class="container-fluid min-vh-100 d-flex flex-column justify-content-center shadow contact-clean overflow-hidden"
        style="background: linear-gradient(90deg, rgba(191,208,233,1) 0%, rgba(173,197,232,1) 9%, rgba(168,194,232,1) 38%, rgba(126,155,199,1) 100%);">
        <div class="row d-flex align-items-center justify-content-center mt-5">
            <form class="bg-light border rounded border-secondary shadow-lg col-6 my-5" action="receitas" method="POST"
                style="background: rgb(248,248,249);">

                <h2 class="text-center spend_text text-black mb-5">Insira uma Receita!</h2>
                
                <div class="form-group mb-3 spend_logo">
                    <label for="">ID: </label>
                    <input class="form-control" type="number" required id="idReceita" name="idReceita" placeholder="" value="<%=r.getId()%>">
                </div>

                <div class="form-group mb-3 spend_logo">
                    <label for="">Valor: </label>
                    <input class="form-control" type="number" required id="valorReceita" name="valorReceita" placeholder="" value="<%=r.getValor()%>">
                </div>

                <div class="form-group mb-3 spend_logo">
                    <label for="">Data: </label>
                    <input class="form-control" type="text" required id="dataReceita" name="dataReceita" placeholder="" value="<%=r.getData()%>">
                </div>
                
                <div class="form-group mb-3 spend_logo">
                    <label for="">Usuário: </label>
                    <select name="usuarioID" id="usuarioID" value="<%=r.getUsuario().getNome()%>">
                            <%
                                for(Usuario user:usuarios){
                            %>
                            <option value="<%=user.getId()%>"><%=user.getNome()%></option>
                            <%
                                }
                            %>
                    </select>
                </div>

                <div class="form-group mb-3 spend_logo">
                    <label for="">Descrição: </label>
                    <input class="form-control" type="text" required id="descricaoReceita" name="descricaoReceita" placeholder="" value="<%=r.getDescricao()%>">
                </div>
                
                <div class="form-group mb-3 spend_logo">
                    <button class="btn btn-primary" type="submit" name="enviarReceita" id="enviarReceita" value="<%=botaoReceita%>"><%=botaoReceita%></button>
                </div>
            </form>

            <form class="bg-light border rounded border-secondary shadow-lg col-6 my-5" action="despesas" method="POST"
                style="background: rgb(248,248,249);">

                <h2 class="text-center spend_text text-black mb-5">Insira uma Despesa!</h2>
                
                <div class="form-group mb-3 spend_logo">
                    <label for="">ID: </label>
                    <input class="form-control" type="number" required id="idDespesa" name="idDespesa" placeholder="" value="<%=d.getId()%>">
                </div>

                <div class="form-group mb-3 spend_logo">
                    <label for="">Valor: </label>
                    <input class="form-control" type="number" required id="valorDespesa" name="valorDespesa" placeholder="" value="<%=d.getValor()%>">
                </div>

                <div class="form-group mb-3 spend_logo">
                    <label for="">Data: </label>
                    <input class="form-control" type="text" required id="dataDespesa" name="dataDespesa" placeholder="" value="<%=d.getData()%>">
                </div>
                
                <div class="form-group mb-3 spend_logo">
                    <select name="usuarioIDespesa" id="usuarioIDespesa" value="<%=d.getUsuario().getNome()%>">
                            <%
                                for(Usuario user:usuarios){
                            %>
                            <option value="<%=user.getId()%>"><%=user.getNome()%></option>
                            <%
                                }
                            %>
                    </select>
                </div>

                <div class="form-group mb-3 spend_logo">
                    <label for="">Categoria: </label>
                    <input class="form-control" type="text" required id="categoriaDespesa" name="categoriaDespesa" placeholder="" value="<%=d.getCategoria()%>">
                </div>
                
                <div class="form-group mb-3 spend_logo">
                    <button class="btn btn-primary" type="submit" name="enviarDespesa" id="enviarDespesa" value="<%=botaoDespesa%>"><%=botaoDespesa%></button>
                </div>
            </form>
        </div>
    </section>

    <script src="assets/js/insert.js"></script>

    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
