package de.eventon.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class DatabaseConfigurationService {

	@Produces
	public EntityManager createEntityManager(){
		Persistence.generateSchema("h2", null);
		EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("h2");
		return createEntityManagerFactory.createEntityManager();
	}
}
