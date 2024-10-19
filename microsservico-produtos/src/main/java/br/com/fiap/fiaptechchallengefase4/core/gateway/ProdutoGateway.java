package br.com.fiap.fiaptechchallengefase4.core.gateway;

import br.com.fiap.fiaptechchallengefase4.infra.persistence.ProdutoEntity;

import java.util.List;

public interface ProdutoGateway {
    List<ProdutoEntity> findAll();
    ProdutoEntity findById(Long id);
    void save(ProdutoEntity produto);
    void delete(Long id);

}
