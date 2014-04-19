package br.com.etyllica;
import br.com.etyllica.animation.scripts.HorizontalAnimationScript;
import br.com.etyllica.animation.scripts.RotateAnimationScript;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.input.mouse.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class Example extends Application{
	
	public Example(int w, int h) {
		super(w, h);
	}

	private ImageLayer layer;
	private ImageLayer layerGhost;
	private ImageLayer layerBig;
	private ImageLayer layerTurn;
	
	@Override
	public void load() {
		
		layer = new ImageLayer(200, 50, "weka.png");
		
		layerGhost =  new ImageLayer(200, 150, "weka.png");
		layerGhost.setOpacity(50);
		
		layerBig = new ImageLayer(20, 150, "weka.png");
		layerBig.setScale(2);		
		
		layerTurn = new ImageLayer(100, 350, "weka.png");
		layerTurn.setAngle(50);
		
		HorizontalAnimationScript horizontal = new HorizontalAnimationScript(layerGhost,10000);
		horizontal.setInterval(200, 300);
		horizontal.setEndless(true);
		this.animation.add(horizontal);
		
		HorizontalAnimationScript invHorizontal = new HorizontalAnimationScript(layerGhost,5000);
		invHorizontal.setInterval(300, 200);
				
		horizontal.setNext(invHorizontal);
		
		
		RotateAnimationScript rotate = new RotateAnimationScript(1000,12000);
		rotate.setTarget(layerTurn);
		rotate.setInterval(0, 720);
		this.animation.add(rotate);
		
		loading = 100;
	}
	
	@Override
	public void draw(Graphic g) {
		layer.draw(g);
		layerBig.draw(g);
		layerGhost.draw(g);
		layerTurn.draw(g);
	}

	@Override
	public void updateMouse(PointerEvent event) {

		layer.setX(event.getX()-layer.getW()/2);
		layer.setY(event.getY()-layer.getH()/2);
		
	}
	
}
