package com.gmail.aperavoznikau.jd2w3.service.converter;

import com.gmail.aperavoznikau.jd2w3.data.model.Item;
import com.gmail.aperavoznikau.jd2w3.service.model.ItemDTO;

public interface ItemConverter {

    ItemDTO toDTO(Item item);

    Item toEntity(ItemDTO item);

}
