package br.com.etyllica.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.input.mouse.PointerEvent;
import br.com.etyllica.core.video.Graphic;

public class Core extends View implements Runnable{

	private Application application;
	private Graphic graphic;
	private Handler handler = new Handler();
	
	private final int FPS = 50;

	public Core(Context context, float xScale, float yScale) {
		super(context);
		graphic = new Graphic(xScale, yScale);
	}

	@Override
	public void onDraw(Canvas canvas) {

		graphic.setCanvas(canvas);
		canvas.drawColor(Color.WHITE);

		application.draw(graphic);
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {

		PointerEvent event = new PointerEvent((int)me.getX(), (int)me.getY(), me.getAction());
		application.updateMouse(event);

		// Schedules a repaint.
		invalidate();
		return true;
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
		handler.post(this);
	}

	@Override
	public void run() {
		
		Application nextApplication = application.getReturnApplication();
		
		if(nextApplication!=null){
			nextApplication.load(); 
			this.application = nextApplication;
		}
		
		handler.postDelayed(this, 1000/FPS);
	}

}
