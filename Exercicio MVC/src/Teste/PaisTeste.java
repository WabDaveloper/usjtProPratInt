package Teste;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import Modelo.Pais;
import Service.PaisService;



public class PaisTeste {
	
	Pais paises,copia;
	PaisService paisesService;
	static int id = 0;
	static long populacao;
	static double area;
	static String nome;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("\nsetup");
		paises = new Pais();
		paises.setId(id);
		paises.setNome("Australia");
		paises.setPopulacao(25080200);
		paises.setArea(7692024);
		
		copia = new Pais();
		copia.setId(id);
		copia.setNome("Australia");
		copia.setPopulacao(25080200);
		copia.setArea(7692024);
		
		paisesService = new PaisService();
		System.out.println(paises);
		System.out.println(copia);
		System.out.println(id);
	}

	@Test
	public void test00Carregar() {
		System.out.println("\ncarregar");

		Pais fixture = new Pais();
		fixture.setId(1);
		fixture.setNome("Brazil");
		fixture.setPopulacao(1246700);
		fixture.setArea(9.90);
		PaisService newPaisesService = new PaisService();
		Pais novo = newPaisesService.carregar(1);
		assertEquals("testa inclusao", novo, fixture);
	}
	@Test
	public void test01Criar() {
		System.out.println("\ncriar");
		id = paisesService.criar(paises);
		System.out.println(id);
		copia.setId(id);
		System.out.println("--------------------------------------------");
		System.out.println(paises);
		System.out.println(copia);
		System.out.println("--------------------------------------------");
		assertEquals("testa criacao", paises, copia);
	}
	

	@Test
	public void test02Atualizar() {
		System.out.println("\natualizar");
		paises.setArea(9.9);
		copia.setArea(9.9);
		paisesService.atualizar(paises);
		paises = paisesService.carregar(copia.getId());
		System.out.println("--------------------------------------------");
		System.out.println(paises);
		System.out.println(copia);
		System.out.println("--------------------------------------------");
		assertEquals("testa atualizacao", paises, copia);
	
	}

	@Test
	public void test03Excluir() {
		System.out.println("\nexcluir");
		copia.setId(0);
		copia.setNome(null);
		copia.setPopulacao(0);
		copia.setArea(0.0);
		paisesService.excluir(id);
		paises = paisesService.carregar(id);
		System.out.println("--------------------------------------------");
		System.out.println(paises);
		System.out.println(copia);
		System.out.println("--------------------------------------------");
		assertEquals("testa exclusao", paises, copia);
	}
	
	@Test
	public void test04menorArea() {
		System.out.println("\nMenor Area");
//para funcionar o cliente 1 deve ter sido carregado no banco por forassss
		paisesService.menorArea(paises);
		area = paises.getArea();
		nome = paises.getNome();
		copia.setArea(area);
		copia.setNome(nome);
		System.out.println("--------------------------------------------");
		System.out.println(paises);
		System.out.println(copia);
		System.out.println("--------------------------------------------");
		assertEquals("testa a menor area", copia, paises);
	}
	
	@Test
	public void test04maiorPopulacao() {
		System.out.println("\nMaiorPopulação");
		paisesService.maiorPopulacao(paises.getPopulacao());
		populacao = paises.getPopulacao();
		copia.setPopulacao(populacao);
		System.out.println("--------------------------------------------");
		System.out.println(paises);
		System.out.println(copia);
		System.out.println("--------------------------------------------");
		assertEquals("Testa o menor valor da tabela", copia, paises);
	}
	
	@Test
	public void test05testarVetor() {
		System.out.println("\nVetor 3 Paises");
		Pais[] vetor = paisesService.tresPaises();
		for (Pais paises : vetor) {
			id = paises.getId();
			area = paises.getArea();
			nome = paises.getNome();
			populacao = paises.getPopulacao();
			copia.setId(id);
			copia.setArea(area);
			copia.setNome(nome);
			copia.setPopulacao(populacao);
			System.out.println("--------------------------------------------");
			System.out.println(paises);
			System.out.println(copia);
			System.out.println("--------------------------------------------");
			assertEquals("Testa o menor valor da tabela", copia, paises);
		}
	}
}
