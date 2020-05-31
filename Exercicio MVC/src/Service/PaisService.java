package Service;

import java.util.ArrayList;

import DAO.PaisesDAO;
import Modelo.Pais;

public class PaisService {
	
	PaisesDAO dao = new PaisesDAO();
	
	public int criar(Pais paises) {
		return dao.criar(paises);
	}
	
	public void atualizar(Pais paises) {
		 dao.atualizar(paises);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Pais carregar(int id){
		 return dao.carregar(id);
	}
	
	public void menorArea(Pais paises) {
		dao.menorArea(paises);
	}
	
	public long maiorPopulacao(long populacao) {
		return dao.maiorPopulacao(populacao);
	}
	
	
	public Pais[] tresPaises() {
		return dao.tresPaises();
		
	}
	public ArrayList<Pais> listarPaises() {
		return dao.listarTodos();
		
	}
	
	public ArrayList<Pais> listarPaises(String chave) {
		return dao.listarTodos(chave);
		
	}


}