package dev.legrug.positionalparser.pojo;

import dev.legrug.positionalparser.annotation.PositionalField;
import dev.legrug.positionalparser.annotation.PositionalList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PojoWithList {

    @PositionalList(occurrences = 3)
    private List<FlatPojo> listOfFlatPojos;

}
