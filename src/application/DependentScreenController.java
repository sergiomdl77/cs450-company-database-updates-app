package application;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

import javafx.fxml.FXML;
/*
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
*/
import javafx.fxml.FXMLLoader;

public class DependentScreenController 
{
	String ssn;
	
	@FXML
	private TextField ssnInputTxtF;
	@FXML
	private TextField n1;
	@FXML
	private TextField s1;
	@FXML
	private TextField b1;
	@FXML
	private TextField r1;
	@FXML
	private TextField n2;
	@FXML
	private TextField s2;
	@FXML
	private TextField b2;
	@FXML
	private TextField r2;
	@FXML
	private TextField n3;
	@FXML
	private TextField s3;
	@FXML
	private TextField b3;
	@FXML
	private TextField r3;
	@FXML
	private TextField n4;
	@FXML
	private TextField s4;
	@FXML
	private TextField b4;
	@FXML
	private TextField r4;
	@FXML
	private TextField n5;
	@FXML
	private TextField s5;
	@FXML
	private TextField b5;
	@FXML
	private TextField r5;
	@FXML
	private TextField n6;
	@FXML
	private TextField s6;
	@FXML
	private TextField b6;
	@FXML
	private TextField r6;
	
	
	@FXML
	void insertDependents() throws SQLException
	{
		CompanyApp ca = new CompanyApp();
		
		if (!n1.getText().isEmpty() && !s1.getText().isEmpty() && !b1.getText().isEmpty() && !r1.getText().isEmpty() ) 
			ca.getDepenList().addDepen(n1.getText(), s1.getText(), b1.getText(), r1.getText(), 0);
		if (!n2.getText().isEmpty() && !s2.getText().isEmpty() && !b2.getText().isEmpty() && !r2.getText().isEmpty() ) 
			ca.getDepenList().addDepen(n2.getText(), s2.getText(), b2.getText(), r2.getText(), 1);
		if (!n3.getText().isEmpty() && !s3.getText().isEmpty() && !b3.getText().isEmpty() && !r3.getText().isEmpty() ) 
			ca.getDepenList().addDepen(n3.getText(), s3.getText(), b3.getText(), r3.getText(), 2);
		if (!n4.getText().isEmpty() && !s4.getText().isEmpty() && !b4.getText().isEmpty() && !r4.getText().isEmpty() ) 
			ca.getDepenList().addDepen(n4.getText(), s4.getText(), b4.getText(), r4.getText(), 3);
		if (!n5.getText().isEmpty() && !s5.getText().isEmpty() && !b5.getText().isEmpty() && !r5.getText().isEmpty() ) 
			ca.getDepenList().addDepen(n5.getText(), s5.getText(), b5.getText(), r5.getText(), 4);
		if (!n6.getText().isEmpty() && !s6.getText().isEmpty() && !b6.getText().isEmpty() && !r6.getText().isEmpty() ) 
			ca.getDepenList().addDepen(n6.getText(), s6.getText(), b6.getText(), r6.getText(), 5);
		
		String resultStr = ca.insertDependents(ssn);
		
		if (resultStr.compareTo("success") == 0)
		{
			ca.close();

 			try
			{
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				BorderPane root = loader.load(getClass().getResource("EmployeeInfoScreen.fxml").openStream());
				EmployeeInfoScreenController employeeInfoScreenController = (EmployeeInfoScreenController)loader.getController();
				employeeInfoScreenController.passInSsn(ssn);
				employeeInfoScreenController.printReport();
				Scene scene = new Scene(root,400,550);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}		
	}
	
	public void passInSsn(String essn)
	{
		ssn = essn; 
	}
	
	
	
}