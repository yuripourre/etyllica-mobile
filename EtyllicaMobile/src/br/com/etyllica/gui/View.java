package br.com.etyllica.gui;

import android.util.SparseArray;
import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.Action;
import br.com.etyllica.layer.Layer;

public abstract class View extends Layer implements GUIComponent {
	
	protected SparseArray<Action> actions = new SparseArray<Action>();
	
	public View(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public boolean onMouse(int mx, int my) {
		return colideRectPoint(mx, my);
	}
	
	public void addAction(int motionEvent, Action action) {
				
		actions.put(motionEvent, action);
	}
	
	protected void executeAction(int motionEvent) {
		
		Action action = actions.get(motionEvent);
		
		if(action!=null) {
			
			Configuration.getInstance().vibrate(40);
			
			action.executeAction();
			
		}
		
	}
	
}
