package com.andriiiiiko;

import com.andriiiiiko.database.FlywayMigration;
import com.andriiiiiko.database.crud.PersonRepository;
import com.andriiiiiko.database.crud.ResidentRepository;
import com.andriiiiiko.database.entity.Resident;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * This class represents the main application entry point.
 */
public class App {

    private static final Logger LOG = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static EntityTransaction transaction;

    /**
     * The main method of the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        LOG.info("The program has started");

        FlywayMigration.migrateDatabase();

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("osbb");
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            List<Resident> residents = ResidentRepository.readResidentsWithoutCarEntryPermission(entityManager);
            List<Integer> idsOfPeopleWithMultipleApartments =
                    PersonRepository.readIdsOfPeopleWithMultipleApartments(entityManager);

            residents.removeIf(resident -> idsOfPeopleWithMultipleApartments.contains(resident.getPersonId().getId()));

            printList(residents);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            LOG.error(String.format("An error occurred: %s", e.getMessage()));
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        LOG.info("The program has finished");
    }

    /**
     * Prints the elements of a list to the console using their 'toString()' method.
     *
     * @param list The list of elements to be printed.
     * @param <T>  The type of elements in the list.
     */
    public static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item.toString());
        }
    }
}
