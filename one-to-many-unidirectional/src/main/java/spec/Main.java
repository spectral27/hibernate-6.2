package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Date;
import java.time.LocalDate;

public class Main {

    /*
    @OneToMany

    The @OneToMany association links a parent entity with one or more child entities. If the
    @OneToMany doesn’t have a mirroring @ManyToOne association on the child side, the @OneToMany
    association is unidirectional.

    If there is a @ManyToOne association on the child side, the @OneToMany association is
    bidirectional and the application developer can navigate this relationship from both ends.

    When using a unidirectional @OneToMany association, Hibernate resorts to using a link table
    between the two joining entities.

    The @OneToMany association is by definition a parent association, regardless of whether it’s
    a unidirectional or a bidirectional one. Only the parent side of an association makes sense
    to cascade its entity state transitions to children.
     */

    /*
    Adding

      @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
      private List<TechnicalVersion> technicalVersions = new ArrayList<>();

    and doing a

      technicalObject().getTechnicalVersions().remove(technicalVersion);

    inside a transaction, this will result in

      DELETE FROM TechnicalObject_TechnicalVersion
      WHERE TechnicalObject_id = 1

      INSERT INTO TechnicalObject_TechnicalVersion (TechnicalObject_id, technicalVersions_id)
      VALUES (1, 2)

      DELETE FROM TechnicalVersion
      WHERE id = 1

      When persisting the TechnicalObject entity, the cascade will propagate the persist operation
      to the underlying TechnicalVersion children as well. Upon removing a TechnicalVersion from
      the versions collection, the association row is deleted from the link table, and the
      orphanRemoval attribute will trigger a TechnicalVersion removal as well.

      Upon flushing the persistence context, Hibernate deletes all database rows from the link table
      (e.g. TechnicalObject_TechnicalVersion) that are associated with the parent TechnicalObject
      entity and reinserts the ones that are still found in the @OneToMany collection.
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
        java.getTechnicalVersions().add(java17);
        entityManager.persist(java17);

        TechnicalVersion java11 = new TechnicalVersion();
        java11.setVersion("11");
        java11.setReleaseDate(Date.valueOf(LocalDate.of(2018, 9, 25)));
        java.getTechnicalVersions().add(java11);
        entityManager.persist(java11);

        entityManager.getTransaction().commit();
        entityManager.close();


        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        java = entityManager.find(TechnicalObject.class, 1);
        System.out.println(java);
        for (TechnicalVersion version : java.getTechnicalVersions()) {
            System.out.println(version);
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();
    }

}
