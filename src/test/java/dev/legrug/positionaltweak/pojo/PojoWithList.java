package dev.legrug.positionaltweak.pojo;

import dev.legrug.positionaltweak.annotation.PositionalField;
import dev.legrug.positionaltweak.annotation.PositionalList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PojoWithList {

    @PositionalField(
            listInfo = @PositionalList(occurrences = 3))
    private List<FlatPojo> listOfFlatPojos;

}
