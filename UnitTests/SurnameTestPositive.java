package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;

public class SurnameTestPositive  extends TestCase {


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

    public void testNamePositive() throws IOException {


        String expected = "False";
        String real = main.ogrenciSoyadHata.getText();

        assertEquals(expected,real);
    }

}