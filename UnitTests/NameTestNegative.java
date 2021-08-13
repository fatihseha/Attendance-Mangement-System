package UnitTests;

import AMSVersion3.MainScreen;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import junit.framework.TestCase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NameTestNegative extends TestCase {


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

    public void testNameNegative() throws IOException {


        String expected = "False";
        String real = main.ogrenciAdHata.getText();

        assertNotSame(expected,real);
    }

}