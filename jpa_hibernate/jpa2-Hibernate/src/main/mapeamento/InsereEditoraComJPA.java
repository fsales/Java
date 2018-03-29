package main.mapeamento;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.estudo.hibernate.mapeamento.Editora;

public class InsereEditoraComJPA {
	
	// TODO: parei no capitulo 1. continuar no capitulo 2.
	public static void main(String[] args){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("livraria");
		EntityManager manager = factory.createEntityManager();
		
		Editora novaEditora = new Editora();
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Digite o nome da editora:");
		novaEditora.setNome(entrada.nextLine());
		
		System.out.println("Digite o email da editroa:");
		novaEditora.setEmail(entrada.nextLine());
		
		try {
			manager.getTransaction().begin();
			manager.persist(novaEditora);
			manager.getTransaction().commit();
			
			//listar
			Query query = manager.createQuery("SELECT e FROM Editora e");
			@SuppressWarnings("unchecked")
			List<Editora> editoras = query.getResultList();
			for(Editora e:editoras){
				System.out.println("Editora: "+e.getNome() + " - " +e.getEmail()+"\n");
			}
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			factory.close();
		}
			
		
	}
}
