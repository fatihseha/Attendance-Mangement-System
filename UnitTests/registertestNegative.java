package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class registertestNegative extends TestCase {


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
        main.SignUpSurname.setText("Oc56l");
        main.SignUpID.setText("2021000001");
        main.SignUpTC.setText("");
        main.SignUpPass.setText("aA.13");
        main.SignUpPassAgain.setText("aA.123");

        main.register("20210000001","","Seha","Oc56l","aA.13","aA.123");
    }

    public void testRegisterNegative() throws IOException {
        String not_expected = "Fill in the Blanks Correctly";
        String real = main.jLabel17.getText();

        assertNotSame(real,not_expected);
    }

}
