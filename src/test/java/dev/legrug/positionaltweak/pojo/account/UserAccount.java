package dev.legrug.positionaltweak.pojo.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode @ToString
public class UserAccount {

    private Balance balance;
    private Adress adress;

}
