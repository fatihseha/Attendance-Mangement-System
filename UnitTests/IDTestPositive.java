package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;

public class IDTestPositive extends TestCase {


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

    public void testIDPositive() throws IOException {


        String expected = "False";
        String real = main.ogrenciNoHataSıgnUp.getText();

        assertEquals(expected,real);
    }

}
