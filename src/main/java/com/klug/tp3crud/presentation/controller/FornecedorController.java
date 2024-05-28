package com.klug.tp3crud.presentation.controller;

import com.klug.tp3crud.domain.model.Fornecedor;
import com.klug.tp3crud.domain.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController extends PessoaController<Fornecedor> {

    @Autowired
    public FornecedorController(FornecedorRepository fornecedorRepository) {
        super(fornecedorRepository);
    }
}