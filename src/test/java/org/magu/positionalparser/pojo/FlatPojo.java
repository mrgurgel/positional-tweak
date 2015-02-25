package org.magu.positionalparser.pojo;

import org.magu.positionalparser.annotation.PositionalData;

public class FlatPojo 
{
    @PositionalData(position = 1, length = 10)
    private String firstName;
    
    @PositionalData(position = 1, length = 10)
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
