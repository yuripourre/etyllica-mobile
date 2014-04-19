package br.com.etyllica.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.input.mouse.PointerEvent;
import br.com.etyllica.core.video.Graphic;

public class Core extends SurfaceView implements SurfaceHolder.Callback {
	
	private Application application;
	private Graphic graphic;
	
	private CoreThread thread;
	
    public Core(Context context) {
		super(context);
        // adding the callback (this) to the surface holder to intercept events
        getHolder().addCallback(this);
        // create the game loop thread
        thread = new CoreThread(getHolder(), this);

        float scaleX = Configuration.getInstance().getScaleX();
        float scaleY = Configuration.getInstance().getScaleY();
        
        graphic = new Graphic(scaleX, scaleY);

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

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    public Graphic getGraphic() {
		return graphic;
	}

	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public void init(){
		this.application.load();

		//handler.post(this);

	}

	public void update(long now) {
		
		application.getAnimation().animate(now);
		
		Application nextApplication = application.getReturnApplication();

		if(nextApplication!=null){
			nextApplication.load(); 
			this.application = nextApplication;
		}			

	}

	public long getTimeNow(){
		return System.currentTimeMillis();
	}

	public void draw(Canvas canvas){
		canvas.drawColor(Color.WHITE);
		application.draw(graphic);
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {

		PointerEvent event = new PointerEvent((int)me.getX(), (int)me.getY(), me.getAction());
		application.updateMouse(event);

		// Schedules a repaint.
		//invalidate();
		return true;
	}
	
}
