package com.application.model;

import com.application.dto.ShoppingDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "shop")
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userIdentifier;
    private Date date;
    private Float total;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String user_identifier) {
        this.userIdentifier = user_identifier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static Shopping convert(ShoppingDTO shoppingDTO) {
        Shopping shopping = new Shopping();
        shopping.setUserIdentifier(shoppingDTO.getUserIdentifier());
        shopping.setDate(shoppingDTO.getDate());
        shopping.setTotal(shoppingDTO.getTotal());
        shopping.setItems(shoppingDTO.getItems().stream().map(Item::convert).collect(Collectors.toList()));
        return shopping;
    }
}
