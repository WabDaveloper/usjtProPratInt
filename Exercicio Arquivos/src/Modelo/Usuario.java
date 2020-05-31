package Modelo;

public class Usuario {
	
	private int id;
	private String login;
	private String nome;
	private String senha;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String toString() {
		return "[id= " + id + ", login= " + login + ", nome= " + nome + ", senha= " + senha + "]";
	}
	

}
