package main.relacionamento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.estudo.hibernate.relacionamento.manyToMany.Autor;
import br.com.estudo.hibernate.relacionamento.manyToMany.Livro;

public class LivroAutorMain {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("livraria");
		EntityManager m = factory.createEntityManager();
		m.getTransaction().begin();

		Autor a = new Autor();
		a.setNome("Rafael");

		Livro l = new Livro();
		l.setNome("JPA@");
		l.getAutores().add(a);

		m.persist(a);
		m.persist(l);
		
		m.getTransaction().commit();
		m.close();
		factory.close();
	}

}
