package com.andriiiiiko.database.crud;

import com.andriiiiiko.database.entity.Resident;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Repository class for database operations related to Person entities.
 */
public class PersonRepository {

    /**
     * Private constructor to prevent instantiation of the PersonRepository class.
     */
    private PersonRepository() {
    }

    /**
     * Retrieves a list of IDs of people who own multiple apartments.
     *
     * @param entityManager The EntityManager for database operations.
     * @return A list of Integer IDs representing people with multiple apartments.
     */
    public static List<Integer> readIdsOfPeopleWithMultipleApartments(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
        Root<Resident> residentRoot = criteriaQuery.from(Resident.class);

        criteriaQuery.select(residentRoot.get("personId").get("id"))
                .groupBy(residentRoot.get("personId"))
                .having(criteriaBuilder.gt(criteriaBuilder.count(residentRoot.get("apartmentId")), 1));

        TypedQuery<Integer> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
