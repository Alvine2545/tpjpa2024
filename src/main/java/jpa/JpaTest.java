package jpa;
import fr.istic.taa.jaxrs.domain.Role;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
            Role role = new Role("Artiste");
            manager.persist(role);


            Role r = manager.find(Role.class, 1);
            User user = new User("LEFLEUR Anne","java", "anne@gmail.com", "0745869574", "14 rue bovins", "Rennes-En-Grenouilles", 85, r);
            manager.persist(user);

        //}
    }

}