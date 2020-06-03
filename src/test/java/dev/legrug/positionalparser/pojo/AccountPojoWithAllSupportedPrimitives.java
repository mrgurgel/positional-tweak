package dev.legrug.positionalparser.pojo;

import dev.legrug.positionalparser.annotation.PositionalData;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AccountPojoWithAllSupportedPrimitives {

    @PositionalData(length = 10)
    private Long id;

    @PositionalData(length = 20)
    private String clientName;

    @PositionalData(length = 3)
    private Integer age;

    @PositionalData(length = 10, precision = 2)
    private BigDecimal balance;

}
