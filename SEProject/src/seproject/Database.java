/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 *
 * @author reticent
 */
public class Database {
    
    //Declared Variables
    Connection connect;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    String user;
    String password;
    String url;
    
    

    /**
     *  Database()
     * --------------------------
     * Default constructor for Database class that establishes
     * connection to mySQL db "sims_dbm".
     */
    public Database()
    {
        url = "jdbc:mysql://localhost/sims_dbm?";
        user = "root";
        password = "q1w2e3r4";
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    /**
     *  Database(String url, String user, String password)
     * --------------------------
     * 3 parameter constructor for Database class that establishes
     * connection to mySQL db with user provided parameters.
     * @param url
     * @param user
     * @param password 
     */
    public Database(String url, String user, String password)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    /**
     * checkLogin (String uName, String pwd)
     * -------------------------------------------------
     * Returns true if Username and Password match that in the database,
     * else it returns false if Username or Password does not exist or match up.
     * @param uName
     * @param pwd
     * @return 
     */
    public Boolean checkLogin (String uName, char [] pwd)
    {
        try
        {   
            byte [] s = getSalt(uName); //Stores salt of Username
            String hash;   //Stores hashed password
            
            if (s == null) //If salt does not exist due to user not existing in table
            {
                hash = new String(pwd); 
            }
            else
            {
                hash = new PasswordHashing().getHash(pwd, s); 
            }
           
            preparedStatement = connect.prepareStatement("SELECT * FROM Login WHERE Username=? AND Password=?");
            preparedStatement.setString(1, uName);
            preparedStatement.setString(2, hash);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next())    //If username and password are correct
            {
                return true;
            }
            else
            {
                preparedStatement = connect.prepareStatement("SELECT COUNT(*) AS count FROM Login");
                resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next())
                {
                    int i = resultSet.getInt("count");
                    if (i == 0) //Table is empty
                    {
                        createAdmin();  //create admin account
                    }     
                }
                return false;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error while validating: " + e);
            return false;
        } 
    }
    
    /**
     * getLoginInfo(String uName)
     * -------------------------------------
     * Returns User class, either as Staff class or Student class, depending on
     * the "Role" column in Login table.
     * @param uName
     * @return 
     */
    public User getLoginInfo(String uName)
    {
        User user;  //Stores User information
        try
        {   
            preparedStatement = connect.prepareStatement("SELECT * FROM Login WHERE Username=?");
            preparedStatement.setString(1, uName);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) //If User exists
            {
                int uID = resultSet.getInt("UserID");
                String role = resultSet.getString("Role");
                String pwd = resultSet.getString("Password");
                String username = resultSet.getString("Username");
                String firstName = "";
                String lastName = "";
                
                if (role.equals("Staff")) //If User is Staff
                {
                    String position = "";
                    String department = "";
                    
                    preparedStatement = 
                            connect.prepareStatement("SELECT * FROM SchoolStaff "
                                    + "WHERE StaffID=?");
                    preparedStatement.setInt(1, uID);
                    resultSet = preparedStatement.executeQuery();
                    
                    if (resultSet.next())
                    {
                        firstName = resultSet.getString("FirstName");
                        lastName = resultSet.getString("LastName");
                        position = resultSet.getString("Position");
                        department = resultSet.getString("Department");
                    }
                    
                    user = new Staff(username, pwd, firstName, lastName, uID, position, department);
                }
                else  //else, User is Student
                {
                    preparedStatement = 
                            connect.prepareStatement("SELECT * FROM Student "
                                    + "WHERE StaffID=?");
                    preparedStatement.setInt(1, uID);
                    resultSet = preparedStatement.executeQuery();
                    
                    if (resultSet.next())
                    {
                        firstName = resultSet.getString("FirstName");
                        lastName = resultSet.getString("LastName");
                    }
                    
                    user = new Student(username, pwd, firstName, lastName, uID);
                }
                
                return user;
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error while getLoginInfo(): " + e);
            return null;
        }
    }
    
    /**
     * changeUserPwd(String, char [])
     * --------------------------------------------
     * Changes User Password and Salt from Login Table.
     * @param Username
     * @param pwd
     * @return 
     */
    public void changeUserPwd(String username, char [] pwd) throws NoSuchAlgorithmException
    {
        //boolean b = true;
        try
        {
            byte [] salt = new PasswordHashing().createSalt();
            String hashPwd = new PasswordHashing().getHash(pwd,salt);
        
            preparedStatement = 
                    connect.prepareStatement("UPDATE Login SET Password=?, pwdSalt=? "
                            + "WHERE Username=?");
            preparedStatement.setString(1, hashPwd);
            preparedStatement.setBytes(2, salt);
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();
            //return b;
        }
        catch (Exception e)
        {
            System.out.println("Error changing password: " + e);
            //return false;
        }
    }
    
    /**
     * getSalt(String uName)
     * -----------------------------------
     * Returns byte [] of User's password salt if Username exist in the Login table,
     * else returns null if Username does not exist.
     * @param uName
     * @return 
     */
    private byte [] getSalt(String uName)
    {
        try
        {
            preparedStatement = connect.prepareStatement("SELECT pwdSalt FROM Login WHERE Username=?");
            preparedStatement.setString(1, uName);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next())
            {
                return resultSet.getBytes("pwdSalt");
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error getting salt: " + e);
            return null;
        }
    } 
    
    /**
     * createAdmin()
     * ------------------------
     * Method creates a new administrator account (SchoolStaff & Login) 
     * if there is no information Login table.
     */
    private void createAdmin()
    {
        try
        {
            int id = 0;
            byte [] salt = new PasswordHashing().createSalt();
            String hashPwd = new PasswordHashing().getHash("Admin",salt);

            //Inserting Admin information to Login table
            preparedStatement = 
                    connect.prepareStatement("INSERT INTO Login "
                            + "(Username, Password, pwdSalt, Role) "
                            + "VALUES (?,?,?,?)");
            preparedStatement.setString(1, "Admin");
            preparedStatement.setString(2, hashPwd);
            preparedStatement.setBytes(3, salt);
            preparedStatement.setString(4, "Staff");
            preparedStatement.executeUpdate();
            
            //Getting Admin ID
            preparedStatement = connect.prepareStatement("SELECT UserID FROM Login WHERE Username=?");
            preparedStatement.setString(1, "Admin");
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next())
            {
                id = resultSet.getInt("UserID");
            }

            //Inserting Admin information to SchoolStaff table
            preparedStatement = 
                    connect.prepareStatement("INSERT INTO SchoolStaff "
                            + "(StaffID, FirstName, LastName, Position, Department) "
                            + "VALUES (?,?,?,?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "Admin");
            preparedStatement.setString(3, "Nimda");
            preparedStatement.setString(4, "Database Administrator");
            preparedStatement.setString(5, "IT");
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("Error creating Admin account: " + e);
        }
    }
}
