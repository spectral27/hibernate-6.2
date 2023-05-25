package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import java.sql.Time;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mainpersistenceunit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user1 = new User("Mark Cooper", Time.valueOf(LocalTime.now().withSecond(1)));
        entityManager.persist(user1);
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User markCooper = entityManager.find(User.class, 1);
        markCooper.setName("Markk Cooperr");
        markCooper.setTime(Time.valueOf(LocalTime.now().withSecond(2)));
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        User firstRevision = auditReader.find(User.class, 1, 1);
        System.out.printf("%d %s %s\n", firstRevision.getId(), firstRevision.getName(), firstRevision.getTime());
        User secondRevision = auditReader.find(User.class, 1, 2);
        System.out.printf("%d %s %s\n", secondRevision.getId(), secondRevision.getName(), secondRevision.getTime());
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();
    }

}
