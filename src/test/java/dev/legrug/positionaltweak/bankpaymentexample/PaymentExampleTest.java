package dev.legrug.positionaltweak.bankpaymentexample;

import dev.legrug.positionaltweak.parser.PositionalTweak;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaymentExampleTest {

    private static String EXAMPLE_POSITIONAL_STRING =
            "MARCIO GURGEL       0001000.00APPLE P. STORE      IPHONE 6       MACBOOK AIR    APPLE WATCH    ";


    @Test
    public void generatePaymentPojoFromAPositionalString() {
        PaymentPojo paymentPojo = new PositionalTweak()
                .feedPojo(EXAMPLE_POSITIONAL_STRING, PaymentPojo.class);

        Assert.assertEquals(paymentPojo.getClientName(), "MARCIO GURGEL");
    }

    @Test
    public void generatePaymentPositionalFromPojo() {
        PaymentPojo paymentPojo = new PaymentPojo();
        paymentPojo.setClientName("MARCIO GURGEL");
        paymentPojo.setTransactionValue(new BigDecimal("1000.00"));
        paymentPojo.setStore("APPLE P. STORE");
        paymentPojo.setPurchasedItems(
                Stream.of("IPHONE 6", "MACBOOK AIR", "APPLE WATCH").collect(Collectors.toList())
        );

        String generatedPositional = new PositionalTweak().generatePositional(paymentPojo);
        Assert.assertEquals(EXAMPLE_POSITIONAL_STRING, generatedPositional);

    }

}
