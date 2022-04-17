package com.equalexperts.service;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.equalexperts.data.ItemTestData.load2AxeItemsIntoShoppingCart;
import static com.equalexperts.data.ItemTestData.load3DoveItemsIntoShoppingCart;
import static com.equalexperts.data.ItemTestData.load5DoveItemsIntoShoppingCart;
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
        long firstDoveQuantity = shoppingCart.retrieveShoppingCartItems()
                                             .stream()
                                             .filter(item -> item.getItemName().equals("Dove Soap"))
                                             .count();

        assertThat(firstDoveQuantity).isEqualTo(5);

        load3DoveItemsIntoShoppingCart(shoppingCart);
        long secondDoveQuantity = shoppingCart.retrieveShoppingCartItems()
                                              .stream()
                                              .filter(item -> item.getItemName().equals("Dove Soap"))
                                              .count();
        assertThat(secondDoveQuantity).isEqualTo(8);
    }

    @Test
    void calculateTotalAmountFor8Doves_shouldSucceed() {
        load5DoveItemsIntoShoppingCart(shoppingCart);
        load3DoveItemsIntoShoppingCart(shoppingCart);
        assertThat(shoppingCart.calculateTotalAmount()).isEqualTo(Money.of(319.92, "GBP"));
    }

    @Test
    void add2AxeItemsToShoppingCart_shouldSucceed() {
        load2AxeItemsIntoShoppingCart(shoppingCart);
        assertThat(shoppingCart.retrieveShoppingCartItems()).isNotEmpty();

        long doveQuantity = shoppingCart.retrieveShoppingCartItems()
                                        .stream()
                                        .filter(item -> item.getItemName().equals("Axe Deo"))
                                        .count();
        assertThat(doveQuantity).isEqualTo(2);
    }

    @Test
    void calculateTotalItemTax_shouldSucceed() {
        load3DoveItemsIntoShoppingCart(shoppingCart);
        load2AxeItemsIntoShoppingCart(shoppingCart);
        shoppingCart.setTaxValue(12.5);
        assertThat(shoppingCart.totalItemTax()).isEqualTo(39.99);
    }

    @Test
    void calculateAdjustedItemTotalPrice_shouldSucceed() {
        load3DoveItemsIntoShoppingCart(shoppingCart);
        load2AxeItemsIntoShoppingCart(shoppingCart);
        shoppingCart.setTaxValue(12.5);
        shoppingCart.totalItemTax();
        assertThat(shoppingCart.calculateTotalAmount()).isEqualTo(Money.of(359.94, "GBP"));
    }
}
