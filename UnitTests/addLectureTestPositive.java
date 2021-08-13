package UnitTests;

import AMSVersion3.MainScreen;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class addLectureTestPositive extends TestCase {


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
        main.TimeComboBox.addItem("09:00");



        main.Login("20210000001","aA.123");
        main.LectureNameField.setText("SE318");
        main.StudentIDField.setText("20210000002");
        main.AddStudent();
        main.AddLecture();


    }

    public void testAddLecturePositive() throws IOException {


        String expected = "Lecture created!";
        String real = main.LectureCreatedLabel.getText();

        assertEquals(expected,real);
    }

}