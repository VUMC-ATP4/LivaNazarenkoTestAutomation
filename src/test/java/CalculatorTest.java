import clasroomSix.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

public class CalculatorTest {

        @BeforeTest
        public void pirmsTests(){
                System.out.println("Sākās tests");
        }

    @Test
public void testSum(){
        System.out.println("Šis ir mans pirmais testing tests");
        Calculator calculator = new Calculator();
        int actualResult = calculator.sum(13, 25);
        int expectedResult = 38;
        Assert.assertEquals(actualResult, expectedResult);// assert- pārbaudīt, vai kaut kas ir patiess

}

@Test
        public void testMultiply(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.multiplay(5,5), 25);
}

}
