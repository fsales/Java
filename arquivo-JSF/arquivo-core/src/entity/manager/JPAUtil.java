package entity.manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory;
	
	static{
		JPAUtil.factory = Persistence.createEntityManagerFactory("oracle");
	}
	
	public EntityManagerFactory getFactory(){
		return JPAUtil.factory;
	}
}
