package com.klug.tp3crud.presentation.controller;

import com.klug.tp3crud.domain.model.Funcionario;
import com.klug.tp3crud.domain.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController extends PessoaController<Funcionario> {

    @Autowired
    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        super(funcionarioRepository);
    }
}
