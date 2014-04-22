package br.com.etyllica.gui.button;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Button;

/**
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class RoundCornerButton extends Button {

	private int roundness = 10;
	
	public RoundCornerButton(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.roundness = 10;
	}
		
	public int getRoundness() {
		return roundness;
	}

	public void setRoundness(int roundness) {
		this.roundness = roundness;
	}

	@Override
	public void draw(Graphic g) {
		
		g.setAlpha(50);
		g.setColor(color);
		
		g.fillRoundRect(x, y, w, h, roundness, roundness);
				
		drawLabel(g);
	}
		
}
