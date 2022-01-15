package de.esempe.gui.help;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Default;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

//#############################################################
//Klasse Dialog ist wegen final-Attributen nicht ApplicationScoped
//#############################################################
//@ApplicationScoped

@Default
public class HelpDialog extends Dialog<String>
{
	public HelpDialog()
	{
		super();
	}

	@PostConstruct
	private void initDialog()
	{
		this.setTitle("Help for Demo");
		this.setResizable(false);
		this.setHeaderText("Header information...");

		final ButtonType closeButtonType = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().add(closeButtonType);
		this.getDialogPane().setContentText("The real help is still missing... Ask the developer...");
	}
}
