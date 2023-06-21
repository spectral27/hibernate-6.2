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

        em = factory.createEntityManager();
        em.getTransaction().begin();
        RecordClass record = new RecordClass();
        record.setRecord("r1");
        em.persist(record);
        SectorClass sector1 = new SectorClass();
        sector1.setSector("s1");
        record.addSector(sector1);
        em.persist(sector1);
        SectorClass sector2 = new SectorClass();
        sector2.setSector("s2");
        record.addSector(sector2);
        em.persist(sector2);
        em.getTransaction().commit();
        em.close();

        em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<RecordClass> recordsQuery = em.createNamedQuery("select_records", RecordClass.class);
        List<RecordClass> records = recordsQuery.getResultList();
        em.getTransaction().commit();
        em.close();

        for (RecordClass r : records) {
            System.out.println(r.getRecord());
            for (SectorClass s : r.getSectors()) {
                System.out.println(s.getSector());
            }
        }
    }

}
