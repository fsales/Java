package main.mapeamento;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.estudo.hibernate.mapeamento.Usuario;

public class UsuarioTeste {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("livraria");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		Usuario usuario = new Usuario();
		usuario.setEmail("contato@gmail.com");
		usuario.setDataCadastro(Calendar.getInstance());
		
		manager.persist(usuario);
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
}
