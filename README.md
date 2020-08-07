# StudentApplicationSoftware BRIEF
A University Department needs a set of interfaces and classes to manage student data.
The Department has different types of students. These are undergraduate (UG), postgraduate
taught (PGT) and postgraduate research (PGR) students. Students cannot be more than one
type. For this coursework, the significant difference between undergraduate and postgraduate
taught students is that undergraduate students take 120 credits worth of courses in a year
whereas postgraduate taught students take 180. Furthermore, the pass mark for undergraduate
modules is 40% but for postgraduate taught modules is 50%. Postgraduate research students
have a supervisor but do not register for modules.
The University needs to maintain a record of modules and supervisors for students in an
academic year. The system should allow module and supervisor information to be read from
appropriately defined data files. The files should contain one data entry per line with fields
separated by a comma e.g. CSC8002, Advanced Programming, 20. The system should allow
an appropriate number of modules to be added to a student record and to record whether or not
a student is correctly registered (are they taking the right number of credits or do they have a
supervisor allocated).
In addition, the University needs to be able to issue smart cards and login ID’s to all students
on their courses. The following provides more detail on the required functionality:
noOfStudents (typeOfStudent)
This method returns the number of students of the specified type that are currently
enrolled.
registerStudent (Student)
This method registers a new student onto the system and allocates a student ID (see
below).
amendStudentData(studentID, studentData)
This method changes a student record.
terminateStudent (studentID)
This method removes the student record associated with the given student number. In
effect, the student is leaving the University.
When issuing a smart card, the following rules must be observed.
• An undergraduate student must be at least 17 years old.
• A postgraduate student must be at least 20 years old.
• A student cannot be issued with more than one smartcard (i.e. do not try to deal with
lost cards!).
