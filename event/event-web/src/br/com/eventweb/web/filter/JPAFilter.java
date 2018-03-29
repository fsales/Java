package br.com.eventweb.web.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames = { "Faces Servlet" })
public class JPAFilter implements Filter {

	private EntityManagerFactory factory;

	public void init(FilterConfig fConfig) throws ServletException {

		this.factory = Persistence.createEntityManagerFactory("evento");

	}

	public void destroy() {
		this.factory.close();

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		EntityTransaction transaction = null;
		EntityManager manager = this.factory.createEntityManager();
		try {

			request.setAttribute("EntityManager", manager);
			transaction = manager.getTransaction();
			transaction.begin();
			chain.doFilter(request, response);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			manager.close();
		}

	}

}
