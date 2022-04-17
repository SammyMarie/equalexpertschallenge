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

    public static void load2AxeItemsIntoShoppingCart(ShoppingCart shoppingCart) {
        Item secondDoveItem = Item.builder()
                                  .itemName("Axe Deo")
                                  .quantity(1)
                                  .unitPrice(Money.of(99.99, "GBP"))
                                  .build();

        shoppingCart.addItemToBasket(secondDoveItem);
        shoppingCart.addItemToBasket(secondDoveItem);
    }

    public static void addDovePrice(Item item, double itemPrice) {
        item.setItemName("Dove Soap");
        item.setUnitPrice(Money.of(itemPrice, "GBP"));
    }

    public static void addDoveQuantity(Item item) {
        item.setItemName("Dove Soap");
        item.setQuantity(1);
    }

    public static Item addAnotherDoveItemToShoppingCart() {
        return Item.builder().itemName("Dove Soap")
                   .quantity(1)
                   .unitPrice(Money.of(39.99, "GBP"))
                   .build();
    }

    public static void addAxePrice(Item item, Double itemPrice) {
        item.setItemName("Axe Deo");
        item.setUnitPrice(Money.of(itemPrice, "GBP"));
    }

    public static void addAxeQuantity(Item item) {
        item.setItemName("Axe Deo");
        item.setQuantity(1);
    }
}
