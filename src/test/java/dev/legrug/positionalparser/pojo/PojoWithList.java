package dev.legrug.positionalparser.pojo;

import dev.legrug.positionalparser.annotation.PositionalData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PojoWithList {

    @PositionalData(length = 3)
    private List<FlatPojo> listOfFlatPojos;

}
