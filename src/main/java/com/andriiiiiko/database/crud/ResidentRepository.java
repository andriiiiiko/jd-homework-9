package com.andriiiiiko.database.crud;

import com.andriiiiiko.database.entity.Resident;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Repository class for database operations related to Resident entities.
 */
public class ResidentRepository {

    /**
     * Private constructor to prevent instantiation of the ResidentRepository class.
     */
    private ResidentRepository() {
    }

    /**
     * Retrieves a list of residents who do not have car entry permission.
     *
     * @param entityManager The EntityManager for database operations.
     * @return A list of Resident entities without car entry permission.
     */
    public static List<Resident> readResidentsWithoutCarEntryPermission(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Resident> criteriaQuery = criteriaBuilder.createQuery(Resident.class);
        Root<Resident> residentRoot = criteriaQuery.from(Resident.class);

        criteriaQuery.select(residentRoot);

        Predicate predicate = criteriaBuilder.equal(residentRoot.get("carEntryPermission"), false);
        criteriaQuery.where(predicate);

        TypedQuery<Resident> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
