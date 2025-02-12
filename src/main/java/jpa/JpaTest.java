package jpa;


import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Date;
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

			test.createConcerts();
			test.createTickets();

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
			User user1 = new User("Alice", "password123", "alice@gmail.com", "0654879632", "10 rue Paris", "Paris", 28, role2);
			User user2 = new User("Bob", "pass456", "bob@gmail.com", "0754123658", "12 avenue Lyon", "Lyon", 35, rol3);

			manager.persist(user1);
			manager.persist(user2);
		}
	}

	private void listUsers() {
		List<User> resultList = manager.createQuery("Select a From User a", User.class).getResultList();
		System.out.println("num of user:" + resultList.size());
		for (User next : resultList) {
			System.out.println("next User: " + next);
		}
	}

	/**
	 * Add tickets on database
	 */
	private void createTickets() {
		User user = manager.createQuery("SELECT u FROM User u WHERE u.name = 'Alice'", User.class).getSingleResult();
		Concert concert = manager.createQuery("SELECT c FROM Concert c WHERE c.title = 'Rock Festival'", Concert.class).getSingleResult();

		TicketStandard ticket1 = new TicketStandard(50, 25);
		ticket1.setUser(user);
		ticket1.setConcert(concert);
		ticket1.setStatut("Validé");

		TicketLastMinute ticket2 = new TicketLastMinute(25);
		ticket2.setUser(user);
		ticket2.setConcert(concert);
		ticket2.setStatut("En attente");

		manager.persist(ticket1);
		manager.persist(ticket2);
	}

	private void createConcerts() {
		Concert concert1 = new Concert();
		concert1.setTitle("Rock Festival");
		concert1.setDescription("Festival de Rock annuel");
		concert1.setLocation("Stade de France");
		concert1.setPrice("50€");
		concert1.setNbr_ticket(100);

		Concert concert2 = new Concert();
		concert2.setTitle("Jazz Night");
		concert2.setDescription("Soirée Jazz avec les meilleurs artistes");
		concert2.setLocation("Théâtre de Lyon");
		concert2.setPrice("30€");
		concert2.setNbr_ticket(50);

		manager.persist(concert1);
		manager.persist(concert2);
	}


}
