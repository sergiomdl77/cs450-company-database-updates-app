package application;

import javafx.scene.control.Label;

import java.sql.SQLException;

import javafx.fxml.FXML;

public class EmployeeInfoScreenController
{
	String ssn;

	@FXML
	public Label empReportLbl;
	
	public void printReport() throws SQLException
	{
		CompanyApp ca = new CompanyApp();
		empReportLbl.setText(ca.getEmpReport(ssn));
		ca.close();
	}
	
	public void passInSsn(String essn)
	{
		ssn = essn; 
	}
	
}
