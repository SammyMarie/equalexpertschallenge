package com.equalexperts.service;

import com.equalexperts.model.Item;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.equalexperts.ItemTestData.load5DovesIntoShoppingCart;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ShoppingCartTest {

    private ShoppingCart shoppingCart;
    private Item item;

    @BeforeEach
    void setup(){
        shoppingCart = new ShoppingCart();
        item = new Item();
    }

    @Test
    void emptyShoppingCart_shouldSucceed(){
        assertThat(shoppingCart.retrieveShoppingCartItems()).isEmpty();
    }

    @Test
    void addItemToShoppingCart_shouldSucceed(){
        shoppingCart = load5DovesIntoShoppingCart();
        assertThat(shoppingCart.retrieveShoppingCartItems()).isNotEmpty();
        shoppingCart.retrieveShoppingCartItems().forEach((K, V) -> assertThat(K.getQuantity()).isEqualTo(5));
    }

    @Test
    void calculateTotalAmount_shouldSucceed(){
        shoppingCart = load5DovesIntoShoppingCart();
        assertThat(shoppingCart.calculateTotalAmount()).isEqualTo(Money.of(199.95, "GBP"));
    }
}
