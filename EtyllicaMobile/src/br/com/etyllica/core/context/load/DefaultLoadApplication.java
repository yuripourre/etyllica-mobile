package br.com.etyllica.core.context.load;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Color;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.PointerEvent;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class DefaultLoadApplication extends Application implements LoadApplication {

	public DefaultLoadApplication(int w, int h) {
		super(w,h);
	}
	
	protected String phrase = "Loading...";
	
	protected String percent = "0%";

	protected float fill = 0;

	private int bxLimite = 400;
	private int byLimite = 30;

	private int bx = (w/2)-bxLimite/2;

	private int by = 395;

	public void load(){

		by = h-100;
		
		loading = 100;		
	}

	@Override
	public void draw(Graphic g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);

		g.setColor(Color.BLACK);
		g.drawRect(bx,by,bxLimite,byLimite);
		g.setColor(Color.WHITE);
		g.drawRect(bx-1,by-1,bxLimite+2,byLimite+2);

		g.setColor(Color.WHITE);
		g.drawStringShadowX(100, phrase);
			
		//Draw Bar Fill
		g.fillRect(bx+2, by+2, (int)fill*4, byLimite-3);

		g.setColor(Color.WHITE);
		g.drawStringShadowX(h-85, percent);

	}

	@Override
	public void setText(String phrase, float load){
		
		this.phrase = phrase;
		
		//this.percent = Float.toString(load)+"%";
		this.percent = Integer.toString((int)load)+"%";

		this.fill = load;
	}

	@Override
	public void updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		
	}

}