package br.com.fiap.fiaptechchallengefase4.unit.core.usecases;

import br.com.fiap.fiaptechchallengefase4.core.domain.Produto;
import br.com.fiap.fiaptechchallengefase4.core.gateway.ProdutoGateway;
import br.com.fiap.fiaptechchallengefase4.core.usecases.ProdutoUseCase;
import br.com.fiap.fiaptechchallengefase4.infra.persistence.ProdutoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith({MockitoExtension.class})
class ProdutoUseCaseTest {
    @Mock
    private ProdutoGateway produtoGateway;
    @InjectMocks
    private ProdutoUseCase produtoUseCase;
    ProdutoEntity produtoEntity;

    ProdutoUseCaseTest() {
    }

    @Test
    @DisplayName("Deve retornar lista de produtos quando existir produto cadastrado")
    void getAllProdutos() {
        List<ProdutoEntity> produtosMock = Arrays.asList(new ProdutoEntity(1L, "Produto 1", 10.0), new ProdutoEntity(2L, "Produto 2", 20.0));
        when(this.produtoGateway.findAll()).thenReturn(produtosMock);
        List<Produto> result = this.produtoUseCase.getAllProdutos();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Produto 1", ((Produto)result.get(0)).getNome());
        ((ProdutoGateway) verify(this.produtoGateway, times(1))).findAll();
    }

    @Test
    @DisplayName("Deve retornar produto quando buscado por ID Valido")
    void getProdutoById() {
        Long id = 1L;
        ProdutoEntity produtoMock = new ProdutoEntity();
        produtoMock.setId(id);
        produtoMock.setNome("Nome produto");
        when(this.produtoGateway.findById(id)).thenReturn(produtoMock);

        Produto produtoRetornado = this.produtoUseCase.getProdutoById(id);

        Assertions.assertNotNull(produtoRetornado);
        Assertions.assertEquals(id, produtoRetornado.getId());
        Assertions.assertEquals("Nome produto", produtoRetornado.getNome());

        verify(this.produtoGateway, times(1)).findById(id);
    }


    @Test
    @DisplayName("Deve retornar null ao buscar produto por ID inválido")
    void getProdutoById_Invalido() {
        Long idInvalido = 999L;
        when(produtoGateway.findById(idInvalido)).thenReturn(null);

        Produto produtoRetornado = produtoUseCase.getProdutoById(idInvalido);

        assertNull(produtoRetornado);
        verify(produtoGateway, times(1)).findById(idInvalido);
    }

    @Test
    @DisplayName("Deve salvar um produto corretamente")
    void saveProduto() {
        Produto produto = new Produto(1L, "Produto 1", 10.0);
        this.produtoUseCase.saveProduto(produto);
        ArgumentCaptor<ProdutoEntity> captor = ArgumentCaptor.forClass(ProdutoEntity.class);
        ((ProdutoGateway) verify(this.produtoGateway, times(1))).save((ProdutoEntity)captor.capture());
        ProdutoEntity produtoEntityCapturado = (ProdutoEntity)captor.getValue();
        Assertions.assertEquals(produto.getId(), produtoEntityCapturado.getId());
        Assertions.assertEquals(produto.getNome(), produtoEntityCapturado.getNome());
        Assertions.assertEquals(produto.getPreco(), produtoEntityCapturado.getPreco());
    }

    @Test
    @DisplayName("Deve lançar exceção ao salvar produto inválido")
    void saveProduto_Invalido() {
        Produto produtoInvalido = new Produto(null, null, -10.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            produtoUseCase.saveProduto(produtoInvalido);
        });
        assertEquals("Produto inválido", exception.getMessage());
        verify(produtoGateway, never()).save(any());
    }

    @Test
    @DisplayName("Deve deletar um produto corretamente")
    void deleteProduto() {
        Long id = 1L;
        this.produtoUseCase.deleteProduto(id);
        ((ProdutoGateway) verify(this.produtoGateway, times(1))).delete(id);
    }

    @Test
    @DisplayName("Deve fazer nada ao deletar produto que não existe")
    void deleteProduto_NaoExistente() {
        Long id = 999L;
        produtoUseCase.deleteProduto(id);
        verify(produtoGateway, times(1)).delete(id);
    }
}