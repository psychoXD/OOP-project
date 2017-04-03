SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS Login;
DROP TABLE IF EXISTS Student;
DROP TABLE IF EXISTS SchoolStaff;
DROP TABLE IF EXISTS Class;
DROP TABLE IF EXISTS Student_Class;
DROP TABLE IF EXISTS Assignment;
DROP TABLE IF EXISTS ClassMaterial;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE Login
(
    UserID INT NOT NULL AUTO_INCREMENT,
        PRIMARY KEY (UserID),
    Username VARCHAR (40),
        UNIQUE (Username),
    Password CHAR (40),
    pwdSalt BINARY (16),
    Role VARCHAR (25)
) ENGINE=InnoDB AUTO_INCREMENT=1000000;

CREATE TABLE Student
(
    StudentID INT NOT NULL AUTO_INCREMENT,
        FOREIGN KEY fk_StudentID(StudentID)
        REFERENCES Login(UserID),
    FirstName VARCHAR (20),
    LastName VARCHAR (20),
    PRIMARY KEY (StudentID)
);

CREATE TABLE SchoolStaff
(
    StaffID INT NOT NULL,
        FOREIGN KEY fk_StaffID(StaffID)
        REFERENCES Login(UserID),
    FirstName VARCHAR (50),
    LastName VARCHAR (50),
    Position VARCHAR (30),
    Department VARCHAR (30),
    PRIMARY KEY (StaffID)
);

CREATE TABLE Class
(
    SectionNo INT NOT NULL AUTO_INCREMENT,
        PRIMARY KEY (SectionNo),
    CourseID INT,
    RoomNo VARCHAR(10),
    ClassTime DATE,
    ClassDays VARCHAR (2),
    StaffID INT NOT NULL,
        FOREIGN KEY fk_StaffID(StaffID)
        REFERENCES  SchoolStaff(StaffID)
);

CREATE TABLE Student_Class
(
    StudentID INT NOT NULL,
        FOREIGN KEY fk_StudentID(StudentID)
        REFERENCES Student(StudentID),
    SectionNo INT NOT NULL,
        FOREIGN KEY fk_SectionNo(SectionNo)
        REFERENCES Class(SectionNo),
    PRIMARY KEY (StudentID, SectionNo)
);

CREATE TABLE ClassMaterial
(
    DocumentNo INT NOT NULL,
        PRIMARY KEY (DocumentNo),
    MaterialName VARCHAR (50),
    DocumentType VARCHAR (40),
    SectionNo INT NOT NULL,
        FOREIGN KEY fk_SectionNo(SectionNo)
        REFERENCES Class(SectionNo)
);

CREATE TABLE Assignment
(
    DocumentNo INT NOT NULL,
        FOREIGN KEY fk_DocumentNo(DocumentNo)
        REFERENCES ClassMaterial(DocumentNo),
    StudentID INT NOT NULL,
        FOREIGN KEY fk_StudentID(StudentID)
        REFERENCES Student(StudentID),
    DueDate DATE,
    Grade INT,
    PRIMARY KEY (DocumentNo, StudentID)
);
