package com.klug.tp3crud.domain.repository;

import com.klug.tp3crud.domain.model.Funcionario;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends PessoaRepository<Funcionario> {
}

