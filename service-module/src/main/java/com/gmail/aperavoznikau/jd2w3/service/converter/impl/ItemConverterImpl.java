package com.gmail.aperavoznikau.jd2w3.service.converter.impl;

import com.gmail.aperavoznikau.jd2w3.data.model.Item;
import com.gmail.aperavoznikau.jd2w3.service.converter.ItemConverter;
import com.gmail.aperavoznikau.jd2w3.service.model.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemConverterImpl implements ItemConverter {

    @Override
    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setStatus(item.getStatus());
        return itemDTO;
    }

    @Override
    public Item toEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setStatus(itemDTO.getStatus());
        return item;
    }


}
