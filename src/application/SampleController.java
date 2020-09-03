package application;

import javafx.scene.control.TextField;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SampleController 
{
	@FXML
	private Label reseaRepLbl;
	@FXML
	private Label houstRepLbl;
	@FXML
	private TextField inputSsnTxtF;
	@FXML
	private Label wrongSsnLbl;
	
	
	@FXML
	void getResearchDeptRoster() throws SQLException
	{
		CompanyApp ca = new CompanyApp();
		reseaRepLbl.setText(ca.getResearchDeptRoster());
	}
	
	@FXML
	void getHoustonZproductReport() throws SQLException
	{
		CompanyApp ca = new CompanyApp();
		houstRepLbl.setText(ca.getHoustonZproductReport());
	}
	
	@FXML
	void isManager() throws SQLException
	{
		CompanyApp ca = new CompanyApp();
		ca.printAllEmps();
		
		if (ca.isManager(inputSsnTxtF.getText()))
		{
			try
			{
				inputSsnTxtF.clear();
				Stage primaryStage = new Stage();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("ManagerScreen.fxml"));
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
		else
		{
			inputSsnTxtF.clear();
			wrongSsnLbl.setText("Invalid Manager SSN#. Try again!");
		}

	}
	
}
