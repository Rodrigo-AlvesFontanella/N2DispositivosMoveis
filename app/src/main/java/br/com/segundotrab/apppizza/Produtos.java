package br.com.segundotrab.apppizza;

public class Produtos {


    public int id;
    public String nome;
    public int quantidade;
    private Double preco;
    public String categoria;

    public Produtos() {

    }

    public Produtos(int id, String nome, int quantidade, Double preco, String categoria) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produtos(String nome, int quantidade, Double preco, String categoria) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public double getPrecoTotal() {
        return quantidade * preco;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " | " + categoria + " - " + quantidade + " - " + String.format("%.2f",preco) ;
    }
}
