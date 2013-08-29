package br.com.etyllica;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.input.mouse.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.ImageLayer;


public class Example extends Application{
	
	private ImageLayer layer = new ImageLayer(200, 50, "weka.png");
	private ImageLayer layer2 = new ImageLayer(200, 150, "weka.png");
	private ImageLayer layer3 = new ImageLayer(20, 150, "weka.png");
	
	@Override
	public void load() {
		loading = 100;
	}
	
	@Override
	public void draw(Graphic g) {
		layer.draw(g);
		layer2.draw(g);
		layer3.draw(g);
	}

	@Override
	public void updateMouse(PointerEvent event) {

		layer.setX(event.getX()-layer.getW()/2);
		layer.setY(event.getY()-layer.getH()/2);
		
	}
	
}
