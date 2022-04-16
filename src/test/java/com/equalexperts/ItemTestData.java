package com.equalexperts;

import com.equalexperts.model.Item;
import com.equalexperts.service.ShoppingCart;
import org.javamoney.moneta.Money;

public final class ItemTestData {
    public static void load5DoveItemsIntoShoppingCart(ShoppingCart shoppingCart) {
        Item doveItem = Item.builder()
                            .itemName("Dove Soap")
                            .quantity(1)
                            .unitPrice(Money.of(39.99, "GBP"))
                            .build();

        shoppingCart.addItemToBasket(doveItem);
        shoppingCart.addItemToBasket(doveItem);
        shoppingCart.addItemToBasket(doveItem);
        shoppingCart.addItemToBasket(doveItem);
        shoppingCart.addItemToBasket(doveItem);
    }

    public static void load3DoveItemsIntoShoppingCart(ShoppingCart shoppingCart) {
        Item secondDoveItem = Item.builder()
                                  .itemName("Dove Soap")
                                  .quantity(1)
                                  .unitPrice(Money.of(39.99, "GBP"))
                                  .build();
        shoppingCart.addItemToBasket(secondDoveItem);
        shoppingCart.addItemToBasket(secondDoveItem);
        shoppingCart.addItemToBasket(secondDoveItem);
    }
}
