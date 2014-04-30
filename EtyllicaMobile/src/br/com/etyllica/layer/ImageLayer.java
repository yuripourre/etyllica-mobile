package br.com.etyllica.layer;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.loader.ImageLoader;

public class ImageLayer extends StaticLayer {
	
	protected int xImage = 0;
	
	protected int yImage = 0;
	
	public ImageLayer(String path) {
		this(0, 0, path);
	}
	
	public ImageLayer(int x, int y) {
		super(x, y);
	}
	
	public ImageLayer(int x, int y, String path) {
		super(path);
		
		setCoordinates(x, y);
		
	}
	
	public ImageLayer(int x, int y, int w, int h, String path) {
		super(x, y, w, h, path);
	}
	
	public ImageLayer(int x, int y,int w, int h, int xImage, int yImage, String path) {
		super(x,y,w,h);
		
		this.xImage = xImage;
		this.yImage = yImage;
		
		this.path = path;		
	}
	
	public int getXImage() {
		return xImage;
	}

	public void setXImage(int xImage) {
		this.xImage = xImage;
	}

	public int getYImage() {
		return yImage;
	}

	public void setYImage(int yImage) {
		this.yImage = yImage;
	}

	public void draw(Graphic g) {
		
		if(!visible) {
			return;
		}
		
		if(opacity!=255) {			
			g.setOpacity(opacity);
		}
		
		g.setMatrix(getMatrix());
		
		g.drawBitmap(ImageLoader.getInstance().getImage(path), x, y, w, h, xImage, yImage);
		
		g.resetMatrix();
		
	}
	
}
