package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class registerTestPositive extends TestCase {


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


        main.Name();
        main.Surname();
        main.TC();
        main.ID();
        main.PassSignUp();
        main.PassSignUpAgain();

        main.SignUpName.setText("Seha");
        main.SignUpSurname.setText("Ocal");
        main.SignUpID.setText("20210000010");
        main.SignUpTC.setText("11111111111");
        main.SignUpPass.setText("aA.123");
        main.SignUpPassAgain.setText("aA.123");

        main.register("20210000010","11111111111","Seha","Ocal","aA.123","aA.123");
    }

    public void testRegisterPositive() throws IOException {
        String expected = "SUCCESS";
        String real = main.jLabel17.getText();

        assertEquals(expected,real);
    }

}