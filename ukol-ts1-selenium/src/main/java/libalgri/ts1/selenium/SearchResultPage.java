package libalgri.ts1.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultPage
{
    private WebDriver driver;

    public SearchResultPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void loadPage()
    {
        this.driver.get("https://link.springer.com/search");
    }

    public String getFirstResultTitle()
    {
        WebElement title = new WebDriverWait(driver,
                Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(By.className("title")));
        return title.getText();
    }

    public void filterBy(String filter)
    {
        WebElement filterButton = new WebDriverWait(driver,
                Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), '" + filter + "')]")));
        filterButton.click();
    }

    public ArticlePage goToPage(int location)
    {
        new WebDriverWait(driver,
                Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(By.className("title")));
        driver.findElements(By.className("title")).get(location).click();
        return new ArticlePage(driver);
    }
}
