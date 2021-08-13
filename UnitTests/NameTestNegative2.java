package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;

public class NameTestNegative2 extends TestCase {


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


        main.Name();
        main.SignUpName.setText("Yaman");
        main.Name();

    }

    public void testNameNegative2() throws IOException {


        String expected = "";
        String real = main.ogrenciAdHata.getText();

        assertNotSame(expected,real);
    }

}