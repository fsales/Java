package br.com.evento.core;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class GerarTabelas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EntityManager manager = Persistence.createEntityManagerFactory("event").createEntityManager();
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
