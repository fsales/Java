package test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Produto;

import org.junit.Test;

public class ProdutoTest {
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("tp");
	@Test
	public void populaBanco() {
		EntityManager manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			for (int i =0; i<100;i++){
				Produto p = new Produto();
				p.setNome("produto" +i);
				p.setPreco(i *10.0);
				manager.persist(p);
			}
			manager.getTransaction().commit();
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	//@Test
	public void AumentaPreco(){
		EntityManager manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			Query query = manager.createQuery("update Produto p SET p.preco = p.preco * 1.1");
			query.executeUpdate();
			manager.getTransaction().commit();
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	//@Test
	public void RemoveProdutos(){
		EntityManager manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			Query query = manager.createQuery("DELETE Produto p WHERE p.preco <50");
			query.executeUpdate();
			manager.getTransaction().commit();
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
}
