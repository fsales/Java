package main.relacionamento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.estudo.hibernate.relacionamento.oneToOne.Estado;
import br.com.estudo.hibernate.relacionamento.oneToOne.Governador;

public class GovernadorEstadoMain {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("livraria");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		Governador g = new Governador();
		g.setNome("Marcos");
		
		Estado e = new Estado();
		e.setNome("São Paulo");
		e.setGovernador(g);
		
		manager.persist(g);
		manager.persist(e);
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
}
