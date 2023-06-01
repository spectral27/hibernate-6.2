package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jakarta-persistence");

        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Processor processor = new Processor("i7-1165G7");
        em.persist(processor);
        em.getTransaction().commit();
        em.close();

        em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Processor> query = em.createNamedQuery("select_processors", Processor.class);
        List<Processor> processors = query.getResultList();
        for (Processor p : processors) {
            System.out.println(p.getId() + " " + p.getModel());
        }
        em.getTransaction().commit();
        em.close();
    }

}
