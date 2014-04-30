package br.com.etyllica.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.context.Container;
import br.com.etyllica.core.context.load.DefaultLoadApplication;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.PointerEvent;

public class Core extends SurfaceView implements SurfaceHolder.Callback {

	private Container activeWindow;

	private Graphic graphic;

	private CoreThread thread;

	private long start;

	private float scaleX = 1;
	private float scaleY = 1;

	private int fps;

	public Core(Context context, int width, int height) {
		super(context);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);

		activeWindow = new Container(width, height);

		// create the game loop thread
		thread = new CoreThread(getHolder(), this);

		scaleX = Configuration.getInstance().getScaleX();
		scaleY = Configuration.getInstance().getScaleY();

		graphic = new Graphic(width, height, scaleX, scaleY);

		start = System.currentTimeMillis();

		// make the GamePanel focusable so it can handle events
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop

		thread.setRunning(true);
		thread.start();

	}

	//From http://www.javacodegeeks.com/2011/07/android-game-development-basic-game_05.html
	
	@Override 
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
	}

	public Graphic getGraphic() {
		return graphic;
	}

	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
	}

	public void setMainApplication(Application application) {

		reload(application);		

	}

	public void setLoadApplication(DefaultLoadApplication loadApplication) {

		activeWindow.setLoadApplication(loadApplication);

	}

	public void update() {

		if(!activeWindow.isLoaded()) {
			return;
		}

		long now = System.currentTimeMillis()-start;

		Application application = activeWindow.getApplication();

		updateApplication(application, now);

	}

	private void updateApplication(Application context, long now) {

		if(!context.isLocked()) {

			if(context.getUpdateInterval()==0) {

				context.update(now);

				context.setLastUpdate(now);

				context.getAnimation().animate(now);

			} else if(now-context.getLastUpdate()>=context.getUpdateInterval()) {

				context.timeUpdate(now);

				context.setLastUpdate(now);

				context.getAnimation().animate(now);

			}

			if(context.getReturnApplication()!=null) {

				this.changeApplication();

			}

		}

	}

	protected void changeApplication() {

		reload(activeWindow.getApplication().getReturnApplication());

	}

	private void reload(Application application) {

		if(application==null) {
			System.err.println("Application cannot be null.");
		}

		activeWindow.reload(application);

	}

	public long getTimeNow() {

		return System.currentTimeMillis();
	}

	public void draw(Canvas canvas) {

		canvas.drawColor(Color.WHITE);

		Application application = activeWindow.getApplication();

		application.draw(graphic);
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {

		PointerEvent event = new PointerEvent((int)(me.getX()/scaleX), (int)(me.getY()/scaleY), me.getAction());

		Application application = activeWindow.getApplication();

		application.updateMouse(event);

		// Schedules a repaint.
		//invalidate();
		return true;
	}

	public void setPause(boolean pause) {
		thread.setRunning(!pause);
	}

	public Application getApplication() {

		return activeWindow.getApplication();	
	}

	public boolean onBackPressed() {

		Application application = activeWindow.getApplication();

		return application.back();
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

}
