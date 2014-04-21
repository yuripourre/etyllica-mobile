package br.com.etyllica.gui;

import android.view.MotionEvent;
import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Color;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.PointerEvent;
import br.com.etyllica.gui.label.Label;
import br.com.etyllica.layer.Layer;

public class Button extends Layer implements Drawable {

	protected Label label;
	
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
		
		drawLabel(g);

	}
	
	protected void drawLabel(Graphic g) {
		
		if(label!=null) {
			label.draw(g);
		}
		
	}

	public void handleEvent(PointerEvent event) {

		int mx = event.getX();
		int my = event.getY();

		if(event.getAction() == MotionEvent.ACTION_DOWN) {

			if(colideRectPoint(mx, my)) {

				System.out.println("Change Color!!");

				if(color.getBlue()>200) {
					color = Color.BLACK;
				} else {
					color = Color.BLUE;
				}

			}

		}

	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
		
		this.label.setContentBounds(x, y, w, h);
	}
	
}
