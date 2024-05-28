package com.klug.tp3crud;

import com.klug.tp3crud.domain.model.Produto;
import com.klug.tp3crud.domain.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void testCreateProduto() throws Exception {
        String produtoJson = "{\"nome\":\"Produto A\",\"preco\":100.0,\"quantidade\":10}";

        mockMvc.perform(post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Produto A"));
    }

    @Test
    public void testGetAllProdutos() throws Exception {
        Produto produto = new Produto();
        produto.setNome("Produto A");
        produto.setPreco(100.0);
        produto.setQuantidade(10);
        produtoRepository.save(produto);

        mockMvc.perform(get("/api/produtos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Produto A"));
    }

    @Test
    public void testUpdateProduto() throws Exception {
        Produto produto = new Produto();
        produto.setNome("Produto A");
        produto.setPreco(100.0);
        produto.setQuantidade(10);
        produto = produtoRepository.save(produto);

        String updatedProdutoJson = "{\"nome\":\"Produto B\",\"preco\":150.0,\"quantidade\":20}";

        mockMvc.perform(put("/api/produtos/" + produto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProdutoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Produto B"));
    }

    @Test
    public void testDeleteProduto() throws Exception {
        Produto produto = new Produto();
        produto.setNome("Produto A");
        produto.setPreco(100.0);
        produto.setQuantidade(10);
        produto = produtoRepository.save(produto);

        mockMvc.perform(delete("/api/produtos/" + produto.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}