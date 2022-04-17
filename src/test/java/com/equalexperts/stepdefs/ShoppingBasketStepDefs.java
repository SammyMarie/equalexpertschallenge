package com.equalexperts.stepdefs;

import com.equalexperts.model.Item;
import com.equalexperts.service.ShoppingCart;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.javamoney.moneta.Money;

import java.util.stream.IntStream;

import static com.equalexperts.data.ItemTestData.addAnotherDoveItemToShoppingCart;
import static com.equalexperts.data.ItemTestData.addAxePrice;
import static com.equalexperts.data.ItemTestData.addAxeQuantity;
import static com.equalexperts.data.ItemTestData.addDovePrice;
import static com.equalexperts.data.ItemTestData.addDoveQuantity;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ShoppingBasketStepDefs {

    private final ShoppingCart shoppingCart = new ShoppingCart();
    private final Item doveItem = new Item();
    private final Item axeItem = new Item();


    @Given("An empty shopping cart")
    public void anEmptyShoppingCart() {
        shoppingCart.retrieveShoppingCartItems();
    }

    @Given("A product, Dove Soap with a unit price of {double}")
    public void aProductDoveSoapWithAUnitPriceOf(double itemPrice) {
        addDovePrice(doveItem, itemPrice);
    }

    @Given("Another product, Axe Deo with a unit price of {double}")
    public void anotherProductAxeDeoWithAUnitPriceOf(Double itemPrice) {
        addAxePrice(axeItem, itemPrice);
    }

    @Given("A tax rate of {double}%")
    public void aTaxRateOf(Double itemTax) {
        shoppingCart.setTaxValue(itemTax);
    }

    @When("The user adds {int} Dove Soaps to the shopping cart")
    public void theUserAddsDoveSoapsToTheShoppingCart(int itemQuantity) {
        for (int index = 1; index <= itemQuantity; index++) {
            addDoveQuantity(doveItem);
            shoppingCart.addItemToBasket(doveItem);
        }
    }

    @When("Adds another {int} Dove Soaps to the shopping cart")
    public void addsAnotherDoveSoapsToTheShoppingCart(int itemQuantity) {
        IntStream.rangeClosed(1, itemQuantity)
                 .mapToObj(index -> addAnotherDoveItemToShoppingCart())
                 .forEach(shoppingCart::addItemToBasket);
    }

    @When("Adds {int} Axe Deo’s to the shopping cart")
    public void addsAxeDeoSToTheShoppingCart(Integer itemQuantity) {
        for (int index = 1; index <= itemQuantity; index++) {
            addAxeQuantity(axeItem);
            shoppingCart.addItemToBasket(axeItem);
        }
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
                    .filter(item -> item.getItemName().equals("Dove Soap"))
                    .forEach(item -> assertThat(item.getUnitPrice().getNumber()
                                                    .doubleValue()).isEqualTo(itemPrice));
    }

    @Then("The shopping cart’s total price should equal {double}")
    public void theShoppingCartSTotalPriceShouldEqual(double itemPrice) {
        assertThat(shoppingCart.calculateTotalAmount()).isEqualTo(Money.of(itemPrice, "GBP"));
    }

    @Then("The total tax amount should equal {double}")
    public void theTotalTaxAmountShouldEqual(double totalItemTax) {
        assertThat(shoppingCart.totalItemTax()).isEqualTo(totalItemTax);
    }

    @Then("The shopping cart should contain {int} Axe Deo’s each with a unit price of {double}")
    public void theShoppingCartShouldContainAxeDeoSEachWithAUnitPriceOf(Integer itemQuantity, Double itemPrice) {
        long doveQuantity = shoppingCart.retrieveShoppingCartItems()
                                        .stream()
                                        .filter(item -> item.getItemName().equals("Axe Deo"))
                                        .count();
        assertThat((int) doveQuantity).isEqualTo(itemQuantity);

        shoppingCart.retrieveShoppingCartItems()
                    .stream()
                    .filter(item -> item.getItemName().equals("Axe Deo"))
                    .forEach(item -> assertThat(item.getUnitPrice().getNumber()
                                                    .doubleValue()).isEqualTo(itemPrice));
    }
}
