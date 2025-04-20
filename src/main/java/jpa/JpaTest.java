package jpa;
import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.Genre;
import fr.istic.taa.jaxrs.domain.Role;
import fr.istic.taa.jaxrs.domain.User;
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
       // int numOfRoles = manager.createQuery("Select a From Role a", Role.class).getResultList().size();
        //if (numOfRoles != 0) {
        Role role = new Role("Organisateur");
        Role role1 = new Role("abonné");
        manager.persist(role);
        manager.persist(role1);


        Role r = manager.find(Role.class, 1);
        User user = new User("LEFLEUR Anne","java", "anne@gmail.com", "0745869574", "14 rue bovins", "Rennes-En-Grenouilles", 85, r);
        manager.persist(user);

       // Concert c = manager.find(Role.class, 1);

        Genre g = new Genre("K-POP");
        manager.persist(g);

        Concert c = new Concert("Concert1","Pas de description", "RUE DU PARVIS, RENNES-EN-GRENOUILLES", "a.jpeg", new Date(), g);
        manager.persist(c);

        //}
    }

}