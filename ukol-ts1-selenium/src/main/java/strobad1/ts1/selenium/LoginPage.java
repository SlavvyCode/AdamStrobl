package strobad1.ts1.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage
{
    private WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void loadPage()
    {
        this.driver.get("https://link.springer.com/signup-login");
    }

    public void fillInLogin(String email, String password)
    {
        WebElement emailInput = new WebDriverWait(driver,
                Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(By.id("login-box-email")));
        emailInput.sendKeys(email);
        driver.findElement(By.id("login-box-pw")).sendKeys(password);
    }

    public void submitLogin()
    {
        driver.findElement(By.cssSelector("[title=\"Log in\"]")).click();
    }
}
