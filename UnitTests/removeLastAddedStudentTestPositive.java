package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class removeLastAddedStudentTestPositive extends TestCase {


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

        main.students = new ArrayList<>();

        main.Login("20210000001","aA.123");
        main.LectureNameField.setText("SE318");
        main.StudentIDField.setText("20210000002");
        main.AddStudent();
        main.RemoveLastAddedStudent();
    }

    public void testPassAgainPositive() throws IOException {


        String expected = "20210000002 removed from the lecture.";
        String real = main.StudentAddedLabel.getText();

        assertEquals(expected,real);
    }

}
