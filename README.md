# positional-tweak

## Converts a given positional string into a instance of POJO using Java Annotations

### Requirements

a) Import this project;

b) Java 8;

c) Your POJOs, that will be annotated with positional-tweak annotations, MUST have a default constructor, and every attribute  must have the "setter" method;


### Steps to use:

- Annotate your POJO fields, with our annotations

UserAccount.java
```java
@Getter @Setter @EqualsAndHashCode @ToString
public class UserAccount {

    private Balance balance;
    private Adress adress;

}
```


Balance.java
```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class Balance {

    @PositionalField(length = 10, monetaryInfo =
        @PositionalMonetary(decimalSeparator = ".", numberOfDecimalPlaces = 2))
    private BigDecimal currentBalance;

    @PositionalField(length = 10, monetaryInfo =
    @PositionalMonetary(decimalSeparator = ".", numberOfDecimalPlaces = 2))
    private BigDecimal desiredBalance;
}
```

Adress.java
```java
@ToString @EqualsAndHashCode @AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class Adress {

    @PositionalField(length = 10)
    private String zip;
    @PositionalField(length = 50)
    private String location;
}

```

_(For more details, this project contains unit tests that are (almost?) self-explanatory)_

#### Converting from a positional to a POJO

Java fragment:
```java
String positional = "0000000.100100000.0072900     Brasilia                                          ";
UserAccount userAccountFromPositional = new PositionalTweak().feedPojo(positional, UserAccount.class);

System.out.println(userAccountFromPositional.getBalance().getCurrentBalance());

```

#### Converting from POJO positional to a positional

Java fragment:
```java
UserAccount userAccount = new UserAccount();
userAccount.setBalance(new Balance(new BigDecimal("0.10"), new BigDecimal("100000.00")));
userAccount.setAdress(new Adress("72900", "Brasilia"));

String generatedPositional = new PositionalTweak().generatePositional(userAccount);
System.out.println(generatedPositional);
```
