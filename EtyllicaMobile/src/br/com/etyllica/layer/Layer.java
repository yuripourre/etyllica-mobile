package br.com.etyllica.layer;

public class Layer {

	protected int x;
	
	protected int y;
	
	/**
     * if layer is visible
     */
	protected boolean visible = true;
	
	public Layer(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
		
}
