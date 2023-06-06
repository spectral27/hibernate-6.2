package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jakarta-persistence");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Processor processorToInsert = new Processor("i7-1165G7");
        entityManager.persist(processorToInsert);
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        /*
        This won't result in an Hibernate query, because the entity is already in cache.
        The last query in output will be the previous insert one.
         */
        entityManager.find(Processor.class, 1);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
