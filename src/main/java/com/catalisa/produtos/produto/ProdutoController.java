package com.catalisa.produtos.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;


    @GetMapping
    public ResponseEntity<?> listar() {

        var produtos = produtoRepository.findAll();
        var produtoResponse = new ArrayList<ProdutoResponse>();

        for (var produto : produtos) {
            produtoResponse.add(ProdutoResponse.toResponse(produto));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoResponse);

    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://172.31.48.1")
    public ResponseEntity<?> consultar(@PathVariable Long id) {

        Produto produto = getProduto(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ProdutoResponse.toResponse(produto));
    }

    private Produto getProduto(Long id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(ProdutoNaoEncontradoException::new);
        return produto;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @PostMapping
    public ResponseEntity<?> inserir(@Valid @RequestBody ProdutoRequest request) {

        var produto = request.toModel();
        return salvar(produto, HttpStatus.CREATED);

    }

    private ResponseEntity<?> salvar(Produto produto, HttpStatus status) {
        if (produto.existeOutroProdutoComMesmosDados(produtoRepository)) {
            return ResponseEntity.badRequest().body("Já existe produto com mesmo nome.");
        } else {
            produto = produtoRepository.save(produto);

            return ResponseEntity
                    .status(status)
                    .body(ProdutoResponse.toResponse(produto));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id){

        Produto produto = getProduto(id);
        produtoRepository.delete(produto);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @Valid @RequestBody ProdutoRequest request){
        Produto produto = getProduto(id);
        produto.setNome(request.getNome());
        produto.setQuantidade(request.getQuantidade());
        return salvar(produto, HttpStatus.OK);
    }

}
