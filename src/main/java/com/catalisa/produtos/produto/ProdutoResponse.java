package com.catalisa.produtos.produto;

public class ProdutoResponse {
    private Long id;
    private String nome;
    private Integer quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public static ProdutoResponse toResponse(Produto produto){

        var produtoResponse = new ProdutoResponse();

        produtoResponse.setId(produto.getId());
        produtoResponse.setNome(produto.getNome());
        produtoResponse.setQuantidade(produto.getQuantidade());

        return produtoResponse;
    }
}
