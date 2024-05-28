package com.klug.tp3crud.domain.model;

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
