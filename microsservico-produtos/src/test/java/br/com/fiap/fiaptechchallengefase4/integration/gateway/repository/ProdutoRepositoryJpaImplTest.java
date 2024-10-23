package br.com.fiap.fiaptechchallengefase4.integration.gateway.repository;

import br.com.fiap.fiaptechchallengefase4.gateway.repository.ProdutoRepository;
import br.com.fiap.fiaptechchallengefase4.infra.persistence.ProdutoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class ProdutoRepositoryJpaImplTest {


    @Autowired
    private ProdutoRepository produtoRepository;


    @BeforeEach
    void setup() {
        produtoRepository.deleteAll();
    }


    @Test
    @DisplayName("Deve retornar todos os produtos")
    void findAll() {
        ProdutoEntity produto1 = new ProdutoEntity();
        produto1.setNome("Produto 1");
        produto1.setPreco(10.00);

        ProdutoEntity produto2 = new ProdutoEntity();
        produto2.setNome("Produto 2");
        produto2.setPreco(20.00);

        produtoRepository.save(produto1);
        produtoRepository.save(produto2);

        List<ProdutoEntity> produtos = produtoRepository.findAll();
        assertEquals(2, produtos.size());
    }

    @Test
    @DisplayName("Deve encontrar produto por ID")
    void findById() {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome("Produto Teste");
        produto.setPreco(99.99);

        ProdutoEntity produtoSalvo = produtoRepository.save(produto);

        ProdutoEntity produtoEncontrado = produtoRepository.findById(produtoSalvo.getId()).orElse(null);

        assertNotNull(produtoEncontrado);
        assertEquals(produtoSalvo.getId(), produtoEncontrado.getId());
    }

    @Test
    @DisplayName("Deve salvar o produto")
    void save() {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome("Produto Teste");
        produto.setPreco(99.99);

        ProdutoEntity produtoSalvo = produtoRepository.save(produto);

        assertNotNull(produtoSalvo.getId());
        assertEquals("Produto Teste", produtoSalvo.getNome());
    }

    @Test
    @DisplayName("Deve deletar o produto")
    void delete() {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome("Produto Teste");
        produto.setPreco(99.99);

        ProdutoEntity produtoSalvo = produtoRepository.save(produto);
        produtoRepository.deleteById(produtoSalvo.getId());

        ProdutoEntity produtoDeletado = produtoRepository.findById(produtoSalvo.getId()).orElse(null);
        assertNull(produtoDeletado);
    }
}