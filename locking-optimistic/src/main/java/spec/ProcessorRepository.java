package spec;

import jakarta.persistence.*;

public class ProcessorRepository {

    private static ProcessorRepository processorRepository;
    private final EntityManagerFactory entityManagerFactory;

    private ProcessorRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jakarta-persistence");
    }

    public static ProcessorRepository getInstance() {
        if (processorRepository == null) {
            processorRepository = new ProcessorRepository();
        }
        return processorRepository;
    }

    public void insertProcessor(Processor processor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(processor);
        em.getTransaction().commit();
        em.close();
    }

    public void changeProcessorPrice(int id, String price) {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            try (em) {
                Processor processorToUpdate = em.find(Processor.class, id);
                processorToUpdate.setPrice(price);
                em.getTransaction().commit();
            } catch (RollbackException e) {
                if (e.getCause() instanceof OptimisticLockException) {
                    System.out.println(e.getCause().getClass().getCanonicalName());
                    System.out.println(e.getCause().getMessage());
                }
                em.getTransaction().rollback();
            }
    }

}
