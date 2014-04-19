package br.com.etyllica.layer;


/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class GeometricLayer {

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

	public GeometricLayer(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public GeometricLayer(int x, int y, int w, int h) {
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

	public void setCoordinates(int x, int y) {
		setX(x);
		setY(y);
	}

}
