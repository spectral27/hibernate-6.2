package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jakarta-persistence");

        Processor processor = new Processor();
        processor.setModel("i7-1165G7");
        processor.setPrice("9.99");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(processor);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Processor processorToUpdateReference = em.find(Processor.class, 1);
        processorToUpdateReference.setPrice("19.99");
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Processor savedProcessor = em.find(Processor.class, 1);
        System.out.printf("%d %s %s\n", savedProcessor.getId(), savedProcessor.getModel(), savedProcessor.getPrice());
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Processor processorToDeleteReference = em.find(Processor.class, 1);
        em.remove(processorToDeleteReference);
        em.getTransaction().commit();
        em.close();
    }

}
