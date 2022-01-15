package de.esempe.gui;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import de.esempe.gui.i18n.BundleUtil;
import javafx.scene.Parent;
import javafx.stage.Window;

/**
 * Basisklasse für alle konkreten Views.
 *
 * @author Stefan Prechtl (www.esempe.de)
 *
 * @param <R> "Root"-GUI Element (in der Regel ein Pane-Objekt)
 * @param <P> zugehöriger Presenter (gemaess MVP-Pattern)
 */
public abstract class BaseView<R extends Parent, P extends BasePresenter>
{
	protected R root;
	protected P presenter;
	protected ApplicationRegistry registry;
	protected ResourceBundle bundle;

	protected BaseView(final P presenter, final ApplicationRegistry registry)
	{
		this.presenter = presenter;
		this.registry = registry;
	}

	protected void initBundle()
	{
		this.bundle = PropertyResourceBundle.getBundle(BundleUtil.BUNDLE_BASENAME, this.registry.getLocale());
	}

	/**
	 * Jeder konkrete View muss diese Methode als @PostConstruct implementieren
	 */
	abstract protected void initView();

	public Parent getRoot()
	{
		return this.root;
	}

	public Window getRootWindow()
	{
		return this.getRoot().getScene().getWindow();
	}

}
