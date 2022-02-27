package com.italomaciel.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.italomaciel.cursomc.domain.Categoria;
import com.italomaciel.cursomc.domain.Cidade;
import com.italomaciel.cursomc.domain.Cliente;
import com.italomaciel.cursomc.domain.Endereco;
import com.italomaciel.cursomc.domain.Estado;
import com.italomaciel.cursomc.domain.Pagamento;
import com.italomaciel.cursomc.domain.PagamentoComBoleto;
import com.italomaciel.cursomc.domain.PagamentoComCartao;
import com.italomaciel.cursomc.domain.Pedido;
import com.italomaciel.cursomc.domain.Produto;
import com.italomaciel.cursomc.domain.enums.EstadoPagamento;
import com.italomaciel.cursomc.domain.enums.TipoCliente;
import com.italomaciel.cursomc.repositories.CategoriaRepository;
import com.italomaciel.cursomc.repositories.CidadeRepository;
import com.italomaciel.cursomc.repositories.ClienteRepository;
import com.italomaciel.cursomc.repositories.EnderecoRepository;
import com.italomaciel.cursomc.repositories.EstadoRepository;
import com.italomaciel.cursomc.repositories.PagamentoRepository;
import com.italomaciel.cursomc.repositories.PedidoRepository;
import com.italomaciel.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class PraticamcApplication implements CommandLineRunner {
	// O CommandLineRunner permite ao iniciar a aplicação executar uma ação por meio de uma função auxiliar.
	
	/*Referência cíclica, como as duas classes categoria e produto estão correlacionadas, quando chama uma delas na aplicação 
	fica fazendo uma chamada uma pela outra de forma indefinida, gerando uma exceção.*/
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired 
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
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
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
	}

	
	
}
