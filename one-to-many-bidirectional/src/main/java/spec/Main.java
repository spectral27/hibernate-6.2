package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Main {

    /*
    @OneToMany

    The @OneToMany association links a parent entity with one or more child entities. If the
    @OneToMany doesn’t have a mirroring @ManyToOne association on the child side, the @OneToMany
    association is unidirectional.

    If there is a @ManyToOne association on the child side, the @OneToMany association is
    bidirectional and the application developer can navigate this relationship from both ends.

    The @OneToMany association is by definition a parent association, regardless of whether it’s
    a unidirectional or a bidirectional one. Only the parent side of an association makes sense
    to cascade its entity state transitions to children.

    The bidirectional @OneToMany association also requires a @ManyToOne association on the child
    side. Although the Domain Model exposes two sides to navigate this association, behind the
    scenes, the relational database has only one foreign key for this relationship.

    Every bidirectional association must have one owning side only (the child side), the other one
    being referred to as the inverse (or the mappedBy) side.

    Whenever a bidirectional association is formed, the application developer must make sure both
    sides are in-sync at all times.

    The addVersion() and removeVersion() are utility methods that synchronize both ends whenever
    a child element is added or removed.
     */

    /*
    Adding

      @OneToMany(mappedBy = "object", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<TechnicalVersion> technicalVersions = new ArrayList<>();

    and doing a

      technicalObject.removeVersion(version);

    inside a transaction, this will result in

      DELETE FROM TechnicalVersion
      WHERE id = 1

    Unlike the unidirectional @OneToMany, the bidirectional association is much more efficient
    when managing the collection persistence state. Every element removal only requires a single
    update (in which the foreign key column is set to NULL), and, if the child entity lifecycle is
    bound to its owning parent so that the child cannot exist without its parent, then we can
    annotate the association with the orphanRemoval attribute and dissociating the child will
    trigger a delete statement on the actual child table row as well.
     */

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mainpersistenceunit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TechnicalObject java = new TechnicalObject("Java");
        entityManager.persist(java);

        TechnicalVersion java17 = new TechnicalVersion("17", Date.valueOf(LocalDate.of(2021, 9, 14)));
        java.addVersion(java17);
        entityManager.persist(java17);

        TechnicalVersion java11 = new TechnicalVersion("11", Date.valueOf(LocalDate.of(2018, 9, 25)));
        java.addVersion(java11);
        entityManager.persist(java11);

        entityManager.getTransaction().commit();
        entityManager.close();


        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        java = entityManager.find(TechnicalObject.class, 1);
        System.out.println(java);

        entityManager.getTransaction().commit();
        entityManager.close();


        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<TechnicalVersion> versions = entityManager
                .createQuery("select v from TechnicalVersion v", TechnicalVersion.class)
                .getResultList();
        for (TechnicalVersion version : versions) {
            System.out.println(version);
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();
    }

}
