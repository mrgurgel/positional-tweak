# positional-tweak

## Converts a given positional string into a instance of POJO using Java Annotations

### Requirements

a) Import this project;

b) Java 8;

c) Your POJOs, that will be annotated with positional-tweak annotations, MUST have a default constructor, and every attribute  must have the "setter" method;


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
        new PositionalTweak().feedPojo("0000001010MARCIO GURGEL       0330000007777",
                AccountPojoWithAllSupportedPrimitives.class);

```

#### 3) Your pojo attributes will be ready for usage:

```
System.out.println(accountPojoGenerated.getClientName())
-- Result: MARCIO GURGEL
```


### Supported "primitive" fields:

The following fields are abble to be parsed from a positional string if they're used inside your java POJO or as a `java.util.List` content.

- java.lang.String;
- java.lang.Long;
- java.lang.Integer;
- java.math.BigDecimal;


### Upcoming features

- Allow the usage of YAML as an alternative to Java Annotations. It will be useful when you dont have access to the source-code of your POJO
- Convert list attributes into positional