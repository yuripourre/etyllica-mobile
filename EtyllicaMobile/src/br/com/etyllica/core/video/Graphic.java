package br.com.etyllica.core.video;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class Graphic {

	private Canvas canvas;
	
	private Paint paint;

	private float xScale = 1;
	private float yScale = 1;
	
	private boolean antiAlias = true;

	public Graphic(float xScale, float yScale) {
		super();		

		paint = new Paint();
		paint.setAntiAlias(antiAlias);
		
		this.xScale = xScale;
		this.yScale = yScale;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public float getxScale() {
		return xScale;
	}

	public void setxScale(float xScale) {
		this.xScale = xScale;
	}

	public float getyScale() {
		return yScale;
	}

	public void setyScale(float yScale) {
		this.yScale = yScale;
	}
			
	public void drawBitmap(Bitmap bitmap, int x, int y, Paint paint) {
		canvas.drawBitmap(bitmap, x*xScale, y*yScale, paint);
	}
	
	public void drawBitmap(Bitmap bitmap, int x, int y, int w, int h, int xImage, int yImage, Paint paint) {

		if(xScale==1 && yScale==1) {

			Rect src = new Rect(xImage,yImage,xImage+w,yImage+h);
			Rect dist = new Rect(x,y,x+w,y+h);

			canvas.drawBitmap(bitmap, src, dist, paint);
			
		} else {
			
			Rect src = new Rect((int)(xImage*xScale),(int)(yImage*yScale),xImage+w,yImage+h);
			Rect dist = new Rect(x,y,x+w,y+h);

			canvas.drawBitmap(bitmap, src, dist, paint);
		}
		
		resetMatrix();
	}
	
	private void resetMatrix() {
		
		Matrix eye  = new Matrix();
		eye.reset();
		
		canvas.setMatrix(eye);
	}

	public void setMatrix(Matrix matrix){
		canvas.setMatrix(matrix);
	}
	
	public void setAntiAlias(boolean antiAlias) {
		
		this.antiAlias = antiAlias;
		
		paint.setAntiAlias(antiAlias);
	}
	
	public void setColor(br.com.etyllica.core.video.Color color) {
		this.setColor(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public void setColor(int r, int g, int b) {
		paint.setColor(Color.rgb(r, g, b));
	}
	
	public void setOpacity(int opacity) {
		paint.setAlpha(opacity);
	}
	
	public void setAlpha(int percent) {

		int alpha = (int)((float)percent*255/100);
	
		paint.setAlpha(alpha);
		
	}
	
	public void drawRect(int x, int y, int w, int h) {
		
		paint.setStyle(Paint.Style.STROKE);
		
		canvas.drawRect(x, y, x+w, y+h, paint);
	}
	
	public void fillRect(int x, int y, int w, int h) {
		
		paint.setStyle(Paint.Style.FILL);
		
		canvas.drawRect(x, y, x+w, y+h, paint);
		
	}
	
}
