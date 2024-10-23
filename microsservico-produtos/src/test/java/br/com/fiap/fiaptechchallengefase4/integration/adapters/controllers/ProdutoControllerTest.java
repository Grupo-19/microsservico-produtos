package br.com.fiap.fiaptechchallengefase4.integration.adapters.controllers;


import br.com.fiap.fiaptechchallengefase4.adapters.dtos.ProdutoDTO;
import br.com.fiap.fiaptechchallengefase4.core.domain.Produto;
import br.com.fiap.fiaptechchallengefase4.core.usecases.ProdutoUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoUseCase produtoUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve Retornar Todos os Produtos")
    public void getProductsByAll() throws Exception {
        Produto produto1 = new Produto(1L, "Produto 1", 10.0);
        Produto produto2 = new Produto(2L, "Produto 2", 20.0);
        List<Produto> produtos = Arrays.asList(produto1, produto2);

        when(produtoUseCase.getAllProdutos()).thenReturn(produtos);

        mockMvc.perform(get("/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Produto 1"))
                .andExpect(jsonPath("$[1].nome").value("Produto 2"));

        verify(produtoUseCase, times(1)).getAllProdutos();
    }


    @Test
    @DisplayName("Deve Retornar os produtos buscando por ID")
    void getProdutoById() throws Exception {
        Produto produto = new Produto(1L, "Produto 1", 10.0);

        when(produtoUseCase.getProdutoById(1L)).thenReturn(produto);

        mockMvc.perform(get("/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto 1"));

        verify(produtoUseCase, times(1)).getProdutoById(1L);
    }

    @Test
    @DisplayName("Deve Criar um produtos novo com sucesso")
    void createProduto() throws Exception {
        ProdutoDTO produtoDTO = new ProdutoDTO(null, "Produto Novo", 30.0);
        Produto produto = new Produto(null, "Produto Novo", 30.0);

        doNothing().when(produtoUseCase).saveProduto(any(Produto.class));

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoDTO)))
                .andExpect(status().isOk());

        verify(produtoUseCase, times(1)).saveProduto(any(Produto.class));
    }

    @Test
    @DisplayName("Deve deletar um produto com sucesso")
    void deleteProduto() throws Exception {
        doNothing().when(produtoUseCase).deleteProduto(1L);

        mockMvc.perform(delete("/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(produtoUseCase, times(1)).deleteProduto(1L);
    }
}