package dev.legrug.positionaltweak.pojo;

import dev.legrug.positionaltweak.annotation.PositionalField;
import lombok.*;

/**
 * Flat pojo example
 * @author Marcio Gurgel (marcio.rga@gmail.com)
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter @Setter
public class FlatPojo 
{
    @PositionalField(length = 10)
    private String firstName;
    
    @PositionalField(length = 10)
    private String lastName;


}
