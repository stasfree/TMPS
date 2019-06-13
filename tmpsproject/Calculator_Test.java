package ua.tmpsproject;

import org.testng.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Calculator_Test {

    Calculator calculator = new Calculator();
    double x = 10.0;
    double y= 20.0;

    @DataProvider(name="sum")
    public Object[][] generateDataForSum(){
        return new Double[][]{
                {2.0,3.0,5.0},
                {10.0,20.0,30.0},
                {5.5,4.5,10.0}
        };
    }

    @Test(dataProvider = "sum")
    public void testAddWithDataProvider(double a, double b, double c){
        Assert.assertEquals(calculator.add(a,b),c);
    }

    @Test
    public void testAdd(){
        Assert.assertEquals(calculator.add(x,y),30.0);
    }

    @Test
    public void testSubstract(){
        Assert.assertEquals(calculator.substract(x,y),-10.0);
    }

    @Test
    public void testMultiply(){
        Assert.assertEquals(calculator.multiply(x,y),200.0);
    }

    @Test
    public void testDivide(){
        Assert.assertEquals(calculator.divide(x,y),0.5);
    }

}
