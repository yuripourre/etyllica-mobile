package br.com.etyllica.gui.label;

import br.com.etyllica.core.graphics.Color;
import br.com.etyllica.core.graphics.Graphic;

public class TextLabel extends Label {

	private String text = "";
	
	private float size = 22f;
	
	private Color color = Color.WHITE;
	
	public TextLabel(String text) {
		super(0, 0);
		
		this.text = text;
	}
	
	public TextLabel(String text, float size) {
		this(text);
		
		this.size = size;		
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void draw(Graphic g) {
		g.setColor(color);
		g.setFontSize(size);
		g.drawString(bx, by, bw, bh, text);
	}
	
}
