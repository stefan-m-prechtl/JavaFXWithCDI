package de.esempe.gui;

import java.util.Locale;

import jakarta.inject.Inject;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxApp extends Application
{
	@Inject
	private ApplicationRegistry registry;

	public static void main(String[] args)
	{
		launch();
	}

	@Override
	public void init() throws Exception
	{
		CDI.COMTAINTER.isRunning();

		// Init client's registry
		this.registry = CDI.COMTAINTER.getType(ApplicationRegistry.class);

		this.registry.putLocale(Locale.GERMAN);
		// this.registry.putLocale(Locale.ENGLISH);
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		// create and show main view
		final MainView view = CDI.COMTAINTER.getType(MainView.class);
		final Parent parent = view.getRoot();

		final Scene scene = new Scene(parent, 800, 600);
		scene.getStylesheets().add("/styles/Styles.css");

		stage.setTitle("JavaFX with CDI");
		stage.setResizable(true);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();

	}

	@Override
	public void stop() throws Exception
	{
		CDI.COMTAINTER.shutdown();
	}

}
