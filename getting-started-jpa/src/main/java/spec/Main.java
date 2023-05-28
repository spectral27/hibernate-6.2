package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    private final EntityManagerFactory entityManagerFactory;

    {
        entityManagerFactory = Persistence.createEntityManagerFactory("jakarta-persistence");
    }

    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.setModel("i7-1165G7");
        processor.setCores(4);
        processor.setThreads(8);

        Main main = new Main();
        main.persist(processor);

        List<Processor> processors = main.getResultList("select p from Processor p", Processor.class);
        processors.forEach(System.out::println);
    }

    public void persist(Object object) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        em.close();
    }

    public <T> List<T> getResultList(String query, Class<T> resultClass) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<T> resultList = em.createQuery(query, resultClass).getResultList();
        em.getTransaction().commit();
        em.close();
        return resultList;
    }

}
