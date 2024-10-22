package br.com.fiap.fiaptechchallengefase4.unit.mapper;

import br.com.fiap.fiaptechchallengefase4.core.domain.Produto;
import br.com.fiap.fiaptechchallengefase4.core.mapper.ProdutoMapper;
import br.com.fiap.fiaptechchallengefase4.infra.persistence.ProdutoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoMapperTest {


    @Test
    @DisplayName("Deve mapear ProdutoEntity para Produto corretamente")
    void testToDomain() {
        ProdutoEntity entity = new ProdutoEntity(1L, "Produto A", 100.0);
        Produto produto = ProdutoMapper.toDomain(entity);

        assertNotNull(produto);
        assertEquals(entity.getId(), produto.getId());
        assertEquals(entity.getNome(), produto.getNome());
        assertEquals(entity.getPreco(), produto.getPreco());
    }

    @Test
    @DisplayName("Deve mapear Produto para ProdutoEntity corretamente")
    void testToEntity() {
        Produto domain = new Produto(1L, "Produto A", 100.0);
        ProdutoEntity entity = ProdutoMapper.toEntity(domain);

        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getNome(), entity.getNome());
        assertEquals(domain.getPreco(), entity.getPreco());
    }
}