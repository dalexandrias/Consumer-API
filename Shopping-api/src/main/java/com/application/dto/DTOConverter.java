package com.application.dto;

import com.application.model.Item;
import com.application.model.Shopping;

import java.util.stream.Collectors;

public class DTOConverter {

    public static ItemDTO convert(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(item.getPrice());
        itemDTO.setProductIdentifier(item.getProductIdentifier());
        return itemDTO;
    }

    public static ShoppingDTO convert(Shopping shopping) {
        ShoppingDTO shoppingDTO = new ShoppingDTO();
        shoppingDTO.setDate(shopping.getDate());
        shoppingDTO.setItems(shopping.getItems().stream().map(DTOConverter::convert).collect(Collectors.toList()));
        shoppingDTO.setUserIdentifier(shopping.getUserIdentifier());
        shoppingDTO.setTotal(shopping.getTotal());
        return shoppingDTO;
    }
}
