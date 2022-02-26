package com.italomaciel.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.italomaciel.cursomc.domain.Categoria;
import com.italomaciel.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class PraticamcApplication implements CommandLineRunner {
	// O CommandLineRunner permite ao iniciar a aplicação executar uma ação por meio de uma função auxiliar.
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PraticamcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório"); //O Id é gerado automaticamente pelo banco.

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2)); //Arrays.asList cria uma lista automaticamente.
		
	}

	
	
}
