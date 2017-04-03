/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject;

import java.security.MessageDigest;
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
     * Database()
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
     * checkLogin (String uName, String pwd)
     * -------------------------------------------------
     * Checks User table to ensure Login information is correct.
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
                        byte [] salt = new PasswordHashing().createSalt();
                        String hashPwd = new PasswordHashing().getHash("Admin",salt);
                        
                        preparedStatement = connect.prepareStatement("INSERT INTO Login (Username, Password, pwdSalt, Role) VALUES (?,?,?,?)");
                        preparedStatement.setString(1, "Admin");
                            preparedStatement.setString(2, hashPwd);
                        preparedStatement.setBytes(3, salt);
                        preparedStatement.setString(4, "Administrator");
                        preparedStatement.executeUpdate();  
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
            System.out.println("Error retreating salt: " + e);
            return null;
        }
    }    
}
