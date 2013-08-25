package br.com.etyllica.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.input.mouse.PointerEvent;
import br.com.etyllica.core.video.Graphic;

public class Core extends View{

	private Application application;
	private Graphic graphic;
	
	public Core(Context context) {
		super(context);
		graphic = new Graphic();
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
		
}
