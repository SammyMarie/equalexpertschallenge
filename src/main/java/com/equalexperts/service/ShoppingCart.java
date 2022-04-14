package com.equalexperts.service;

import com.equalexperts.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

@Builder
@AllArgsConstructor
public class ShoppingCart {

    private Map<Item, Integer> itemMap;

    public ShoppingCart() {
        itemMap = new HashMap<>();
    }

    public void addItemToBasket(Item item) {
        itemMap.put(item, itemMap.containsKey(item) ? itemMap.get(item) + 1 : 1);
    }

    public Map<Item, Integer> retrieveShoppingCartItems() {
        return itemMap;
    }
}
