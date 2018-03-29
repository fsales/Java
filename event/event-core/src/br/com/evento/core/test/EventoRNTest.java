package br.com.evento.core.test;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.evento.core.dominio.Cidade;
import br.com.evento.core.dominio.Contato;
import br.com.evento.core.dominio.Endereco;
import br.com.evento.core.dominio.Estado;
import br.com.evento.core.dominio.Evento;
import br.com.evento.core.dominio.Usuario;
import br.com.evento.core.servico.CidadeRN;
import br.com.evento.core.servico.EstadoRN;
import br.com.evento.core.servico.EventoRN;
import br.com.evento.core.servico.UsuarioRN;

public class EventoRNTest {

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

			Estado es = new Estado();
			es.setEstado("Distrito Federal");
			es.setUf("DF");
			EstadoRN er = new EstadoRN(manager);
			Estado esretorno= er.salvar(es);

			Cidade c = new Cidade();
			c.setCidade("Cruzeiro");
			c.setEstado(esretorno);
			CidadeRN cr = new CidadeRN(manager);
			Cidade cRetorno=cr.salvar(c);

			Evento e = new Evento();
			// adicionando cidade
			
			e.setCidade(cRetorno);
			e.setDescricao("evento de software livre");
			e.setNome("ESL");

			// adicionando contato
			Contato contato = new Contato();
			contato.setCelular("9999-0990");
			contato.setEmail("esl@gmail.com");
			contato.setFax("8888-0000");
			contato.setTelefone("8877-8888");
			e.setContato(contato);

			// adicionando endereco;
			Endereco endereco = new Endereco();
			endereco.setCep("7120003");
			endereco.setComplemento("perto posto");
			endereco.setLogradouro("rua sem numero");
			endereco.setNumero(22);
			e.setEndereco(endereco);
			// adicionando data de inicio e fim
			Calendar calendar = Calendar.getInstance();
			calendar.set(2012, 3, 12);

			e.setDataInicio(calendar.getTime());
			calendar.set(2012, 3, 21);
			e.setDataTermino(calendar.getTime());

			EventoRN rn = new EventoRN(manager);
			rn.salvar(e);

			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().rollback();
			fail(e.getMessage());
		} finally {
			manager.close();
		}

	}

	//@Test
	public void testRecuperarPorId() {
		try {
			manager.getTransaction().begin();

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			manager.close();
		}

	}

	@Test
	public void testRecuperarTodos() {
		try {
			manager.getTransaction().begin();
			EventoRN eventoRN = new EventoRN(manager);
			//Usuario usuario, Date dataInicio,Date dataFim
			Usuario u = new Usuario();
			UsuarioRN rnusu = new UsuarioRN(manager);
			u = rnusu.recuperarPorId(1);
			List<Evento> eventos = eventoRN.recuperarTodos(u,true);
			System.out.println("Eventos "+ eventos.size());

		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			manager.close();
		}

	}

	//@Test
	public void testDeletar() {
		try {
			manager.getTransaction().begin();

		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			manager.close();
		}

	}

}
