package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MainTest {

    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("test-unit");

    @Test
    @SuppressWarnings("unchecked")
    public void mainTest() {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        String query = "select 1";
        List<Integer[]> result = entityManager.createNativeQuery(query, Integer[].class).getResultList();

        int actual = result.get(0)[0];

        Assertions.assertEquals(1, actual);
    }

}
