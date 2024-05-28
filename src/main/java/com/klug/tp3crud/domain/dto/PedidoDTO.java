package com.klug.tp3crud.domain.dto;

import lombok.Data;

@Data
public class PedidoDTO {
    private Long id;
    private String dataPedido;
    private Long clienteId;
    private Long fornecedorId;
    private Long produtoId;
}