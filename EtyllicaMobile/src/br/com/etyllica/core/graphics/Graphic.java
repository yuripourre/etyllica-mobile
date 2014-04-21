package br.com.etyllica.core.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Graphic {

	private Canvas canvas;
	
	private Paint paint;

	private float xScale = 1;
	private float yScale = 1;
	
	private boolean antiAlias = true;
	
	private int alpha = 255;

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

	public void drawBitmap(Bitmap bitmap, int x, int y, int w, int h, int xImage, int yImage, Paint paint) {

		if(xScale==1 && yScale==1) {

			Rect src = new Rect(xImage,yImage,xImage+w,yImage+h);
			Rect dist = new Rect(x,y,x+w,y+h);

			canvas.drawBitmap(bitmap, src, dist, paint);
			
		} else {
			
			float cw = w*xScale;
			float ch = h*yScale;
			
			Rect src = new Rect((int)(xImage*xScale),(int)(yImage*yScale),(int)(xImage+cw),(int)(yImage+ch));
			RectF dist = new RectF(x, y, x+cw, y+ch);

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
	
	public void setColor(br.com.etyllica.core.graphics.Color color) {
		this.setColor(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public void setColor(int r, int g, int b) {
		paint.setColor(Color.rgb(r, g, b));
	}
	
	public void setOpacity(int opacity) {
		
		alpha = opacity;
	}
	
	public void setAlpha(int percent) {

		alpha = (int)((float)percent*255/100);			
	}
	
	public void drawString(String text, int x, int y) {
		this.write(x, y, text);
	}
	
	public void drawString(int x, int y, int w, int h, String text) {
		
		Rect bounds = new Rect();
		
		paint.getTextBounds(text, 0, text.length(), bounds);	
		canvas.drawText(text, (x+w/2+(bounds.left-bounds.right)/2)*xScale, (y+h/2+(bounds.bottom-bounds.top)/2)*yScale, paint);
	}
	
	public void write(int x, int y, String text) {
		canvas.drawText(text, x*xScale, y*yScale, paint); 
	}
	
	public void setFontSize(float size) {
		paint.setTextSize(size*yScale);
	}
	
	public void drawRect(int x, int y, int w, int h) {
		
		setDrawStyle();
				
		drawCanvasRect(x,y,w,h);
	}
	
	public void fillRect(int x, int y, int w, int h) {
		
		setFillStyle();
		
		drawCanvasRect(x,y,w,h);
	}
	
	private void setFillStyle() {
		
		paint.setStyle(Paint.Style.FILL);
		
		if(alpha!=255)
			paint.setAlpha(alpha);
		
	}
	
	private void setDrawStyle() {
		
		paint.setStyle(Paint.Style.STROKE);
		
		if(alpha!=255)
			paint.setAlpha(alpha);
		
	}
	
	private void drawCanvasRect(int x, int y, int w, int h) {
		
		RectF rect = getScaledRect(x, y, w, h);
		
		canvas.drawRect(rect, paint);
	}
	
	private RectF getScaledRect(int x, int y, int w, int h) {
		
		float cx = x*xScale;
		float cy = y*yScale;
		float cw = cx+w*xScale;
		float ch = cy+h*yScale;
		
		return new RectF(cx, cy, cw, ch);
		
	}
	
}
