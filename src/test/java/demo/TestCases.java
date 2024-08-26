package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import demo.utils.ExcelDataProvider;
import demo.utils.ExcelReaderUtil;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelReaderUtil { // Lets us read the data
        ChromeDriver driver;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

       @Test
        public void testCase01() throws InterruptedException
        {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
                 driver.get("https://www.youtube.com/");
                 Thread.sleep(2000);
                 SoftAssert sf = new SoftAssert();
                 sf.equals("https://www.youtube.com/");
                 wait.until(ExpectedConditions.urlToBe("https://www.youtube.com/"));
                 WebElement about = driver.findElement(By.xpath("//a[text()='About']"));
                 Actions action = new Actions(driver);
                 action.moveToElement(about).click();
                 action.build().perform();
                 WebElement aboutYouTube = driver.findElement(By.xpath("//h1[contains(text(), 'About YouTube')]"));
                 if(aboutYouTube.isDisplayed())
                 {
                        System.out.println(aboutYouTube.getText());
                 }
                 WebElement paraText = driver.findElement(By.xpath("//p[contains(text(), 'everyone a voice')]"));
                 if(paraText.isDisplayed())
                 {
                        System.out.println(paraText.getText());
                 }      
                
        }
        @Test
        public void testCase02() throws InterruptedException
        {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                 driver.get("https://www.youtube.com/");
                 Thread.sleep(2000);
                 //WebElement hamburger = driver.findElement(By.xpath("//yt-icon-button[@class='style-scope ytd-masthead']//button[@id='button']//yt-icon[@id = 'guide-icon']"));
                 //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//yt-icon-button[@class='style-scope ytd-masthead']//button[@id='button']//yt-icon[@id = 'guide-icon']")));
                 //hamburger.click();
                 
                 WebElement mvoiesElement = driver.findElement(By.xpath("//yt-formatted-string[text()='Films']"));
                 //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//yt-formatted-string[text()='Movies']")));
                 Actions action = new Actions(driver);
                 action.moveToElement(mvoiesElement).build().perform();
                 mvoiesElement.click();

                 List<WebElement> titleElements = driver.findElements(By.xpath("//span[@id='title']"));
                 

                 
                 for(WebElement title : titleElements)
                 {
                        
                        if(title.getText().equals("Top selling"))
                        {
                             WebElement arrowElement = driver.findElement(By.xpath("//button[@aria-label='Next']//div[@class='yt-spec-touch-feedback-shape__fill']"));
                             for(int i = 0; i < 3; i++)
                             {
                                if(arrowElement.isEnabled())
                                {
                                        Wrappers.click(arrowElement);;

                                }
                                
                             }

                        }
                        
                 }
                 SoftAssert sf = new SoftAssert();
                 
                 List<WebElement> ratingElements = driver.findElements(By.xpath("//ytd-badge-supported-renderer[contains(@class,'ytd-grid-movie-renderer')]//div[2][contains(@aria-label,'A')]"));
                 Thread.sleep(2000);
                 List<WebElement> movieCategoriesElement = driver.findElements(By.xpath("//span[contains(@class,'grid-movie-renderer-metadata')]"));
                 Thread.sleep(2000);
                 WebElement movieElement = driver.findElement(By.xpath("//span[contains(@title,'Kung Fu Panda 4')]"));
                 Thread.sleep(2000);
                 //wait.until(ExpectedConditions.textToBePresentInElementValue(movieElement, "Kung Fu Panda"));
                 for(WebElement ratingElement : ratingElements)
                 {
                        String ratingText = ratingElement.getText();
                                
                                sf.assertEquals(ratingText, 'A');
                        
                 }
                 for(WebElement movieCategory : movieCategoriesElement)
                 {
                        String movieCategoryText = movieCategory.getText();
                        sf.assertEquals(movieCategoryText, "Comedy");
                 }
                 

                 
        }
        @Test
        public void testCase03() throws InterruptedException
        {
                Actions action = new Actions(driver);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                 driver.get("https://www.youtube.com/");
                 Thread.sleep(2000);
                 WebElement musicElement = driver.findElement(By.xpath("//a[@title='Music']"));
                 action.moveToElement(musicElement);
                 Wrappers.click(musicElement);
                 action.build().perform();
                 
                 WebElement musicTitles = driver.findElement(By.xpath("//span[contains(text(), 'Biggest Hits')]"));
                 System.out.println("**************************"+musicTitles.getText());
                 action.moveToElement(musicTitles).build().perform();
                 Thread.sleep(2000);
                 List<WebElement> arrowElements = driver.findElements(By.xpath("//*[@id='right-arrow']/ytd-button-renderer/yt-button-shape/button/yt-touch-feedback-shape/div/div[2]"));
                 if(musicTitles.getText().contains("Biggest Hits"))
                 {
                        if(musicTitles.isDisplayed())
                        {
                                //checking if i am inside first section//
                              boolean  status = true;
                              System.out.println(status);
                              //First section ends here

                              //Below code trying to get arrow element and chekcing if displayed then perform click
                               WebElement arrow = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-browse[2]/ytd-two-column-browse-results-renderer/div[1]/ytd-section-list-renderer/div[2]/ytd-item-section-renderer[1]/div[3]/ytd-shelf-renderer/div[1]/div[2]/yt-horizontal-list-renderer/div[3]/ytd-button-renderer/yt-button-shape/button/yt-touch-feedback-shape/div/div[2]"));
                             // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-browse[2]/ytd-two-column-browse-results-renderer/div[1]/ytd-section-list-renderer/div[2]/ytd-item-section-renderer[1]/div[3]/ytd-shelf-renderer/div[1]/div[2]/yt-horizontal-list-renderer/div[3]/ytd-button-renderer/yt-button-shape/button/yt-touch-feedback-shape/div/div[2]")));
                              action.moveToElement(arrow).build().perform();
                              System.out.println("Code working till here");
                                        Thread.sleep(2000);
                                        System.out.println("Click on arrow loop will now happen");
                                        for(int i = 0; i<3;i++)
                                        {
                                                arrow.click();
                                        }
                                //arrow code ends here
                        }
                              
                        
                       // for(int j=0; j < arrowElements.size(); j++)
                       // {
                                
                                      //  WebElement arrow = arrowElements.get(j);
                                       // if(arrow.isDisplayed())
                                       // {
                                               
                                       
                                       // for(int z= 0; z < 3 ; z++)
                                      //  {
                                       // arrow.click();
                                       // }


                                       // }
                           
                        
                       // }
                       

                 }
                 
                 Thread.sleep(2000);
                WebElement extremeElement = driver.findElement(By.xpath("//h3[contains(text(),'Bollywood Dance Hitlist')]"));
                System.out.println(extremeElement.getText());

                

                 WebElement trackElement = driver.findElement(By.xpath("//p[contains(text(),'Bollywood dance tracks')]//parent::div//parent::a/p"));
                       SoftAssert sf = new SoftAssert();
                        sf.assertEquals(trackElement.getText(), "50 tracks");

        }
        @Test
        public void testCase04() throws InterruptedException
        {
                Actions action = new Actions(driver);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                 driver.get("https://www.youtube.com/");
                 Thread.sleep(2000);
                 WebElement newsElement = driver.findElement(By.xpath("//yt-formatted-string[text()='News']"));
                 action.moveToElement(newsElement).click().build().perform();
                 WebElement titleElement = driver.findElement(By.xpath("//span[text()='Latest news posts']"));
                 int sum = 0;
                 
                 if(titleElement.getText().equals("Latest news posts"))
                 {
                        List<WebElement> postTitles = driver.findElements(By.xpath("//yt-formatted-string[@id='home-content-text']"));
                        //yt-formatted-string[@id='home-content-text']/span[contains(@class,'yt-formatted-string')]
                        List<WebElement> imageElement = driver.findElements(By.xpath("//yt-img-shadow[contains(@class,'image-renderer')]"));
                       
                        for(int i = 0; i < 3; i++)
                        //int likesSum = 0;
                        {
                                String title = postTitles.get(i).getText();
                                System.out.println("%%%%%%%%5");
                                System.out.println(title);
                                System.out.println("%%%%%%%%5");
                                
                                // code for body
                                WebElement bodyElement = imageElement.get(i);
                               
                                if(bodyElement.isDisplayed())
                                 {
                                        Wrappers.click(bodyElement);
                                 }
                                 
                                
                                WebElement body = driver.findElement(By.xpath("//div[contains(@class,'backstage-post-renderer')]//div[2]//div//following-sibling::ytd-expander//div//following-sibling::yt-formatted-string//span[1]"));
                                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'backstage-post-renderer')]//div[2]//div//following-sibling::ytd-expander//div//following-sibling::yt-formatted-string//span[1]")));
                                if(body.isDisplayed())
                                {
                                        Thread.sleep(2000);
                                        System.out.println("**************");
                                        System.out.println(body.getText());
                                        System.out.println("**************");
                                     
                                       
                                }
                               
                                //ends here      
                                action.moveToElement(newsElement).click().build().perform();
                                Thread.sleep(2000);
                                
                        }
                       
                      
                        
                        
                        
                 }
                 if(titleElement.getText().equals("Latest news posts"))
                 {
                   List<WebElement> likeElement = driver.findElements(By.xpath("//span[contains(@id,'vote-count-middle')]"));

                 for(int i =0; i< 3; i++)
                        {
                                int value = Integer.parseInt(likeElement.get(i).getText().trim());
                                Thread.sleep(2000);
                                //System.out.println(value);
                               // Thread.sleep(2000);
                                sum = sum + value;   

                        }
                        System.out.println("Likes sum is ***********************:-" + sum);

                 }




        //span[text()='Latest news posts']

        //yt-formatted-string[text()='News']
        
        }
        @Test(enabled = true, dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
        public void testCase05(String searchname) throws InterruptedException
        {      long targetNumber = 0;
                long targetToAchieve = 100000000;
                Actions action = new Actions(driver);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                 driver.get("https://www.youtube.com/");
                 Thread.sleep(2000);
                 WebElement searElement = driver.findElement(By.xpath("//input[@id='search']"));
                 searElement.click();
                action.moveToElement(searElement).sendKeys(searchname).sendKeys(Keys.ENTER).build().perform();
                 Thread.sleep(10000);
                 List<WebElement> viewsElement = driver.findElements(By.xpath("//*[@id=\"metadata-line\"]/span[1]"));
                for(WebElement viewElement : viewsElement)
                {
                        String views = viewElement.getText();
                        String viewNumber = views.split(" ")[0];
                       // System.out.println("The first value of string" + viewNumber);
                        String numbersOnly = viewNumber.replaceAll("'^\"|\"$","");
                        //String numbersOnly = viewNumber.replaceAll("\\D", "");
                         //System.out.println(" Number without quotes "+ numbersOnly);
                         if(numbersOnly != null && !numbersOnly.isEmpty())
                         {
                                while(targetNumber <= targetToAchieve)
                                {
                                        long numbers =  Wrappers.convertViewsToNumber(numbersOnly);
                                         targetNumber += numbers;
                                         

                                }
                                  
                         }
                         
                      
                }
                System.out.println(targetNumber);
                
                 
                 

        }

        

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                
                driver.manage().window().maximize();
        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}