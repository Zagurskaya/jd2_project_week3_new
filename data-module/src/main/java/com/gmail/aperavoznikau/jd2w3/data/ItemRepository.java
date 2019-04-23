package com.gmail.aperavoznikau.jd2w3.data;

import java.sql.Connection;
import java.util.List;

import com.gmail.aperavoznikau.jd2w3.data.model.Item;

public interface ItemRepository extends ConnectionRepository {

    List<Item> getItems(Connection connection);
}
