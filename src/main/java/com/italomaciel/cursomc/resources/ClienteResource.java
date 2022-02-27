package com.italomaciel.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.italomaciel.cursomc.domain.Cliente;
import com.italomaciel.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	//Deve-se associar a um verbo de requisição HTTP para toda aplicação REST, o GET refere-se a obter os dados.
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		System.out.println("Meu ID: "+ id);
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
