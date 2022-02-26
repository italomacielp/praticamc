package com.italomaciel.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.italomaciel.cursomc.domain.Categoria;
import com.italomaciel.cursomc.domain.Produto;
import com.italomaciel.cursomc.repositories.CategoriaRepository;
import com.italomaciel.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class PraticamcApplication implements CommandLineRunner {
	// O CommandLineRunner permite ao iniciar a aplicação executar uma ação por meio de uma função auxiliar.
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PraticamcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório"); //O Id é gerado automaticamente pelo banco.

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2)); //Aqui as categorias estão associadas aos produtos instanceados.
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2)); //Arrays.asList cria uma lista automaticamente.
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
	}

	
	
}
