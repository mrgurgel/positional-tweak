package dev.legrug.positionaltweak.pojo.account;

import dev.legrug.positionaltweak.annotation.PositionalField;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Adress {

    @PositionalField(length = 10)
    private String zip;
    @PositionalField(length = 50)
    private String location;
}
