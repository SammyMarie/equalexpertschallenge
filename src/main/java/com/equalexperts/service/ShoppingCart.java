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
import java.util.HashMap;
import java.util.Map;

@Builder
@AllArgsConstructor
public class ShoppingCart {

    private Map<Item, Integer> itemMap;
    private MonetaryAmount totalAmount;

    public ShoppingCart() {
        itemMap = new HashMap<>();
        MonetaryOperator ROUNDING = Monetary.getRounding(RoundingQueryBuilder.of()
                                                                             .setScale(2)
                                                                             .set(RoundingMode.UP)
                                                                             .build());
        totalAmount = Money.of(0, "GBP").with(ROUNDING);
    }

    public void addItemToBasket(Item item) {
        itemMap.put(item, itemMap.containsKey(item) ? itemMap.get(item) + 1 : 1);
    }

    public Map<Item, Integer> retrieveShoppingCartItems() {
        itemMap.forEach((K, V) -> {
            if (V > K.getQuantity()) {
                K.setQuantity(V);
            }
        });

        return itemMap;
    }

    public MonetaryAmount calculateTotalAmount() {
        retrieveShoppingCartItems().keySet()
                          .forEach(item -> totalAmount = totalAmount.add(item.getUnitPrice()
                                                                             .multiply(item.getQuantity())));
        return totalAmount;
    }
}
