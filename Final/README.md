##Introdcution to Software Engineering CS 4318 Project 

##Contents of the Folder
<br />
Data.sql (MySQLdump of the Database)
<br />sims_dbm.sql (Schema of the database)
<br />SEProject folder (Content of the Software Engineering SIMS Project)
<br />README.md (this file)

##Setup
<br />Prior to this, ensure that there is a database in MySQL named "sims_dbm". If not, then change the Database.java Default Constructor url assignment to "jdbc:mysql://localhost/xxxx"
where "xxxx" is the name of the database.
	

##Notes on Program
<br />If the database information would or is empty, then the first attempt to login (which will fail) will result in the creation of an admin account.<br /><br />
That account's login information is the following:
	<br />Username: "Admin
	<br />Password (case-sensitive): "Admin"

<br />When Admin creates account, the default password of every account is the following format:
	<br />Username: LastName + FirstLetterInitial_Of_FirstName + number
	<br />Password (case-sensitive): FirstName + LastName

<br />An example being the name "John Smith" with their only being one instance of "SmithJ" in the database:
	<br />Username: SmithJ1
	<br />Password (case-sensitive): JohnSmith


##Running the program (Netbeans)
1. Import data.sql to the databse "sims_dbm" in MySQL.
2. Load the project into NetBeans and then run the program.
3. LoginGUI should appear and one can proceed to login.


##Running the program (Jar File)
1. Import data.sql to the databse "sims_dbm" in MySQL.
2. Go to the SEProject/dist folder and run SEProject.jar file
3. LoginGUI should appear and one can proceed to login.


