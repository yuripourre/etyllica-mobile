package br.com.etyllica.core.application;

import br.com.etyllica.animation.AnimationHandler;
import br.com.etyllica.core.input.mouse.PointerEvent;
import br.com.etyllica.core.video.Graphic;

public abstract class Application {
	
	protected int w;
	
	protected int h;
	
	protected float xScale = 1;
	
	protected float yScale = 1;

	protected int loading = 0;
	
	protected Application returnApplication = null;
		
	protected AnimationHandler animation = new AnimationHandler();
	
	public Application(int w, int h){
		super();
		
		this.w = w;
		this.h = h;
	}
	
	public Application(int w, int h, float xScale, float yScale){
		super();
		
		this.w = w;
		this.h = h;
		this.xScale = xScale;
		this.yScale = yScale;
	}
	
	public abstract void draw(Graphic g);
	public abstract void load();
	public abstract void updateMouse(PointerEvent event);
	
	public Application getReturnApplication() {
		return returnApplication;
	}
	
	public void setReturnApplication(Application returnApplication) {
		this.returnApplication = returnApplication;
	}
	
	public int getLoading() {
		return loading;
	}
	
	public void setLoading(int loading) {
		this.loading = loading;
	}

	public AnimationHandler getAnimation() {
		return animation;
	}

	public void setAnimation(AnimationHandler animation) {
		this.animation = animation;
	}
				
}
