package dev.legrug.positionalparser.pojo;

import dev.legrug.positionalparser.annotation.PositionalData;
import lombok.*;

import java.util.Objects;

/**
 * Flat pojo example
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter @Setter
public class FlatPojo 
{
    @PositionalData(length = 10)
    private String firstName;
    
    @PositionalData(length = 10)
    private String lastName;


}
