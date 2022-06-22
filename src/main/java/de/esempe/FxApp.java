package de.esempe;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class FxApp extends Application
{
	

	public static void main(final String[] args)
	{
		launch();
	}

	

	@Override
	public void start(final Stage stage) throws Exception
	{
		// create and show main view
		var mainview = new MainView();
		final Parent parent = mainview.getRoot();
		final Scene scene = new Scene(parent, 800, 600);
		scene.getStylesheets().add("/styles/Styles.css");
		stage.setTitle("JavaFX with CDI");
		stage.setResizable(true);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}

	

}
