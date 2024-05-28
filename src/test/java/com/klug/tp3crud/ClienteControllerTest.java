package com.klug.tp3crud;

import com.klug.tp3crud.domain.model.Cliente;
import com.klug.tp3crud.domain.repository.ClienteRepository;
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
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testCreateCliente() throws Exception {
        String clienteJson = "{\"nome\":\"John Doe\",\"email\":\"john.doe@example.com\",\"endereco\":\"123 Main St\",\"telefone\":\"1234567890\"}";

        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clienteJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("John Doe"));
    }

    @Test
    public void testGetAllClientes() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("John Doe");
        cliente.setEmail("john.doe@example.com");
        cliente.setEndereco("123 Main St");
        cliente.setTelefone("1234567890");
        clienteRepository.save(cliente);

        mockMvc.perform(get("/api/clientes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("John Doe"));
    }

    @Test
    public void testUpdateCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("John Doe");
        cliente.setEmail("john.doe@example.com");
        cliente.setEndereco("123 Main St");
        cliente.setTelefone("1234567890");
        cliente = clienteRepository.save(cliente);

        String updatedClienteJson = "{\"nome\":\"Jane Doe\",\"email\":\"jane.doe@example.com\",\"endereco\":\"456 Elm St\",\"telefone\":\"0987654321\"}";

        mockMvc.perform(put("/api/clientes/" + cliente.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedClienteJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Jane Doe"));
    }

    @Test
    public void testDeleteCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("John Doe");
        cliente.setEmail("john.doe@example.com");
        cliente.setEndereco("123 Main St");
        cliente.setTelefone("1234567890");
        cliente = clienteRepository.save(cliente);

        mockMvc.perform(delete("/api/clientes/" + cliente.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}