package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;


public class ContatoDAO {
	
	
	/*
	 * CRUD =
	 * CREATE
	 * READ
	 * UPDATE
	 * DELETE
	 */
	
	public void save(Contato contato) {

		
		
		String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES  (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		
		try {
			
			// cria uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySql();
			
			// Criamos uma preparedStatement, para executar uma query
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			//executar a query
			pstm.execute();
			
			System.out.println("Contato salvo com sucesso!");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			// fecha as conexões 
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void update (Contato contato) {
		String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ?"+
		 "WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySql();
			
			// Cria a classe para executar a query
			pstm =  (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			pstm.setInt(4, contato.getId());
			
			//Executar a query
			
			pstm.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	} 
	
	
	public void deleteByID (int id) {
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
				
		try{
			conn = ConnectionFactory.createConnectionToMySql();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null) {
				conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("finally")
	public List<Contato> getContatos(){
		
		String sql = "SELECT * FROM contatos ";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		//Classe que vai recuperar os dados do banco
		
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySql();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
					
					rset = pstm.executeQuery();
					
					while (rset.next()) {
						Contato contato = new Contato();
						
						//Recuperar o id
						
						contato.setId(rset.getInt("id"));
						
						// nome 
						
						contato.setNome(rset.getString("nome"));
						
						//idade
						
						contato.setIdade(rset.getInt("idade"));
						
						//data
						
						contato.setDataCadastro(rset.getDate("datacadastro"));
						
						
						contatos.add(contato);
						
					}
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						try {
						if(rset!=null) {
							rset.close();
						}
						
						if (pstm!=null) {
							pstm.close();
						}
						
						if (conn!=null) {
							conn.close();
						}
					}catch(final Exception e) {
						e.printStackTrace();					
					}
					
					
			
		
			return contatos;
			
					}
	}
	
}
		
		

		
			

	
