package com.equalexperts.service;

import com.equalexperts.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.equalexperts.ItemTestData.loadItemsIntoShoppingCart;
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
        shoppingCart = loadItemsIntoShoppingCart();
        assertThat(shoppingCart.retrieveShoppingCartItems()).isNotEmpty();
    }

    @Test
    void calculateTotalAmount_shouldSucceed(){

    }
}
