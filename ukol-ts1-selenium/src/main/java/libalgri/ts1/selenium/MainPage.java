package libalgri.ts1.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage
{
    private WebDriver driver;

    public MainPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void loadPage()
    {
        this.driver.get("https://link.springer.com");
        WebElement cookieButton = new WebDriverWait(driver,
                Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(By.className("cc-banner__button-accept")));
        cookieButton.click();
    }

    public LoginPage goToLoginPage()
    {
        WebElement loginButton = driver.findElement(By.className("register-link"));
        loginButton.click();
        return new LoginPage(driver);
    }

    public AdvancedSearchPage goToAdvancedSearch()
    {
        WebElement searchOptionsButton = driver.findElement(By.className("open-search-options"));
        WebElement advancedSearchLink = driver.findElement(By.id("advanced-search-link"));
        searchOptionsButton.click();
        advancedSearchLink.click();
        return new AdvancedSearchPage(driver);
    }

    public SearchResultPage basicSearch(String prompt)
    {
        WebElement searchBar = new WebDriverWait(driver,
                Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(By.id("query")));
        searchBar.sendKeys(prompt);
        driver.findElement(By.id("search")).click();
        return new SearchResultPage(driver);
    }
}
