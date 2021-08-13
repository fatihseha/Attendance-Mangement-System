/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMSVersion3;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicButtonUI;






public class MainScreen extends javax.swing.JFrame {
    public static double userCount=1;

    //Creating static variables
    //We use multi-dimensional ArrayList arrays to store both student and teacher datas.
    //We make them static to reach them in static field.


    Firestore db = FirestoreClient.getFirestore();

    static ArrayList<User> UserArrayList= new ArrayList<>();
    static ArrayList[][] TCArrayList=new ArrayList[2][1];
    static ArrayList<String> studentTC=new ArrayList<>();
    static ArrayList<String> teacherTC=new ArrayList<>();
    static ArrayList[][] geciciTCArrayList=new ArrayList[2][1];
    static ArrayList<String> geciciStudentTC=new ArrayList<>();
    static ArrayList<String> geciciTeacherTC=new ArrayList<>();
    static ArrayList[][] SignUpIDArrayList=new ArrayList[2][1];
    static ArrayList<String> studentSignID=new ArrayList<>();
    static ArrayList<String> teacherSignID=new ArrayList<>();
    public ArrayList<String> students;
    public User selectedUser;





    /**
     * Creates new form login
     */

    CardLayout cardLayout;
    CardLayout cardLayout2;
    CardLayout cardLayout3;

    public MainScreen() throws IOException {



        initComponents();
        Component [] components = this.getContentPane().getComponents();
        for(Component component:components){
        if(component instanceof JButton){
        ((JButton) component).setUI(new BasicButtonUI());
        ((JButton) component).setFocusPainted(false);


            }
        }

        cardLayout= (CardLayout)(pnlCards.getLayout());
        cardLayout2= (CardLayout) (pnlCards2.getLayout());
        cardLayout3= (CardLayout) (pnlCards3.getLayout());




    }
    //Create regex patterns to inspect user inputs to see wheter inputs are valid or not
    Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
    Pattern lowerCasePatten = Pattern.compile("[a-z ]");
    Pattern digitCasePatten = Pattern.compile("[0-9 ]");
    Pattern space = Pattern.compile(".*\\s.*");



    // student signup functions

    //Check wheter Ad field is valid.It shouldn't contain numbers and special chars and spaces.
    public void Name(){

        SignUpName.getDocument().addDocumentListener(new DocumentListener()
    {
      @Override
      public void removeUpdate(DocumentEvent e)
      {

          if( specailCharPatten.matcher(SignUpName.getText()).find()  || digitCasePatten.matcher(SignUpName.getText()).find()
          || SignUpName.getText().length()==0 || space.matcher(SignUpName.getText()).find()  ){
          ogrenciAdHata.setText("False");




          }
                else{
              ogrenciAdHata.setText("True");
          }
      }

      @Override
      public void insertUpdate(DocumentEvent e)
      {
        if(specailCharPatten.matcher(SignUpName.getText()).find()  || digitCasePatten.matcher(SignUpName.getText()).find()
          || SignUpName.getText().length()==0 || space.matcher(SignUpName.getText()).find() ){
          ogrenciAdHata.setText("False");


          }
                else{
              ogrenciAdHata.setText("True");
          }


      }

      @Override
      public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
  });
    }
    //Check wheter Soyad field is valid.It shouldn't contain numbers and special chars and spaces.
    public void Surname(){

        SignUpSurname.getDocument().addDocumentListener(new DocumentListener()
    {
      @Override
      public void removeUpdate(DocumentEvent e)
      {

          if( specailCharPatten.matcher(SignUpSurname.getText()).find()  || digitCasePatten.matcher(SignUpSurname.getText()).find()
          || SignUpSurname.getText().length()==0 || space.matcher(SignUpSurname.getText()).find()  ){
          ogrenciSoyadHata.setText("False");

          }
                else{
              ogrenciSoyadHata.setText("True");
          }
      }

      @Override
      public void insertUpdate(DocumentEvent e)
      {
        if(specailCharPatten.matcher(SignUpSurname.getText()).find()  || digitCasePatten.matcher(SignUpSurname.getText()).find()
          || SignUpSurname.getText().length()==0 || space.matcher(SignUpSurname.getText()).find() ){
          ogrenciSoyadHata.setText("False");

          }
                else{
              ogrenciSoyadHata.setText("True");
          }
      }

      @Override
      public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
  });
    }
    //Check wheter TC field is valid.It shouldn't contain chars and special chars and spaces.
    public void TC(){
        SignUpTC.getDocument().addDocumentListener(new DocumentListener()
    {
      @Override
      public void removeUpdate(DocumentEvent e)
      {

          if(SignUpTC.getText().matches("[0-9]+")&& SignUpTC.getText().length()==11 && SignUpTC.getText().length()!=0){
          ogrenciTCHata.setText("True");

          }
                else{
              ogrenciTCHata.setText("False");
          }
      }

      @Override
      public void insertUpdate(DocumentEvent e)
      {
        if(SignUpTC.getText().matches("[0-9]+") && SignUpTC.getText().length()==11 && SignUpTC.getText().length()!=0){
          ogrenciTCHata.setText("True");

          }
                else{
              ogrenciTCHata.setText("False");
          }
      }

      @Override
      public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
  });

    }
    //Check wheter No field is valid.It should contain numbers only and shouldn't cointain spaces.
    public void ID(){
        SignUpID.getDocument().addDocumentListener(new DocumentListener()
    {
      @Override
      public void removeUpdate(DocumentEvent e)
      {

          if(SignUpID.getText().matches("[0-9]+")&& SignUpID.getText().length()==11 && SignUpID.getText().length()!=0){
          ogrenciNoHataSıgnUp.setText("True");

          }
                else{
              ogrenciNoHataSıgnUp.setText("False");
          }
      }

      @Override
      public void insertUpdate(DocumentEvent e)
      {
        if(SignUpID.getText().matches("[0-9]+") && SignUpID.getText().length()==11 && SignUpID.getText().length()!=0){
          ogrenciNoHataSıgnUp.setText("True");

          }
                else{
              ogrenciNoHataSıgnUp.setText("False");
          }
      }

      @Override
      public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
  });
    }
    //Check wheter SPass field is valid.It should contain at least one special char,number,uppercase and lowercase letter.It shouldn't contain spaces.
    public void PassSignUp(){


        SignUpPass.getDocument().addDocumentListener(new DocumentListener()
    {
      @Override
      public void removeUpdate(DocumentEvent e)
      {

          if(specailCharPatten.matcher(SignUpPass.getText()).find() && UpperCasePatten.matcher(SignUpPass.getText()).find()
          && lowerCasePatten.matcher(SignUpPass.getText()).find() && digitCasePatten.matcher(SignUpPass.getText()).find()
          && SignUpPass.getText().length()>=6 && SignUpPass.getText().length()!=0 && !space.matcher(SignUpPass.getText()).find()) {
          ogrenciPassSignUpHata.setText("True");

          }
                else{
              ogrenciPassSignUpHata.setText("False");
          }
      }

      @Override
      public void insertUpdate(DocumentEvent e)
      {
        if(specailCharPatten.matcher(SignUpPass.getText()).find() && UpperCasePatten.matcher(SignUpPass.getText()).find()
          && lowerCasePatten.matcher(SignUpPass.getText()).find() && digitCasePatten.matcher(SignUpPass.getText()).find()
          && SignUpPass.getText().length()>=6 && SignUpPass.getText().length()!=0 && !space.matcher(SignUpPass.getText()).find()){
          ogrenciPassSignUpHata.setText("True");

          }
                else{
              ogrenciPassSignUpHata.setText("False");
          }
      }

      @Override
      public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
  });
    }
    //Check wheter PassAgain field is valid.It should match with the Pass field.
    public void PassSignUpAgain(){
            SignUpPassAgain.getDocument().addDocumentListener(new DocumentListener()
    {
      @Override
      public void removeUpdate(DocumentEvent e)
      {

          if(SignUpPassAgain.getText().equals(SignUpPass.getText()) && ogrenciPassSignUpHata.getText().equals("True")){
          ogrenciPassAgainSignUpHata.setText("True");

          }
                else{
              ogrenciPassAgainSignUpHata.setText("False");
          }
      }

      @Override
      public void insertUpdate(DocumentEvent e)
      {
        if(SignUpPassAgain.getText().equals(SignUpPass.getText()) && ogrenciPassSignUpHata.getText().equals("True")){
          ogrenciPassAgainSignUpHata.setText("True");

          }
                else{
              ogrenciPassAgainSignUpHata.setText("False");
          }
      }

      @Override
      public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
  });
    }

