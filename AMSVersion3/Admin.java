/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMSVersion3;

import java.util.ArrayList;

/**
 *
 * @author Seha
 */
public class Admin extends User{
    String userName;
    String password;
    String name;
    String surname;

    public Admin(){
        this.userName="admin";
        this.password="admin";
        this.name="admin";
        this.surname="admin";

    }
    public String getID(){
        return userName;
    }
    public String getPass(){
        return password;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }

    @Override
    public ArrayList<Lecture> getLectures() {
        return null;
    }
}
