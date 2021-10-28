package com.application.model;

import com.application.dto.ItemDTO;

import javax.persistence.Embeddable;

@Embeddable
public class Item {

    private String productIdentifier;
    private Float price;

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public static Item convert(ItemDTO itemDTO) {
        Item item = new Item();
        item.setPrice(itemDTO.getPrice());
        item.setProductIdentifier(itemDTO.getProductIdentifier());
        return item;
    }
}
