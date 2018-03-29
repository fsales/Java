package test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dominio.Pessoa;

public class PessoaJpql {

	static EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("entity");

	@Test
	public void consultaNamedQuery() {
		EntityManager manager = factory.createEntityManager();
		Query query = null;
		try {
			query = manager.createNamedQuery("Pessoa.findAll");
			//Consulta todas as pessoas
			System.out.println("\nConsulta todas as pessoas\n");
			List<Pessoa> pessoas = query.getResultList();
			assertNotNull(pessoas);
			System.out.println(pessoas.toString());
			
			//consulta pesso apor idade passando parametro
			System.out.println("\nconsulta pesso apor idade passando parametro\n");
			query = manager.createNamedQuery("Pessao.findByIdade");
			query.setParameter(1, 18);
			List<Pessoa> pessoasComMaisDe18 = query.getResultList();
			assertNotNull(pessoasComMaisDe18.toString());
			System.out.println("\nPessoas com mais de 18: "+pessoasComMaisDe18.toString());
			
			//consulta retornando somente o nome da pessoa
			System.out.println("\nconsulta retornando somente o nome da pessoa\n");
			query = manager.createNamedQuery("Pessoa.findNome");
			List<String> nomes = query.getResultList();
			System.out.println("nomes: "+nomes);
			
			//consulta retornando quantidade de pessoas
			System.out.println("\nconsulta retornando quantidade de pessoas\n");
			TypedQuery<Long> queryCount = manager.createNamedQuery("Pessoa.count",Long.class);
			Long count = queryCount.getSingleResult();
			System.out.println("quantidade de pessoas: "+count);
			
			//consulta maior idade
			System.out.println("\nconsulta maior idade\n");
			TypedQuery<Integer> queryMax = manager.createNamedQuery("Pessoa.maxIdade",Integer.class);
			Integer maiorIdade = queryMax.getSingleResult();
			System.out.println("Maior idade: "+maiorIdade);
			
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	}

}
