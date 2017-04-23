INSERT INTO Student (UserID, FirstName, LastName)
  VALUES (1, "Austin", "Fundie"),
         (2, "James", "Grace"),
		 (3, "Tyler", "Smith"),
         (4, "William", "Rainville"),
		 (5, "John", "Caulfield"),
         (6, "Olaf", "Ronayne"),
         (7, "Mark", "Palumbo"),
         (8, "Pei", "Wang"),
		 (9, "Oliver", "West"),
         (10, "William", "Kendall"),
		 (11, "John", "Bodin"),
         (12, "Allen", "Sedaris"),
         (13, "Paul", "Jacobson"),
         (14, "Aaron", "Staples"),
		 (15, "Tom", "Zapata"),
         (16, "Nicole", "Paquette"),
		 (17, "Jen", "Winters"),
         (18, "Viella", "Prescott"),
         (19, "Jessica", "Argyle"),
         (20, "Emily", "Wood"),
         (21, "Seb", "Gesincourt"),
		 (22, "Steve", "Bodin"),
         (23, "Steve", "Lunngren"),
         (24, "Mark", "Whelan"),
         (25, "Adam", "Li"),
		 (26, "Wesley", "Paquette"),
         (27, "Florian", "Paik"),
		 (28, "Jen", "Brill"),
         (29, "Chase", "Venti"),
         (30, "Price", "Miller");

INSERT INTO SchoolStaff (UserID, FirstName, LastName, Position, Department)
  VALUES (1, "Martin", "Chan", "Teacher", "Mathematics"),
         (2, "Aaron", "White", "Staff", "???"),
		 (3, "Joseph", "Andrada", "Staff", "???"),
         (4, "William", "Hope", "Staff", "???"),
		 (5, "John", "Stock", "Staff", "???"),
         (6, "Sheila", "Liu", "Staff", "???"),
         (7, "Hugo", "Martinez", "Staff", "???"),
         (8, "Julie", "Sims", "Staff", "???");
         
INSERT INTO Class (SectionNo, CourseID, RoomNo, ClassBeginTime, ClassEndTime, ClassDays, UserID)
  VALUES (1, "642766", "N2345", '08:00:00','09:15:00', 8),
         (2, "823553", "S8767", '09:30:00','11:00:00', 7),
		 (3, "582587", "N5466", '11:15:00','12:30:00', 6),
         (4, "857643", "A1232", '12:45:00','14:00:00', 5),
		 (5, "466388", "A9876", '14:15:00','15:30:00', 4),
         (6, "858710", "C3456", '15:45:00','17:00:00', 3),
         (7, "898662", "C6565", '17:15:00','18:30:00', 2),
         (8, "810851", "A2223", '18:45:00','20:00:00', 1);
         
INSERT INTO Student_Class (UserID, SectionNo, GradeAvg)
  VALUES (1, 1, NULL),
         (7, 1, NULL),
		 (8, 1, NULL),
         (12, 1, NULL),
		 (15, 1, NULL),
         (17, 1, NULL),
         (20, 1, NULL),
         (2, 2, NULL),
		 (3, 2, NULL),
         (4, 2, NULL),
		 (6, 2, NULL),
         (8, 2, NULL),
         (13, 2, NULL),
         (14, 2, NULL),
		 (15, 2, NULL),
         (19, 2, NULL),
		 (3, 3, NULL),
         (4, 3, NULL),
         (6, 3, NULL),
         (9, 3, NULL),
         (11, 3, NULL),
		 (1, 4, NULL),
         (5, 4, NULL),
         (6, 4, NULL),
         (9, 4, NULL),
		 (18, 4, NULL),
         (1, 5, NULL),
		 (2, 5, NULL),
         (3, 5, NULL),
         (4, 5, NULL),
         (5, 5, NULL),
         (6, 5, NULL),
		 (7, 6, NULL),
         (8, 6, NULL),
		 (9, 6, NULL),
         (10, 6, NULL),
         (11, 6, NULL),
         (12, 6, NULL),
		 (13, 6, NULL),
         (14, 6, NULL),
		 (15, 6, NULL),
         (11, 7, NULL),
         (12, 7, NULL),
         (13, 7, NULL),
		 (14, 7, NULL),
         (15, 7, NULL),
		 (16, 7, NULL),
         (17, 7, NULL),
         (18, 7, NULL),
         (19, 7, NULL),
         (20, 7, NULL),
		 (1, 8, NULL),
         (4, 8, NULL),
         (5, 8, NULL),
         (8, 8, NULL),
		 (10, 8, NULL),
         (11, 8, NULL),
		 (17, 8, NULL),
         (18, 8, NULL),
         (20, 8, NULL);