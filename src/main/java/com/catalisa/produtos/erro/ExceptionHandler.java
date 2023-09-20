package com.catalisa.produtos.erro;

import com.catalisa.produtos.produto.ProdutoNaoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleDatabaseErrors(RuntimeException e, WebRequest request) {


        Map<String, Object> body = Map.of(
                "status", 500,
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", e.getLocalizedMessage()
        );
        return ResponseEntity
                .internalServerError().body(body);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<?> handleDatabaseErrorsRequest(RuntimeException e, WebRequest request) {


        Map<String, Object> body = Map.of(
                "status", 402,
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", e.getMessage()
        );
        return ResponseEntity.unprocessableEntity().body(body);
    }
}