    //Request ID function to check wheter they are valid or not.
    public void requestIDTC(){
        requestIDTC.getDocument().addDocumentListener(new DocumentListener()
    {
      @Override
      public void removeUpdate(DocumentEvent e)
      {

          if(requestIDTC.getText().matches("[0-9]+")&& requestIDTC.getText().length()==11 && requestIDTC.getText().length()!=0){
          ogrenciNoAlmaTCHata.setText("True");

          }
                else{
              ogrenciNoAlmaTCHata.setText("False");
          }
      }

      @Override
      public void insertUpdate(DocumentEvent e)
      {
        if(requestIDTC.getText().matches("[0-9]+") && requestIDTC.getText().length()==11 && requestIDTC.getText().length()!=0){
          ogrenciNoAlmaTCHata.setText("True");

          }
                else{
              ogrenciNoAlmaTCHata.setText("False");
          }
      }

      @Override
      public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
  });
    }


    //TeacherLoggedIn Screen checker functions
    public void TeacherScreenStudentID(){
        StudentIDField.getDocument().addDocumentListener(new DocumentListener()
    {
      @Override
      public void removeUpdate(DocumentEvent e)
      {

          if(StudentIDField.getText().matches("[0-9]+")&& StudentIDField.getText().length()==11 && StudentIDField.getText().length()!=0){
          StudentIDCheckerLabel.setText("True");

          }
                else{
              StudentIDCheckerLabel.setText("False");
          }
      }

      @Override
      public void insertUpdate(DocumentEvent e)
      {
        if(StudentIDField.getText().matches("[0-9]+")&& StudentIDField.getText().length()==11 && StudentIDField.getText().length()!=0){
          StudentIDCheckerLabel.setText("True");

          }
                else{
              StudentIDCheckerLabel.setText("False");
          }
      }

      @Override
      public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
  });
    }

