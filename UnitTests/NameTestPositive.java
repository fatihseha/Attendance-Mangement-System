package UnitTests;

import AMSVersion3.MainScreen;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import junit.framework.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NameTestPositive extends TestCase {

    FileInputStream serviceAccount;

    {
        try {
            serviceAccount = new FileInputStream("D:\\YAZLIM IDE\\deneme-6ae61-firebase-adminsdk-79l87-c2420154d7.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    FirebaseOptions options;

    {
        try {
            options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
            FirebaseApp.initializeApp(options);
            Firestore db = FirestoreClient.getFirestore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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

    public void testNamePositive() throws IOException {


        String expected = "True";
        String real = main.ogrenciAdHata.getText();

        assertEquals(expected,real);
    }

}
