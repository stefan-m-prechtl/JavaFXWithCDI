package de.esempe.gui;

public abstract class BasePresenter<V extends BaseView>
{
	protected V view;

	protected BasePresenter(final V view)
	{
		this.view = view;
	}
}
