package dev.legrug.positionaltweak.bankpaymentexample;

import dev.legrug.positionaltweak.annotation.PositionalField;
import dev.legrug.positionaltweak.annotation.PositionalList;
import dev.legrug.positionaltweak.annotation.PositionalMonetary;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class PaymentPojo {

    @PositionalField(length = 20)
    private String clientName;

    @PositionalField(length = 10, monetaryInfo = @PositionalMonetary(decimalSeparator = ".", numberOfDecimalPlaces = 2))
    private BigDecimal transactionValue;

    @PositionalField(length = 20)
    private String store;

    @PositionalField(length = 15, listInfo = @PositionalList(occurrences = 3))
    private List<String> purchasedItems;



}
