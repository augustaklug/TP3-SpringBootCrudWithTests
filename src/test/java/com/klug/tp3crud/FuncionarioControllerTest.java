package com.klug.tp3crud;

import com.klug.tp3crud.domain.model.Funcionario;
import com.klug.tp3crud.domain.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    public void setUp() {
        funcionarioRepository.deleteAll(); // Limpa o repositório antes de cada teste

        Funcionario funcionarioA = new Funcionario();
        funcionarioA.setNome("Funcionario A");
        funcionarioA.setEmail("funcionario.a@example.com");
        funcionarioA.setCargo("Desenvolvedor");
        funcionarioA.setSalario(5000.0);
        funcionarioRepository.save(funcionarioA);

        Funcionario funcionarioB = new Funcionario();
        funcionarioB.setNome("Funcionario B");
        funcionarioB.setEmail("funcionario.b@example.com");
        funcionarioB.setCargo("Gerente");
        funcionarioB.setSalario(7000.0);
        funcionarioRepository.save(funcionarioB);
    }

    @Test
    public void testGetAllFuncionarios() throws Exception {
        mockMvc.perform(get("/api/funcionarios"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Funcionario A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("Funcionario B"));
    }

    @Test
    public void testCreateFuncionario() throws Exception {
        String funcionarioJson = "{\"nome\":\"Funcionario C\",\"email\":\"funcionario.c@example.com\",\"cargo\":\"Analista\",\"salario\":6000.0}";

        mockMvc.perform(post("/api/funcionarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(funcionarioJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Funcionario C"));
    }

    @Test
    public void testUpdateFuncionario() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionario D");
        funcionario.setEmail("funcionario.d@example.com");
        funcionario.setCargo("Tester");
        funcionario.setSalario(4000.0);
        funcionario = funcionarioRepository.save(funcionario);

        String updatedFuncionarioJson = "{\"nome\":\"Funcionario E\",\"email\":\"funcionario.e@example.com\",\"cargo\":\"Tester\",\"salario\":4500.0}";

        mockMvc.perform(put("/api/funcionarios/" + funcionario.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedFuncionarioJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Funcionario E"));
    }

    @Test
    public void testDeleteFuncionario() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionario F");
        funcionario.setEmail("funcionario.f@example.com");
        funcionario.setCargo("Tester");
        funcionario.setSalario(4000.0);
        funcionario = funcionarioRepository.save(funcionario);

        mockMvc.perform(delete("/api/funcionarios/" + funcionario.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}