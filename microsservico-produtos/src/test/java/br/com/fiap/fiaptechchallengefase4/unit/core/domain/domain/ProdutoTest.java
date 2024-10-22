package br.com.fiap.fiaptechchallengefase4.unit.core.domain.domain;

import br.com.fiap.fiaptechchallengefase4.core.domain.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProdutoTest {


    @Test
    void testProdutoGetters() {
        // Arrange: Criando um novo produto
        Produto produto = new Produto(1L, "Produto Teste", 19.99);

        // Act & Assert: Validando se os getters funcionam corretamente
        assertEquals(1L, produto.getId());
        assertEquals("Produto Teste", produto.getNome());
        assertEquals(19.99, produto.getPreco());
    }

    @Test
    void testProdutoConstrutor() {
        // Arrange: Criando um novo produto
        Produto produto = new Produto(2L, "Outro Produto", 29.99);

        // Act & Assert: Validando se o construtor está setando os valores corretamente
        assertNotNull(produto);
        assertEquals(2L, produto.getId());
        assertEquals("Outro Produto", produto.getNome());
        assertEquals(29.99, produto.getPreco());
    }
}
