package de.esempe.gui;

import de.esempe.gui.MainView.PERSPECTIVE_TYPE;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;
import javafx.application.Platform;

/**
 *
 * @author @author Stefan Prechtl (www.esempe.de)
 */
@ApplicationScoped
public class MainPresenter extends BasePresenter<MainView>
{

	@Inject
	protected MainPresenter(final MainView view)
	{
		super(view);
	}

	@PostConstruct
	void created()
	{

	}

	void initView(@ObservesAsync final MessageEvent e)
	{
		// System.out.println("got event:" + e.getMsg());
	}

	void showAdminPerspective()
	{
		this.view.changePerspective(PERSPECTIVE_TYPE.ADMIN);
	}

	void showUserPerspective()
	{
		this.view.changePerspective(PERSPECTIVE_TYPE.USER);
	}

	void exit()
	{
		Platform.exit();
	}

}
