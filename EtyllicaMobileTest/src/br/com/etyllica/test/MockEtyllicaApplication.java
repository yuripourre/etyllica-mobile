package br.com.etyllica.test;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.PointerEvent;

public class MockEtyllicaApplication extends Application{

	public MockEtyllicaApplication(int w, int h) {
		super(w, h);
	}
	
	@Override
	public void draw(Graphic g) {
		// TODO Auto-generated method stub
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		loading = 100;
	}

	@Override
	public void updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
	}
	
}
