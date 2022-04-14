package com.equalexperts.stepdefs;

import com.equalexperts.model.Item;
import com.equalexperts.service.ShoppingCart;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.javamoney.moneta.Money;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ShoppingBasketStepDefs {

    private final ShoppingCart shoppingCart = new ShoppingCart();
    private final Item item = new Item();

    @Given("An empty shopping cart")
    public void anEmptyShoppingCart() {
        shoppingCart.retrieveShoppingCartItems();
    }

    @Given("A product, Dove Soap with a unit price of {double}")
    public void aProductDoveSoapWithAUnitPriceOf(Double itemPrice) {
        item.setItemName("Dove Soap");
        item.setUnitPrice(Money.of(itemPrice, "GBP"));
    }

    @When("The user adds {int} Dove Soaps to the shopping cart")
    public void theUserAddsDoveSoapsToTheShoppingCart(Integer productQuantity) {
        item.setItemName("Dove Soap");
        item.setQuantity(productQuantity);
        shoppingCart.addItemToBasket(item);
    }

    @Then("The shopping cart should contain {int} Dove Soaps each with a unit price of {double}")
    public void theShoppingCartShouldContainDoveSoapsEachWithAUnitPriceOf(Integer itemQuantity,
                                                                          Double itemPrice) {
        shoppingCart.retrieveShoppingCartItems().forEach((K, V) -> {
            assertThat(K.getQuantity()).isEqualTo(itemQuantity);
            assertThat(K.getUnitPrice().getNumber().doubleValue()).isEqualTo(itemPrice);
        });
    }

    @Then("The shopping cart’s total price should equal {double}")
    public void theShoppingCartSTotalPriceShouldEqual(Double itemPrice) {
        assertThat(shoppingCart.calculateTotalAmount()).isEqualTo(Money.of(itemPrice, "GBP"));
    }
}
