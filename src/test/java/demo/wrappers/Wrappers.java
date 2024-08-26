package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     public static void click(WebElement e)
     {
        e.click();
     }
     public static long convertViewsToNumber(String viewonlynumberwithsuffice1)
     {
      String numberWithoutQuotes = viewonlynumberwithsuffice1.replaceAll("'^\"|\"$","");
      numberWithoutQuotes = numberWithoutQuotes.trim();

      //convert the views from 1.3M to 1300000
      if(numberWithoutQuotes.endsWith("K"))
      {
         String viewnumber = numberWithoutQuotes.substring(0, numberWithoutQuotes.length()-1).trim();
         return (long) (Double.parseDouble(viewnumber)*1000);

      }else if(numberWithoutQuotes.endsWith("M"))
      {
         String viewnumber = numberWithoutQuotes.substring(0, numberWithoutQuotes.length()-1).trim();
         return (long) (Double.parseDouble(viewnumber)*1000000);

      }
      else
      {
         return Long.parseLong(numberWithoutQuotes);
      }

     }
}
