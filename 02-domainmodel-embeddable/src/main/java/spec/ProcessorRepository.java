package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ProcessorRepository {

    private final EntityManagerFactory entityManagerFactory = Main.get();

    public void insert(Processor processor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(processor);
        em.getTransaction().commit();
        em.close();
    }

    public Processor select(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Processor processor = em.find(Processor.class, id);
        em.getTransaction().commit();
        em.close();
        return processor;
    }

}
