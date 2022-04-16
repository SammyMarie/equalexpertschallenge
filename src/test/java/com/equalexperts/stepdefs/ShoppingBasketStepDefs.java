package com.equalexperts.stepdefs;

import com.equalexperts.model.Item;
import com.equalexperts.service.ShoppingCart;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.javamoney.moneta.Money;

import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ShoppingBasketStepDefs {

    private final ShoppingCart shoppingCart = new ShoppingCart();
    private final Item item = new Item();

    @Given("An empty shopping cart")
    public void anEmptyShoppingCart() {
        shoppingCart.retrieveShoppingCartItems();
    }

    @Given("A product, Dove Soap with a unit price of {double}")
    public void aProductDoveSoapWithAUnitPriceOf(double itemPrice) {
        addDovePrice(item, itemPrice);
    }

    @When("The user adds {int} Dove Soaps to the shopping cart")
    public void theUserAddsDoveSoapsToTheShoppingCart(int itemQuantity) {
        for (int index = 1; index <= itemQuantity; index++) {
            addDoveQuantity(item);
            shoppingCart.addItemToBasket(item);
        }
    }

    @When("Adds another {int} Dove Soaps to the shopping cart")
    public void addsAnotherDoveSoapsToTheShoppingCart(int itemQuantity) {
        IntStream.rangeClosed(1, itemQuantity)
                 .mapToObj(index -> addAnotherDoveItemToShoppingCart())
                 .forEach(shoppingCart::addItemToBasket);
    }

    @Then("The shopping cart should contain {int} Dove Soaps each with a unit price of {double}")
    public void theShoppingCartShouldContainDoveSoapsEachWithAUnitPriceOf(int itemQuantity,
                                                                          double itemPrice) {
        long doveQuantity = shoppingCart.retrieveShoppingCartItems()
                                        .stream()
                                        .filter(item -> item.getItemName().equals("Dove Soap"))
                                        .count();
        assertThat((int) doveQuantity).isEqualTo(itemQuantity);

        shoppingCart.retrieveShoppingCartItems()
                    .stream()
                    .filter(item1 -> item.getItemName().equals("Dove Soap"))
                    .forEach(item -> assertThat(item.getUnitPrice().getNumber()
                                                    .doubleValue()).isEqualTo(itemPrice));
    }

    @Then("The shopping cart’s total price should equal {double}")
    public void theShoppingCartSTotalPriceShouldEqual(double itemPrice) {
        assertThat(shoppingCart.calculateTotalAmount()).isEqualTo(Money.of(itemPrice, "GBP"));
    }

    private void addDovePrice(Item item, double itemPrice) {
        item.setItemName("Dove Soap");
        item.setUnitPrice(Money.of(itemPrice, "GBP"));
    }

    private void addDoveQuantity(Item item) {
        item.setItemName("Dove Soap");
        item.setQuantity(1);
    }

    private Item addAnotherDoveItemToShoppingCart() {
        return Item.builder().itemName("Dove Soap")
                   .quantity(1)
                   .unitPrice(Money.of(39.99, "GBP"))
                   .build();
    }
}
