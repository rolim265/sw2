package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	
	//nome do usuário do mysql
	private static final String USERNAME = "root";
	
	//senha do banco
	private static final String PASSWORD = "";
	
	//Caminho do banco de dados, porta, nome do banco de dados
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

	/*
	 * Conexão do banco de dados
	 */
	
	public static Connection  createConnectionToMySql() throws SQLException, ClassNotFoundException {
		
		// Faz com que a classe seja carregada pela JVM
		
		Class.forName("com.mysql.jdbc.Driver");
		
		//cria a conexão com o banco de dados
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// Recuperar uma conexão com o banco de dados
		Connection con = createConnectionToMySql();
		
		//Testar se a conexão é nula
		if(con!=null) {
			System.out.println("Conexão obtida com sucesso");
			con.close();
		}
			
	}
	
	
	
}
