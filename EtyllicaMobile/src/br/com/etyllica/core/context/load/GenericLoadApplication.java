package br.com.etyllica.core.context.load;

import br.com.etyllica.core.graphics.Color;
import br.com.etyllica.core.graphics.Graphic;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class GenericLoadApplication extends DefaultLoadApplication {
		
	public GenericLoadApplication(int w, int h) {
		super(w, h);
		
	}
	
	private int rectW = w*2/3;
	private int rectX = w/2-rectW/2;
	private int rectY = h/2+100;
	private int rectH = 32;

	private Color backgroundColor = new Color(0x00,0xcc,0xff);
	
	@Override
	public void draw(Graphic g) {

		g.setFontSize(26);
		
		g.setColor(backgroundColor);
		g.fillRect(0, 0, w, h);
		
		g.setColor(Color.WHITE);
		g.drawStringShadowX(280, phrase);

		g.drawRect(rectX, rectY, rectW, rectH);
		g.fillRect(rectX+2, rectY+2, (int)((rectW*fill)/100)-3, rectH-3);
		
		g.setFontSize(18);
		g.drawStringShadow(rectX, rectY, rectW, rectH, percent, Color.BLACK);
		
	}	
		
}