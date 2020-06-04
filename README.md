# cs450-company-database-updates-app

Project Summary:

This project is designed to explore the SQL Programming Language and the use of Java and the
JDBC interface and function libraries to access relational databases. In this case you will use the
Oracle DBMS.
We will use the COMPANY database that was provided in HW 2. Please use this database
schema and database instances to run your client/server queries.
Part 1
For problems 1) and 2) use JDBC with Java as the host language:
1) Write a program segment that retrieves the employees who work in the Research department
and print the employee’s last name and their SSN.
2) Write a program segment that retrieves the employees who work in departments located in
Houston and work on the project ‘ProductZ’. List their last name, SSN, and the number of hours
that the employee works on that project.

Part 2
Write a JDBC program that will allow a department manager to add a new employee to the
COMPANY database.
The program should first prompt the manager for their SSN, and then check if the employee is
really a manager. If not, prompt the user that they are not a manager, and stop.
If the user is indeed a manager, then present a screen that will allow the manager to provide the
employee SSN, last name, first name, and the other attributes associated with the employee.
Next provide a screen that allows the manager to assign the new employee to one or more
projects. The total time allocated to all projects should not exceed 40 hours per week. You may
check this at runtime and provide feedback to the manager so as to allocate the hours to projects
while satisfying the 40 hours total.
If the new employee has dependents, (yes or no checkbox) then enter the new dependents
according to the DEPENDENT table attributes. Please write code to handle both cases.
When the data entry is completed, print a report that can be presented to the new employee.

