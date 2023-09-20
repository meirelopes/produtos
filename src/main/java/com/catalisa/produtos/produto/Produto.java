package com.catalisa.produtos.produto;

import javax.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "nome")
    private String nome;
    @Column(nullable = false, name = "quantidade")
    private Integer quantidade;

    public Produto(){}
    public Produto(String nome, Integer quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

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
    public boolean existeOutroProdutoComMesmosDados(ProdutoRepository repository){

        if(repository.findByNome(
                        this.getNome())
                .isPresent()) {
            return true;
        }
        return false;
    }
}
