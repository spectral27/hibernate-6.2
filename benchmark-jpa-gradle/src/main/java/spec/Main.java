package spec;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            DriverManager.getConnection(
                    "jdbc:h2:mem:database01;INIT=runscript from 'classpath:init.sql'",
                    "username",
                    "password"
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Processor processor = new Processor();
        processor.setId(1);
        processor.setModel("i7-1165G7");
        processor.setCores(4);
        processor.setThreads(8);
        processor.setMaxFrequency("4.70 Ghz");
        processor.setCache("12 MB");
        processor.setMaxRam("64 GB");
        processor.setRamType("DDR4-3200");
        processor.setRamChannels(2);

        HibernateExecutor doInJpa = new HibernateExecutor();

        long start = System.currentTimeMillis();

        doInJpa.execute(entityManager -> entityManager.persist(processor));

        doInJpa.getResult(entityManager -> entityManager.createQuery("select p from Processor p", Processor.class).getResultList());

        doInJpa.execute(entityManager -> {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Processor> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Processor.class);
            Root<Processor> root = criteriaUpdate.getRoot();
            criteriaUpdate.set("model", processor.getModel());
            criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), processor.getId()));
            entityManager.createQuery(criteriaUpdate).executeUpdate();
        });

        doInJpa.execute(entityManager -> {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Processor> criteriaDelete = criteriaBuilder.createCriteriaDelete(Processor.class);
            Root<Processor> root = criteriaDelete.getRoot();
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), processor.getId()));
            entityManager.createQuery(criteriaDelete).executeUpdate();
        });

        long end = System.currentTimeMillis();

        double timeElapsed = (double) (end - start) / 1000;
        System.out.println(timeElapsed);

    }

}
