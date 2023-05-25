package libalgri.ts1.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdvancedSearchPage
{
    private WebDriver driver;

    public AdvancedSearchPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void loadPage()
    {
        driver.get("https://link.springer.com/advanced-search");
        WebElement cookieButton = new WebDriverWait(driver,
                Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(By.className("cc-banner__button-accept")));
        cookieButton.click();
    }

    public void fillInForm(String allWords, String exactPhrase, String oneOfWords, String without, String title, String author, int yearFrom, int yearTo)
    {
        WebElement allWordsInput = new WebDriverWait(driver,
                Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(By.id("all-words")));
        allWordsInput.sendKeys(allWords);
        driver.findElement(By.id("exact-phrase")).sendKeys(exactPhrase);
        driver.findElement(By.id("least-words")).sendKeys(oneOfWords);
        driver.findElement(By.id("without-words")).sendKeys(without);
        driver.findElement(By.id("title-is")).sendKeys(title);
        driver.findElement(By.id("author-is")).sendKeys(author);
        driver.findElement(By.id("facet-start-year")).sendKeys(yearFrom + "");
        driver.findElement(By.id("facet-end-year")).sendKeys(yearTo + "");
    }

    public SearchResultPage submitForm()
    {
        driver.findElement(By.id("submit-advanced-search")).click();
        return new SearchResultPage(driver);
    }
}
