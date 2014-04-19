package br.com.etyllica.layer;

import android.graphics.Matrix;
import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Layer extends GeometricLayer implements Drawable{

	/**
	 * Opacity
	 */
	protected int opacity = 255;

	/**
	 * Angle in degrees
	 */
	protected double angle = 0;

	/**
	 * Scale factor
	 */
	protected double scale = 1;

	/**
	 * if layer is visible
	 */
	protected boolean visible = true;

	public Layer(int x, int y) {
		super(x, y);
	}

	public Layer(int x, int y, int w, int h) {
		super(x,y,w,h);
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

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
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
		matrix.postRotate((float)angle);
		if(scale!=1){
			matrix.postScale((float)scale, (float)scale);
			matrix.postTranslate(x-w/2, y-h/2);
		}else{
			matrix.postScale((float)scale, (float)scale);
			matrix.postTranslate(x, y);
		}

		return matrix;
	}

	@Override
	public void draw(Graphic g) {
		// TODO Auto-generated method stub
	}

}
