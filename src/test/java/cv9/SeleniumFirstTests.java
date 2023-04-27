package cv9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumFirstTests {


    @Test
    public void firstSeleniumTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Download/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://automationteststore.com/index.php?rt=account/create");

        String titleText = driver.findElement(By.cssSelector("h1 span")).getText();


        driver.findElement(By.cssSelector("#AccountFrm_firstname")).sendKeys("Adam");

        driver.findElement(By.cssSelector("#AccountFrm_lastname")).sendKeys("Adam");

        driver.findElement(By.cssSelector("#AccountFrm_email")).sendKeys("Adam@Adam.com");

        driver.findElement(By.cssSelector("#AccountFrm_address_1")).sendKeys("Adamovska 123");

        driver.findElement(By.cssSelector("#AccountFrm_city")).sendKeys("Adamovska 123");

        driver.findElement(By.cssSelector("#AccountFrm_postcode")).sendKeys("Adamovska 123");

        driver.findElement(By.cssSelector("#AccountFrm_loginname")).sendKeys("AdamAdam123");

        driver.findElement(By.cssSelector("#AccountFrm_password")).sendKeys("AdamisGodXx");


        driver.findElement(By.cssSelector("#AccountFrm_confirm")).sendKeys("AdamisGodXx");


        Select countrySelect = new Select(driver.findElement(By.cssSelector("#AccountFrm_country_id")));
        countrySelect.selectByVisibleText("Czech Republic");

        Thread.sleep(500);

        Select Kraj = new Select(driver.findElement(By.cssSelector("#AccountFrm_zone_id")));
        Kraj.selectByVisibleText("Jihocesky");

        driver.findElement(By.cssSelector("#AccountFrm_agree")).click();
        driver.findElement(By.cssSelector("button[title='Continue']")).click();
        //        driver.close();







    }
//@BeforeEach
//    public void setDriver(){
//
//    }
//
//    public void fillForm(){
//        driver.findElement(By.cssSelector("")).sendKeys("");
//        driver.findElement(By.cssSelector("")).sendKeys("");
//        driver.findElement(By.cssSelector("")).sendKeys("");
//        driver.findElement(By.cssSelector("")).sendKeys("");
//        driver.findElement(By.cssSelector("")).sendKeys("");
//
//        driver.findElement(By.cssSelector("")).sendKeys("");
//    }

}
