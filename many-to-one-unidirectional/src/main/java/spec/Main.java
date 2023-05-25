package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Date;
import java.time.LocalDate;

public class Main {

    /*
    @ManyToOne

    Is the most common association, having a direct equivalent in the relational database as well
    (e.g. foreign key), and so it establishes a relationship between a child entity and a parent.

    Each entity has a lifecycle of its own. Once the @ManyToOne association is set, Hibernate will
    set the associated database foreign key column.
     */

    /*
    Doing a

      technicalVersion.setTechnicalObject(null)

    inside a transaction, this will result in

      UPDATE TechnicalVersion
      SET version = 'currentVersion', releaseDate = 'currentReleaseDate', technicalObject_id = NULL
      WHERE id = technicalVersion.id
     */

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mainpersistenceunit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TechnicalObject java = new TechnicalObject();
        java.setName("Java");
        entityManager.persist(java);

        TechnicalVersion java17 = new TechnicalVersion();
        java17.setVersion("17");
        java17.setReleaseDate(Date.valueOf(LocalDate.of(2021, 9, 14)));
        java17.setTechnicalObject(java);
        entityManager.persist(java17);

        TechnicalVersion java11 = new TechnicalVersion();
        java11.setVersion("11");
        java11.setReleaseDate(Date.valueOf(LocalDate.of(2018, 9, 25)));
        java11.setTechnicalObject(java);
        entityManager.persist(java11);

        entityManager.getTransaction().commit();
        entityManager.close();


        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        java17 = entityManager.find(TechnicalVersion.class, 1);
        System.out.println(java17);
        java = java17.getTechnicalObject();
        System.out.println(java);

        java11 = entityManager.find(TechnicalVersion.class, 2);
        System.out.println(java11);
        java = java11.getTechnicalObject();
        System.out.println(java);

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();
    }

}
