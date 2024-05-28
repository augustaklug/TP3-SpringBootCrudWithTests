package com.klug.tp3crud.model.entity;

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
