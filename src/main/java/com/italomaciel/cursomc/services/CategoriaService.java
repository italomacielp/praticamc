package com.italomaciel.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.italomaciel.cursomc.domain.Categoria;
import com.italomaciel.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	//Essa anotação é responsável em instanciar um repositório.
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
}
