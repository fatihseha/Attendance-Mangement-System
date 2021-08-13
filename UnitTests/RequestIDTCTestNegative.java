package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;

public class RequestIDTCTestNegative extends TestCase {


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


        main.requestIDTC();
        main.requestIDTC.setText("11111111111");
        main.requestIDTC();

    }

    public void testrequestIDTCNegative() throws IOException {


        String expected = "False";
        String real = main.ogrenciNoAlmaTCHata.getText();

        assertNotSame(expected,real);
    }

}