package br.com.fiap.fiaptechchallengefase4.adapters.dtos;


import br.com.fiap.fiaptechchallengefase4.core.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double preco;

    public ProdutoDTO (Produto produto){
        this.id=produto.getId();
        this.nome=produto.getNome();
        this.preco=produto.getPreco();


    }
    public Produto getProdutoDomain(){
        return new Produto(id,nome,preco);
    }
}
