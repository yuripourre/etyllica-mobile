package br.com.etyllica.gui.button;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Button;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class RoundButton extends Button {

	public RoundButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Graphic g) {
		
		g.setAlpha(50);
		g.setColor(color);
		
		g.fillOval(x,y,w,h);
		
		drawLabel(g);

	}

}
