package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;

public class SurnameTestNegative extends TestCase {


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


        main.Surname();
        main.SignUpSurname.setText("Koper  ");
        main.Surname();

    }

    public void testSurNameNegative() throws IOException {


        String expected = "True";
        String real = main.ogrenciSoyadHata.getText();

        assertNotSame(expected,real);
    }

}