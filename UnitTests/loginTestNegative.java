package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class loginTestNegative extends TestCase {


    MainScreen main;

    {
        try {
            main = new MainScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void setUp() throws IOException, ExecutionException, InterruptedException {
        main.Login("20210000001","aA.123");

    }

    public void testLoginNegative() throws IOException {
        String expected = "20210000033";
        String real = main.selectedUser.getID();

        assertNotSame(expected,real);
    }

}
