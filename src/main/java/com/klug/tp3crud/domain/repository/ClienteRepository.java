package com.klug.tp3crud.domain.repository;

import com.klug.tp3crud.domain.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends PessoaRepository<Cliente> {
}
