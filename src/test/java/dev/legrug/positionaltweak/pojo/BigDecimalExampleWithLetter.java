package dev.legrug.positionaltweak.pojo;

import dev.legrug.positionaltweak.annotation.PositionalField;
import dev.legrug.positionaltweak.annotation.PositionalMonetary;

import java.math.BigDecimal;

public class BigDecimalExampleWithLetter {


    @PositionalField(length = 16, monetaryInfo =
    @PositionalMonetary(decimalSeparator = "B", numberOfDecimalPlaces = 3))
    private BigDecimal money;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
