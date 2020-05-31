package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Pais;

public class PaisesDAO {

// MÉTODO CREATE //	

	public int criar(Pais paises) {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES(?,?,?)";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, paises.getNome());
			stm.setLong(2, paises.getPopulacao());
			stm.setDouble(3, paises.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";

			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) { //
				if (rs.next()) {
					paises.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paises.getId();
	}

	// MÉTODO READ //
	public Pais carregar(int id) {
		String sqlSelect = "SELECT nome, populacao, area From pais WHERE pais.id = ?";
		Pais paises = new Pais();
		paises.setId(id);
		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, paises.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					paises.setNome(rs.getString("nome"));
					paises.setPopulacao(rs.getLong("populacao"));
					paises.setArea(rs.getDouble("area"));
				} else {
					paises.setId(0);
					paises.setNome(null);
					paises.setPopulacao(0);
					paises.setArea(0);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return paises;
	}

	// MÉTODO UPDATE //

	public void atualizar(Pais paises) {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, paises.getNome());
			stm.setLong(2, paises.getPopulacao());
			stm.setDouble(3, paises.getArea());
			stm.setInt(4, paises.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// MÉTODO DELETE//

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM pais WHERE id=?";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// MINOR AREA //

	public void menorArea(Pais paises) {
		String sqlMenor = "SELECT  nome, area FROM pais WHERE area = (select MIN(area) FROM pais)LIMIT 1";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlMenor);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					paises.setNome(rs.getString("nome"));
					paises.setArea(rs.getDouble("area"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
	}

	// LARGER AREA //
	public long maiorPopulacao(Long l) {
		String sqlMaior = "SELECT  nome, populacao FROM pais WHERE populacao = (select Max(populacao) FROM pais)";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlMaior);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					rs.getString("nome");
					l = rs.getLong("populacao");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	// SELECT 3 COUNTRIES //

	public Pais[] tresPaises() {

		Pais paises = null;
		Pais[] vetor = new Pais[3];
		int i = 0;

		String sqltres = "SELECT * FROM pais LIMIT 3";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqltres);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Integer id = rs.getInt("id");
					String nome = rs.getString("nome");
					Long populacao = rs.getLong("populacao");
					Double area = rs.getDouble("area");

					paises = new Pais(id, nome, populacao, area);
					vetor[i++] = paises;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return vetor;
	}

	public ArrayList<Pais> listarTodos() {
		ArrayList<Pais> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao, area From paises.pais";
		Pais paises;

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();) {
			while (rs.next()) {
				paises = new Pais();
				paises.setId(rs.getInt("id"));
				paises.setNome(rs.getString("nome"));
				paises.setPopulacao(rs.getLong("populacao"));
				paises.setArea(rs.getDouble("area"));
				lista.add(paises);
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	

	public ArrayList<Pais> listarTodos(String chave) {
		Pais paises;
		ArrayList<Pais> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao, area From paises.pais where upper(nome) like ?";

		try (Connection conn = connectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, "%" + chave.toUpperCase() + "%");
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					paises = new Pais();
					paises.setId(rs.getInt("id"));
					paises.setNome(rs.getString("nome"));
					paises.setPopulacao(rs.getLong("populacao"));
					paises.setArea(rs.getDouble("area"));
					lista.add(paises);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
}
