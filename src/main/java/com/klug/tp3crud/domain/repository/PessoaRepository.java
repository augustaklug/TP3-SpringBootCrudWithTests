package com.klug.tp3crud.domain.repository;

import com.klug.tp3crud.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PessoaRepository<T extends Pessoa> extends JpaRepository<T, Long> {
}
