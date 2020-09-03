package application;
import java.sql.*;

public class CompanyApp 
{
	private static Connection conn;
	private ProjList projList;
	private DepenList depenList;
	
	public CompanyApp() throws SQLException
	{
		projList = new ProjList();
		depenList = new DepenList();
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Driver could not be loaded"); 
		}
		
		conn = DriverManager.getConnection("jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu","sdelgado","ustegrib");
		
	}
		
		
	public ProjList getProjList()
	{
		return projList;
	}
	
	
	public DepenList getDepenList()
	{
		return depenList;
	}
	
	
	public Connection getConn()
	{
		return conn;
	}
	
	
	public void close() throws SQLException
	{
		conn.close();
	}
	
	private static String withFiller(String str, int l)
	{
		for (int i=str.length(); i <= l; i++)
			str += " ";
		
		return str;
	}
	
	
	public String getResearchDeptRoster() throws SQLException
	{
		String repString = "";
		
		// Preparing query
		String q = "SELECT lname, ssn "
				 + "FROM employee JOIN department ON dno=dnumber "
				 + "WHERE dname = 'Research'";
		
		// creating a statement from the query
		Statement s = conn.createStatement();
		// executing sql statement
		ResultSet r = s.executeQuery(q);
		
		repString += "EMPLOYEES IN RESEARCH DEPARTMENT\n\n";
		while (r.next())
		{
			repString += "Last Name: "  + withFiller(r.getString(1),15) + "   ";
			repString += "Social Sec. #: "  + r.getString(2) + "\n";	
		}		
		
		return repString;
	}
	
	
	public String getHoustonZproductReport() throws SQLException
	{
		String repString = "";
		
		// Preparing query
		String q = "SELECT lname, ssn, hours "
				 + "FROM ((employee JOIN dept_locations ON dno=dnumber)"
				 + "               JOIN works_on ON ssn=essn)"
				 + "               JOIN project ON pno=pnumber "
				 + "WHERE dlocation = 'Houston'"
				 + "  AND pname = 'ProductZ'";
		
		// creating a statement from the query
		Statement s = conn.createStatement();
		// executing sql statement
		ResultSet r = s.executeQuery(q);
		
		repString += "EMPLOYEES WORKKING ON PRODUCT Z IN HOUSTON\n\n";
		while (r.next())
		{
			repString += "Last Name: "  + withFiller(r.getString(1),15) + "   ";
			repString += "Social Sec. #: "  + r.getString(2) + "   ";	
			repString += "Hours a week: " + r.getString(3) + "\n";
		}		
		
		return repString;
		
	}
	
	
	public String getEmpReport(String empSsn) throws SQLException
	{
		String repString = "";
		
		/////////////////////////////////////////////////
		// OBTAINING THE EMPLOYEES PERSONAL INFORMATION
		/////////////////////////////////////////////////
		String q = "SELECT * "
				 + "FROM employee "
				 + "WHERE ssn = " + empSsn;
		
		// creating a statement from the query
		Statement s = conn.createStatement();
		// executing sql statement
		ResultSet r = s.executeQuery(q);
		
		r.next();
		repString += "EMPLOYEE'S PERSONAL INFORMATION\n\n";
		repString += "First Name:        "  + r.getString(1) + "\n";
		repString += "Middle Initial:    "  + r.getString(2) + "\n";
		repString += "Last Name:         "  + r.getString(3) + "\n";
		repString += "Social Sec. #:     "  + r.getString(4) + "\n";
		repString += "Birth Date:        "  + r.getString(5) + "\n";
		repString += "Address:           "  + r.getString(6) + "\n";
		repString += "Sex:               "  + r.getString(7) + "\n";
		repString += "Salary:            "  + r.getString(8) + "\n";
		repString += "Supervisor's SSN:  "  + r.getString(9) + "\n";
		repString += "Department Number: "  + r.getString(10) + "\n";
		repString += "Email:             "  + r.getString(11) + "\n\n\n";
		
		
		////////////////////////////////////////////////
		// OBTAINING THE EMPLOYEES PROJECTS INFORMATION
		////////////////////////////////////////////////
		q = "SELECT hours, pname "
	 	  + "FROM works_on JOIN project ON pno=pnumber "
		  + "WHERE essn = " + empSsn;
		
		// creating a statement from the query
		s = conn.createStatement();
		// executing sql statement
		r = s.executeQuery(q);

		repString += "PROJECTS\n\n";
		while (r.next())
		{
			repString +=  withFiller(r.getString(2)+":",16)  + " " + r.getString(1) + " hours a week.\n";
		}		
		repString += "\n\n";

		///////////////////////////////////////////////////
		// OBTAINING THE EMPLOYEES DEPENDENTS INFORMATION
		///////////////////////////////////////////////////
		q = "SELECT dependent_name, relationship, sex, bdate "
	 	  + "FROM dependent "
		  + "WHERE essn = " + empSsn;
		
		// creating a statement from the query
		s = conn.createStatement();
		// executing sql statement
		r = s.executeQuery(q);

		repString += "DEPENDENTS\n\n";
		while (r.next())
		{
			repString += "Name:  " + withFiller(r.getString(1),15)
					  + "  Relationship: " + withFiller(r.getString(2),8) 
					  + "  Sex: " + r.getString(3) 
					  + "    Birth Date: " + r.getString(4) + "\n";
		}		
		
		
		return repString;
	}
	
	
	public boolean isManager(String mgrSsn) throws SQLException
	{
		String q = "SELECT mgrssn "
				 + "FROM department ";
		
		// creating a statement from the query
		Statement s = conn.createStatement();
		// executing sql statement
		ResultSet r = s.executeQuery(q);
		
		// comparing mgrSsn against all department managers in database
		while (r.next())
		{
			if (mgrSsn.compareTo(r.getString(1)) == 0 )
				return true;
		}
		
		return false;
	}
		
	
	public String insertEmp(String fname, String minit,  String lname,
							 String ssn,   String bdate,  String address,
							 String sex,   String salary, String superssn,
							 String dno,   String email ) throws SQLException
	{
		String q = "INSERT INTO employee VALUES ('"
				 + fname  + "', '" + minit  + "', '" + lname + "', '"
				 + ssn +   "', '" + bdate  + "', '" + address + "', '"
				 + sex +   "', " + salary + ", '" + superssn + "', " 
				 + dno +   ", '" + email  + "')";
		
		// creating a statement from the query
		Statement s = conn.createStatement();
		// executing sql statement
		s.executeUpdate(q);		
		
		return "success";
	}
	
	
	public int countEmpDepProjsFromList(String eSsn) throws SQLException
	{
		int curProjNum;
		int total = 0;
		
		// traverses the whole list of inputs (pnArray[i] == -1 are empty entries, so they get ignored)
		for (int i=0; i<projList.MAX_PROJS; i++)   
		{
			curProjNum = projList.getPnArray()[i];
			if (curProjNum >= 0)	// if this element of array is an actual project number input
			{
				String q = "SELECT COUNT(*) "
						 + "FROM project " 
						 + "WHERE pnumber = " + curProjNum
						 + "  AND dnum  IN ( SELECT dno "
						 + "				 FROM employee "
						 + "				 WHERE ssn = '" + eSsn + "'"
						 + "			   )";
				
				// creating a statement from the query
				Statement s = conn.createStatement();
				// executing sql statement
				ResultSet r = s.executeQuery(q);
				
				r.next();
				if (r.getInt(1) == 1)
				{
					total++;
				}
			}
			
		}
		
		return total;
	}
	
	
	public int countEmpDepProjsFromDB(String eSsn) throws SQLException
	{
		// Preparing query
		String q = "SELECT COUNT(*) "
				 + "FROM works_on "
				 + "WHERE essn = '" + eSsn + "' "
				 + "  AND pno IN (SELECT pnumber "
				 + "				FROM project "
				 + "				WHERE dnum IN (SELECT dno "
				 + "								 FROM employee "
				 + "								 WHERE ssn = '" + eSsn + "' "
				 + "								)"
				 + "				)";
		
		// creating a statement from the query
		Statement s = conn.createStatement();
		// executing sql statement
		ResultSet r = s.executeQuery(q);
		
		r.next();
		return r.getInt(1);
	}
	
	
	public String insertProjects(String eSsn) throws SQLException
	{
		String q = "";
		Statement s;
		
		
		// if duplicate inputs for a project are found in list of projects
		if (projList.duplicateError() == true)
		{
			projList.reset();
			return "Error! There was at least one project duplicate input upon submission. \n"
					+ "Please, modify inputs and re-submit. ";
		}

		// if the total of hours for projects exceeds 40
		int totalHours = projList.getTotalHours();
		
		if (totalHours > 40)
		{
			projList.reset();
			return "Error! The total number of weekly hours for employee's projects exceeds 40. \n"
					+ "The total of hours submitted currently is:  " + totalHours + "\n"
					+ "Please, modify inputs and re-submit. ";
		}
		
		// check if employee is not assigned to any project in his/her department yet...
		int count = this.countEmpDepProjsFromList(eSsn);
		
		if (count < 1)
		{
			projList.reset();
			return "Error! No projects assigned from his/her own department.\n"
					+ "Please, modify inputs and re-submit. ";
		}

		// check if employee is not assigned to any project in his/her department yet...
		if (count > 2)
		{
			projList.reset();
			return "Error. Such input would cause employee to be assigned more than two projects\n"
					+ "that are controlled by his own department.\n"
					+ "Please, modify inputs and re-submit. ";
		}
		

		int curProjNum;
		for (int i=0; i<projList.MAX_PROJS; i++)
		{
			curProjNum = projList.getPnArray()[i];
			if (curProjNum >= 0)	// if this element of array is an actual project number input
			{	
				q = "INSERT INTO works_on VALUES ('" + eSsn + "', "
															+ projList.getPnArray()[i] + ", " 
															+ projList.getPhArray()[i] + ")";
				
				// creating a statement from the query
				s = conn.createStatement();
				// executing sql statement
				s.executeUpdate(q);
			}
		}
		
		return "success";
	

	}

	
	public String insertDependents(String eSsn) throws SQLException
	{
		String q = "";
		Statement s;
		
		// if duplicate inputs for a project are found in list of dependents
		if (depenList.duplicateError() == true)
		{	
			depenList.reset();
			return "Error! There was at least one dependent duplicate input upon submition. \n"
					+ "Please, modify inputs and re-submit. ";
		}

		String curDepenNum;
		for (int i=0; i<depenList.MAX_DEPEN; i++)
		{
			curDepenNum = depenList.getFnArray()[i];
			if (curDepenNum.compareTo("") != 0)	// if this element of array is an actual dependent input
			{	
				q = "INSERT INTO dependent VALUES ('" + eSsn + "', '"
												 	  + depenList.getFnArray()[i] + "', '" 
												 	  + depenList.getSexArray()[i] + "', '" 
												 	  + depenList.getBdArray()[i] + "', '" 
											 		  + depenList.getRelArray()[i] + "')";
				
				// creating a statement from the query
				s = conn.createStatement();
				// executing sql statement
				s.executeUpdate(q);
			}
		}	
		
		return "success";
	}
	
	
	public String removeEmpFromProj(String eSsn, int pno) throws SQLException
	{
		String q;
		Statement s;
		ResultSet r;

		//////////////////////////////////////////////////////////////
		//         Obtaining project's department number
		//////////////////////////////////////////////////////////////
		
		// Preparing query
		q = "SELECT dnum "
		  + "FROM project "
		  + "WHERE pnumber = " + pno ;
		
		// creating a statement from the query
		s = conn.createStatement();
		// executing sql statement
		r = s.executeQuery(q);
		
		r.next();
		int projDepNum = r.getInt(1);

		
		//////////////////////////////////////////////////////////////
		//         Obtaining employee's department number
		//////////////////////////////////////////////////////////////
		
		q = "SELECT dno "
		  + "FROM employee "
		  + "WHERE ssn = " + eSsn ;
		
		// creating a statement from the query
		s = conn.createStatement();
		// executing sql statement
		r = s.executeQuery(q);
		
		r.next();
		int empDepNum = r.getInt(1);
		
		int totalProjs;
		
		////////////////////////////////////////////////////////////////
		//    If the project to remove is in employee's department
		//  we check and see if the project removal violates constraint.
		////////////////////////////////////////////////////////////////
		
		if (empDepNum == projDepNum)
		{
			// if employee only has one project in his/her department before removal
			totalProjs = countEmpDepProjsFromDB(eSsn);
			if (totalProjs == 1)
				return "Error! The removal of employee from the project would leave\n"
					+ "the him/her with no project controlled by their own department\n"
				    + "\nSUGGESTION: Add one more project from his/her own department.\n" 
				    + "Only then you'll be able to safely remove this project from the employee.";
		}
		
		
		q = "DELETE FROM works_on "
		  + "WHERE essn = '" + eSsn + "' AND pno = " + pno; 

		// creating a statement from the query
		s = conn.createStatement();
		// executing sql statement
		s.executeUpdate(q);
		
		return "GREAT NEWS!!! PROJECT WAS SUCCESSFULLY REMOVED FROM EMPLOYEE.\n" 
		+ "No violation of the NUMBER-OF-PROJECTS CONSTRAINT occurred in the process.";
		
	}
	
	
	public String scanDbForProjConstraint() throws SQLException
	{
		String q;
		Statement s;
		ResultSet r;

		//////////////////////////////////////////////////////////////
		//    Obtaining every employee in the company to check on
		//        violation of Number-of-projects Constraint.
		//////////////////////////////////////////////////////////////
		
		// Preparing query
		q = "SELECT ssn, fname, lname "
		  + "FROM employee ";
		
		// creating a statement from the query
		s = conn.createStatement();
		// executing sql statement
		r = s.executeQuery(q);
		
		String nameStr = "";
		String eSsn;
		String resultStr = "EMPLOYEES VIOLATING THE NUMBER-OF-PROJECTS CONSTRAINT:\n\n" ;
		int projsInOwnDepNum;
		int counter = 0;
		
		
		////////////////////////////////////////////////////////////////
		//     Checking on every employee (one at a time) to see
		//  how many project in his/her department they currently have
		////////////////////////////////////////////////////////////////
		
		while (r.next())
		{
			nameStr = "";
			eSsn = r.getString(1);
			nameStr += r.getString(2) + " " + r.getString(3) + ",  ssn: " + eSsn + "\n";
			
			projsInOwnDepNum = countEmpDepProjsFromDB(eSsn);
			
			if (projsInOwnDepNum < 1 || projsInOwnDepNum > 2)
			{
				counter++;
				resultStr+= counter + ". " + nameStr 
						 + "------  Working on " + projsInOwnDepNum + " project(s) controlled by his/her department.\n";
			}
		}
		
		if (counter == 0)
			return "GREAT NEWS!!! SCANNING REPORTED NO EMPLOYEES VIOLATING THE NUMBER-OF-PROJECTS CONSTRAINT OF THE COMPANY.";
		
		return resultStr;
	}
	
	
	public String removeProject(int pno) throws SQLException
	{
		String q;
		Statement s;
		ResultSet r;

		//////////////////////////////////////////////////////////////
		//         Obtaining project's department number
		//////////////////////////////////////////////////////////////
		
		// Preparing query
		q = "SELECT dnum "
		  + "FROM project "
		  + "WHERE pnumber = " + pno ;
		
		// creating a statement from the query
		s = conn.createStatement();
		// executing sql statement
		r = s.executeQuery(q);
		
		r.next();
		int projDepNum = r.getInt(1);
		
		
		//////////////////////////////////////////////////////////////
		//         Obtaining employees who work on the project
		//////////////////////////////////////////////////////////////
		
		// Preparing query
		q = "SELECT e.ssn, e.fname, e.lname "
		  + "FROM works_on w JOIN employee e ON w.essn=e.ssn "
		  + "WHERE w.pno = " + pno 
		  + "  AND e.dno = " + projDepNum;
		
		// creating a statement from the query
		s = conn.createStatement();
		// executing sql statement
		r = s.executeQuery(q);
		
		String nameStr = "";
		String eSsn;
		String resultStr = "EMPLOYEES WHO WOULD VIOLATE NUMBER-OF-PROJECTS\n"
						 + " CONSTRAINT AFTER REMOVAL OF PROJECT " + pno + ":\n\n" ;
		int projsInOwnDepNum;
		int counter = 0;
		
		///////////////////////////////////////////////////////////////
		//        Visiting every employee who works on that project
		///////////////////////////////////////////////////////////////
		
		while (r.next())
		{
			// capture currently visited employee's name and ssn
			nameStr = "";
			eSsn = r.getString(1);
			nameStr += r.getString(2) + " " + r.getString(3) + ",  ssn: " + eSsn + "\n";
			
			// getting current employee's number of projects in his/her own dept. 
			projsInOwnDepNum = countEmpDepProjsFromDB(eSsn);
			
			// if this was the only project that current employee had with his/her own department
			if (projsInOwnDepNum == 1)
			{
				counter++;
				resultStr+= counter + ". " + nameStr 
						 + "------  Working on " + projsInOwnDepNum + " project(s) controlled by his/her department.\n";
			}
		}
		
		if (counter > 0)
		{
			resultStr += "\nSUGGESTION: Add one more project from his/her own department to \n"
					+ "each employee who has only one project in their own department."
					+ "Only then you'll be able to safely remove this project";
			return resultStr;
		}
			
		q = "DELETE FROM works_on "
		  + "WHERE pno = " + pno; 

		// creating a statement from the query
		s = conn.createStatement();
		// executing sql statement
		s.executeUpdate(q);
		
		q = "DELETE FROM project "
		  + "WHERE pnumber = " + pno; 

		// creating a statement from the query
		s = conn.createStatement();
		// executing sql statement
		s.executeUpdate(q);
		
		return "GREAT NEWS!!! PROJECT WAS SUCCESSFULLY REMOVED.\n" 
				+ "No violation of the NUMBER-OF-PROJECTS CONSTRAINT occurred in the process.";
		
	}
	
	
	public void printAllEmps() throws SQLException
	{
		String q = "SELECT ssn, fname, lname, dno "
				 + "FROM employee "; 
		
		// creating a statement from the query
		Statement s = conn.createStatement();
		// executing sql statement
		ResultSet r1 = s.executeQuery(q);
		
		while(r1.next())
		{
			String ssn = r1.getString(1);
			String fname = r1.getString(2);
			String lname = r1.getString(3);
			String dno = r1.getString(4);
			
			q = "SELECT pno, hours "
			  + "FROM works_on "
			  + "WHERE essn = " + ssn;
			
			s = conn.createStatement();
			// executing sql statement
			ResultSet r2 = s.executeQuery(q);
			
			String result = "Emp: " + ssn + " " + fname + " " + lname + " dept=" + dno + "\n";
			while (r2.next())
			{
				result+= "proj " + r2.getInt(1) + " " + "hours " + r2.getInt(2) + "\n";
			}
			
			System.out.println(result);
		}
		
		
	}
	
	
	public static void main(String[] args) throws SQLException
	{
		
//		CompanyApp ca = new CompanyApp();
		
//		ca.insertEmp("Sergio", "M", "Delgado", "224639999", "30-DEC-77", "6805 Radcliffe", "M", "25000", "333445555", "5", "sdelgado@gmu.edu");

//		System.out.println(ca.countEmpDepProjsFromDB("666884444"));
		
		
//		ca.getProjList().addProj(1, 10, 1);
//		ca.getProjList().addProj(10, 2, 2);
//		ca.getProjList().addProj(2, 10, 3);
//		ca.getProjList().addProj(3, 5, 4);
//		System.out.println(ca.insertProjects("224639999"));
		
		
//		ca.getDepenList().addDepen("Evelyn","causin","F","01-OCT-81",1);
//		ca.getDepenList().addDepen("Shirley","causin","F","17-JUN-85",2);
//		ca.getDepenList().addDepen("Evelyn","causin","F","01-OCT-81",3);
//		ca.getDepenList().addDepen("Marvin","primo","M","24-JAN-78",4);
//		System.out.println(ca.insertDependents("224639999"));
		
//		System.out.println(ca.removeEmpFromProj("224639999", 2));

//		System.out.println(ca.getEmpReport("224639999"));

//		System.out.println(ca.scanDbForProjConstraint());
		
//		System.out.println(ca.countEmpDepProjsFromList("123456789"));
		
//		System.out.println(ca.removeProject(2));
		
//		String mgr = "987654321";
//		if (ca.isManager(mgr))
//			System.out.println("yes");


		
//		ca.getResearchDeptRoster();
//		System.out.println(ca.lnameList.toString() );
//		System.out.println(ca.ssnList.toString() );

		
		
//		System.out.println(ca.getEmpReport("333445555"));
//		System.out.println(ca.getResearchDeptRoster());
//		System.out.println(ca.getHoustonZproductReport());

		
	}

}
