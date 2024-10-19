package br.com.fiap.fiaptechchallengefase4.core.mapper;

import br.com.fiap.fiaptechchallengefase4.core.domain.Produto;
import br.com.fiap.fiaptechchallengefase4.infra.persistence.ProdutoEntity;

public class ProdutoMapper {
    public static Produto toDomain(ProdutoEntity entity) {
        Produto produto = new Produto(entity.getId(),entity.getNome(),entity.getPreco());

        return produto;
    }

    public static ProdutoEntity toEntity(Produto domain) {
        ProdutoEntity entity = new ProdutoEntity(domain.getId(),domain.getNome(),domain.getPreco());
        return entity;
    }
}
