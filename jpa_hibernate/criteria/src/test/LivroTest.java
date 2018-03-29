package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import modelo.Autor;
import modelo.Livro;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LivroTest {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("cr");

	@Before
	public void setUp() {
		
		
	}

	@After
	public void tearDown(){

	}
	//@Test
	public void salvar() {
		EntityManager manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();

			Livro livro1 = new Livro();
			livro1.setNome("The Battle for Your Mind");
			livro1.setPreco(20.6);
			manager.persist(livro1);

			Livro livro2 = new Livro();
			livro2.setNome("Differentiate or Die");
			livro2.setPreco(15.8);
			manager.persist(livro2);

			Livro livro3 = new Livro();
			livro3.setNome("How to Transform Your Ideas");
			livro3.setPreco(32.7);
			manager.persist(livro3);

			Livro livro4 = new Livro();
			livro4.setNome("Digital Fortress");
			livro4.setPreco(12.9);
			manager.persist(livro4);

			Livro livro5 = new Livro();
			livro5.setNome("Marketing in an Era of Competition, Change and Crisis");
			livro5.setPreco(26.8);
			manager.persist(livro5);

			Autor autor1 = new Autor();
			autor1.setNome("Patrick Cullen");
			autor1.getLivros().add(livro2);
			autor1.getLivros().add(livro4);
			manager.persist(autor1);

			Autor autor2 = new Autor();
			autor2.setNome("Fraser Seitel");
			autor2.getLivros().add(livro3);
			manager.persist(autor2);

			Autor autor3 = new Autor();
			autor3.setNome("Al Ries");
			autor3.getLivros().add(livro1);
			manager.persist(autor3);

			Autor autor4 = new Autor();
			autor4.setNome("Jack Trout");
			autor4.getLivros().add(livro1);
			autor4.getLivros().add(livro2);
			autor4.getLivros().add(livro5);
			manager.persist(autor4);

			Autor autor5 = new Autor();
			autor5.setNome("Steve Rivkin");
			autor5.getLivros().add(livro2);
			autor5.getLivros().add(livro3);
			autor5.getLivros().add(livro5);
			manager.persist(autor5);

			manager.getTransaction().commit();

			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	//@Test
	public void testeCriteria(){
		EntityManager manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			CriteriaBuilder cb = manager.getCriteriaBuilder();
			CriteriaQuery<Livro> c = cb.createQuery(Livro.class);
			Root<Livro> l = c.from(Livro.class);
			c.select(l);
			
			
			
			TypedQuery<Livro> query = manager.createQuery(c);
			List<Livro> livros = query.getResultList();
			assertNotNull(livros);
			String msg="";
			for(Livro livro : livros){
				msg +="\nLivro: "+livro.getNome()+"\nPreço: "+livro.getPreco()+"\n";
			}
			JOptionPane.showMessageDialog(null, msg);
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	//@Test
	public void criteriaObjetosComuns(){
		EntityManager manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			CriteriaBuilder cb = manager.getCriteriaBuilder();
			CriteriaQuery<String> c = cb.createQuery(String.class);
			Root<Livro> livro = c.from(Livro.class);
			c.select(livro.<String>get("nome"));
			TypedQuery<String> query = manager.createQuery(c);
			List<String> nomes = query.getResultList();
			assertNotNull(nomes);
			String msg="";
			for(String nome:nomes){
				msg+="Nome: "+nome+"\n";
			}
			manager.close();
			JOptionPane.showMessageDialog(null, msg);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	//@Test
	public void mediaValorLivros(){
		EntityManager manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			
			CriteriaBuilder cb = manager.getCriteriaBuilder();
			CriteriaQuery<Double> c = cb.createQuery(Double.class);
			Root<Livro> l = c.from(Livro.class);
			c.select(cb.avg(l.<Double>get("preco")));
			
			TypedQuery<Double> query = manager.createQuery(c);
			Double media = query.getSingleResult();
			JOptionPane.showMessageDialog(null, "A média dos valores é R$"+media);
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void ConsultaNomePrecoDosLivros(){
		EntityManager manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			CriteriaBuilder cb = manager.getCriteriaBuilder();
			CriteriaQuery<Tuple> c = cb.createQuery(Tuple.class);
			Root<Livro> l = c.from(Livro.class);
			c.multiselect(l.<String>get("nome").alias("livro.nome"),l.<Double>get("preco").alias("livro.preco"));
			
			TypedQuery<Tuple> query = manager.createQuery(c);
			List<Tuple> resultado = query.getResultList();
			String msg="";
			for(Tuple registro: resultado){
				msg+="\nLivro: "+registro.get("livro.nome")+"\nPreço: "+registro.get("livro.preco")+"\n";
			}
			JOptionPane.showMessageDialog(null, msg);
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void TestePredicate(){
		EntityManager manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			CriteriaBuilder cb = manager.getCriteriaBuilder();
			CriteriaQuery<Livro> c = cb.createQuery(Livro.class);
			Root<Livro> l = c.from(Livro.class);
			c.select(l);
			
			Predicate predicate = cb.equal(l.get("nome"), "The Battle for Your Mind");
			c.where(predicate);
			TypedQuery<Livro> query = manager.createQuery(c);
			List<Livro> livros = query.getResultList();
			String msg="";
			for(Livro livro:livros){
				msg+="\nId: "+livro.getId()+"\nNome: "+livro.getNome()+"\nPreço: "+livro.getPreco()+"\n";
			}
			JOptionPane.showMessageDialog(null, msg);
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
}
