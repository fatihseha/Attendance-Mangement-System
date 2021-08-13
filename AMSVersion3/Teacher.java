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
public class Teacher extends User{

    private String userName;
    private String password;
    private String name;
    private String surname;
    private String tc;
    private boolean isTeacher=true;
    private ArrayList<Lecture> lectures=new ArrayList<Lecture>();
   // private String[][] dayAndTime = new String[4][9];
   // ArrayList <ArrayList> dayAndTime = new ArrayList<>();
     public ArrayList[] dayAndTime = new ArrayList[5];
    public ArrayList<String>Monday = new ArrayList<String>();
    public ArrayList<String>Tuesday = new ArrayList<String>();
    public ArrayList<String>Wednesday = new ArrayList<String>();
    public ArrayList<String>Thursday = new ArrayList<String>();
    public ArrayList<String>Friday = new ArrayList<String>();

       public ArrayList[] getDayandTime (){
            return dayAndTime;
        }



    public Teacher(String name,String surname,String username,String password,String tc){
        this.userName=username;
        this.password=password;
        this.name=name;
        this.surname=surname;
        this.tc=tc;

        dayAndTime[0]=Monday;
        dayAndTime[1]=Tuesday;
        dayAndTime[2]=Wednesday;
        dayAndTime[3]=Thursday;
        dayAndTime[4]=Friday;

        Monday.add("09.00");
        Monday.add("10.00");
        Monday.add("11.00");
        Monday.add("12.00");
        Monday.add("13.00");
        Monday.add("14.00");
        Monday.add("15.00");
        Monday.add("16.00");
        Monday.add("17.00");
        Monday.add("18.00");

        Tuesday.add("09.00");
        Tuesday.add("10.00");
        Tuesday.add("11.00");
        Tuesday.add("12.00");
        Tuesday.add("13.00");
        Tuesday.add("14.00");
        Tuesday.add("15.00");
        Tuesday.add("16.00");
        Tuesday.add("17.00");
        Tuesday.add("18.00");

        Wednesday.add("09.00");
        Wednesday.add("10.00");
        Wednesday.add("11.00");
        Wednesday.add("12.00");
        Wednesday.add("13.00");
        Wednesday.add("14.00");
        Wednesday.add("15.00");
        Wednesday.add("16.00");
        Wednesday.add("17.00");
        Wednesday.add("18.00");

        Thursday.add("09.00");
        Thursday.add("10.00");
        Thursday.add("11.00");
        Thursday.add("12.00");
        Thursday.add("13.00");
        Thursday.add("14.00");
        Thursday.add("15.00");
        Thursday.add("16.00");
        Thursday.add("17.00");
        Thursday.add("18.00");

        Friday.add("09.00");
        Friday.add("10.00");
        Friday.add("11.00");
        Friday.add("12.00");
        Friday.add("13.00");
        Friday.add("14.00");
        Friday.add("15.00");
        Friday.add("16.00");
        Friday.add("17.00");
        Friday.add("18.00");

    }



    @Override
    public String getID(){
        return userName;
    }
    @Override
    public String getPass(){
        return password;
    }
    @Override
    public String getName(){
        return name;
    }
    @Override
    public String getSurname(){
        return surname;
    }

    @Override
    public boolean getIsTeacher() {
        return isTeacher;
    }

    public void addLecture(Lecture lecture){
        this.lectures.add(lecture);

    }

    @Override
    public ArrayList getLectures() {
        return lectures;
    }


}
