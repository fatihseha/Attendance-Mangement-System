package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;

public class PassAgainTestNegative2 extends TestCase {


    MainScreen main;

    {
        try {
            main = new MainScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void setUp() throws IOException {

        main.PassSignUp();
        main.SignUpPass.setText("aA.123");
        main.PassSignUp();

        main.PassSignUpAgain();
        main.SignUpPassAgain.setText("aA.12");
        main.PassSignUpAgain();
    }

    public void testPassAgainNegative2() throws IOException {


        String expected = "";
        String real = main.ogrenciPassAgainSignUpHata.getText();

        assertNotSame(expected,real);
    }

}