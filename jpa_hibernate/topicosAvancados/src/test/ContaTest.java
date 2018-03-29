package test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Conta;

import org.junit.Test;

public class ContaTest {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("tp");
	//@Test
	public void adicionar() {
		EntityManager manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			Conta c = new Conta();
			c.setSaldo(200);
			manager.persist(c);
			manager.getTransaction().commit();
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testaAcessoConcorrente(){
		EntityManager manager1 = factory.createEntityManager();
		EntityManager manager2 = factory.createEntityManager();
		
		try {
			manager1.getTransaction().begin();
			manager2.getTransaction().begin();
			
			Conta conta1= manager1.find(Conta.class, 1L);
			conta1.setSaldo(conta1.getSaldo()+500);
			
			Conta conta2 = manager2.find(Conta.class, 1L);
			conta2.setSaldo(conta2.getSaldo()-500);
			manager1.getTransaction().commit();
			manager2.getTransaction().commit();
			manager1.close();
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
