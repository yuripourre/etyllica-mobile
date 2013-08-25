package br.com.etyllica.core.video;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Graphic {

	private Canvas canvas;
	
	public Graphic(){
		super();		
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public void drawImage(Bitmap bitmap, int x, int y, Paint paint){
		canvas.drawBitmap(bitmap, x, y, paint);
	}	

}
