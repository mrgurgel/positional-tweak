package dev.legrug.positionalparser.pojo;

import dev.legrug.positionalparser.annotation.PositionalField;
import dev.legrug.positionalparser.annotation.PositionalMonetary;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AccountPojoWithAllSupportedPrimitives {

    @PositionalField(length = 10)
    private Long id;

    @PositionalField(length = 20)
    private String clientName;

    @PositionalField(length = 3)
    private Integer age;

    @PositionalField(length = 10)
    @PositionalMonetary(numberOfDecimalPlaces = 2)
    private BigDecimal balance;

}
