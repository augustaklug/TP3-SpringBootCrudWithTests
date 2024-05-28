package com.klug.tp3crud.presentation.controller;

import com.klug.tp3crud.domain.model.Cliente;
import com.klug.tp3crud.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController extends PessoaController<Cliente> {

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        super(clienteRepository);
    }
}
