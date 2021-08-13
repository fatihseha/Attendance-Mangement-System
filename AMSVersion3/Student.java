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
public class Student extends User {
    private String userName;
    private String password;
    private String name;
    private String surname;
    private String tc;
    private boolean isTeacher=false;
    private ArrayList<Lecture> lectures=new ArrayList<Lecture>();


    public Student(String name,String surname,String username,String password,String tc){
        this.userName=username;
        this.password=password;
        this.name=name;
        this.surname=surname;
        this.tc=tc;
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
    public boolean getIsTeacher() {
        return isTeacher;
    }
    public ArrayList<Lecture> getLectures(){
        return lectures;
    }
}
