package br.com.etyllica.core.context;

import br.com.etyllica.core.context.load.LoadListener;

public abstract class Context {

	/**
	 * The updateInterval between executions
	 */
	private int updateInterval = 0;

	/**
	 * Load percentage unlock Application when reaches 100 
	 */
	protected float loading = 0;

	/**
	 * Loading phrase while loading Application 
	 */
	protected String loadingPhrase = "Loading...";

	/**
	 * Application title (useful with windows) 
	 */
	protected String title = "Application";

	/**
	 * Clear application before every draw call  
	 */
	protected boolean clearBeforeDraw = true;

	/**
	 * Last time updated
	 */

	protected long lastUpdate = 0;

	/**
	 * Lock
	 */
	private boolean locked = false;
	
	/**
	 * Pause
	 */
	protected boolean paused = false;
	
	private LoadListener loadListener;
	
	public Context() {
		super();
	}
	
	public void startLoad() {

		locked = true;
		this.loading = 0;
		load();
		locked = false;
		notifyListeners();

	}
	
	public abstract void load();
	
	private void notifyListeners(){

		loadListener.loaded();

	}
	
	public float getLoading() {
		return loading;
	}

	public String getLoadingPhrase() {
		return loadingPhrase;
	}

	public int getUpdateInterval() {
		return updateInterval;
	}

	public void setUpdateInterval(int updateInterval) {
		this.updateInterval = updateInterval;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public long getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setLoadListener(LoadListener loadListener) {
		this.loadListener = loadListener;
	}

	public boolean isClearBeforeDraw() {
		return clearBeforeDraw;
	}

	public void setClearBeforeDraw(boolean clearBeforeDraw) {
		this.clearBeforeDraw = clearBeforeDraw;
	}
		
}
