package com.equalexperts.service;

import com.equalexperts.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.javamoney.moneta.Money;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryOperator;
import javax.money.RoundingQueryBuilder;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
public class ShoppingCart {

    private List<Item> items;
    private MonetaryAmount totalAmount;

    public ShoppingCart() {
        MonetaryOperator ROUNDING = Monetary.getRounding(RoundingQueryBuilder.of()
                                                                             .setScale(2)
                                                                             .set(RoundingMode.HALF_EVEN)
                                                                             .build());
        items = new ArrayList<>();
        totalAmount = Money.of(0, "GBP").with(ROUNDING);
    }

    public void addItemToBasket(Item item){
        items.add(item);
    }

    public List<Item> retrieveShoppingCartItems(){
        return items;
    }

    public MonetaryAmount calculateTotalAmount(){
        items.forEach(item -> totalAmount = totalAmount.add(item.getUnitPrice().multiply(item.getQuantity())));
        return totalAmount;
    }
}
