package dev.legrug.positionaltweak.pojo.account;

import dev.legrug.positionaltweak.annotation.PositionalField;
import dev.legrug.positionaltweak.annotation.PositionalList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @EqualsAndHashCode @ToString
public class UserAccountWithList {

    @PositionalField(
            listInfo = @PositionalList(occurrences = 2))
    private List<Balance> balances;
    @PositionalField(
            listInfo = @PositionalList(occurrences = 2))
    private List<Adress> adresses;

}
