package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.junit.Test;

import dominio.Departamento;
import dominio.Funcionario;
import dominio.consulta.FuncionarioDepartamento;

public class FuncionarioDepartamentoTest {

	static EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("entity");

	@Test
	public void salvar() {
		EntityManager manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();

			Departamento d = new Departamento();
			d.setNome("Treinamentos");

			Funcionario f = new Funcionario();
			f.setNome("Fábio Sales");
			f.setDepartamento(d);

			manager.persist(f);
			manager.persist(d);

			manager.getTransaction().commit();
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void recuperar() {
		try {
			EntityManager manager = factory.createEntityManager();
			manager.getTransaction().begin();
			TypedQuery<Object[]> query = manager.createQuery(
					"select f.nome, f.departamento.nome from Funcionario f",
					Object[].class);

			List<Object[]> lista = query.getResultList();
			String msg = "";
			for (Object[] tupla : lista) {

				msg += "Funcionário: " + tupla[0] + "\nDepartamento: "
						+ tupla[1] + "\n";

			}
			JOptionPane.showMessageDialog(null, msg);
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void recuperarUtilizandoOperadorNew() {
		try {
			EntityManager manager = factory.createEntityManager();
			manager.getTransaction().begin();
			TypedQuery<FuncionarioDepartamento> query = manager
					.createQuery(
							"select new dominio.consulta.FuncionarioDepartamento(f.nome,f.departamento.nome) from Funcionario f",
							FuncionarioDepartamento.class);
			query.setFirstResult(2);
			query.setMaxResults(3);
			List<FuncionarioDepartamento> lista = query.getResultList();
			
			String msg ="";
			for(FuncionarioDepartamento fd : lista){
				msg += "\nFuncionario: "+fd.getFuncionario()+"\nDepartamento: "+fd.getDepartamento()+"\n";
			}
			JOptionPane.showMessageDialog(null, msg);
			manager.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
