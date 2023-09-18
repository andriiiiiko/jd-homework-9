package com.andriiiiiko.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

/**
 * This class is responsible for managing database schema migration using Flyway.
 */
public class FlywayMigration {

    private static final Logger LOG = LogManager.getLogger(FlywayMigration.class);

    /**
     * Private constructor to prevent instantiation of the FlywayMigration class.
     */
    private FlywayMigration() {
    }

    /**
     * Executes database migration using Flyway to maintain the consistency of the database schema.
     */
    public static void migrateDatabase() {
        LOG.info("Flyway migration execute");

        Flyway.configure()
                .dataSource(Config.JDBC_URL, Config.USERNAME, Config.PASSWORD)
                .locations("classpath:flyway/scripts")
                .load()
                .migrate();

        LOG.info("Flyway migration completed");
    }
}
