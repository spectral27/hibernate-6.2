package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("main-unit");

    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.setName("i7-1165G7");

        execute(entityManager -> entityManager.persist(processor));

        List<Processor> processors = getResult(entityManager ->
                entityManager.createQuery("select p from Processor p", Processor.class).getResultList()
        );

        for (Processor p : processors) {
            System.out.printf("%d %s\n", p.getId(), p.getName());
        }
    }

    public static void execute(FunctionalExecutor<EntityManager> functionalExecutor) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        functionalExecutor.executeTask(entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static <T> T getResult(FunctionalProvider<EntityManager, T> functionalProvider) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        T result = functionalProvider.getResult(entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

}
