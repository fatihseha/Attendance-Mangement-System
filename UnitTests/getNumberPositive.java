package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static AMSVersion3.MainScreen.userCount;

public class getNumberPositive extends TestCase {


    MainScreen main;

    {
        try {
            main = new MainScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String year="2021";
    String number;




    @Override
    protected void setUp() throws IOException, ExecutionException, InterruptedException {
        number=year;

        while((number+(int)userCount).length()!=11){
            number=number+"0";
        }


        main.requestIDTC.setText("11111111111");
        main.requestedID.setText(main.getNumber("11111111111"));


    }

    public void testNumberPositive() throws IOException {
        String expected =number+((int)userCount-1) ;
        String real = main.requestedID.getText();

        assertEquals(expected,real);
    }

}
