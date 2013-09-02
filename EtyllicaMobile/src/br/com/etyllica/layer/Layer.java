package br.com.etyllica.layer;

import android.graphics.Matrix;
import br.com.etyllica.core.DrawableComponent;
import br.com.etyllica.core.video.Graphic;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Layer implements DrawableComponent{

	/**
	 * x position of a Layer
	 */
	protected int x = 0;

	/**
	 * y position of a Layer
	 */
	protected int y = 0;

	/**
	 * Layer's width
	 */
	protected int w = 0;

	/**
	 * Layer's height
	 */
	protected int h = 0;

	/**
	 * Opacity
	 */
	protected int opacity = 255;

	/**
	 * Angle in degrees
	 */
	protected float angle = 0;

	/**
	 * Scale factor
	 */
	protected float scale = 1;

	/**
	 * if layer is visible
	 */
	protected boolean visible = true;

	public Layer(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Layer(int x, int y, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getOpacity() {
		return opacity;
	}

	public void setOpacity(int opacity) {
		if(opacity>255){
			this.opacity = 255;
		}else if(opacity<0){
			this.opacity = 0;
		}else{
			this.opacity = opacity;
		}
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Matrix getMatrix(){

		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		if(scale!=1){
			matrix.postScale(scale, scale);
			matrix.postTranslate(x-w/2, y-h/2);
		}else{
			matrix.postScale(scale, scale);
			matrix.postTranslate(x, y);
		}

		return matrix;
	}

	@Override
	public void draw(Graphic g) {
		// TODO Auto-generated method stub
	}

}
