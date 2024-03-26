package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		
		ContatoDAO contatoDao = new ContatoDAO();
		
		Contato contato = new Contato();
		contato.setNome("Maria Gabriela");
		contato.setIdade(55);
		contato.setDataCadastro(new Date());
		
		//contatoDao.save(contato);

		// atualiza o contato
		
		Contato c1 = new Contato();
		c1.setNome("Maria Gabriela Dias Orlando");
		c1.setIdade(37);
		c1.setDataCadastro(new Date());
		c1.setId(1); 
		
		//contatoDao.update(c1);
		
		//Deletar o contato pelo seu número de ID
		
		contatoDao.deleteByID(1);
		
		
		//visualização de todoss os registros do banco de dados
 		
		
		for(Contato c : contatoDao.getContatos()) {
			System.out.println("Contato: "+c.getNome());
		}
	}

}
