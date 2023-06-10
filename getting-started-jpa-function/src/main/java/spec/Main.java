package spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        JpaExecutor jpaExecutor = new JpaExecutor();

        Processor processor = new Processor();
        processor.setModel("i7-1165G7");
        processor.setPrice("9.99");

        jpaExecutor.execute(entityManager -> entityManager.persist(processor));

        jpaExecutor.execute(entityManager -> {
            Processor processorToUpdate = entityManager.find(Processor.class, 1);
            processorToUpdate.setPrice("19.99");
        });

        Processor savedProcessor = jpaExecutor.getResult(entityManager -> entityManager.find(Processor.class, 1));
        System.out.printf("%d %s %s\n", savedProcessor.getId(), savedProcessor.getModel(), savedProcessor.getPrice());

        jpaExecutor.execute(entityManager -> {
            Processor processorToDelete = entityManager.find(Processor.class, 1);
            entityManager.remove(processorToDelete);
        });
    }

}
