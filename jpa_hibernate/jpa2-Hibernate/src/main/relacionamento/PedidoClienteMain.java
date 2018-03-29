package main.relacionamento;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.estudo.hibernate.relacionamento.manyToOne.Cliente;
import br.com.estudo.hibernate.relacionamento.manyToOne.Pedido;

public class PedidoClienteMain {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("livraria");
		EntityManager m = factory.createEntityManager();
		
		m.getTransaction().begin();
		
		Cliente c = new Cliente();
		c.setNome("Fabio");
		
		Pedido p = new Pedido();
		p.setData(Calendar.getInstance());
		p.setCliente(c);
		
		m.persist(c);
		m.persist(p);
		
		m.getTransaction().commit();
		m.close();
		factory.close();
	}
}
