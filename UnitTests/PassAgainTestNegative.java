package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;

public class PassAgainTestNegative extends TestCase {


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

    public void testPassAgainNegative() throws IOException {


        String expected = "True";
        String real = main.ogrenciPassAgainSignUpHata.getText();

        assertNotSame(expected,real);
    }

}