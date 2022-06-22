package de.esempe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class MainView
{
	private BorderPane root;
	
	@FXML
	ListView<String> lvwSearch;
	
	@FXML
	SubMainView subMainViewController;
	
	
	public MainView()
	{
		URL fxmlURL = getClass().getResource("/fxml/main.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
		fxmlLoader.setController(this);
		
		try
		{
			this.root = fxmlLoader.load();
			
			subMainViewController.txtVorname.setText("Hans");
			
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public BorderPane getRoot()
	{
		return root;
	}

}
