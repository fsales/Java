package br.com.evento.core.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.evento.core.dominio.Usuario;
import br.com.evento.core.servico.UsuarioRN;
import br.com.evento.core.servico.exception.RNException;

public class UsuarioRNTest {
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
			Usuario u = new Usuario();
			u.setNome("fabio");
			u.setAtivo(true);
			u.setEmail("fabio@gmail.com");
			u.setNascimento(Calendar.getInstance().getTime());
			u.getPermissoes().add("administrador");
			u.setSenha("123");

			UsuarioRN rn = new UsuarioRN(manager);
			rn.salvar(u);
			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().rollback();

			fail(e.getMessage());
		} finally {
			manager.close();
		}
	}

	@Test
	public void testRecuperarPorId() {
		try {
			manager.getTransaction().begin();

			UsuarioRN rn = new UsuarioRN(manager);
			Usuario u = rn.recuperarPorId(1);
			assertNotNull(u.getNome());

		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			manager.close();
		}
	}

	@Test
	public void testRecuperarTodos() {
		try {
			manager.getTransaction().begin();

			UsuarioRN rn = new UsuarioRN(manager);
			List<Usuario> usuarios = rn.recuperarTodos();
			assertNotNull(usuarios);

		} catch (Exception e) {

			fail(e.getMessage());
		} finally {
			manager.close();
		}
	}

	@Test
	public void testBuscarPorLogin(){
		manager.getTransaction().begin();
		UsuarioRN usuarioRN = new UsuarioRN(manager);
		try {
			Usuario usu = usuarioRN.buscaPorLogin("fabio@gmail.com");
			System.out.println(">>>>>>>usuário: "+usu.getEmail());
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//@Test
	public void testDeletar() {
		try {
			manager.getTransaction().begin();

			UsuarioRN rn = new UsuarioRN(manager);
			List<Usuario> usuarios = rn.recuperarTodos();

			rn.deletar(usuarios.get(0));

			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().rollback();

			fail(e.getMessage());
		} finally {
			manager.close();
		}
	}

}
