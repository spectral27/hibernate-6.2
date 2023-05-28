package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private final EntityManagerFactory entityManagerFactory;

    {
        entityManagerFactory = Persistence.createEntityManagerFactory("jakarta-persistence");
    }

    public static void main(String[] args) {
        Person user = new Person();
        user.setUsername("user1");
        List<String> phones = new ArrayList<>();
        phones.add("123 4567890");
        phones.add("246 8024680");
        user.setPhones(phones);

        Main main = new Main();
        main.persist(user);

        main.getResultList();
    }

    public void persist(Object object) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        em.close();
    }

    @SuppressWarnings("unchecked")
    public void getResultList() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Object[]> results = em.createNativeQuery(
                "select pp.phones_ORDER, pp.phones from Person_phones pp",
                Object[].class
        ).getResultList();

        for (Object[] result : results) {
            Integer index = (Integer) result[0];
            String phone = (String) result[1];
            System.out.println(index + " " + phone);
        }

        em.getTransaction().commit();
        em.close();
    }

}
