package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import dominio.Pessoa;

public class PessoaTest {
	static EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("entity");

	@Test
	public void salvar() {
		EntityManager manager = factory.createEntityManager();
		try {

			System.out.println("\n --Teste Salvar--");
			// abrindo a transação
			manager.getTransaction().begin();

			// Objeto no estado New
			Pessoa p = new Pessoa();
			p.setNome("Fabio");
			p.setIdade(18);

			// objeto no estado managed
			manager.persist(p);

			// sincronizando e confirmando a tranzação
			manager.getTransaction().commit();

			assertNotNull(p.getId());
			System.out.println("Pessoa id: " + p.getId());

		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			manager.close();
		}
	}

	@Test
	public void testFind() {
		EntityManager manager = factory.createEntityManager();
		try {

			manager.getTransaction().begin();
			Pessoa p = manager.find(Pessoa.class, 1L);
			assertNotNull(p);
			System.out.println("\n --Teste Find--");
			System.out.println("id: " + p.getId());
			System.out.println("nome: " + p.getNome());
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			manager.close();
		}
	}

	@Test
	public void testManaged() {
		EntityManager manager = factory.createEntityManager();
		try {
			System.out.println("\n --Teste Managed--");
			manager.getTransaction().begin();
			
			// objeto no estado managed
			Pessoa p = manager.find(Pessoa.class, 1L);
			System.out.println("estado do objeto: "+manager.contains(p));
			assertNotNull(p);
			// alterando o conteudo do objeto
			p.setNome("marcelo");

			// sincronizando e confirmando a transacao
			manager.getTransaction().commit();
			System.out.println("id: "+p.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			manager.close();
		}
	}

	@Test
	public void testDetached(){
		EntityManager manager = factory.createEntityManager();
		try {
			System.out.println("\n--teste detached--");
			manager.getTransaction().begin();
			
			//objeto no estado managed
			Pessoa p = new Pessoa();
			
			//objeto no estado detached
			manager.detach(p);
			
			//alterando o conteudo do objeto
			p.setNome("marcos");
			System.out.println("id: "+p.getId());
			//sincronizando e confirmando a transacao
			manager.getTransaction().commit();
			manager.close();
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testeFindEager(){
		EntityManager manager = factory.createEntityManager();
		try {
			System.out.println("\nTeste findEager");
			System.out.println("---Chamando o find---");
			Pessoa p = manager.find(Pessoa.class, 1L);
			System.out.println("---fez o select---");
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testeGetReferenceLazy(){
		EntityManager manager = factory.createEntityManager();
		try {
			System.out.println("\nTeste getReferenceLazy");
			System.out.println("---Chamando o getreference---");
			Pessoa p = manager.getReference(Pessoa.class, 1L);
			
			System.out.println("---nao fez o select---");
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
