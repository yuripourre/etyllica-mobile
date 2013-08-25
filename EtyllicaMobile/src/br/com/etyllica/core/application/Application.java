package br.com.etyllica.core.application;

import br.com.etyllica.core.input.mouse.PointerEvent;
import br.com.etyllica.core.video.Graphic;

public abstract class Application {

	protected Application returnApplication;
	
	protected int loading = 0;
	
	public abstract void draw(Graphic g);
	public abstract void load();
	public abstract void updateMouse(PointerEvent event);
	
	public Application getReturnApplication() {
		return returnApplication;
	}
	
	public void setReturnApplication(Application returnApplication) {
		this.returnApplication = returnApplication;
	}
		
}
