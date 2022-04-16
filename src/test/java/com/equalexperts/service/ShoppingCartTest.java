package com.equalexperts.service;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.equalexperts.ItemTestData.load3DoveItemsIntoShoppingCart;
import static com.equalexperts.ItemTestData.load5DoveItemsIntoShoppingCart;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ShoppingCartTest {

    private ShoppingCart shoppingCart;

    @BeforeEach
    void setup() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    void emptyShoppingCart_shouldSucceed() {
        assertThat(shoppingCart.retrieveShoppingCartItems()).isEmpty();
    }

    @Test
    void add5DoveItemsToShoppingCart_shouldSucceed() {
        load5DoveItemsIntoShoppingCart(shoppingCart);
        assertThat(shoppingCart.retrieveShoppingCartItems()).isNotEmpty();

        long doveQuantity = shoppingCart.retrieveShoppingCartItems()
                                        .stream()
                                        .filter(item -> item.getItemName().equals("Dove Soap"))
                                        .count();
        assertThat(doveQuantity).isEqualTo(5);
    }

    @Test
    void calculateTotalAmountFor5Doves_shouldSucceed() {
        load5DoveItemsIntoShoppingCart(shoppingCart);
        assertThat(shoppingCart.calculateTotalAmount()).isEqualTo(Money.of(199.95, "GBP"));
    }

    @Test
    void add5DoveItemsThen3MoreToShoppingCart_shouldSucceed() {
        load5DoveItemsIntoShoppingCart(shoppingCart);
        load3DoveItemsIntoShoppingCart(shoppingCart);
        assertThat(shoppingCart.retrieveShoppingCartItems()).isNotEmpty();

        long doveQuantity = shoppingCart.retrieveShoppingCartItems()
                                        .stream()
                                        .filter(item -> item.getItemName().equals("Dove Soap"))
                                        .count();
        assertThat(doveQuantity).isEqualTo(8);
    }

    @Test
    void calculateTotalAmountFor8Doves_shouldSucceed() {
        load5DoveItemsIntoShoppingCart(shoppingCart);
        load3DoveItemsIntoShoppingCart(shoppingCart);
        assertThat(shoppingCart.calculateTotalAmount()).isEqualTo(Money.of(319.92, "GBP"));
    }
}
