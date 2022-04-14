package com.equalexperts;

import com.equalexperts.model.Item;
import com.equalexperts.service.ShoppingCart;
import org.javamoney.moneta.Money;

public final class ItemTestData {

    public static ShoppingCart load5DovesIntoShoppingCart(){
        ShoppingCart shoppingCart = new ShoppingCart();

        Item firstItem = Item.builder()
                             .itemName("Dove Soap")
                             .quantity(1)
                             .unitPrice(Money.of(39.99, "GBP"))
                             .build();
        Item secondItem = Item.builder()
                              .itemName("Dove Soap")
                              .quantity(1)
                              .unitPrice(Money.of(39.99, "GBP"))
                              .build();
        Item thirdItem = Item.builder()
                             .itemName("Dove Soap")
                             .quantity(1)
                             .unitPrice(Money.of(39.99, "GBP"))
                             .build();
        Item fourthItem = Item.builder()
                              .itemName("Dove Soap")
                              .quantity(1)
                              .unitPrice(Money.of(39.99, "GBP"))
                              .build();
        Item fifthItem = Item.builder()
                             .itemName("Dove Soap")
                             .quantity(1)
                             .unitPrice(Money.of(39.99, "GBP"))
                             .build();

        /* add the item to the shoppingCart */
        shoppingCart.addItemToBasket(firstItem);
        shoppingCart.addItemToBasket(secondItem);
        shoppingCart.addItemToBasket(thirdItem);
        shoppingCart.addItemToBasket(fourthItem);
        shoppingCart.addItemToBasket(fifthItem);

        return shoppingCart;
    }
}
