package mavenTestNGHomework;

import Homework.Calculator;
import org.testng.Assert;
import org.testng.annotations.*;


public class CalculatorTestHomework {

    public void pirmsTesta(){
        System.out.println("Sākas tests!");
    }
    @AfterMethod
    public void pecTesta(){
        System.out.println("Tests ir beidzies!");
    }

    @Test
    public void testSum(){
        System.out.println("Saskaitīšana");
        Calculator calculator = new Calculator();
        int actualResult = calculator.add(15,5);
        int expectedResult = 20;
        Assert.assertEquals(actualResult, expectedResult);
    }
    @AfterTest
    public void pecTestaViens(){
        System.out.println("Šī bija saskaitīšana");
    }

    @Test
    public void testSubtraction(){
        System.out.println("Atņemšana");
        Calculator calculator = new Calculator();
        int actualResult = calculator.substract(15,5);
        int expectedResult = 10;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @AfterSuite// nesaprotu šo
    public void pecTestiem() {
        System.out.println("Malači?");
    }

    @Test
    public void testMultiply(){
        System.out.println("Reizināšana");
        Calculator calculator = new Calculator();
        int actualResult = calculator.multiply(15, 3);
        int expectedResult = 45;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testDivision(){
        System.out.println("Dalīšana");
        Calculator calculator = new Calculator();
        float actualResult= calculator.divide(15,3);
        float expectedResult = 5;
        Assert.assertEquals(actualResult, expectedResult);
    }

}
