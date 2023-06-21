package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jakarta-persistence");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Processor processorToInsert = new Processor();
        processorToInsert.setModel("i7-1165");
        em.persist(processorToInsert);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Processor processorToUpdate = em.find(Processor.class, 1);
        processorToUpdate.setModel("i7-1165G7");
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.getReference(Processor.class, 1));
        em.getTransaction().commit();
        em.close();
    }

}
