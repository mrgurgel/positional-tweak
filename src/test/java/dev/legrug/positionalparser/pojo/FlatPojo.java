package dev.legrug.positionalparser.pojo;

import dev.legrug.positionalparser.annotation.PositionalData;

/**
 * Flat pojo example
 * @author Márcio Gurgel (marcio.rga@gmail.com)
 *
 */
public class FlatPojo 
{
    @PositionalData(length = 10)
    private String firstName;
    
    @PositionalData(length = 10)
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
    
    
    
}
