package br.com.etyllica.core.video;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

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
	
	public void drawImage(Bitmap bitmap, int x, int y, int w, int h, int xImage, int yImage, Paint paint){
		
		Rect src = new Rect(xImage,yImage,xImage+w,yImage+h);
		Rect dist = new Rect(x,y,x+w,y+h);
		
		canvas.drawBitmap(bitmap, src, dist, paint);
	}

}
