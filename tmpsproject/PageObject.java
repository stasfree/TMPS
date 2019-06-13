package ua.tmpsproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PageObject {
    private WebDriverFacade webDriver;

    public PageObject(WebDriverFacade webDriver) {
        this.webDriver = webDriver;
    }

    private WebElement getSearchField() {
        return webDriver.findElement(By.id("search"));
    }

    public String getSearchLabel() {
        WebElement element = webDriver.findElement(By.cssSelector("div.find label"));
        return element.getText();
    }

    public void searchFor(String text) {
        getSearchField().sendKeys(text);
    }

    public void clearSearch() {
        webDriver.executeJavaScript("$('span.cancel').click()");
    }
}
