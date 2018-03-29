package br.com.evento.core.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.evento.core.dominio.Estado;
import br.com.evento.core.servico.EstadoRN;

public class EstadoRNTest {
	static EntityManager manager;

	@Before
	public void setUp() throws Exception {
		manager = Persistence.createEntityManagerFactory("event")
				.createEntityManager();

	}

	@After
	public void tearDown() throws Exception {

	}

	//@Test
	public void testSalvar() {

		try {
			manager.getTransaction().begin();
			Estado e = new Estado();
			e.setEstado("Minas Gerais");
			e.setUf("mg");

			EstadoRN rn = new EstadoRN(manager);

			rn.salvar(e);
			manager.getTransaction().commit();
			manager.close();
		} catch (Exception e) {

			fail(e.getMessage());
		}
	}

	@Test
	public void testRecuperarTodos() {
		try {
			manager.getTransaction().begin();
			EstadoRN rn = new EstadoRN(manager);
			List<Estado> lista = rn.recuperarTodos();
			lista.get(0).getEstado();
		} catch (Exception e) {
			e.getMessage();
			fail(e.getMessage());
		}
	}

	//@Test
	public void testRecuperarPorId() {
		try {
			manager.getTransaction().begin();
			EstadoRN rn = new EstadoRN(manager);
			Estado e = rn.recuperarPorId(5);
			assertNotNull(e.getUf());

			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	//@Test
	public void testDeletar() {
		try {
			manager.getTransaction().begin();
			Estado estado= new Estado();
			estado.setEstado("");
			estado.setId(7);
			EstadoRN rn = new EstadoRN(manager);
			
			rn.deletar(estado);

			manager.getTransaction().commit();
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
