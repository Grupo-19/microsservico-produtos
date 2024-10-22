package br.com.fiap.fiaptechchallengefase4.integration.domain;

import br.com.fiap.fiaptechchallengefase4.core.domain.Produto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProdutoTest {


    @Test
    @DisplayName("Deve trazer produto utilizando getter")
    void testGetterProduto() {
        Produto produto = new Produto(1L, "Produto Teste", 19.99);

        assertEquals(1L, produto.getId());
        assertEquals("Produto Teste", produto.getNome());
        assertEquals(19.99, produto.getPreco());
    }

    @Test
    @DisplayName("Deve funcioanr o construtor do produto")
    void testProdutoConstrutor() {
        Produto produto = new Produto(2L, "Outro Produto", 29.99);

        assertNotNull(produto);
        assertEquals(2L, produto.getId());
        assertEquals("Outro Produto", produto.getNome());
        assertEquals(29.99, produto.getPreco());
    }
}
