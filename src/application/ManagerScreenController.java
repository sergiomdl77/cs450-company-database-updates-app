package application;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ManagerScreenController
{
	@FXML 
	private TextField t1;
	@FXML 
	private TextField t2;
	@FXML 
	private TextField t3;
	@FXML 
	private TextField t4;
	@FXML 
	private TextField t5;
	@FXML 
	private TextField t6;
	@FXML 
	private TextField t7;
	@FXML 
	private TextField t8;
	@FXML 
	private TextField t9;
	@FXML 
	private TextField t10;
	@FXML 
	private TextField t11;
	@FXML
	private Label insEmpStatus;
	@FXML
	private Label scanReportLbl;
	@FXML
	private Label remProjStaLbl;
	@FXML
	private TextField projNumTxtF;
	@FXML
	private TextField projNTxtF;
	@FXML
	private TextField empSsnTxtF;
	@FXML
	private Label remPFromEStatLbl;

	
	@FXML
	void insertEmp() throws SQLException, IOException
	{
		CompanyApp ca = new CompanyApp();
		
		String resultStr = ca.insertEmp(t1.getText(), t2.getText(), t3.getText(),
				  t4.getText(), t5.getText(), t6.getText(), t7.getText(),
				  t8.getText(), t9.getText(), t10.getText(), t11.getText());
		insEmpStatus.setText(resultStr );
		
		if (resultStr.compareTo("success") == 0)
		{
			ca.close();
 			try
			{
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				BorderPane root = loader.load(getClass().getResource("ProjectScreen.fxml").openStream());
				ProjectScreenController projectScreenController = (ProjectScreenController)loader.getController();
				projectScreenController.passInSsn(t4.getText());
				Scene scene = new Scene(root,400,550);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				clear();
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
			
		}		

	}
	
	@FXML
	void scanDbForProjConstraint() throws SQLException
	{
		CompanyApp ca = new CompanyApp();
		scanReportLbl.setText(ca.scanDbForProjConstraint());
		ca.close();
	}
	
	@FXML
	void removeProject() throws SQLException
	{
		CompanyApp ca = new CompanyApp();
		remProjStaLbl.setText(ca.removeProject(Integer.parseInt(projNumTxtF.getText())));
		ca.close();
		clear();

	}

	@FXML
	void removeEmpFromProj() throws SQLException
	{	
		CompanyApp ca = new CompanyApp();
		int projNum = Integer.parseInt( projNTxtF.getText() );
		String ssn = empSsnTxtF.getText();
		remPFromEStatLbl.setText(ca.removeEmpFromProj(ssn, projNum));	
		ca.close();
		clear();
	}
	
	private void clear()
	{
		t1.clear();
		t2.clear();
		t3.clear();
		t4.clear();
		t5.clear();
		t6.clear();
		t7.clear();
		t8.clear();
		t9.clear();
		t10.clear();
		t11.clear();
		projNumTxtF.clear();
		projNTxtF.clear();
		empSsnTxtF.clear();
		
	}
	
}
