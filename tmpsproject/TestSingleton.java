package ua.tmpsproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSingleton {
    private static TestSingleton instanceOfTestSingletonClass=null;
    private WebDriver driver;
    private TestSingleton(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver= new ChromeDriver();
    }
    public static TestSingleton getInstanceOfTestSingletonClass(){
        if(instanceOfTestSingletonClass==null){
            instanceOfTestSingletonClass = new TestSingleton();
        }
        return instanceOfTestSingletonClass;
    }
    public WebDriver getDriver()
    {
        return driver;
    }

    public static void main(String[] args) {
        TestSingleton sbc1= TestSingleton.getInstanceOfTestSingletonClass();
        WebDriver driver1 = sbc1.getDriver();
        TestSingleton sbc2= TestSingleton.getInstanceOfTestSingletonClass();
        WebDriver driver2 = sbc2.getDriver();
        driver2.get("https://www.google.com");
    }
}
