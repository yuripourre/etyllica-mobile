package br.com.etyllica.core;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class CoreThread extends Thread {

	private final int MAX_FPS = 25;

	private final int MAX_FRAME_SKIPS = 16;

	private final double FRAME_PERIOD = 1000000000D / MAX_FPS;

	// Surface holder that can access the physical surface
	private SurfaceHolder surfaceHolder;

	// The actual view that handles inputs
	// and draws to the surface
	private Core core;

	// flag to hold game state
	private boolean running;

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

	private void updateEngine(double delta) {
		core.update();
	}

	@Override

	public void run() {

		long ticksPS = 1000 / MAX_FPS;

		long startTime;

		long sleepTime;

		while (running) {

			Canvas canvas = null;

			startTime = System.currentTimeMillis();
			
			core.update();

			try {

				canvas = this.surfaceHolder.lockCanvas();
				core.getGraphic().setCanvas(canvas);
				
				//synchronized (surfaceHolder) {

					core.draw(canvas);

				//}

			} finally {

				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}

			}

			sleepTime = ticksPS-(System.currentTimeMillis() - startTime);

			try {

				if (sleepTime > 0)

					sleep(sleepTime);

				else

					sleep(10);

			} catch (Exception e) {}

		}

	}

	public void oldRun() {
		Canvas canvas;

		long lastTime = System.nanoTime();

		int ups = 0;
		int fps = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		while (running) {

			canvas = null;
			// try locking the canvas for exclusive pixel editing
			// in the surface
			try {

				canvas = this.surfaceHolder.lockCanvas();

				core.getGraphic().setCanvas(canvas);

				synchronized (surfaceHolder) {

					long now = System.nanoTime();
					delta += (now - lastTime) / FRAME_PERIOD;
					lastTime = now;

					boolean renderOK = false;

					while(delta >= 1) {
						ups++;
						updateEngine(delta);

						delta -= 1;
						renderOK = true;
					}

					if(renderOK) {
						fps++;
						core.draw(canvas);
					}

					if(System.currentTimeMillis() - lastTimer >= 1000) {
						lastTimer += 1000;

						System.out.println("frames: " + fps + " | updates: " + ups);
						core.setFps(fps);

						fps = 0;
						ups = 0;
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
