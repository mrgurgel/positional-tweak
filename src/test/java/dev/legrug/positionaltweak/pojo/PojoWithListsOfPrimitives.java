package dev.legrug.positionaltweak.pojo;

import dev.legrug.positionaltweak.annotation.PositionalField;
import dev.legrug.positionaltweak.annotation.PositionalList;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PojoWithListsOfPrimitives {

    @PositionalField(length = 2,
            listInfo = @PositionalList(occurrences = 2))
    private List<Long> id;

    @PositionalField(length = 2,
            listInfo = @PositionalList(occurrences = 2))
    private List<String> clientName;

    @PositionalField(length = 2,
            listInfo = @PositionalList(occurrences = 2))
    private List<Integer> age;

    @PositionalField(length = 3,
            listInfo = @PositionalList(occurrences = 2))
    private List<BigDecimal> balance;

}
