package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;

public class TCTestPositive extends TestCase {


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


        main.TC();
        main.SignUpTC.setText("111111111111");
        main.TC();
    }

    public void testTCPositive() throws IOException {


        String expected = "False";
        String real = main.ogrenciTCHata.getText();

        assertEquals(expected,real);
    }

}