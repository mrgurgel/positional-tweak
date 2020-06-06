package dev.legrug.positionaltweak.pojo;

import dev.legrug.positionaltweak.annotation.PositionalField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PojoWithListAndWrongAnnotation {

    @PositionalField
    private List<String> listOfFlatPojos;

}
