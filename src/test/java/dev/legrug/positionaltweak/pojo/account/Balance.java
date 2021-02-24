package dev.legrug.positionaltweak.pojo.account;

import dev.legrug.positionaltweak.annotation.PositionalField;
import dev.legrug.positionaltweak.annotation.PositionalMonetary;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class Balance {

    @PositionalField(length = 10, monetaryInfo =
        @PositionalMonetary(decimalSeparator = ".", numberOfDecimalPlaces = 2))
    private BigDecimal currentBalance;

    @PositionalField(length = 10, monetaryInfo =
    @PositionalMonetary(decimalSeparator = ".", numberOfDecimalPlaces = 2))
    private BigDecimal desiredBalance;
}
