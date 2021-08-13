package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;

public class TCTestNegative2  extends TestCase {


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

    public void testTCNegative2() throws IOException {


        String expected = "";
        String real = main.ogrenciTCHata.getText();

        assertNotSame(expected,real);
    }

}