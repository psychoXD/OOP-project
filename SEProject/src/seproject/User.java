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
public class User {
    //Declared Attributes/Variables
    private String username;    //User username
    private String password;    //User Hash Password
    private String firstName;   //User First Name
    private String lastName;    //User Last Name
    private int id;             //User ID
    
    
    /**
     * User(String, String, String, String, int)
     * --------------------------------------------------
     * User Constructor of 5 parameters.
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param id 
     */
    public User(String username, String password, String firstName, String lastName, int id)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }
    

    /**
     * getUsername()
     * ------------------------------
     * Return username of User as String.
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * getPassword()
     * --------------------------------
     * Return hash password of User as String.
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * getFirstName()
     * ---------------------------------
     * Return firstName of User as String.
     * @return 
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getLastName()
     * ----------------------------------
     * Return lastName of User as String.
     * @return 
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * getID()
     * ------------------------------------
     * Return ID of User as int.
     * @return 
     */
    public int getId() {
        return id;
    }
}
