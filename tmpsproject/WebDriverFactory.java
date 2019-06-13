package ua.tmpsproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverFactory {

    public WebDriver createInstance(Browsers browser){
        if (Browsers.Chrome == browser){
            return new ChromeDriver();
        }
        else if (Browsers.IE == browser){
            return new InternetExplorerDriver();
        }
        else return new FirefoxDriver();
    }
    public enum Browsers{
        Chrome, IE, Firefox
    }
}
