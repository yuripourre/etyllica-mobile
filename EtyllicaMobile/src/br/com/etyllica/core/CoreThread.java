package br.com.etyllica.core;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class CoreThread extends Thread {

	// Surface holder that can access the physical surface
	private SurfaceHolder surfaceHolder;

	// The actual view that handles inputs
	// and draws to the surface
	private Core core;

	// flag to hold game state
	private boolean running;

	private boolean paused = false;

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public CoreThread(SurfaceHolder surfaceHolder, Core gamePanel) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.core = gamePanel;
	}

	private final int MAX_FPS = 30;
	private final int MAX_FRAME_SKIPS = 16;
	private final int FRAME_PERIOD = 1000 / MAX_FPS;

	@Override
	public void run() {
		Canvas canvas;

		long beginTime;		// the time when the cycle begun
		long timeDiff;		// the time it took for the cycle to execute
		int sleepTime;		// ms to sleep (<0 if we're behind)
		int framesSkipped;	// number of frames being skipped 

		sleepTime = 0;

		while (running) {

			canvas = null;
			// try locking the canvas for exclusive pixel editing
			// in the surface
			try {

				canvas = this.surfaceHolder.lockCanvas();

				core.getGraphic().setCanvas(canvas);

				synchronized (surfaceHolder) {

					framesSkipped = 0;	// resetting the frames skipped

					beginTime = System.currentTimeMillis();

					// calculate how long did the cycle take
					timeDiff = System.currentTimeMillis() - beginTime;

					if(!paused){

						core.update();
						core.draw(canvas);

					}

					// calculate sleep time
					sleepTime = (int)(FRAME_PERIOD - timeDiff);

					if (sleepTime > 0) {
						// if sleepTime > 0 we're OK
						try {
							// send the thread to sleep for a short period
							// very useful for battery saving
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {}
					}

					while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
						
						// we need to catch up
						// update without rendering
						core.update(); 
						// add frame period to check if in next frame
						sleepTime += FRAME_PERIOD;	
						framesSkipped++;
					}

				}

			} finally {
				// in case of an exception the surface is not left in 
				// an inconsistent state
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}	// end finally
		}
	}

}
