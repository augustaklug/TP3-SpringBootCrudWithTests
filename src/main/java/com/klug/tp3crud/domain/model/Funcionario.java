package com.klug.tp3crud.domain.model;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {
    private String cargo;
    private double salario;
}
