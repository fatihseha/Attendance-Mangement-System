package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;

public class IDTestNegative extends TestCase {


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


        main.ID();
        main.SignUpID.setText("20210000457 ");
        main.ID();

    }

    public void testIDNegative() throws IOException {


        String expected = "True";
        String real = main.ogrenciNoHataSıgnUp.getText();

        assertNotSame(expected,real);
    }

}