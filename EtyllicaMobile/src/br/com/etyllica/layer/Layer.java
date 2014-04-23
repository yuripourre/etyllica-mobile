package br.com.etyllica.layer;

import android.graphics.Matrix;
import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Layer extends GeometricLayer implements Drawable {

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

	public Layer() {
		super();
	}
	
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
		
		float xScale = Configuration.getInstance().getScaleX();
		float yScale = Configuration.getInstance().getScaleY();
				
		if(scale!=1) {
			matrix.postScale((float)scale, (float)scale);
			matrix.postTranslate(x*xScale-(w*xScale)/2, y*yScale-h*yScale/2);
		} else {
			matrix.postTranslate(x*xScale, y*yScale);
		}

		return matrix;
	}

	@Override
	public void draw(Graphic g) {
		// TODO Auto-generated method stub
	}

}
