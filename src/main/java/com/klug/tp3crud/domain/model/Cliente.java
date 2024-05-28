package com.klug.tp3crud.model.entity;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {
    private String endereco;
    private String telefone;
}
