package com.italomaciel.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.italomaciel.cursomc.domain.Pedido;
import com.italomaciel.cursomc.repositories.PedidoRepository;
import com.italomaciel.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	//Essa anotação é responsável em instanciar um repositório.
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
}
