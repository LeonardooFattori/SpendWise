package br.spendwise.model.domain;

public class Orcamento {
    private int id;
    private double valor;
    private String data;
    private Usuario usuario;
    private String tipo;

    public Orcamento(int id, double valor, String data, Usuario usuario, String tipo) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.usuario = usuario;
        this.tipo = tipo;
    }

    public Orcamento() {
        this.id = 0;
        this.valor = 0;
        this.data = "";
        this.usuario = new Usuario();
        this.tipo = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Orcamento [id=" + id + ", valor=" + valor + ", data=" + data + ", usuario=" + usuario + "]";
    }

}
