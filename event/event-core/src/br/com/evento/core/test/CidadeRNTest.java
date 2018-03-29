package br.com.evento.core.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.evento.core.dominio.Cidade;
import br.com.evento.core.dominio.Estado;
import br.com.evento.core.servico.CidadeRN;
import br.com.evento.core.servico.EstadoRN;

public class CidadeRNTest {

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
			e.setEstado("Distrito Federal");
			e.setUf("DF");
			EstadoRN rnEstado = new EstadoRN(manager);
			rnEstado.salvar(e);
			

			Cidade c = new Cidade();
			c.setCidade("Lago Sul");
			c.setEstado(e);

			CidadeRN rnCidade = new CidadeRN(manager);
			rnCidade.salvar(c);
			

			manager.getTransaction().commit();
			manager.close();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			manager.close();
			fail(e.getMessage());
		}
	}

	@Test
	public void testconsultaCidades(){
		try {
			manager.getTransaction().begin();
			CidadeRN rn = new CidadeRN(manager);
			
			EstadoRN esrn = new EstadoRN(manager);
			Estado estado = new Estado();
			estado = esrn.recuperarPorId(1);
			//estado.setId(1);
			//estado.setEstado("Distrito Federal");
			//estado.setUf("DF");
			
			List<Cidade> cidades = rn.consultaCidades(estado);
			System.out.println("cidades: "+cidades.get(0).getEstado().getUf());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	//@Test
	public void testRecuperarTodos() {
		try {
			manager.getTransaction().begin();
			CidadeRN rn = new CidadeRN(manager);
			List<Cidade> cidades = rn.recuperarTodos();
			assertNotNull(cidades);
			manager.close();
		} catch (Exception e) {
			manager.close();
			fail(e.getMessage());
		}
	}

	//@Test
	public void testRecuperarPorId() {
		try {
			manager.getTransaction().begin();
			CidadeRN rn = new CidadeRN(manager);
			Cidade c = new Cidade();
			
			List<Cidade> cidades = new ArrayList<Cidade>();
			cidades = rn.recuperarTodos();
			
			c = rn.recuperarPorId(cidades.get(0).getId());
			assertNotNull(c.getEstado());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	//@Test
	public void testDeletar() {
		try {
			manager.getTransaction().begin();
			
			
			
			
			Cidade cidade = new Cidade();
			cidade.setCidade("Lago Sul");
			cidade.setId(5);
			
			
			CidadeRN rn = new CidadeRN(manager);
			rn.deletar(cidade);
			
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
