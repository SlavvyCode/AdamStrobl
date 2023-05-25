package strobad1.ts1.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MoodleTests
{
    private WebDriver driver = new FirefoxDriver();
    @Test
    public void getDataTest() throws FileNotFoundException {
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage(driver);
        advancedSearchPage.loadPage();
        advancedSearchPage.fillInForm("Page Object Model", "", "Sellenium Testing", "", "", "", 2023, 2023);
        SearchResultPage searchResultPage = advancedSearchPage.submitForm();
        searchResultPage.filterBy("Article");

        PrintWriter out = new PrintWriter("src/test/resources/data.csv");

        for (int i = 0; i < 4; i++)
        {
            ArticlePage articlePage = searchResultPage.goToPage(i);
            String info = String.join(",", articlePage.getInfo());
            out.println(info);
            articlePage.goBack();
        }
        out.close();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void verifyDataTest(String title, String doi, String date)
    {
        MainPage mainPage = new MainPage(driver);
        mainPage.loadPage();
        SearchResultPage searchResultPage = mainPage.basicSearch(title);
        ArticlePage articlePage = searchResultPage.goToPage(0);
        String[] data = articlePage.getInfo();

        Assertions.assertEquals(doi, data[1]);
        Assertions.assertEquals(date, data[2]);
    }
}
