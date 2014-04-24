package br.com.etyllica.core.context;

import br.com.etyllica.animation.AnimationHandler;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.PointerEvent;

public abstract class Application extends Context {
	
	protected int w;
	
	protected int h;
	
	protected Application returnApplication = null;
		
	protected AnimationHandler animation = new AnimationHandler();
	
	public Application(int w, int h){
		super();
		
		this.w = w;
		this.h = h;
	}
	
	public abstract void draw(Graphic g);
		
	public abstract void updateMouse(PointerEvent event);
	
	public void update(long now) {
		// TODO Auto-generated method stub		
	}

	public void timeUpdate(long now) {
		// TODO Auto-generated method stub
		
	}
	
	public Application getReturnApplication() {
		return returnApplication;
	}
	
	public void setReturnApplication(Application returnApplication) {
		this.returnApplication = returnApplication;
	}
	
	public AnimationHandler getAnimation() {
		return animation;
	}

	public void setAnimation(AnimationHandler animation) {
		this.animation = animation;
	}
	
}
