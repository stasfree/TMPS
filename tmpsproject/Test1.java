package ua.tmpsproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Test1 {

    private static final String URL = "http://automationrhapsody.com/examples/utf8icons.html";
    private WebDriverFacade webDriver;

    @BeforeClass
    public void setUp(){
        webDriver = new WebDriverFacade(WebDriverFactory.Browsers.Chrome);
        webDriver.start(URL);
    }

    @Test
    public void nullObjectPattern(){
        WebElement element = webDriver.findElement(By.cssSelector("notExisting"));
        element.click();

        if (NullWebElement.getNull().equals(element)){
            System.out.println(NullWebElement.getNull().equals(element));
        }
        Assert.assertTrue(NullWebElement.getNull().equals(element));
        if (NullWebElement.isNull(element)) {
            System.out.println("NullWebElement.isNull(element)");
        }
        Assert.assertTrue(NullWebElement.isNull(element));

        // Because we use singleton it is possible to compare even references
        if (element == NullWebElement.getNull()) {
            System.out.println("element == NullWebElement.getNull()");
        }
        Assert.assertTrue(element == NullWebElement.getNull());
    }

    @Test
    public void pageObjectPattern() {
        PageObject homePage = new PageObject(webDriver);
        // One element is defined on only one place - we do not repeat ourselves
        System.out.println("Search label is: " + homePage.getSearchLabel());
        Assert.assertEquals(homePage.getSearchLabel(), "Search:");
        homePage.clearSearch();
        homePage.searchFor("automation");
    }

    @AfterClass
    public void tearDown() {
        webDriver.stop();
    }
}
