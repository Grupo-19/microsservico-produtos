package br.com.fiap.fiaptechchallengefase4.gateway.repository;

import br.com.fiap.fiaptechchallengefase4.core.gateway.ProdutoGateway;
import br.com.fiap.fiaptechchallengefase4.gateway.repository.ProdutoRepository;
import br.com.fiap.fiaptechchallengefase4.infra.persistence.ProdutoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoRepositoryJpaImpl implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoRepositoryJpaImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<ProdutoEntity> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public ProdutoEntity findById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ProdutoEntity produto) {
        produtoRepository.save(produto);
    }

    @Override
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}

