package com.klug.tp3crud.domain.model;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fornecedores")
public class Fornecedor extends Pessoa {
    private String empresa;
    private String telefone;
}
