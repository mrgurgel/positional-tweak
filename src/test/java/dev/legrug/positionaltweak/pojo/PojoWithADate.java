package dev.legrug.positionaltweak.pojo;

import dev.legrug.positionaltweak.annotation.PositionalField;

import java.util.Date;

public class PojoWithADate
{
   @PositionalField(length = 8, pattern = "yyyyMMdd")
   private Date someRandomDate = new Date();

   public Date getSomeRandomDate()
   {
      return someRandomDate;
   }

   public void setSomeRandomDate(Date someRandomDate)
   {
      this.someRandomDate = someRandomDate;
   }
}
