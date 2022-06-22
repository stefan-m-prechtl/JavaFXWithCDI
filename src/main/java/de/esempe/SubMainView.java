package de.esempe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SubMainView implements Initializable
{
	
	
	@FXML
	Button btnSuchen;
	
	@FXML
	TextField txtVorname;
	
	public SubMainView()  
	{
				
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.btnSuchen.setOnAction(e-> {
			System.out.println("Klicked Suchen");
		});
		
	}
}
