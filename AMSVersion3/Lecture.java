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
public class Lecture {
    private ArrayList<Student> StudentArrayList=new ArrayList();
    private String startTime;
    private String finishTime;
    public String day;
    private Teacher teacher;
    private String name;

    public Lecture(String name,String day1,String time){
        this.name=name;
        this.day=day1;
        this.startTime=time;

    }
    public void addStudent(Student student){
        StudentArrayList.add(student);
    }
    public void removeStudentByID(String ID){
        boolean done=false;
        for(int i=0;i<StudentArrayList.size();i++){
            if(StudentArrayList.get(i).getID().equals(ID)){
                StudentArrayList.remove(i);
                done=true;
            }
        }
        if(done){
            System.out.println("Succes");
        }
        else{
            System.out.println("There is no student with such ID in this lecture.");
        }

    }
    public void removeLastStudent(){
        StudentArrayList.remove(StudentArrayList.size()-1);
    }
    public void setTeacher(Teacher teacher){
        this.teacher=teacher;
    }
    public String getStartTime() {
        return startTime;
    }




}
