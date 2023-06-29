package spec;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("jakarta-persistence");
    }

    public static void main(String[] args) {
        ProcessorCodename codename = new ProcessorCodename();
        codename.setCodename("Tiger Lake");

        Processor processor = new Processor();
        processor.setName("i7-1165G7");
        processor.setProcessorCodename(codename);

        ProcessorRepository processorRepository = new ProcessorRepository();
        processorRepository.insert(processor);

        processorRepository.update(1, 469.99);

        Processor savedProcessor = processorRepository.select(1);
        System.out.println(savedProcessor);

        processorRepository.delete(1);
    }

    public static EntityManagerFactory get() {
        return entityManagerFactory;
    }

}
