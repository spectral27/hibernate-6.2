package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem::database01", "username", "password");
            SqlFile sqlFile = new SqlFile(new File("init.sql"));
            sqlFile.setConnection(connection);
            sqlFile.execute();
        } catch (SQLException | IOException | SqlToolError e) {
            throw new RuntimeException(e);
        }

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mainpersistenceunit");

        JavaObject javaObject = new JavaObject(1, System.getProperty("java.vendor"), System.getProperty("java.version"));

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(javaObject);
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<JavaObject> selectQuery = entityManager.createQuery("select j from JavaObject j", JavaObject.class);
        List<JavaObject> javaObjects = selectQuery.getResultList();
        javaObjects.forEach(j -> System.out.printf("%s %s\n", j.getVendor(), j.getVersion()));
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();
    }

}
