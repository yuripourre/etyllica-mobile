package br.com.etyllica.layer;

public class Layer {

	protected int x;
	
	protected int y;
	
	protected int w;
	
	protected int h;
	
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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
		
}
