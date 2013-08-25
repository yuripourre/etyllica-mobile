package br.com.etyllica.core.input.mouse;

import android.view.MotionEvent;

public class PointerEvent {

	private int x;
	private int y;
	
	private int action = MotionEvent.ACTION_MOVE;
	
	public PointerEvent(int x, int y){
		super();
		this.x = x;
		this.y = y;
	}
	
	public PointerEvent(int x, int y, int action){
		this(x,y);
		this.action = action;
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

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}
			
}
