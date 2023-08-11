package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

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
        List<Processor> processors = em.createQuery("select p from Processor p", Processor.class).getResultList();
        em.getTransaction().commit();
        em.close();

        processors.forEach(p -> System.out.printf("%s %s %s", p.getId(), p.getModel(), p.getPrice()));
    }

}
