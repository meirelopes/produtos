package com.catalisa.produtos.produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProdutoRequest {
    @NotBlank
    private String nome;
    @NotNull
    private Integer quantidade;

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
    public Produto toModel(){

        return new Produto(this.getNome(), this.getQuantidade());

    }
}
