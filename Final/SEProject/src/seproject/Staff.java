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
public class Staff extends User {
    //Declared Variables
    private String position;    //Staff Role
    private String department;  //Department Staff works for

    
    /**
     * Staff(String, String, String, String, String, int, String, String) Constructor
     * ---------------------------------------------------------------------------------
     * Staff Constructor with 8 parameters.
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param id
     * @param position
     * @param department 
     */
    public Staff(String username, String password, String firstName, 
                 String lastName, int id, String position, String department) 
    {
        super(username, password, firstName, lastName, id);
        this.position = position;
        this.department = department;
    }

    /**
     * getPosition()
     * ---------------------
     * Return position of Staff as String.
     * @return 
     */
    public String getPosition() {
        return position;
    }

    /**
     * getDaprtment()
     * ---------------------------
     * Returns department of Staff as String.
     * @return 
     */
    public String getDepartment() {
        return department;
    }
    
}
