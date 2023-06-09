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
        Processor processorToUpdate;
        try {
            processorToUpdate = em.find(Processor.class, id, LockModeType.PESSIMISTIC_WRITE);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            processorToUpdate.setPrice(price);
            em.getTransaction().commit();
        } catch (PessimisticLockException | org.hibernate.PessimisticLockException e) {
                System.out.println(e.getClass().getCanonicalName());
                System.out.println(e.getMessage());
                em.getTransaction().rollback();
        }
    }

}
