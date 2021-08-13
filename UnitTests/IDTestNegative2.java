package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;

public class IDTestNegative2 extends TestCase {


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

    public void testIDNegative2() throws IOException {


        String expected = "";
        String real = main.ogrenciNoHataSıgnUp.getText();

        assertNotSame(expected,real);
    }

}
