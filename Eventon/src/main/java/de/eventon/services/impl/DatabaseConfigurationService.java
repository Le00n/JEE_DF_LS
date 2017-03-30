package de.eventon.services.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class DatabaseConfigurationService implements Serializable{

	private static final long serialVersionUID = -2267992486213377214L;

	@PersistenceContext(name = "h2")
	private EntityManager entityManager;
	
	@Produces
	@ApplicationScoped
	EntityManager createEntityManager() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
//		return emf.createEntityManager();
		return this.entityManager;
	}
}
