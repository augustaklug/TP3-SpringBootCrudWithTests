package com.klug.tp3crud.presentation.controller;

import com.klug.tp3crud.domain.model.Pedido;
import com.klug.tp3crud.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
        Pedido createdPedido = pedidoRepository.save(pedido);
        return new ResponseEntity<>(createdPedido, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido pedidoDetails) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido not found for this id :: " + id));

        pedido.setDataPedido(pedidoDetails.getDataPedido());
        pedido.setCliente(pedidoDetails.getCliente());
        pedido.setFornecedor(pedidoDetails.getFornecedor());
        pedido.setProduto(pedidoDetails.getProduto());

        final Pedido updatedPedido = pedidoRepository.save(pedido);
        return new ResponseEntity<>(updatedPedido, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido not found for this id :: " + id));

        pedidoRepository.delete(pedido);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}