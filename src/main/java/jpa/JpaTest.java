package jpa;


import entities.Role;
import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaTest {


	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EntityManager manager = EntityManagerHelper.getEntityManager();

		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {

			// TODO create and persist entity
			test.createUsers();

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		//test.listEmployees();
			
   	 manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}

	private void createUsers() {
		int numOfEmployees = manager.createQuery("Select u From User u", User.class).getResultList().size();
		if (numOfEmployees == 0) {
			Role role = new Role("Administrateur");
			Role role2 = new Role("Client");
			Role rol3 = new Role("Organisateur");
			manager.persist(role);
			manager.persist(role2);
			manager.persist(rol3);

			manager.persist(new User("Jakab Gipsz", "jhgf", "jakab@gmail.com","0745256895","231 Avenue Goerges","Monacco", 25,role));
			manager.persist(new User("Captain Nemoi","jhgf", "nemo@gmail.com","0745252695","31 Avenue du Castre","Vorchez", 45,role));

		}
	}

	private void listUsers() {
		List<User> resultList = manager.createQuery("Select a From User a", User.class).getResultList();
		System.out.println("num of user:" + resultList.size());
		for (User next : resultList) {
			System.out.println("next User: " + next);
		}
	}
}
