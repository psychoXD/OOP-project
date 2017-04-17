SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS Login;
DROP TABLE IF EXISTS Student;
DROP TABLE IF EXISTS SchoolStaff;
DROP TABLE IF EXISTS Class;
DROP TABLE IF EXISTS Student_Class;
DROP TABLE IF EXISTS Assignment;
DROP TABLE IF EXISTS ClassMaterial;
DROP TABLE IF EXISTS Student_Assignment;

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
    UserID INT NOT NULL AUTO_INCREMENT,
        FOREIGN KEY fk_UserID(UserID)
        REFERENCES Login(UserID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FirstName VARCHAR (20),
    LastName VARCHAR (20),
    PRIMARY KEY (UserID)
);

CREATE TABLE SchoolStaff
(
    UserID INT NOT NULL,
        FOREIGN KEY fk_UserID(UserID)
        REFERENCES Login(UserID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FirstName VARCHAR (50),
    LastName VARCHAR (50),
    Position VARCHAR (30),
    Department VARCHAR (30),
    PRIMARY KEY (UserID)
);

CREATE TABLE Class
(
    SectionNo INT NOT NULL AUTO_INCREMENT,
        PRIMARY KEY (SectionNo),
    CourseID VARCHAR(15),
    RoomNo VARCHAR(10),
    ClassBeginTime TIME,
    ClassEndTime TIME,
    ClassDays VARCHAR (5),
    UserID INT NOT NULL,
        FOREIGN KEY fk_UserID(UserID)
        REFERENCES  SchoolStaff(UserID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1000000;

CREATE TABLE Student_Class
(
    UserID INT NOT NULL,
        FOREIGN KEY fk_UserID(UserID)
        REFERENCES Student(UserID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    SectionNo INT NOT NULL,
        FOREIGN KEY fk_SectionNo(SectionNo)
        REFERENCES Class(SectionNo)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    PRIMARY KEY (UserID, SectionNo)
);

CREATE TABLE ClassMaterial
(
    DocumentNo INT NOT NULL AUTO_INCREMENT,
        PRIMARY KEY (DocumentNo),
    MaterialName VARCHAR (50),
    DocumentType VARCHAR (40),
    SectionNo INT NOT NULL,
        FOREIGN KEY fk_SectionNo(SectionNo)
        REFERENCES Class(SectionNo)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Assignment
(
    DocumentNo INT NOT NULL,
        FOREIGN KEY fk_DocumentNo(DocumentNo)
        REFERENCES ClassMaterial(DocumentNo)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    DueDate DATE,
    GradeWeight FLOAT,
    PRIMARY KEY (DocumentNo)
);

CREATE TABLE Student_Assignment
(
    UserID INT NOT NULL,
        FOREIGN KEY fk_UserID(UserID)
        REFERENCES Student(UserID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    DocumentNo INT NOT NULL,
        FOREIGN KEY fk_DocumentNo(DocumentNo)
        REFERENCES ClassMaterial(DocumentNo)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    Grade FLOAT,
    PRIMARY KEY (DocumentNo, UserID)
);
