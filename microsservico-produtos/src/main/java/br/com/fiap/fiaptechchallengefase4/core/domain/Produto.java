package br.com.fiap.fiaptechchallengefase4.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Produto {

    private Long id;
    private String nome;
    private Double preco;
}
