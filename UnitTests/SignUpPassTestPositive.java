package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;

public class SignUpPassTestPositive extends TestCase {


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

    }

    public void testPassPositive() throws IOException {


        String expected = "True";
        String real = main.ogrenciPassSignUpHata.getText();

        assertEquals(expected,real);
    }

}