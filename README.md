# positional-tweak

## Transform your positional string to regular Java's POJOs files. No more subtrings done by hand

### Requirements

a) Import this project;

b) Your POJOs MUST have a default constructor;


### Steps to use:

#### 1) Annotate your Java Pojos with positional-weak

Example with "primitive/wrapper" objects:
```
public class AccountPojoWithAllSupportedPrimitives {

    @PositionalField(length = 10)
    private Long id;

    @PositionalField(length = 20)
    private String clientName;

    @PositionalField(length = 3)
    private Integer age;

    @PositionalField(length = 10,
            monetaryInfo = @PositionalMonetary(numberOfDecimalPlaces = 2))
    private BigDecimal balance;

```

Example with collections:
```
    @PositionalField(length = 2,
            listInfo = @PositionalList(occurrences = 2))
    private List<Integer> age;

```

#### 2) Call the positional-tweak engine informing your positional string and the class that represents the string

```
AccountPojoWithAllSupportedPrimitives accountPojoGenerated =
        new PositionalParser().toPojo("0000001010MARCIO GURGEL       0330000007777",
                AccountPojoWithAllSupportedPrimitives.class);

```

#### 3) Your pojo attributes will be filled with the corresponding positional content




### Supported "primitive" fields:

The following fields are abble to be parsed from a positional string if they're used inside your java POJO or as a `java.util.List` content.

- java.lang.String;
- java.lang.Long;
- java.lang.Integer;
- java.math.BigDecimal;
