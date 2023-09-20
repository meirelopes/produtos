package com.catalisa.produtos.produto;

public class ProdutoNaoEncontradoException extends RuntimeException{

    public ProdutoNaoEncontradoException(){

        super("Não existe cadastro de produto com o id informado.");

    }
}
