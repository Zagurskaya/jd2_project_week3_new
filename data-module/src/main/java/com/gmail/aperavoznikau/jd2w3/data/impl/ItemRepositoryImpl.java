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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends AbstractRepository implements ItemRepository {

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
}
