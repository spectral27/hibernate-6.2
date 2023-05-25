package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class Main {

    public static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("mainpersistenceunit");
    }

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Device deviceToInsert = new Device("Apple", "iPhone 14");
        entityManager.persist(deviceToInsert);
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query updateQuery = entityManager.createQuery("update Device d set d.model = :model where d.id = :id");
        updateQuery.setParameter("model", "iPhone 14 Pro");
        updateQuery.setParameter("id", 1);
        updateQuery.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Device> devices = entityManager.createQuery("select d from Device d", Device.class).getResultList();
        for (Device device : devices) {
            System.out.println(device);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Device d where d.id = :id");
        query.setParameter("id", 1);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

        Long now = System.currentTimeMillis();
        Double time = (double) (now - start) / 1000;
        System.out.printf("%.3fs\n", time);
    }

}
