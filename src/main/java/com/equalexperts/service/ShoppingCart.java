package com.equalexperts.service;

import com.equalexperts.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.javamoney.moneta.Money;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryOperator;
import javax.money.RoundingQueryBuilder;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
public class ShoppingCart {

    private double taxValue;
    private double totalItemTax;
    private List<Item> items;
    private MonetaryAmount totalAmount;
    private MonetaryAmount subAmount;

    public ShoppingCart() {
        MonetaryOperator ROUNDING = Monetary.getRounding(RoundingQueryBuilder.of()
                                                                             .setScale(2)
                                                                             .set(RoundingMode.HALF_UP)
                                                                             .build());
        items = new ArrayList<>();
        totalAmount = Money.of(0, "GBP").with(ROUNDING);
        subAmount = Money.of(0, "GBP").with(ROUNDING);
    }

    public void addItemToBasket(Item item){
        items.add(item);
    }

    public List<Item> retrieveShoppingCartItems(){
        return items;
    }

    public MonetaryAmount calculateTotalAmount() {
        items.forEach(item -> totalAmount = totalAmount.add(item.getUnitPrice().multiply(item.getQuantity())));

        if (taxValue() != 0.00) {
            totalAmount = totalAmount.add(Money.of(totalItemTax, "GBP"));
        }

        return totalAmount;
    }

    public double totalItemTax() {
        items.forEach(item -> subAmount = subAmount.add(item.getUnitPrice().multiply(item.getQuantity())));
        double itemTotalTax = subAmount.multiply(taxValue() / 100).getNumber().doubleValue();
        totalItemTax = new BigDecimal(String.valueOf(itemTotalTax)).setScale(2, RoundingMode.HALF_UP)
                                                                   .doubleValue();
        return totalItemTax;
    }

    public double taxValue() {
        return taxValue;
    }

    public void setTaxValue(double taxValue) {
        this.taxValue = taxValue;
    }
}
