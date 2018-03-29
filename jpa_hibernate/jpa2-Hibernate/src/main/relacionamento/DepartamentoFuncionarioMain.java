package main.relacionamento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.estudo.hibernate.relacionamento.oneToMany.Departamento;
import br.com.estudo.hibernate.relacionamento.oneToMany.Endereco;
import br.com.estudo.hibernate.relacionamento.oneToMany.Funcionario;

public class DepartamentoFuncionarioMain {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("livraria");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();

		Endereco e = new Endereco();
		e.setEstado("GO");
		e.setCidade("Valparaíso");
		e.setCep(71200020);
		e.setLogradouro("rua sem número");
		e.setNumero(34);
		e.setPais("Brasil");
		e.setComplemento("perto igreja");
		Funcionario f = new Funcionario();
		f.setNome("Fabio");
		f.setEndereco(e);

		Departamento d = new Departamento();
		d.setNome("Financeiro");
		d.getFuncionarios().add(f);

		manager.persist(f);
		manager.persist(d);
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
}
