package com.klug.tp3crud;

import com.klug.tp3crud.domain.model.Cliente;
import com.klug.tp3crud.domain.model.Fornecedor;
import com.klug.tp3crud.domain.model.Pedido;
import com.klug.tp3crud.domain.model.Produto;
import com.klug.tp3crud.domain.repository.ClienteRepository;
import com.klug.tp3crud.domain.repository.FornecedorRepository;
import com.klug.tp3crud.domain.repository.PedidoRepository;
import com.klug.tp3crud.domain.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private Cliente cliente;
    private Fornecedor fornecedor;
    private Produto produto;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setNome("Cliente A");
        cliente.setEmail("cliente.a@example.com");
        cliente.setEndereco("123 Main St");
        cliente.setTelefone("1234567890");
        cliente = clienteRepository.save(cliente);

        fornecedor = new Fornecedor();
        fornecedor.setNome("Fornecedor A");
        fornecedor.setEmail("fornecedor.a@example.com");
        fornecedor.setEmpresa("Empresa A");
        fornecedor.setTelefone("1234567890");
        fornecedor = fornecedorRepository.save(fornecedor);

        produto = new Produto();
        produto.setNome("Produto A");
        produto.setPreco(100.0);
        produto.setQuantidade(10);
        produto = produtoRepository.save(produto);
    }

    @Test
    public void testCreatePedido() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String dateStr = dateFormat.format(new Date());
        String pedidoJson = String.format("{\"dataPedido\":\"%s\",\"cliente\":{\"id\":%d},\"fornecedor\":{\"id\":%d},\"produto\":{\"id\":%d}}",
                dateStr, cliente.getId(), fornecedor.getId(), produto.getId());

        mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pedidoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cliente.id").value(cliente.getId()));
    }

    @Test
    public void testGetAllPedidos() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(new Date());
        pedido.setCliente(cliente);
        pedido.setFornecedor(fornecedor);
        pedido.setProduto(produto);
        pedidoRepository.save(pedido);

        mockMvc.perform(get("/api/pedidos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cliente.id").value(cliente.getId()));
    }

    @Test
    public void testUpdatePedido() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(new Date());
        pedido.setCliente(cliente);
        pedido.setFornecedor(fornecedor);
        pedido.setProduto(produto);
        pedido = pedidoRepository.save(pedido);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String dateStr = dateFormat.format(new Date());
        String updatedPedidoJson = String.format("{\"dataPedido\":\"%s\",\"cliente\":{\"id\":%d},\"fornecedor\":{\"id\":%d},\"produto\":{\"id\":%d}}",
                dateStr, cliente.getId(), fornecedor.getId(), produto.getId());

        mockMvc.perform(put("/api/pedidos/" + pedido.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedPedidoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cliente.id").value(cliente.getId()));
    }

    @Test
    public void testDeletePedido() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(new Date());
        pedido.setCliente(cliente);
        pedido.setFornecedor(fornecedor);
        pedido.setProduto(produto);
        pedido = pedidoRepository.save(pedido);

        mockMvc.perform(delete("/api/pedidos/" + pedido.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}