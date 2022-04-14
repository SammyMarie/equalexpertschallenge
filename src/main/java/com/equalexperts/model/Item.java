package com.equalexperts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String itemName;
    private int quantity;
    private MonetaryAmount unitPrice;

    @Override
    public String toString() {
        return "Name " + "  Quantity " + " UnitPrice \n" +
               getItemName() + "  " + getQuantity() + "     " + getUnitPrice();
    }
}
