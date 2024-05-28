package com.klug.tp3crud;

import com.klug.tp3crud.domain.model.Fornecedor;
import com.klug.tp3crud.domain.repository.FornecedorRepository;
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
public class FornecedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Test
    public void testCreateFornecedor() throws Exception {
        String fornecedorJson = "{\"nome\":\"Fornecedor A\",\"email\":\"fornecedor.a@example.com\",\"empresa\":\"Empresa A\",\"telefone\":\"1234567890\"}";

        mockMvc.perform(post("/api/fornecedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(fornecedorJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Fornecedor A"));
    }

    @Test
    public void testGetAllFornecedores() throws Exception {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("Fornecedor A");
        fornecedor.setEmail("fornecedor.a@example.com");
        fornecedor.setEmpresa("Empresa A");
        fornecedor.setTelefone("1234567890");
        fornecedorRepository.save(fornecedor);

        mockMvc.perform(get("/api/fornecedores"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Fornecedor A"));
    }

    @Test
    public void testUpdateFornecedor() throws Exception {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("Fornecedor A");
        fornecedor.setEmail("fornecedor.a@example.com");
        fornecedor.setEmpresa("Empresa A");
        fornecedor.setTelefone("1234567890");
        fornecedor = fornecedorRepository.save(fornecedor);

        String updatedFornecedorJson = "{\"nome\":\"Fornecedor B\",\"email\":\"fornecedor.b@example.com\",\"empresa\":\"Empresa B\",\"telefone\":\"0987654321\"}";

        mockMvc.perform(put("/api/fornecedores/" + fornecedor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedFornecedorJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Fornecedor B"));
    }

    @Test
    public void testDeleteFornecedor() throws Exception {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("Fornecedor A");
        fornecedor.setEmail("fornecedor.a@example.com");
        fornecedor.setEmpresa("Empresa A");
        fornecedor.setTelefone("1234567890");
        fornecedor = fornecedorRepository.save(fornecedor);

        mockMvc.perform(delete("/api/fornecedores/" + fornecedor.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}