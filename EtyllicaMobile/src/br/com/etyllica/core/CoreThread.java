package br.com.etyllica.core;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class CoreThread extends Thread {

	private final int MAX_FPS = 25;

	private final int MAX_FRAME_SKIPS = 16;

	private final double FRAME_PERIOD = 1000D / MAX_FPS; //Frame period in Milliseconds

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
		core.update(delta);
	}

	@Override
	public void run() {
		Canvas canvas;

		long lastTime = System.currentTimeMillis();

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

					long now = System.currentTimeMillis();
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

						//System.out.println("frames: " + fps + " | updates: " + ups);
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
