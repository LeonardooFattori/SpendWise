package br.spendwise.model.domain;

public class Despesa extends Orcamento{
    private String categoria;

    public Despesa(int id, double valor, String data, Usuario usuario, String categoria, String tipo) {
        super(id, valor, data, usuario, tipo);
        this.categoria = categoria;
    }

    public Despesa(String categoria) {
        this.categoria = categoria;
    }

    public Despesa() {
        this.categoria = "";
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Despesa [categoria=" + categoria + "]";
    }

}
