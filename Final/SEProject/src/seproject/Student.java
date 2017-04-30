/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject;

/**
 *
 * @author reticent
 */
public class Student extends User {
    
    //Declared Variables
    private double gpa; //Students overall GPA
    
    /**
     * Student (String, String, String, String, id)
     * ---------------------------------------------------------
     * Student Constructor with 5 Parameters.
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param id 
     */
    public Student(String username, String password, String firstName, String lastName, int id) {
        super(username, password, firstName, lastName, id);
    }

    /**
     * getGPA()
     * ---------------------------------
     * Return GPA of Student as Double.
     * @return 
     */
    public double getGpa() {
        return gpa;
    }
    
    
    
}
