package dev.legrug.positionaltweak.pojo;

import dev.legrug.positionaltweak.annotation.PositionalField;
import dev.legrug.positionaltweak.annotation.PositionalMonetary;

import java.math.BigDecimal;

public class BigDecimalExampleWithDot {


    @PositionalField(length = 15, monetaryInfo =
    @PositionalMonetary(decimalSeparator = ".", numberOfDecimalPlaces = 2))
    private BigDecimal money;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