    //we check our database which user is logged in and check our label to remove the lecture from database
    public void removeLecture() throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("Teacher").document(selectedUser.getID());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.getString("ID") != null && document.getString("ID").equals(selectedUser.getID())) {
            if (!RemoveLectureNameField.getText().equals("")) {
                DocumentReference docRef2 = db.collection("Main").document("HuZBsxxLeXqI7ByCvbdn").collection("Lectures").document(RemoveLectureNameField.getText());
                ApiFuture<DocumentSnapshot> future2 = docRef2.get();
                DocumentSnapshot document2 = future2.get();
                List<String> gecici;
                gecici = (List<String>) document.get("TeacherTime" + document2.getString("LectureDay"));
                System.out.println(document2.getString("LectureDay"));
                gecici.add(document2.getString("LectureTime"));
                ApiFuture<Void> futureTransaction = db.runTransaction(transaction -> {
                    // retrieve document and increment population field
                    DocumentSnapshot snapshot = transaction.get(docRef).get();
                    transaction.update(docRef, "TeacherTime" + DayComboBox.getSelectedItem(), gecici);
                    return null;
                });
                ApiFuture<WriteResult> writeResult = db.collection("Main").document("HuZBsxxLeXqI7ByCvbdn").collection("Lectures").document(RemoveLectureNameField.getText()).delete();
                RemoveLectureNameField.setText("DELETED !");
            }
            else{
                RemoveLectureNameField.setText("This cannot be empty!");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FirstScreen = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        LogInSwitchButton = new javax.swing.JButton();
        SignUpSwitchButton = new javax.swing.JButton();
        GetIDSwitchButon = new javax.swing.JButton();
        pnlCards = new javax.swing.JPanel();
        Login = new javax.swing.JPanel();
        LoginButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        SignInID = new javax.swing.JTextField();
        SignInPass = new javax.swing.JPasswordField();
        LoginModifier = new javax.swing.JLabel();
        SignUp = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        SignUpName = new javax.swing.JTextField();
        SignUpSurname = new javax.swing.JTextField();
        SignUpTC = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        SignUpPass = new javax.swing.JPasswordField();
        SignUpPassAgain = new javax.swing.JPasswordField();
        SignUpButton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        SignUpID = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        ogrenciNoHataSıgnUp = new javax.swing.JLabel();
        ogrenciAdHata = new javax.swing.JLabel();
        ogrenciSoyadHata = new javax.swing.JLabel();
        ogrenciTCHata = new javax.swing.JLabel();
        ogrenciPassSignUpHata = new javax.swing.JLabel();
        ogrenciPassAgainSignUpHata = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        RequestID = new javax.swing.JPanel();
        requestIDTC = new javax.swing.JTextField();
        requestedID = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        RequestIDButton = new javax.swing.JButton();
        ogrenciNoAlmaTCHata = new javax.swing.JLabel();
        LoggedInScreenTeacher = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        TeacherBackToMenuSwitchButton = new javax.swing.JButton();
        LogOutTeacher = new javax.swing.JButton();
        AddLectureSwitchButton = new javax.swing.JButton();
        RemoveLectureSwitchButton = new javax.swing.JButton();
        pnlCards2 = new javax.swing.JPanel();
        TeacherMainMenuPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        RemoveLecturePanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        RemoveLectureNameField = new javax.swing.JTextField();
        RemoveLectureButton = new javax.swing.JButton();
        AddLecturePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        AddStudentButton = new javax.swing.JButton();
        RemoveLastAddedStudentButton = new javax.swing.JButton();
        LectureNameField = new javax.swing.JTextField();
        StudentIDField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        DayComboBox = new javax.swing.JComboBox<>();
        TimeComboBox = new javax.swing.JComboBox<>();
        AddLectureButton = new javax.swing.JButton();
        LectureNameCheckerLabel = new javax.swing.JLabel();
        StudentIDCheckerLabel = new javax.swing.JLabel();
        StudentAddedLabel = new javax.swing.JLabel();
        LectureCreatedLabel = new javax.swing.JLabel();
        LoggedInScreenStudent = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        LogOutStudent = new javax.swing.JButton();
        StudentMainMenuStudentSwitchButton = new javax.swing.JButton();
        StudentTakeAttandenceSwitchButton = new javax.swing.JButton();
        pnlCards3 = new javax.swing.JPanel();
        StudentMainScreenPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        StudentTakeAttandenceScreenPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        AttandenceInfoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setEnabled(false);

        LogInSwitchButton.setText(" Log In");
        LogInSwitchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogInSwitchButtonActionPerformed(evt);
            }
        });

        SignUpSwitchButton.setText(" Sign Up");
        SignUpSwitchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpSwitchButtonActionPerformed(evt);
            }
        });

        GetIDSwitchButon.setText("Request ID");
        GetIDSwitchButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetIDSwitchButonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SignUpSwitchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LogInSwitchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(GetIDSwitchButon, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addComponent(GetIDSwitchButon)
                .addGap(64, 64, 64)
                .addComponent(SignUpSwitchButton)
                .addGap(64, 64, 64)
                .addComponent(LogInSwitchButton)
                .addGap(110, 110, 110))
        );

        FirstScreen.setLeftComponent(jPanel1);

        pnlCards.setLayout(new java.awt.CardLayout());

        LoginButton.setText("Log In");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    LoginButtonActionPerformed(evt);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        jLabel1.setText("ID");

        jLabel2.setText("Password");

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login);
        Login.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(41, 41, 41)
                        .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SignInID)
                            .addComponent(SignInPass, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(LoginModifier, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(598, Short.MAX_VALUE))
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(SignInID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(SignInPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(LoginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoginModifier, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        pnlCards.add(Login, "card2");

        jLabel5.setText("Name");

        jLabel6.setText("Surname");

        jLabel7.setText("TC No");

        jLabel8.setText("Password");

        jLabel9.setText("Password (Again)");

        SignUpButton.setText("Be a Member");
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    SignUpButtonActionPerformed(evt);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        jLabel14.setText("ID");

        jLabel20.setText("Password should contain uppercase,lowercase letters,numbers and special characters");

        jLabel24.setText("and its should be at least 6 character long.Do not enter spaces");

        javax.swing.GroupLayout SignUpLayout = new javax.swing.GroupLayout(SignUp);
        SignUp.setLayout(SignUpLayout);
        SignUpLayout.setHorizontalGroup(
            SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SignUpLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SignUpLayout.createSequentialGroup()
                        .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SignUpSurname)
                            .addComponent(SignUpTC)
                            .addComponent(SignUpPass, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(SignUpPassAgain)))
                    .addGroup(SignUpLayout.createSequentialGroup()
                        .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SignUpName)
                            .addComponent(SignUpID, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ogrenciAdHata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ogrenciSoyadHata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ogrenciTCHata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ogrenciPassSignUpHata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ogrenciPassAgainSignUpHata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ogrenciNoHataSıgnUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(SignUpLayout.createSequentialGroup()
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SignUpLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(SignUpButton))
                    .addGroup(SignUpLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SignUpLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SignUpLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel24))
                            .addComponent(jLabel20))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SignUpLayout.setVerticalGroup(
            SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SignUpLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SignUpID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ogrenciNoHataSıgnUp)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(SignUpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ogrenciAdHata))
                .addGap(18, 18, 18)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(SignUpSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ogrenciSoyadHata))
                .addGap(18, 18, 18)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(SignUpTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ogrenciTCHata))
                .addGap(18, 18, 18)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(SignUpPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ogrenciPassSignUpHata))
                .addGap(18, 18, 18)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SignUpPassAgain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ogrenciPassAgainSignUpHata)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SignUpButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addGap(8, 8, 8))
        );

        pnlCards.add(SignUp, "card4");

        requestedID.setEditable(false);

        jLabel123.setText("TC No");

        jLabel22.setText("ID");

        RequestIDButton.setText("Request No");
        RequestIDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    RequestIDButtonActionPerformed(evt);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout RequestIDLayout = new javax.swing.GroupLayout(RequestID);
        RequestID.setLayout(RequestIDLayout);
        RequestIDLayout.setHorizontalGroup(
            RequestIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RequestIDLayout.createSequentialGroup()
                .addGroup(RequestIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RequestIDLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(RequestIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel123))
                        .addGap(18, 18, 18)
                        .addGroup(RequestIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(requestIDTC, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(requestedID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ogrenciNoAlmaTCHata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(RequestIDLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(RequestIDButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)))
                .addGap(555, 555, 555))
        );
        RequestIDLayout.setVerticalGroup(
            RequestIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RequestIDLayout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addGroup(RequestIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(requestIDTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel123)
                    .addComponent(ogrenciNoAlmaTCHata))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RequestIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(requestedID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(RequestIDButton)
                .addContainerGap(204, Short.MAX_VALUE))
        );

        pnlCards.add(RequestID, "card6");

        FirstScreen.setRightComponent(pnlCards);

        TeacherBackToMenuSwitchButton.setText("Back To Menu");
        TeacherBackToMenuSwitchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TeacherBackToMenuSwitchButtonActionPerformed(evt);
            }
        });

        LogOutTeacher.setText("Log Out");
        LogOutTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutTeacherActionPerformed(evt);
            }
        });

        AddLectureSwitchButton.setText("Add Lecture");
        AddLectureSwitchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    AddLectureSwitchButtonActionPerformed(evt);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        RemoveLectureSwitchButton.setText("Remove Lecture");
        RemoveLectureSwitchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveLectureSwitchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TeacherBackToMenuSwitchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(LogOutTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RemoveLectureSwitchButton)
                    .addComponent(AddLectureSwitchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(AddLectureSwitchButton)
                .addGap(30, 30, 30)
                .addComponent(RemoveLectureSwitchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                .addComponent(TeacherBackToMenuSwitchButton)
                .addGap(7, 7, 7)
                .addComponent(LogOutTeacher))
        );

        jSplitPane2.setLeftComponent(jPanel4);

        pnlCards2.setLayout(new java.awt.CardLayout());

        jLabel12.setText("TEACHER MAIN SCREEN");

        javax.swing.GroupLayout TeacherMainMenuPanelLayout = new javax.swing.GroupLayout(TeacherMainMenuPanel);
        TeacherMainMenuPanel.setLayout(TeacherMainMenuPanelLayout);
        TeacherMainMenuPanelLayout.setHorizontalGroup(
            TeacherMainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TeacherMainMenuPanelLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel12)
                .addContainerGap(239, Short.MAX_VALUE))
        );
        TeacherMainMenuPanelLayout.setVerticalGroup(
            TeacherMainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TeacherMainMenuPanelLayout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(jLabel12)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        pnlCards2.add(TeacherMainMenuPanel, "card2");

        jLabel11.setText("Lecture Name :");

        RemoveLectureButton.setText("Remove Lecture");

        javax.swing.GroupLayout RemoveLecturePanelLayout = new javax.swing.GroupLayout(RemoveLecturePanel);
        RemoveLecturePanel.setLayout(RemoveLecturePanelLayout);
        RemoveLecturePanelLayout.setHorizontalGroup(
            RemoveLecturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RemoveLecturePanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RemoveLectureNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(RemoveLectureButton)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        RemoveLecturePanelLayout.setVerticalGroup(
            RemoveLecturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RemoveLecturePanelLayout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addGroup(RemoveLecturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(RemoveLectureNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RemoveLectureButton))
                .addContainerGap(275, Short.MAX_VALUE))
        );

        pnlCards2.add(RemoveLecturePanel, "card3");

        jLabel3.setText("Lecture Name :");

        jLabel4.setText("Student ID :");

        jLabel10.setText("Lecture Time : ");

        AddStudentButton.setText("Add Student");
        AddStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    AddStudentButtonActionPerformed(evt);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        RemoveLastAddedStudentButton.setText("Remove Last Added Student");
        RemoveLastAddedStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveLastAddedStudentButtonActionPerformed(evt);
            }
        });

        jLabel13.setText("Lecture Day :");

        DayComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" }));
        DayComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                try {
                    DayComboBoxİtemStateChanged(evt);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        DayComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DayComboBoxActionPerformed(evt);
            }
        });

        TimeComboBox.setMaximumRowCount(10);

        AddLectureButton.setText("Add Lecture");
        AddLectureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    AddLectureButtonActionPerformed(evt);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout AddLecturePanelLayout = new javax.swing.GroupLayout(AddLecturePanel);
        AddLecturePanel.setLayout(AddLecturePanelLayout);
        AddLecturePanelLayout.setHorizontalGroup(
            AddLecturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RemoveLastAddedStudentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(AddLectureButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(AddStudentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(AddLecturePanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(AddLecturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(AddLecturePanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LectureNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddLecturePanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(StudentIDField)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddLecturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LectureNameCheckerLabel)
                    .addComponent(StudentIDCheckerLabel))
                .addGap(35, 35, 35)
                .addGroup(AddLecturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddLecturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DayComboBox, 0, 100, Short.MAX_VALUE)
                    .addComponent(TimeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(AddLecturePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StudentAddedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LectureCreatedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        AddLecturePanelLayout.setVerticalGroup(
            AddLecturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddLecturePanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(AddLecturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(LectureNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(DayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LectureNameCheckerLabel))
                .addGap(52, 52, 52)
                .addGroup(AddLecturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(StudentIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(TimeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StudentIDCheckerLabel))
                .addGap(42, 42, 42)
                .addGroup(AddLecturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StudentAddedLabel)
                    .addComponent(LectureCreatedLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddLectureButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddStudentButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RemoveLastAddedStudentButton)
                .addContainerGap(179, Short.MAX_VALUE))
        );

        pnlCards2.add(AddLecturePanel, "card4");

        jSplitPane2.setRightComponent(pnlCards2);

        javax.swing.GroupLayout LoggedInScreenTeacherLayout = new javax.swing.GroupLayout(LoggedInScreenTeacher);
        LoggedInScreenTeacher.setLayout(LoggedInScreenTeacherLayout);
        LoggedInScreenTeacherLayout.setHorizontalGroup(
            LoggedInScreenTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoggedInScreenTeacherLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane2))
        );
        LoggedInScreenTeacherLayout.setVerticalGroup(
            LoggedInScreenTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoggedInScreenTeacherLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane2)
                .addContainerGap())
        );

        LogOutStudent.setText("Log Out");
        LogOutStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutStudentActionPerformed(evt);
            }
        });

        StudentMainMenuStudentSwitchButton.setText("Back To Main Menu");
        StudentMainMenuStudentSwitchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentMainMenuStudentSwitchButtonActionPerformed(evt);
            }
        });
        RemoveLectureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    RemoveLectureButtonActionPerformed(evt);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        StudentTakeAttandenceSwitchButton.setText("Take Attandence");
        StudentTakeAttandenceSwitchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentTakeAttandenceSwitchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(StudentMainMenuStudentSwitchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LogOutStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StudentTakeAttandenceSwitchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(StudentTakeAttandenceSwitchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
                .addComponent(StudentMainMenuStudentSwitchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LogOutStudent)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel2);

        pnlCards3.setLayout(new java.awt.CardLayout());

        jLabel15.setText("STUDENT MAIN SCREEN");

        javax.swing.GroupLayout StudentMainScreenPanelLayout = new javax.swing.GroupLayout(StudentMainScreenPanel);
        StudentMainScreenPanel.setLayout(StudentMainScreenPanelLayout);
        StudentMainScreenPanelLayout.setHorizontalGroup(
            StudentMainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentMainScreenPanelLayout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(jLabel15)
                .addContainerGap(237, Short.MAX_VALUE))
        );
        StudentMainScreenPanelLayout.setVerticalGroup(
            StudentMainScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentMainScreenPanelLayout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel15)
                .addContainerGap(257, Short.MAX_VALUE))
        );

        pnlCards3.add(StudentMainScreenPanel, "card2");

        jButton1.setText("Take Attandence");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton1ActionPerformed(evt);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout StudentTakeAttandenceScreenPanelLayout = new javax.swing.GroupLayout(StudentTakeAttandenceScreenPanel);
        StudentTakeAttandenceScreenPanel.setLayout(StudentTakeAttandenceScreenPanelLayout);
        StudentTakeAttandenceScreenPanelLayout.setHorizontalGroup(
            StudentTakeAttandenceScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentTakeAttandenceScreenPanelLayout.createSequentialGroup()
                .addGroup(StudentTakeAttandenceScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StudentTakeAttandenceScreenPanelLayout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jButton1))
                    .addGroup(StudentTakeAttandenceScreenPanelLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(AttandenceInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        StudentTakeAttandenceScreenPanelLayout.setVerticalGroup(
            StudentTakeAttandenceScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentTakeAttandenceScreenPanelLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jButton1)
                .addGap(76, 76, 76)
                .addComponent(AttandenceInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );

        pnlCards3.add(StudentTakeAttandenceScreenPanel, "card3");

        jSplitPane1.setRightComponent(pnlCards3);

        javax.swing.GroupLayout LoggedInScreenStudentLayout = new javax.swing.GroupLayout(LoggedInScreenStudent);
        LoggedInScreenStudent.setLayout(LoggedInScreenStudentLayout);
        LoggedInScreenStudentLayout.setHorizontalGroup(
            LoggedInScreenStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        LoggedInScreenStudentLayout.setVerticalGroup(
            LoggedInScreenStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FirstScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(LoggedInScreenTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addComponent(LoggedInScreenStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FirstScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(LoggedInScreenTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(LoggedInScreenStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void RemoveLectureButtonActionPerformed(ActionEvent evt) throws ExecutionException, InterruptedException {
        removeLecture();
        }


    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    //login switch button handling
    private void LogInSwitchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        pnlCards.removeAll();
        pnlCards.add(Login);
        pnlCards.repaint();
        pnlCards.revalidate();

        jLabel17.setText("");


        SignUpName.setText("");
        SignInID.setText("");
        SignUpID.setText("");
        SignUpPassAgain.setText("");
        SignInPass.setText("");
        SignUpPass.setText("");
        SignUpSurname.setText("");
        SignUpTC.setText("");

        ogrenciAdHata.setText((""));
        ogrenciSoyadHata.setText((""));
        ogrenciTCHata.setText((""));
        ogrenciNoHataSıgnUp.setText((""));
        ogrenciPassSignUpHata.setText((""));
        ogrenciPassAgainSignUpHata.setText((""));




        requestIDTC.setText("");
        ogrenciNoAlmaTCHata.setText("");
        requestedID.setText("");




    }//GEN-LAST:event_LogInSwitchButtonActionPerformed

    //Sign up swtich button handling
    private void SignUpSwitchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        pnlCards.removeAll();
        pnlCards.add(SignUp);
        pnlCards.repaint();
        pnlCards.revalidate();
        jLabel17.setText("");


        SignUpName.setText("");
        SignInID.setText("");
        SignUpID.setText("");
        SignUpPassAgain.setText("");
        SignInPass.setText("");
        SignUpPass.setText("");
        SignUpSurname.setText("");
        SignUpTC.setText("");

        ogrenciAdHata.setText((""));
        ogrenciSoyadHata.setText((""));
        ogrenciTCHata.setText((""));
        ogrenciNoHataSıgnUp.setText((""));
        ogrenciPassSignUpHata.setText((""));
        ogrenciPassAgainSignUpHata.setText((""));



        requestIDTC.setText("");
        ogrenciNoAlmaTCHata.setText("");
        requestedID.setText("");



        Name();
        Surname();
        PassSignUp();
        PassSignUpAgain();
        TC();
        ID();
    }//GEN-LAST:event_SignUpSwitchButtonActionPerformed

    //Login button handling
    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) throws ExecutionException, InterruptedException {
        // TODO add your handling code here:

        if(SignInID.getText().equals("") || SignInPass.getPassword().equals("") ){LoginModifier.setText("Please fill the blanks.");}
        else {

            Login(SignInID.getText(), new String(SignInPass.getPassword()));
        }

        SignInID.setText("");
        SignInPass.setText("");


    }//GEN-LAST:event_LoginButtonActionPerformed

    //SignUp button handling
    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) throws ExecutionException, InterruptedException {
        // TODO add your handling code here:
        register(SignUpID.getText(), SignUpTC.getText(), SignUpName.getText(), SignUpSurname.getText(),new String(SignUpPass.getPassword()), new String(SignUpPassAgain.getPassword()));
        SignUpName.setText("");
        SignInID.setText("");
        SignUpID.setText("");
        SignUpPassAgain.setText("");
        SignInPass.setText("");
        SignUpPass.setText("");
        SignUpSurname.setText("");
        SignUpTC.setText("");

        ogrenciAdHata.setText((""));
        ogrenciSoyadHata.setText((""));
        ogrenciTCHata.setText((""));
        ogrenciNoHataSıgnUp.setText((""));
        ogrenciPassSignUpHata.setText((""));
        ogrenciPassAgainSignUpHata.setText((""));



        ogrenciAdHata.setText("");
        ogrenciSoyadHata.setText("");
        ogrenciTCHata.setText("");
        ogrenciPassAgainSignUpHata.setText("");
        ogrenciPassSignUpHata.setText("");
        ogrenciNoHataSıgnUp.setText("");

    }//GEN-LAST:event_SignUpButtonActionPerformed

    public MainScreen(CardLayout cardLayout, JButton OgrenciGirisSwitchButton, JPanel OgrenciLogin, JPanel OgrenciSignUp, JButton OgrenciUyeOlmaSwtichButton, JButton OgretimGorevlisiGirisSwitchButton, JPanel OgretimGorevlisiLogin, JPanel OgretimGorevlisiSignUp, JButton OgretimGorevlisiUyeOlmaSwitchButton, JLabel jLabel1, JLabel jLabel10, JLabel jLabel11, JLabel jLabel12, JLabel jLabel13, JLabel jLabel14, JLabel jLabel15, JLabel jLabel16, JLabel jLabel17, JLabel jLabel18, JLabel jLabel2, JLabel jLabel3, JLabel jLabel4, JLabel jLabel5, JLabel jLabel6, JLabel jLabel7, JLabel jLabel8, JLabel jLabel9, JPanel jPanel1, JSplitPane jSplitPane1, JTextField ogrenciAd, JButton ogrenciGiris, JTextField ogrenciNoSign, JTextField ogrenciNoSignUp, JPasswordField ogrenciPassAgainSignup, JPasswordField ogrenciPassSign, JPasswordField ogrenciPassSignup, JButton ogrenciSignup, JTextField ogrenciSoyad, JTextField ogrenciTCSignup, JTextField ogretimGorevlisiAd, JButton ogretimGorevlisiGiris, JTextField ogretimGorevlisiNoSign, JTextField ogretimGorevlisiNumarasıSignup, JPasswordField ogretimGorevlisiPassAgainSignup, JPasswordField ogretimGorevlisiPassSign, JPasswordField ogretimGorevlisiPassSignup, JButton ogretimGorevlisiSignUp, JTextField ogretimGorevlisiSoyad, JTextField ogretimGorevlisiTC, JPanel pnlCards) throws HeadlessException, IOException {
        this.cardLayout = cardLayout;
        this.LogInSwitchButton = OgrenciGirisSwitchButton;
        this.Login = OgrenciLogin;
        this.SignUp = OgrenciSignUp;
        this.SignUpSwitchButton = OgrenciUyeOlmaSwtichButton;

        this.jLabel1 = jLabel1;

        this.jLabel14 = jLabel14;

        this.jLabel17 = jLabel17;

        this.jLabel2 = jLabel2;

        this.jLabel5 = jLabel5;
        this.jLabel6 = jLabel6;
        this.jLabel7 = jLabel7;
        this.jLabel8 = jLabel8;
        this.jLabel9 = jLabel9;
        this.jPanel1 = jPanel1;
        this.FirstScreen = jSplitPane1;
        this.SignUpName = ogrenciAd;
        this.LoginButton = ogrenciGiris;
        this.SignInID = ogrenciNoSign;
        this.SignUpID = ogrenciNoSignUp;
        this.SignUpPassAgain = ogrenciPassAgainSignup;
        this.SignInPass = ogrenciPassSign;
        this.SignUpPass = ogrenciPassSignup;
        this.SignUpButton = ogrenciSignup;
        this.SignUpSurname = ogrenciSoyad;
        this.SignUpTC = ogrenciTCSignup;

        this.pnlCards = pnlCards;
    }

    //GetId switch button handling
    private void GetIDSwitchButonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        pnlCards.removeAll();
        pnlCards.add(RequestID);
        pnlCards.repaint();
        pnlCards.revalidate();
        jLabel17.setText("");


        SignUpName.setText("");
        SignInID.setText("");
        SignUpID.setText("");
        SignUpPassAgain.setText("");
        SignInPass.setText("");
        SignUpPass.setText("");
        SignUpSurname.setText("");
        SignUpTC.setText("");

        ogrenciAdHata.setText((""));
        ogrenciSoyadHata.setText((""));
        ogrenciTCHata.setText((""));
        ogrenciNoHataSıgnUp.setText((""));
        ogrenciPassSignUpHata.setText((""));
        ogrenciPassAgainSignUpHata.setText((""));



        requestIDTC.setText("");
        ogrenciNoAlmaTCHata.setText("");
        requestedID.setText("");





        requestIDTC();
    }//GEN-LAST:event_GetIDSwitchButonActionPerformed

         //GetId switch button handling
    protected void AddLectureSwitchButtonActionPerformed(java.awt.event.ActionEvent evt) throws ExecutionException, InterruptedException {
        // TODO add your handling code here:

        AddLectureSwitchButton();

    }//GEN-LAST:event_AddLectureSwitchButtonActionPerformed

    private void RemoveLectureSwitchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        pnlCards2.removeAll();
        pnlCards2.add(RemoveLecturePanel);
        pnlCards2.repaint();
        pnlCards2.revalidate();
    }//GEN-LAST:event_RemoveLectureSwitchButtonActionPerformed

    private void TeacherBackToMenuSwitchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        pnlCards2.removeAll();
        pnlCards2.add(TeacherMainMenuPanel);
        pnlCards2.repaint();
        pnlCards2.revalidate();
    }//GEN-LAST:event_TeacherBackToMenuSwitchButtonActionPerformed

    private void LogOutTeacherActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        LoggedInScreenTeacher.setVisible(false);
        FirstScreen.setVisible(true);

        LoginModifier.setText("");
        LoginModifier.setText("");
        LectureCreatedLabel.setText("");
        LectureNameCheckerLabel.setText("");
        LectureNameField.setText("");
        StudentAddedLabel.setText("");
        StudentIDCheckerLabel.setText("");
        StudentIDField.setText("");
    }//GEN-LAST:event_LogOutTeacherActionPerformed

    public void RequestIDButtonActionPerformed(java.awt.event.ActionEvent evt) throws ExecutionException, InterruptedException {
        // TODO add your handling code here:
        requestedID.setText(getNumber(requestIDTC.getText()));
    }//GEN-LAST:event_RequestIDButtonActionPerformed

    private void LogOutStudentActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        LoggedInScreenStudent.setVisible(false);
        FirstScreen.setVisible(true);

        LoginModifier.setText("");
    }//GEN-LAST:event_LogOutStudentActionPerformed

    private void StudentMainMenuStudentSwitchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        pnlCards3.removeAll();
        pnlCards3.add(StudentMainScreenPanel);
        pnlCards3.repaint();
        pnlCards3.revalidate();
    }//GEN-LAST:event_StudentMainMenuStudentSwitchButtonActionPerformed

    private void StudentTakeAttandenceSwitchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        pnlCards3.removeAll();
        pnlCards3.add(StudentTakeAttandenceScreenPanel);
        pnlCards3.repaint();
        pnlCards3.revalidate();
    }//GEN-LAST:event_StudentTakeAttandenceSwitchButtonActionPerformed

    private void AddStudentButtonActionPerformed(java.awt.event.ActionEvent evt) throws ExecutionException, InterruptedException {
        // TODO add your handling code here:
        AddStudent();

    }//GEN-LAST:event_AddStudentButtonActionPerformed

    private void AddLectureButtonActionPerformed(java.awt.event.ActionEvent evt) throws ExecutionException, InterruptedException {
        // TODO add your handling code here:
        AddLecture();


    }
    
    //After lecture adding we refresh our comboboxes to refresh our database
    public void refreshDayAndTime2(String day,List<String> times){
        TimeComboBox.removeAllItems();

        if(day.equals("Monday")){
            DayComboBox.setSelectedIndex(0);
        }
        else if(day.equals("Tuesday")){
            DayComboBox.setSelectedIndex(1);
        }
        else if(day.equals("Wednesday")){
            DayComboBox.setSelectedIndex(2);
        }
        else if(day.equals("Thursday")){
            DayComboBox.setSelectedIndex(3);
        }
        else if(day.equals("Friday")){
            DayComboBox.setSelectedIndex(4);
        }
        for(int i =0;i<times.size();i++){
            TimeComboBox.addItem(times.get(i));
        }




    }//GEN-LAST:event_AddLectureButtonActionPerformed

    public void refreshDayAndTime() throws ExecutionException, InterruptedException {
        TimeComboBox.removeAllItems();
        DocumentReference docRef = db.collection("Teacher").document(selectedUser.getID());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if(document.getString("ID")!=null&&document.getString("ID").equals(selectedUser.getID())&&DayComboBox.getSelectedIndex()==0){
            List<String> dayArray=new ArrayList<>();
            dayArray = (List<String>) document.get("TeacherTimeMonday");
            System.out.println(dayArray);
            System.out.println("a");
            for (int i=0;i<dayArray.size();i++){
                TimeComboBox.addItem((String) dayArray.get(i));
            }
            return;
        }
        if(document.getString("ID")!=null&&document.getString("ID").equals(selectedUser.getID())&&DayComboBox.getSelectedIndex()==1){
            List<String> dayArray=new ArrayList<>();
            dayArray = (List<String>) document.get("TeacherTimeTuesday");
            System.out.println(dayArray);
            for (int i=0;i<dayArray.size();i++){
                TimeComboBox.addItem((String) dayArray.get(i));
            }
            return;
        }
        if(document.getString("ID")!=null&&document.getString("ID").equals(selectedUser.getID())&&DayComboBox.getSelectedIndex()==2){
            List<String> dayArray=new ArrayList<>();
            dayArray = (List<String>) document.get("TeacherTimeWednesday");
            System.out.println(dayArray);
            for (int i=0;i<dayArray.size();i++){
                TimeComboBox.addItem((String) dayArray.get(i));
            }
            return;
        }
        if(document.getString("ID")!=null&&document.getString("ID").equals(selectedUser.getID())&&DayComboBox.getSelectedIndex()==3){
            List<String> dayArray=new ArrayList<>();
            dayArray = (List<String>) document.get("TeacherTimeThursday");
            System.out.println(dayArray);
            for (int i=0;i<dayArray.size();i++){
                TimeComboBox.addItem((String) dayArray.get(i));
            }
            return;
        }
        if(document.getString("ID")!=null&&document.getString("ID").equals(selectedUser.getID())&&DayComboBox.getSelectedIndex()==4){
            List<String> dayArray=new ArrayList<>();
            dayArray = (List<String>) document.get("TeacherTimeFriday");
            System.out.println(dayArray);
            for (int i=0;i<dayArray.size();i++){
                TimeComboBox.addItem((String) dayArray.get(i));
            }
            return;
        }
        return;
    }

    public void DayComboBoxİtemStateChanged(java.awt.event.ItemEvent evt) throws ExecutionException, InterruptedException {//GEN-FIRST:event_DayComboBoxİtemStateChanged

        refreshDayAndTime();





    }//GEN-LAST:event_DayComboBoxİtemStateChanged

    private void DayComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }//GEN-LAST:event_DayComboBoxActionPerformed

    private void RemoveLastAddedStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        RemoveLastAddedStudent();
    }//GEN-LAST:event_RemoveLastAddedStudentButtonActionPerformed

    //Take attendance button
    //We take users day and time
    //after we take time we pars to integer in order to compare with times in combobox and database.
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws ExecutionException, InterruptedException {
        // TODO add your handling code here:
        char space = ' ';
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();


        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String currentDate = new String(formatter.format(date));
        currentDate = currentDate.replace(':', space);
        currentDate = currentDate.replaceAll("\\s+","");
        int currentTime=Integer.parseInt(currentDate);


        ApiFuture<QuerySnapshot> future = db.collection("Main").document("HuZBsxxLeXqI7ByCvbdn").collection("Lectures").get();
// future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            List<String> studentsLec= (List<String>) document.get("LectureStudents");
            if(studentsLec.contains(selectedUser.getID())){
                if(document.getString("LectureDay").equalsIgnoreCase(dayOfWeek.toString())) {
                    String lecTime = document.getString("LectureTime");
                    lecTime = lecTime.replace(':', space);
                    lecTime = lecTime.replaceAll("\\s+", "");
                    int lecTimes = Integer.parseInt(lecTime);
                    if (lecTimes>=(currentTime-59) && lecTimes<(currentTime+59)){
                        if (lecTimes <= currentTime && (lecTimes + 10) > currentTime) {
                            AttandenceInfoLabel.setText("Your attendance succesfuly taken in course: " + document.getId());
                        } else {
                            if (lecTimes > currentTime) {
                                AttandenceInfoLabel.setText("Your lecture "+document.getId()+ " didn't start yet.");
                            } else {
                                if ((lecTimes+10) < currentTime) {
                                    AttandenceInfoLabel.setText("You are late to your "+document.getId()+ " class");
                                }
                            }
                        }
                }
                    else{
                        AttandenceInfoLabel.setText("You have no class in the next 1 hour.");
                    }
                }
            }
        }





       /* for(int i=0;i<UserArrayList.size();i++){
            if(UserArrayList.get(i).getID().equals(selectedID)){
                Student student = (Student)UserArrayList.get(i);
                for(int x =0;x<student.getLectures().size();x++){
                    if(student.getLectures().get(x).day.equalsIgnoreCase(dayOfWeek.toString())){
                        System.out.println(student.getLectures().get(x).getStartTime());
                    }
                }
            }
        }

        */
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) throws IOException, ExecutionException, InterruptedException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //we set our database connection with spesific key
        FileInputStream serviceAccount =
                new FileInputStream("");//You need to put your firestore database access key as file path.

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> query = db.collection("Main").get();
// ...
// query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            userCount=document.getDouble("userCount");
        }


        TCArrayList[0][0]=teacherTC;
        TCArrayList[1][0]=studentTC;
        geciciTCArrayList[0][0]=geciciTeacherTC;
        geciciTCArrayList[1][0]=geciciStudentTC;
        //Creating TC for sign Up and Request ID
        TCArrayList[0][0].add("11111111111");
        TCArrayList[1][0].add("22222222222");



        SignUpIDArrayList[0][0]=teacherSignID;
        SignUpIDArrayList[1][0]=studentSignID;


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainScreen().setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LoggedInScreenTeacher.setVisible(false);
                LoggedInScreenStudent.setVisible(false);


            }
        });
    }

    //Login method checks data from textfield and compare with database
    public void Login(String id,String password) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("Student").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
            if(document.getString("ID")!=null&&document.getString("ID").equals(id) && document.getString("Password").equals(password)){
                while(true) {
                        selectedUser=new Student(document.getString("Name"),document.getString("Surname"),document.getString("ID"),document.getString("Password"), document.getString("TC"));


                        //Teacher Log In

                        FirstScreen.setVisible(false);

                        LoggedInScreenStudent.setVisible(true);

                        break;

                }
            }
            else
            {
                DocumentReference docRef1 = db.collection("Teacher").document(id);
                ApiFuture<DocumentSnapshot> future1 = docRef1.get();
                DocumentSnapshot document1 = future1.get();
                if(document1.getString("ID")!=null&&document1.getString("ID").equals(id) && document1.getString("Password").equals(password)){
                    while(true) {
                        selectedUser=new Teacher(document1.getString("Name"),document1.getString("Surname"),document1.getString("ID"),document1.getString("Password"), document1.getString("TC"));

                        //Student Log In

                        FirstScreen.setVisible(false);

                        LoggedInScreenTeacher.setVisible(true);

                        break;

                    }
                }
                else{
                    LoginModifier.setText("Wrong ID or Password!");

                }
            }




    }

    //register method checks data from textfield and compare with database
    public void register(String id,String tc,String name,String surname,String pass1,String pass2) throws ExecutionException, InterruptedException {
        if(ogrenciAdHata.getText().equals("True") && ogrenciSoyadHata.getText().equals("True") && ogrenciPassAgainSignUpHata.getText().equals("True") && ogrenciPassSignUpHata.getText().equals("True")
                && ogrenciNoHataSıgnUp.getText().equals("True") && ogrenciTCHata.getText().equals("True")){

            DocumentReference docRef1 = db.collection("Main").document("HuZBsxxLeXqI7ByCvbdn");
            ApiFuture<DocumentSnapshot> future1 = docRef1.get();
            DocumentSnapshot document1 = future1.get();
            List<String> gecici= (List<String>) document1.get("UsableTCTeacher");
            DocumentReference docRef2 = db.collection("Main").document("HuZBsxxLeXqI7ByCvbdn");
            ApiFuture<DocumentSnapshot> future2 = docRef2.get();
            DocumentSnapshot document2 = future2.get();
            List<String> gecici2= (List<String>) document2.get("UsableIDTeacher");
            List<String> gecici3= (List<String>) document1.get("UsableTCStudent");
            List<String> gecici4= (List<String>) document2.get("UsableIDStudent");
            for(int i=0;i<gecici2.size();i++){
                if (gecici2.get(i).equals(id)){
                    DocumentReference docRef = db.collection("Teacher").document(id);
                    Map<String, Object> data = new HashMap<>();
                    List<String> timeArray = new ArrayList<String>();
                    for(int f=9;f<19;f++){
                        if(f==9)
                            timeArray.add("0"+f+":00");
                        else
                            timeArray.add(f+":00");
                    }

                    data.put("Name", name);
                    data.put("Surname", surname);
                    data.put("ID", id);
                    data.put("Password", pass1);
                    data.put("TC",tc);
                    data.put("TeacherTimeMonday",timeArray);
                    data.put("TeacherTimeTuesday",timeArray);
                    data.put("TeacherTimeWednesday",timeArray);
                    data.put("TeacherTimeThursday",timeArray);
                    data.put("TeacherTimeFriday",timeArray);
                    ApiFuture<WriteResult> result = docRef.set(data);

                    UserArrayList.add(new Teacher(name,surname,id,pass1,tc));

                    jLabel17.setText("SUCCESS");
                    gecici2.remove(i);
                    System.out.println(gecici);
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("userCount", userCount);
                    docData.put("UsableTCTeacher", gecici);
                    docData.put("UsableIDTeacher", gecici2);
                    docData.put("UsableTCStudent", gecici3);
                    docData.put("UsableIDStudent", gecici4);
                    ApiFuture<WriteResult> future = db.collection("Main").document("HuZBsxxLeXqI7ByCvbdn").set(docData);
                    return;

                }

            }
            for(int i=0;i<gecici4.size();i++){
                if (gecici4.get(i).equals(id)){
                    DocumentReference docRef = db.collection("Student").document(id);
                    Map<String, Object> data = new HashMap<>();
                    data.put("Name", name);
                    data.put("Surname", surname);
                    data.put("ID", id);
                    data.put("Password", pass1);
                    data.put("TC",tc);
                    data.put("Absenteeism",0);
                    ApiFuture<WriteResult> result = docRef.set(data);
                    UserArrayList.add(new Student(name,surname,id,pass1,tc));
                    jLabel17.setText("SUCCESS");
                    gecici4.remove(i);
                    System.out.println(gecici);
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("userCount", userCount);
                    docData.put("UsableTCTeacher", gecici);
                    docData.put("UsableIDTeacher", gecici2);
                    docData.put("UsableTCStudent", gecici3);
                    docData.put("UsableIDStudent", gecici4);
                    ApiFuture<WriteResult> future = db.collection("Main").document("HuZBsxxLeXqI7ByCvbdn").set(docData);
                    return;


                }
            }
        }
        else{
        jLabel17.setText("Fill the blanks correctly");

            }
    }

    //ID method checks data from textfield and compare with database
    public String getNumber(String tc) throws ExecutionException, InterruptedException {


        if(tc.matches("[0-9]+")&& tc.length()==11 && tc.length()!=0){

            DocumentReference docRef1 = db.collection("Main").document("HuZBsxxLeXqI7ByCvbdn");
            ApiFuture<DocumentSnapshot> future1 = docRef1.get();
            DocumentSnapshot document1 = future1.get();
            List<String> gecici= (List<String>) document1.get("UsableTCTeacher");
            DocumentReference docRef2 = db.collection("Main").document("HuZBsxxLeXqI7ByCvbdn");
            ApiFuture<DocumentSnapshot> future2 = docRef2.get();
            DocumentSnapshot document2 = future2.get();
            List<String> gecici2= (List<String>) document2.get("UsableIDTeacher");
            List<String> gecici3= (List<String>) document1.get("UsableTCStudent");
            List<String> gecici4= (List<String>) document2.get("UsableIDStudent");
            for(int i=0;i<gecici.size();i++){
                if(tc.equals(gecici.get(i))){
                    String year="2021";
                    String number;
                    number=year;
                    while((number+(int)userCount).length()!=11){
                        number=number+"0";
                    }
                    gecici.remove(i);
                    userCount++;
                    gecici2.add(number+((int)userCount-1));
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("userCount", userCount);
                    docData.put("UsableTCTeacher", gecici);
                    docData.put("UsableIDTeacher", gecici2);
                    docData.put("UsableTCStudent", gecici3);
                    docData.put("UsableIDStudent", gecici4);
                    ApiFuture<WriteResult> future = db.collection("Main").document("HuZBsxxLeXqI7ByCvbdn").set(docData);
                    return number+((int)userCount-1);

                }
            }

            for(int i=0;i<gecici3.size();i++){
                if(tc.equals(gecici3.get(i))){
                    String year="2021";
                    String number;
                    number=year;
                    while((number+(int)userCount).length()!=11){
                        number=number+"0";
                    }
                    gecici3.remove(i);
                    userCount++;
                    gecici4.add(number+((int)userCount-1));
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("userCount", userCount);
                    docData.put("UsableTCTeacher", gecici);
                    docData.put("UsableIDTeacher", gecici2);
                    docData.put("UsableTCStudent", gecici3);
                    docData.put("UsableIDStudent", gecici4);
                    ApiFuture<WriteResult> future = db.collection("Main").document("HuZBsxxLeXqI7ByCvbdn").set(docData);
                    return number+((int)userCount-1);

                }
            }

      }

      return"";
    }

    //ADD STUDENT TO LECTURE
    public void AddStudent() throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("Student").document(StudentIDField.getText());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if(document.getString("ID")!=null && document.getString("ID").equals(StudentIDField.getText())){
            if(!students.contains(document.getString("ID"))) {
                students.add(document.getString("ID"));
                StudentAddedLabel.setText(StudentIDField.getText() + " added to the Lecture");
            }
            else{
                StudentAddedLabel.setText("This student with id "+StudentIDField.getText() +" already in the list");
            }

        }
        else{
            StudentAddedLabel.setText("There is no student with id "+StudentIDField.getText());
        }


    }
    //ADD LECTURE
    public void AddLecture() throws ExecutionException, InterruptedException {
        LectureCreatedLabel.setText("");
        if(TimeComboBox.getSelectedItem()!=null && students.size()>0 ){
            Lecture lecture = new Lecture(LectureNameField.getText(),(String)DayComboBox.getSelectedItem(),(String)TimeComboBox.getSelectedItem());
            DocumentReference docRef = db.collection("Teacher").document(selectedUser.getID());
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            if(document.getString("ID")!=null && document.getString("ID").equals(selectedUser.getID())){
                while(true) {
                    List<String> gecici;
                    gecici = (List<String>) document.get("TeacherTime"+DayComboBox.getSelectedItem());
                    for(int i=0;i<gecici.size();i++){
                        if(gecici.get(i).equals(TimeComboBox.getSelectedItem())){
                            gecici.remove(i);
                        }
                    }
                    ApiFuture<Void> futureTransaction = db.runTransaction(transaction -> {
                        // retrieve document and increment population field
                        DocumentSnapshot snapshot = transaction.get(docRef).get();
                        transaction.update(docRef, "TeacherTime"+DayComboBox.getSelectedItem(), gecici);
                        return null;
                    });
                    DocumentReference docRef2 = db.collection("Main").document("HuZBsxxLeXqI7ByCvbdn").collection("Lectures").document(LectureNameField.getText());
                    ApiFuture<DocumentSnapshot> future2 = docRef2.get();
                    DocumentSnapshot document2 = future2.get();
                    Map<String, Object> data = new HashMap<>();
                    data.put("LectureTeacher",selectedUser.getID());
                    data.put("LectureName", LectureNameField.getText());
                    data.put("LectureDay", DayComboBox.getSelectedItem());
                    data.put("LectureTime", TimeComboBox.getSelectedItem());
                    data.put("LectureStudents",students);
                    ApiFuture<WriteResult> result = docRef2.set(data);
                    LectureCreatedLabel.setText("Lecture created!");
                    students.clear();





                    refreshDayAndTime2((String) DayComboBox.getSelectedItem(),gecici);



                    break;

                }

            }

        }
        else{
            if(TimeComboBox.getSelectedItem()==null){
                LectureCreatedLabel.setText("Invalid time!");
            }
            else{
                if(students.size()<=0){
                    LectureCreatedLabel.setText("A lecture cannot be created without students");

                }
            }
        }
    }

    public void RemoveLastAddedStudent(){

        if(students.size()>0){
            StudentAddedLabel.setText(students.get(students.size()-1)+" removed from the lecture.");
            students.remove(students.size()-1);
        }
        else{
            StudentAddedLabel.setText("There is no student added yet.");
        }
    }

    public void AddLectureSwitchButton() throws ExecutionException, InterruptedException {
        students=new ArrayList<String>();
        TimeComboBox.removeAllItems();
        DocumentReference docRef = db.collection("Teacher").document(selectedUser.getID());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if(document.getString("ID")!=null&&document.getString("ID").equals(selectedUser.getID())){
            List<String> dayArray;
            dayArray = (ArrayList<String>) document.get("TeacherTimeMonday");
            for (int i=0;i<dayArray.size();i++){
                TimeComboBox.addItem(dayArray.get(i));
            }
        }


        pnlCards2.removeAll();
        pnlCards2.add(AddLecturePanel);


        pnlCards2.repaint();
        pnlCards2.revalidate();








        TeacherScreenStudentID();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton AddLectureButton;
    public javax.swing.JPanel AddLecturePanel;
    public javax.swing.JButton AddLectureSwitchButton;
    public javax.swing.JButton AddStudentButton;
    public javax.swing.JLabel AttandenceInfoLabel;
    public javax.swing.JComboBox<String> DayComboBox;
    public javax.swing.JSplitPane FirstScreen;
    public javax.swing.JButton GetIDSwitchButon;
    public javax.swing.JLabel LectureCreatedLabel;
    public javax.swing.JLabel LectureNameCheckerLabel;
    public javax.swing.JTextField LectureNameField;
    public javax.swing.JButton LogInSwitchButton;
    public javax.swing.JButton LogOutStudent;
    public javax.swing.JButton LogOutTeacher;
    public static javax.swing.JPanel LoggedInScreenStudent;
    public static javax.swing.JPanel LoggedInScreenTeacher;
    public javax.swing.JPanel Login;
    public javax.swing.JButton LoginButton;
    public javax.swing.JLabel LoginModifier;
    public javax.swing.JButton RemoveLastAddedStudentButton;
    public javax.swing.JButton RemoveLectureButton;
    public javax.swing.JTextField RemoveLectureNameField;
    public javax.swing.JPanel RemoveLecturePanel;
    public javax.swing.JButton RemoveLectureSwitchButton;
    public javax.swing.JPanel RequestID;
    public javax.swing.JButton RequestIDButton;
    public javax.swing.JTextField SignInID;
    public javax.swing.JPasswordField SignInPass;
    public javax.swing.JPanel SignUp;
    public javax.swing.JButton SignUpButton;
    public javax.swing.JTextField SignUpID;
    public javax.swing.JTextField SignUpName;
    public javax.swing.JPasswordField SignUpPass;
    public javax.swing.JPasswordField SignUpPassAgain;
    public javax.swing.JTextField SignUpSurname;
    public javax.swing.JButton SignUpSwitchButton;
    public javax.swing.JTextField SignUpTC;
    public javax.swing.JLabel StudentAddedLabel;
    public javax.swing.JLabel StudentIDCheckerLabel;
    public javax.swing.JTextField StudentIDField;
    public javax.swing.JButton StudentMainMenuStudentSwitchButton;
    public javax.swing.JPanel StudentMainScreenPanel;
    public javax.swing.JPanel StudentTakeAttandenceScreenPanel;
    public javax.swing.JButton StudentTakeAttandenceSwitchButton;
    public javax.swing.JButton TeacherBackToMenuSwitchButton;
    public javax.swing.JPanel TeacherMainMenuPanel;
    public javax.swing.JComboBox<String> TimeComboBox;
    public javax.swing.JButton jButton1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel123;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel24;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JSplitPane jSplitPane1;
    public javax.swing.JSplitPane jSplitPane2;
    public javax.swing.JLabel ogrenciAdHata;
    public javax.swing.JLabel ogrenciNoAlmaTCHata;
    public javax.swing.JLabel ogrenciNoHataSıgnUp;
    public javax.swing.JLabel ogrenciPassAgainSignUpHata;
    public javax.swing.JLabel ogrenciPassSignUpHata;
    public javax.swing.JLabel ogrenciSoyadHata;
    public javax.swing.JLabel ogrenciTCHata;
    public javax.swing.JPanel pnlCards;
    public javax.swing.JPanel pnlCards2;
    public javax.swing.JPanel pnlCards3;
    public javax.swing.JTextField requestIDTC;
    public javax.swing.JTextField requestedID;
    // End of variables declaration//GEN-END:variables
}
