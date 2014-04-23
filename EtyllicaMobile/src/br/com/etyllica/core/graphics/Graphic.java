package br.com.etyllica.core.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

public class Graphic {

	private Canvas canvas;
	
	private Paint paint;

	private float xScale = 1;
	private float yScale = 1;
	
	private int width;
	private int height;
	
	private boolean antiAlias = true;
	
	private int alpha = 255;

	public Graphic(int width, int height, float xScale, float yScale) {
		super();		
		
		this.width = width;
		this.height = height;
		
		this.xScale = xScale;
		this.yScale = yScale;
		
		paint = new Paint();
		paint.setAntiAlias(antiAlias);
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
			RectF dist = getScaledRect(x, y, w, h);

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
	
	public void drawString(int x, int y, float w, float h, String text) {
		
		Rect bounds = new Rect();
				
		paint.getTextBounds(text, 0, text.length(), bounds);
				
		canvas.drawText(text, ((x+w/2)*xScale)-bounds.width()/2, ((y+h/2)*yScale)+bounds.height()/2, paint);

	}
	
	public void drawStringShadowX(int y, String text) {
		
		float size = paint.getTextSize();
		
		drawStringShadow(0, y, width, size, text, br.com.etyllica.core.graphics.Color.BLACK);
	}
	
	public void drawStringShadow(int x, int y, float w, float h, String text, br.com.etyllica.core.graphics.Color shadowColor) {
		
		//Save current color
		int color = paint.getColor();
		
		//Draw Shadow
		this.setColor(shadowColor);
		drawString(x+1, y+1, w, h, text);
		
		//Draw Text
		paint.setColor(color);
		drawString(x, y, w, h, text);
	}
	
	public void write(int x, int y, String text) {
		canvas.drawText(text, x*xScale, y*yScale, paint); 
	}
	
	public void setFontSize(float size) {
		paint.setTextSize(size*yScale);
	}
	
	public void setFontStyle(Typeface typeFace) {
		paint.setTypeface(typeFace);
	}
	
	public void drawRect(int x, int y, int w, int h) {
		
		setDrawStyle();
				
		drawCanvasRect(x,y,w,h);
	}
	
	public void fillRect(int x, int y, int w, int h) {
		
		setFillStyle();
		
		drawCanvasRect(x,y,w,h);
	}
	
	public void drawOval(int x, int y, int w, int h) {
		
		setDrawStyle();
		
		drawCanvasOval(x,y,w,h);
		
	}
		
	public void fillOval(int x, int y, int w, int h) {
		
		setFillStyle();
		
		drawCanvasOval(x,y,w,h);
		
	}
	
	public void drawRoundRect(int x, int y, int w, int h, int arcWidth, int arcHeight) {
		
		setDrawStyle();
		
		drawCanvasRoundRect(x, y, w, h, arcWidth, arcHeight);
		
	}
	
	public void fillRoundRect(int x, int y, int w, int h, int arcWidth, int arcHeight) {
		
		setFillStyle();
		
		drawCanvasRoundRect(x, y, w, h, arcWidth, arcHeight);
		
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
	
	private void drawCanvasRoundRect(int x, int y, int w, int h, int roundnessX, int roundnessY) {
		
		RectF rect = getScaledRect(x, y, w, h);
		
		canvas.drawRoundRect(rect, roundnessX, roundnessY, paint);
	}
	
	private void drawCanvasOval(int x, int y, int w, int h) {
		
		RectF rect = getScaledRect(x, y, w, h);
		
		canvas.drawOval(rect, paint);
	}
	
	private RectF getScaledRect(int x, int y, int w, int h) {
		
		float cx = x*xScale;
		float cy = y*yScale;
		float cw = cx+w*xScale;
		float ch = cy+h*yScale;
		
		return new RectF(cx, cy, cw, ch);
		
	}
	
}
