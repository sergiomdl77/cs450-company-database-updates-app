package application;

import javafx.scene.control.TextField;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProjectScreenController 
{
	String ssn;
	
	@FXML
	private Label projInsertStatLbl ;
	@FXML
	private TextField p1;
	@FXML 
	private TextField h1;
	@FXML
	private TextField p2;
	@FXML 
	private TextField h2;
	@FXML
	private TextField p3;
	@FXML 
	private TextField h3;
	@FXML
	private TextField p4;
	@FXML 
	private TextField h4;
	@FXML
	private TextField p5;
	@FXML 
	private TextField h5;
	@FXML
	private TextField p6;
	@FXML 
	private TextField h6;
	@FXML
	private TextField p7;
	@FXML 
	private TextField h7;
	@FXML
	private TextField p8;
	@FXML 
	private TextField h8;
	@FXML
	private TextField p9;
	@FXML 
	private TextField h9;

	
	@FXML
	void insertProjects() throws SQLException
	{
		CompanyApp ca = new CompanyApp();
		
		if (!p1.getText().isEmpty()  && !h1.getText().isEmpty() )
			ca.getProjList().addProj(Integer.parseInt(p1.getText()),
									 Integer.parseInt(h1.getText()), 0);
		if (p2.getText().compareTo("") != 0  && h2.getText().compareTo("") != 0 )
			ca.getProjList().addProj(Integer.parseInt(p2.getText()),
									 Integer.parseInt(h2.getText()), 1);
		if (p3.getText().compareTo("") != 0  && h3.getText().compareTo("") != 0 )
			ca.getProjList().addProj(Integer.parseInt(p3.getText()),
									 Integer.parseInt(h3.getText()), 2);
		if (p4.getText().compareTo("") != 0  && h4.getText().compareTo("") != 0 )
			ca.getProjList().addProj(Integer.parseInt(p4.getText()),
									 Integer.parseInt(h4.getText()), 3);
		if (p5.getText().compareTo("") != 0  && h5.getText().compareTo("") != 0 )
			ca.getProjList().addProj(Integer.parseInt(p5.getText()),
									 Integer.parseInt(h5.getText()), 4);
		if (p6.getText().compareTo("") != 0  && h6.getText().compareTo("") != 0 )
			ca.getProjList().addProj(Integer.parseInt(p6.getText()),
									 Integer.parseInt(h6.getText()), 5);
		if (p7.getText().compareTo("") != 0  && h7.getText().compareTo("") != 0 )
			ca.getProjList().addProj(Integer.parseInt(p7.getText()),
									 Integer.parseInt(h7.getText()), 6);
		if (p8.getText().compareTo("") != 0  && h8.getText().compareTo("") != 0 )
			ca.getProjList().addProj(Integer.parseInt(p8.getText()),
									 Integer.parseInt(h8.getText()), 7);
		if (p9.getText().compareTo("") != 0  && h9.getText().compareTo("") != 0 )
			ca.getProjList().addProj(Integer.parseInt(p9.getText()),
									 Integer.parseInt(h9.getText()), 8);

		String projInsertStat = ca.insertProjects(ssn);
		
		projInsertStatLbl.setText(projInsertStat);
		
		if (projInsertStat.compareTo("success") == 0)
		{
			ca.close();

 			try
			{
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				BorderPane root = loader.load(getClass().getResource("DependentScreen.fxml").openStream());
				DependentScreenController dependentScreenController = (DependentScreenController)loader.getController();
				dependentScreenController.passInSsn(ssn);
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
