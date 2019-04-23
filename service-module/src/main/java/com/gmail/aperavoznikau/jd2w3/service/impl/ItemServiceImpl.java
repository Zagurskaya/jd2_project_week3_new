package com.gmail.aperavoznikau.jd2w3.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.gmail.aperavoznikau.jd2w3.data.ItemRepository;
import com.gmail.aperavoznikau.jd2w3.data.impl.AbstractRepository;
import com.gmail.aperavoznikau.jd2w3.data.model.Item;
import com.gmail.aperavoznikau.jd2w3.service.ItemService;
import com.gmail.aperavoznikau.jd2w3.service.converter.ItemConverter;
import com.gmail.aperavoznikau.jd2w3.service.exception.ServiceException;
import com.gmail.aperavoznikau.jd2w3.service.model.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    private final ItemConverter itemConverter;
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemConverter itemConverter, ItemRepository itemRepository) {
        this.itemConverter = itemConverter;
        this.itemRepository = itemRepository;
    }


    @Override
    public List<ItemDTO> getItems() {
        try (Connection connection = itemRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Item> items = itemRepository.getItems(connection);
                List<ItemDTO> dtos = items.stream()
                        .map(itemConverter::toDTO)
                        .collect(Collectors.toList());
                connection.commit();
                return dtos;
            } catch (Exception e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                throw new ServiceException("Something wrong with database");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException("Something wrong with database");
        }
    }

    @Override
    public void add(ItemDTO itemDTO) {
        //TODO
    }

    @Override
    public void updateStatus(Long id, String status) {
        //TODO
    }
}
