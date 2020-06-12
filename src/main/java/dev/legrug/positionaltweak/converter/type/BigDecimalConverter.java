package dev.legrug.positionaltweak.converter.type;

import dev.legrug.positionaltweak.converter.Converter;
import dev.legrug.positionaltweak.parser.vo.PositionalFieldVO;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BigDecimalConverter implements Converter<BigDecimal>
{

   public static final int DECIMAL_NOT_INFORMED = -1;
   public static final String REGEX_THAT_THREATS_ZERO_ON_LEFT = "^0+(?!$)";

   @Override
   public BigDecimal fromPositional(String input, PositionalFieldVO positionalFieldVO)
   {
      String inputWithoutZerosOnLeft = input.replaceAll(REGEX_THAT_THREATS_ZERO_ON_LEFT, "");

      int numberOfDecimalPlaces = positionalFieldVO.getPositionalMonetaryVO().getNumberOfDecimalPlaces();
      if (numberOfDecimalPlaces != DECIMAL_NOT_INFORMED)
      {
         String valueWithSeparator = insertDotAsDecimalSeparator(inputWithoutZerosOnLeft, numberOfDecimalPlaces);
         return new BigDecimal(valueWithSeparator);
      }
      return new BigDecimal(inputWithoutZerosOnLeft);
   }

   private String insertDotAsDecimalSeparator(String inputWithoutZerosOnLeft, int numberOfDecimalPlaces)
   {
      return inputWithoutZerosOnLeft
               .replaceAll("^(.{" + numberOfDecimalPlaces + "})", "$1" + ".");
   }

   @Override
   public String toPositional(BigDecimal pojoFieldValue, PositionalFieldVO positionalFieldVO)
   {
      String rawPojoValueAsString = pojoFieldValue.toString();
      String correctNumberOfZeros = Stream.generate(() -> "0").limit(positionalFieldVO.getLength() - rawPojoValueAsString.length())
               .collect(Collectors.joining());
      return correctNumberOfZeros + rawPojoValueAsString;
   }

}
