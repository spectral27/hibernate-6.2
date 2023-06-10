package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.function.Consumer;
import java.util.function.Function;

public class JpaExecutor {

    private final EntityManagerFactory entityManagerFactory;

    public JpaExecutor() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jakarta-persistence");
    }

    public void execute(Consumer<EntityManager> consumer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        consumer.accept(entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public <T> T getResult(Function<EntityManager, T> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        T result = function.apply(entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }

}
