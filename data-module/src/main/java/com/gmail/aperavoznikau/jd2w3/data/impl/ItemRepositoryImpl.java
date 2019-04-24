package com.gmail.aperavoznikau.jd2w3.data.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gmail.aperavoznikau.jd2w3.data.ItemRepository;
import com.gmail.aperavoznikau.jd2w3.data.exception.DatabaseConnectionException;
import com.gmail.aperavoznikau.jd2w3.data.model.Item;
import com.gmail.aperavoznikau.jd2w3.data.model.ItemStatusEnum;
import com.gmail.aperavoznikau.jd2w3.data.properties.DatabaseProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends AbstractRepository implements ItemRepository {
    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    @Autowired
    public ItemRepositoryImpl(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }

    @Override
    public List<Item> getItems(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            List<Item> itemList = new ArrayList<>();

            String sql = String.format("SELECT * FROM `item`");
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getLong("id"));
                item.setName(resultSet.getString("name"));
                item.setStatus(ItemStatusEnum.valueOf(resultSet.getString("status")));
                itemList.add(item);
            }
            return itemList;
        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
            throw new DatabaseConnectionException("Database exception during deleting All document", e);
        }
    }

    @Override
    public Item add(Item item, Connection connection) {
        try (Statement statement = connection.createStatement()) {

            String sql = String.format("INSERT INTO `item`(`name`, `status`) VALUES ('%s','%s')",
                    item.getName(), item.getStatus());

            logger.info(sql);

            int count = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if (count == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getLong(1));
                    return item;
                } else {
                    logger.error("item not added");
                    throw new DatabaseConnectionException("Database exception during save item");
                }
            }
//
////            long itemId = statement.executeBatch(sql);
//            if (count > 0) {
//                item.setId(itemId);
//                return item;
//            } else {
//                logger.error("item not added");
//                throw new DatabaseConnectionException("Database exception during save item");
//            }
        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
            throw new DatabaseConnectionException("Database exception during deleting All document", e);
        }
        return null;
    }

    @Override
    public int update(Long id, String status, Connection connection) {
        return 0;
    }
}
