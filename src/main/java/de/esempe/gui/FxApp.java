package de.esempe.gui;

import java.util.Locale;
import java.util.Optional;

import de.esempe.gui.login.LoginDialog;
import jakarta.inject.Inject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

public class FxApp extends Application
{
	@Inject
	private ApplicationRegistry registry;

	public static void main(final String[] args)
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
	public void start(final Stage stage) throws Exception
	{

		// show login dialog
		final LoginDialog dlg = CDI.COMTAINTER.getType(LoginDialog.class);
		final Optional<Pair<Boolean, String>> result = dlg.showAndWait();

		// if login failed --> terminate application
		if (Boolean.FALSE.equals(result.get().getKey()))
		{
			Platform.exit();
			return;
		}

		// store jwt in client registry (will be used to access user's role)
		final var token = result.get().getValue();
		this.registry.putJsonWebToken(token);

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
