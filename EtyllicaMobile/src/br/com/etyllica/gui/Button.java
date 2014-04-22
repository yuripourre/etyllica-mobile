package br.com.etyllica.gui;

import android.view.MotionEvent;
import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Color;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.PointerEvent;
import br.com.etyllica.gui.label.Label;

public class Button extends View implements Drawable {

	protected Label label;

	protected Color color;

	private boolean activated = false;

	public Button(int x, int y, int w, int h) {
		super(x, y, w, h);

		color = Color.BLACK;
	}

	public void draw(Graphic g) {

		g.setAlpha(50);
		g.setColor(color);

		g.fillRect(x, y, w, h);

		g.setAlpha(100);
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

		if(onMouse(mx, my)) {

			if(event.getAction() == MotionEvent.ACTION_DOWN) {

				if(!activated) {
					activated = true;
					color = Color.DARK_GRAY;
				}

			} else if(event.getAction() == MotionEvent.ACTION_UP) {

				if(activated) {

					executeAction(MotionEvent.ACTION_UP);

					color = Color.BLACK;
					
				}

			}				

		}

		if(event.getAction() == MotionEvent.ACTION_MOVE) {

			if(activated) {
				activated = false;
				color = Color.BLACK;
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
