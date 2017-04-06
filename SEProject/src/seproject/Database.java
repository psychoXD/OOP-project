/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
                                    + "WHERE UserID=?");
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
                                    + "WHERE UserID=?");
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
     * @param username
     * @param pwd 
     * @throws java.security.NoSuchAlgorithmException 
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
     * createUser()
     * --------------------------------
     * Create new User to Login Table and in either Student Table or SchoolStaff
     * Table, depending on the size of the object.
     * @param size
     * @param o
     * @return 
     * @throws java.security.NoSuchAlgorithmException
     */
    public boolean createUser(int size, Object[] o) throws NoSuchAlgorithmException
    {
        String firstName = (String)o[0];   //First Name of User
        String lastName = (String)o[1];    //Last Name of User
        String username = createUsername(lastName + firstName.charAt(0));    //Holds Username
        
        if (username == null)
        {
            return false;
            //Dummy if statement//
        }
        else
        {
            byte [] salt = new PasswordHashing().createSalt();  //Holds Password Salt
            String hashPwd =  new PasswordHashing().getHash(firstName.concat(lastName),salt);    //Holds Hashed pwd

            if (size == 4) //New User is Staff
            {
                try
                {
                    String position = (String)o[2];
                    String department = (String)o[3];
                    int id = 0;

                    //Preparing SQL Statement for Login (Staff User)
                    preparedStatement = connect.
                        prepareStatement("INSERT INTO Login "
                                + "(Username, Password, pwdSalt, Role ) "
                                + "VALUES (?,?,?,?)");
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, hashPwd);
                    preparedStatement.setBytes(3, salt);
                    preparedStatement.setString(4, "Staff");
                    preparedStatement.executeUpdate();
                    
                     //Getting User ID
                    preparedStatement = connect.prepareStatement("SELECT* FROM Login WHERE Username=?");
                    preparedStatement.setString(1, username);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next())
                    {
                        id = resultSet.getInt("UserID");
                    }

                    //Inserting New Staff User information to SchoolStaff Table
                    preparedStatement = 
                            connect.prepareStatement("INSERT INTO SchoolStaff "
                                    + "(UserID, FirstName, LastName, Position, Department) "
                                    + "VALUES (?,?,?,?,?)");
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, firstName);
                    preparedStatement.setString(3, lastName);
                    preparedStatement.setString(4, position);
                    preparedStatement.setString(5, department);
                    preparedStatement.executeUpdate();
                    return true;
                }
                catch (Exception e)
                {
                    System.out.println("Error Inserting/Creating Staff User Account: " + e);
                    return false;
                }

            }
            else if (size == 2) //New User is Student
            {
                try
                {
                    int id = 0;

                    //Preparing SQL Statement for Login (Student User)
                    preparedStatement = connect.
                        prepareStatement("INSERT INTO Login "
                                + "(Username, Password, pwdSalt, Role) "
                                + "VALUES (?,?,?,?)");
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, hashPwd);
                    preparedStatement.setBytes(3, salt);
                    preparedStatement.setString(4, "Student");
                    preparedStatement.executeUpdate();
                    
                    //Getting User ID
                    preparedStatement = connect.prepareStatement("SELECT UserID FROM Login WHERE Username =?");
                    preparedStatement.setString(1,  username);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next())
                    {
                        id = resultSet.getInt("UserID");
                    }

                    //Inserting New Student User information to Student Table
                    preparedStatement = 
                            connect.prepareStatement("INSERT INTO Student "
                                    + "(UserID, FirstName, LastName) "
                                    + "VALUES (?,?,?)");
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, firstName);
                    preparedStatement.setString(3, lastName);
                    preparedStatement.executeUpdate();
                    return true;
                }
                catch (Exception e)
                {
                    System.out.println("Error Inserting/Creating Student User Account: " + e);
                    return false;
                }
            }
        }
        return false;
    }
    
    /**
     * createNewCourse(Object [] o)
     * -------------------------------------------
     * Inserts a new Class/Course to the Class entity in the database
     * by unpacking its contents from the Object array.
     * o[0] = CourseID
     * o[1] = Room No.
     * o[2] = ClassBeginTime
     * o[3] = ClassEndTime
     * o[4] = ClassDays
     * o[5] = StaffID
     * @param o
     * @return 
     */
    public boolean createNewCourse(Object [] o)
    {
        try
        {
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            //Instanced Variables
            String courseID = (String)o[0];
            String RoomNo = (String)o[1];
            Time beginTime = new Time(formatter.parse((String)o[2]).getTime());
            Time endTime = new Time(formatter.parse((String)o[3]).getTime());
            String day = (String)o[4];
            int id = (int)o[5];
            
            //Preparing SQL Statement for Login (Student User)
            preparedStatement = connect.
                prepareStatement("INSERT INTO Class "
                        + "(CourseID, RoomNo, ClassBeginTime, ClassEndTime, ClassDays, UserID) "
                        + "VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, courseID);
            preparedStatement.setString(2, RoomNo);
            preparedStatement.setTime(3, beginTime);
            preparedStatement.setTime(4, endTime);
            preparedStatement.setString(5, day);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error Inserting New Courses in Database: " + e);
            return false;
        }
    }
    
    /**
     * getAllProfessorID()
     * --------------------------------
     * Returns an ArrayList<Integer> with all UserID of every teacher/professor.
     * @return 
     */
    public ArrayList<Integer> getAllProfessorID()
    {
        try
        {
            ArrayList<Integer> l = new ArrayList<Integer>();    //Holds all Teacher ID
            int id; //Holds current Teacher ID
            
            //Getting User ID
            preparedStatement = connect.prepareStatement("SELECT UserID FROM SchoolStaff WHERE Position =?");
            preparedStatement.setString(1, "Teacher");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                id = resultSet.getInt("UserID");
                l.add(id);
            }
            
            
            return l;
        }
        catch (Exception e)
        {
            System.out.println("Error retrieving all Professor: " + e);
            return null;
        }
    }
    
    /**
     * getAllUserInfo()
     * -------------------------------
     * Returns an ArrayList<ArrayList<Object>> with the inner object array containing
     * information of either a student user or staff user from the Login entity.
     * @return 
     */
    public ArrayList<ArrayList<Object>> getAllUserInfo()
    {
        try
        {
            ArrayList<ArrayList<Object> >o = new ArrayList<ArrayList<Object>>();    //Holds all Teacher ID
            int id; //Holds current Teacher ID
            String username = "";    //Holds username of User
            String firstName = "";   //First Name of User
            String lastName = "";    //Last Name of User
            String position = "";    //Holds Staff Position, if applicable
            String department = "";  //Holds Department Staff works in, if applicable
            ArrayList<Object> temp;
            
            //Getting User ID
            preparedStatement = connect.prepareStatement("SELECT UserID, Username,Role FROM Login");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                temp = new ArrayList<Object>();
                id = resultSet.getInt("UserID");
                username = resultSet.getString("Username");
                String role = resultSet.getString("Role");
                
                temp.add(id);
                temp.add(username);
                
                //Check what role User has (Either Staff or Student)
                if (role.equals("Staff"))
                {
                    preparedStatement = 
                            connect.prepareStatement
                                ("SELECT FirstName, LastName, Position, Department FROM SchoolStaff WHERE UserID= ? AND Position =?");
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, "Teacher");
                    ResultSet r = preparedStatement.executeQuery();
                    
                    if (r.next())
                    {
                        firstName = r.getString("FirstName");
                        lastName = r.getString("LastName");
                        position = r.getString("Position");
                        department = r.getString("Department");
                        
                        temp.add(firstName);
                        temp.add(lastName);
                        temp.add(position);
                        temp.add(department);
                        
                        o.add(temp);
                    }
                }
                else 
                {
                    preparedStatement = 
                            connect.prepareStatement
                                ("SELECT FirstName, LastName FROM Student WHERE UserID= ?");
                    preparedStatement.setInt(1, id);
                    ResultSet r = preparedStatement.executeQuery();
                    
                    if (r.next())
                    {
                        firstName = r.getString("FirstName");
                        lastName = r.getString("LastName");
                        
                        temp.add(firstName);
                        temp.add(lastName);
                        
                        o.add(temp);
                    }
                }
            }
            return o;
        }
        catch (Exception e)
        {
            System.out.println("Error retrieving all User information(getAllUser() function): " + e);
            return null;
        }
    }
    
    /**
     * updateUserInfo(Object [] o)
     * ----------------------------------------------
     * 
     * @param o
     * @return 
     */
    public boolean updateUserInfo(Object [] o)
    {
        try
        {
            int id = (int)o[0];
            String username = (String)o[1];
            String firstName = (String)o[2];
            String lastName= (String)o[3];
            
            if (o.length == 4)  //Update to Student User
            {
                preparedStatement = connect
                        .prepareStatement("UPDATE Student student " 
                                + "SET student.FirstName=?, "
                                + "student.LastName=? "
                                + "WHERE student.UserID=?");
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setInt(3, id);
                
                preparedStatement.executeUpdate();
            }
            else    //Update Staff/Teacher User
            {
                String position = (String)o[4];
                String department = (String)o[5];
                
                preparedStatement = connect
                        .prepareStatement("UPDATE SchoolStaff staff " 
                                + "SET staff.FirstName=?, "
                                + "staff.LastName=?,"
                                + "staff.Position=?."
                                + "staff.Department=? "
                                + "WHERE staff.UserID=?");
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, position);
                preparedStatement.setString(4, department);
                preparedStatement.setInt(5, id);
                
                preparedStatement.executeUpdate();
            }
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error Updating User Information: " + e);
            return false;
        }
    }
    
    /**
     * updateCourse(Object [] o)
     * ----------------------------------------
     * 
     * @param o
     * @return 
     */
    public boolean updateCourseInfo(Object [] o)
    {
        try
        {
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            
            int SectionNo = (int)o[0];
            String CourseID = (String)o[1];
            String RoomNo = (String)o[2];
            Time classBeginTime = (Time)o[3];
            Time classEndTime = (Time)o[4];
            String classDays = (String)o[5];
            int StaffID = (int)o[6];
            
            preparedStatement = connect.
                    prepareStatement("UPDATE Class class "
                            + "SET class.CourseID=?, "
                            + "class.RoomNo=?, "
                            + "class.ClassBeginTime=?, "
                            + "class.ClassEndTime=?, "
                            + "class.ClassDays=?, "
                            + "class.UserID=? "
                            + "WHERE class.SectionNo=?");
            preparedStatement.setString(1, CourseID);
            preparedStatement.setString(2, RoomNo);
            preparedStatement.setTime(3, classBeginTime);
            preparedStatement.setTime(4, classEndTime);
            preparedStatement.setString(5, classDays);
            preparedStatement.setInt(6, StaffID);
            preparedStatement.setInt(7, SectionNo);
            
            preparedStatement.executeUpdate();
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error Updating Course Information: " + e);
            return false;
        }
    }
    
    /**
     * deleteUser(int id)
     * ---------------------------------------------
     * Delete currently selected users by the Administrator.
     * @param id
     * @return 
     */
    public boolean deleteUser(int id)
    {
        try
        {
            preparedStatement = connect
                        .prepareStatement("DELETE FROM Login WHERE UserID=? ");
                preparedStatement.setInt(1, id);
                
                preparedStatement.executeUpdate();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error Deleting User: " + e);
            return false;
        }
    }
    
    
    /**
     * deleteCourse(int id)
     * ---------------------------------------------
     * Delete currently selected course by the Administrator.
     * @param id
     * @return 
     */
    public boolean deleteCourse(int id)
    {
        try
        {
            preparedStatement = connect
                        .prepareStatement("DELETE FROM Class WHERE SectionNo=? ");
                preparedStatement.setInt(1, id);
                
                preparedStatement.executeUpdate();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error Deleting Class/Course: " + e);
            return false;
        }
    }
    
    /**
     * getAllCourses()
     * -------------------------------
     * Returns an ArrayList<ArrayList<Object>> of every course available in 
     * the entity Class from database.
     * @return 
     */
    public ArrayList<ArrayList<Object>> getAllCourses()
    {
        try
        {
            ArrayList<ArrayList<Object> >o = new ArrayList<ArrayList<Object>>();    //Holds all Courses
            
            int sectionNo; //Holds current course SectionNo
            String courseID = "";    //CourseID of Course
            String roomNo = "";   //RoomNo course is held
            Time classBeginTime;    //Time class starts
            Time classEndTime;    //Time class ends
            String classDays = "";  //Day(s) class is held on
            int staffID;    //Holds Staff teaching class
            ArrayList<Object> temp;
            
            //Getting User ID
            preparedStatement = connect.prepareStatement("SELECT * FROM Class");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                temp = new ArrayList<Object>();
                
                sectionNo = resultSet.getInt("SectionNo");
                courseID = resultSet.getString("CourseID");
                roomNo = resultSet.getString("RoomNo");
                classBeginTime = resultSet.getTime("ClassBeginTime");
                classEndTime = resultSet.getTime("ClassEndTime");
                classDays = resultSet.getString("ClassDays");
                staffID = resultSet.getInt("UserID");
                
                temp.add(sectionNo);
                temp.add(courseID);
                temp.add(roomNo);
                temp.add(classBeginTime);
                temp.add(classEndTime);
                temp.add(classDays);
                temp.add(staffID);
                
                o.add(temp); 
            }
            
            return o;
        }
        catch(Exception e)
        {
            System.out.println("Error retrieving all Courses: " + e);
            return null;
        }
        
    }
    
    /**
     * createUsername(String s)
     * -----------------------------------
     * Creates Username for Login information based on First Name, Last Name,
     * and the # of occurrences of LastName+FirstName of UserName.
     * @param s
     * @return 
     */
    private String createUsername (String s)
    {
        String username = s;
        
        try
        {
            preparedStatement = connect.
                    prepareStatement("SELECT COUNT(*) AS count FROM Login WHERE Username LIKE ?");
            preparedStatement.setString(1,"%"+username+"%");
            
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next())
            {
                int num = resultSet.getInt("count");
                username += (num+1);
                return username;
            }
            else
            {
                return null;
            }
            
        }
        catch (Exception e)
        {
            System.out.println("Error creating Username: " + e);
            return null;
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
                            + "(UserID, FirstName, LastName, Position, Department) "
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
