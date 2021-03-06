package br.com.etyllica.core.context.load;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.context.Container;
import br.com.etyllica.core.context.Context;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ApplicationLoader implements LoadListener {

	private Loader loader;
	
	private Updater updater;
	
	private Container window;

	private Application application;

	private LoadApplication loadApplication;

	private boolean called = false;
	
	private ScheduledExecutorService loadExecutor;

	private Future<?> future;
	
	private static final int UPDATE_INTERVAL = 200;
	
	public ApplicationLoader() {
		super();
	}
	
	public void loadApplication() {

		loadExecutor = Executors.newScheduledThreadPool(2);
		
		window.setLoaded(false);

		loader = new Loader();
		updater = new Updater();

		future = loadExecutor.submit(loader);
		
		loadExecutor.scheduleAtFixedRate(updater, 0, UPDATE_INTERVAL, TimeUnit.MILLISECONDS);
		
	}
	
	private class Loader implements Runnable {

		public void run() {
			called = false;
						
			application.setLoadListener(ApplicationLoader.this);
				
			application.startLoad();
			
		}

	}
	
	private class Updater implements Runnable {

		public void run() {

			if(!called) {

				if(!window.isLoaded()) {
					loadApplication.setText(application.getLoadingPhrase(), application.getLoading());
				}

			} else {
				
				try {
					future.get();
				} catch (ExecutionException e) {
					Throwable cause = e.getCause();
					cause.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				window.setApplication(application);

				window.setLoaded(true);
				
				loadExecutor.shutdownNow();
			}			

		}

	}

	public Context getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public LoadApplication getLoadApplication() {
		return loadApplication;
	}

	public void setLoadApplication(LoadApplication loadApplication) {
		this.loadApplication = loadApplication;
	}

	public Container getContainer() {
		return window;
	}

	public void setContainer(Container window) {
		this.window = window;
	}

	@Override
	public void loaded() {
		called = true;
	}

}