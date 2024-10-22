package br.com.fiap.fiaptechchallengefase4.core.usecases;

import br.com.fiap.fiaptechchallengefase4.core.domain.Produto;
import br.com.fiap.fiaptechchallengefase4.core.gateway.ProdutoGateway;
import br.com.fiap.fiaptechchallengefase4.core.mapper.ProdutoMapper;
import br.com.fiap.fiaptechchallengefase4.infra.persistence.ProdutoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public List<Produto> getAllProdutos() {
        return produtoGateway.findAll().stream()
                .map(ProdutoMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Produto getProdutoById(Long id) {
        ProdutoEntity entity = produtoGateway.findById(id);
        if (entity == null) {
            return null; // Retorna null se o produto não for encontrado
        }
        produtoGateway.delete(id);
        return ProdutoMapper.toDomain(entity);
    }

    public void saveProduto(Produto product) {
        // Verificando se o produto é nulo
        if(product == null || product.getNome() == null || product.getPreco() <= 0){
            throw new IllegalArgumentException("Produto inválido");
        }
        produtoGateway.save(ProdutoMapper.toEntity(product));
    }

    public void deleteProduto(Long id) {
        produtoGateway.delete(id);
    }


}
