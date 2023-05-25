package libalgri.ts1.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ArticlePage
{
    private WebDriver driver;

    public ArticlePage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void loadArticle(String article)
    {
        this.driver.get("https://link.springer.com/article/" + article);
    }

    public String[] getInfo()
    {
        WebElement title = new WebDriverWait(driver,
                Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(By.className("c-article-title")));
        String titleContent = title.getText();
        String doiValue = driver.findElement(By.cssSelector(".c-bibliographic-information__list-item--doi .c-bibliographic-information__value")).getText();
        String dateValue = driver.findElement(By.cssSelector(".c-bibliographic-information__list-item time")).getText();

        return new String[] {titleContent, doiValue, dateValue};
    }

    public SearchResultPage goBack()
    {
        driver.navigate().back();
        return new SearchResultPage(driver);
    }
}
