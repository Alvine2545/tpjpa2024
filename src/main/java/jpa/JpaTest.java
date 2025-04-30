package jpa;
import fr.istic.taa.jaxrs.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Date;

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


        manager.close();
        EntityManagerHelper.closeEntityManagerFactory();
        System.out.println(".. done");
    }


    private void createUsers() {

        Artiste artiste = new Artiste("K-MAR", "Jules", "125 rue abis", "BOUZZ");
        manager.persist(artiste);

        Artiste artiste1 = new Artiste("KALASH", "Nice", "125 boulevard des riches", "HESS");
        manager.persist(artiste1);

       // int numOfRoles = manager.createQuery("Select a From Role a", Role.class).getResultList().size();
        //if (numOfRoles != 0) {
        Role role = new Role("ORGANISATEUR");
        Role role1 = new Role("USER");
        Role role2 = new Role("ADMIN");
        manager.persist(role);
        manager.persist(role1);
        manager.persist(role2);

        Role r = manager.find(Role.class, role.getId());
        User user = new User("LEFLEUR Anne","java", "anne@gmail.com", "0745869574", "14 rue bovins", "Rennes-En-Grenouilles", 85, r);
        manager.persist(user);

        Role r1 = manager.find(Role.class, role1.getId());
        User user1 = new User("LAROSE Julie","anne", "julie@gmail.com", "0745869564", "14 rue jean-boscaux", "Rennes", 45, r1);
        manager.persist(user1);

        Role r2 = manager.find(Role.class, role2.getId());
        User user2 = new User("MAJOIE Julien","majoie", "julien@gmail.com", "0748569574", "145 avenue leclerc", "Brest", 35, r2);
        manager.persist(user2);

       // Concert c = manager.find(Role.class, 1);

        Genre g = new Genre("K-POP");
        manager.persist(g);

        Genre g1 = new Genre("RAP");
        manager.persist(g1);

        Genre g2 = new Genre("CLASSIC");
        manager.persist(g2);

        Concert c = new Concert("Concert1","Pas de description", "RUE DU PARVIS, RENNES-EN-GRENOUILLES", "a.jpeg", new Date(), 1235.2, 56, 89, g);
       manager.persist(c);

        //}
    }

}