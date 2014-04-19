package br.com.etyllica.core.video;

public class Color {
	
	private int r = 0;
	
	private int g = 0;
	
	private int b = 0;
	
	public Color(int red, int green, int blue) {
		super();
		
		this.r = red;
		this.g = green;
		this.b = blue;
	}

	public int getRed() {
		return r;
	}

	public int getGreen() {
		return g;
	}

	public int getBlue() {
		return b;
	}
	
	/**
	 * Colors following the awt specification
	 */
	
    public final static Color WHITE = new Color(255, 255, 255);

    public final static Color LIGHT_GRAY = new Color(192, 192, 192);

    public final static Color GRAY = new Color(128, 128, 128);

    public final static Color DARK_GRAY = new Color(64, 64, 64);

    public final static Color BLACK = new Color(0, 0, 0);

    public final static Color RED = new Color(255, 0, 0);

    public final static Color PINK = new Color(255, 175, 175);

    public final static Color ORANGE = new Color(255, 200, 0);

    public final static Color YELLOW = new Color(255, 255, 0);

    public final static Color GREEN = new Color(0, 255, 0);

    public final static Color MAGENTA = new Color(255, 0, 255);

    public final static Color CYAN = new Color(0, 255, 255);

    public final static Color BLUE = new Color(0, 0, 255);
	
}
