package com.gmail.aperavoznikau.jd2w3.service;

import java.util.List;

import com.gmail.aperavoznikau.jd2w3.service.model.ItemDTO;

public interface ItemService {

    List<ItemDTO> getItems();

    void add(ItemDTO itemDTO);

    void updateStatus(Long id, String status);
}
