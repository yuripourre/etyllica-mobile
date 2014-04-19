package br.com.etyllica.gui;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Color;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.Layer;

public class Button extends Layer implements Drawable {

	private Color color;
	
	public Button(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		color = Color.BLACK;
	}
	
	public void draw(Graphic g) {

		g.setAlpha(50);
		g.setColor(color);
		
		g.fillRect(x, y, w, h);
		
		g.setAlpha(100);
		g.setColor(0, 0, 0xff);		
		g.drawRect(x, y, w, h);
		
	}
	
}
