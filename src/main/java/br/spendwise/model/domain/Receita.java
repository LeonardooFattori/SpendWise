package br.spendwise.model.domain;

public class Receita extends Orcamento{
    private String descricao;

    public Receita(int id, double valor, String data, Usuario usuario, String descricao, String tipo) {
        super(id, valor, data, usuario, tipo);
        this.descricao = descricao;
    }

    public Receita(String descricao) {
        this.descricao = descricao;
    }

    public Receita() {
        this.descricao = "";
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Receita [descricao=" + descricao + "]";
    }
    
    
}
