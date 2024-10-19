package br.com.fiap.fiaptechchallengefase4.core.usecases;

import br.com.fiap.fiaptechchallengefase4.core.domain.Produto;
import br.com.fiap.fiaptechchallengefase4.core.gateway.ProdutoGateway;
import br.com.fiap.fiaptechchallengefase4.core.mapper.ProdutoMapper;
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
        produtoGateway.delete(id);
        return ProdutoMapper.toDomain(produtoGateway.findById(id));
    }

    public void saveProduto(Produto product) {
        produtoGateway.save(ProdutoMapper.toEntity(product));
    }

    public void deleteProduto(Long id) {
        produtoGateway.delete(id);
    }


}
