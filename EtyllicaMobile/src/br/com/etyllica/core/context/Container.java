package br.com.etyllica.core.context;

import br.com.etyllica.core.context.load.ApplicationLoader;
import br.com.etyllica.core.context.load.DefaultLoadApplication;
import br.com.etyllica.core.context.load.GenericLoadApplication;

/**
 *
 * Based on Window from Etyllica
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Container {
	
	protected Application application;

	protected ApplicationLoader applicationLoader;

	protected boolean close = false;

	private boolean loaded = true;
		
	/**
	 * Load Application
	 */
	protected DefaultLoadApplication loadApplication = null;

	public Container(int w, int h) {
		super();
		
		applicationLoader = new ApplicationLoader();
		
		loadApplication = new GenericLoadApplication(w,h);
		loadApplication.load();
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public void setLoadApplication(DefaultLoadApplication loadApplication) {
		this.application = loadApplication;
		this.loadApplication = loadApplication;
	}

	public void reload(Application application) {
		
		if(loaded) {
			
			loaded = false;
			
			loadApplication.load();
			setLoadApplication(loadApplication);

			applicationLoader.setContainer(this);
			applicationLoader.setApplication(application);
			applicationLoader.setLoadApplication(loadApplication);

			applicationLoader.loadApplication();
						
		}

	}

	public void closeWindow() {
		setClose(true);
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
		
}
