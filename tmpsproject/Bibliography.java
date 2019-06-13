package ua.tmpsproject;

import org.testng.annotations.DataProvider;

public class Bibliography {

    @DataProvider(name="listOfSites")
    public Object[][] getListOfSites(){
        return new String[][]{
                {"https://ru.wikipedia.org/wiki/Java"},
                {"https://testng.org/doc/"},
                {"https://www.tutorialspoint.com/apache_poi/apache_poi_overview.htm"},
        };
    }
}
