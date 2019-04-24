package com.gmail.aperavoznikau.jd2w3.data.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.annotation.PostConstruct;

import com.gmail.aperavoznikau.jd2w3.data.ConnectionRepository;
import com.gmail.aperavoznikau.jd2w3.data.exception.DatabaseConnectionException;
import com.gmail.aperavoznikau.jd2w3.data.properties.DatabaseProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractRepository implements ConnectionRepository {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);
    private final DatabaseProperties databaseProperties;

    public AbstractRepository(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
        try {
            Class.forName(databaseProperties.getDatabaseDriverName());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            System.exit(0);
        }
    }

    @Override
    public Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", databaseProperties.getDatabaseUsername());
            properties.setProperty("password", databaseProperties.getDatabasePassword());
            properties.setProperty("useUnicode", "true");
            properties.setProperty("useJDBCCompliantTimezoneShift", "true");
            properties.setProperty("useLegacyDatetimeCode", "false");
            properties.setProperty("serverTimezone", "UTC");
            return DriverManager.getConnection(databaseProperties.getDatabaseURL(), properties);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseConnectionException("Database connection error", e);
        }
    }

    @PostConstruct
    public void initializeDatabase() {
        try (Connection connection = getConnection()) {
            processSQL(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseConnectionException("Database connection error", e);
        }
    }

    private void processSQL(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try (Statement statement = connection.createStatement()) {
            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS ITEM(id int auto_increment primary key, name varchar(100), status varchar(100))");
            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS ITEM_AUD(id int auto_increment primary key, action varchar(100), item_id int,date DATE)");
            statement.executeBatch();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            System.exit(0);
        }
    }
}
