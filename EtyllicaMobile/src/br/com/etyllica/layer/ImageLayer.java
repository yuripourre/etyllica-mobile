package br.com.etyllica.layer;

import android.graphics.Bitmap;
import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.video.Graphic;

public class ImageLayer extends Layer {
		
	private String path;
	
	private int xImage = 0;
	
	private int yImage = 0;
	
	public ImageLayer(String path){
		this(0,0,path);
	}
	
	public ImageLayer(int x, int y, String path){
		super(x,y);
		
		this.path = path;
		Bitmap bitmap = ImageLoader.getInstance().getImage(path);
		this.w = bitmap.getWidth();
		this.h = bitmap.getHeight();
		
	}
	
	public ImageLayer(int x, int y,int w, int h, String path){
		super(x,y,w,h);
		
		this.path = path;
	}
	
	public ImageLayer(int x, int y,int w, int h, int xImage, int yImage, String path){
		super(x,y,w,h);
		
		this.xImage = xImage;
		this.yImage = yImage;
		
		this.path = path;		
	}
	
	public int getxImage() {
		return xImage;
	}

	public void setxImage(int xImage) {
		this.xImage = xImage;
	}

	public int getyImage() {
		return yImage;
	}

	public void setyImage(int yImage) {
		this.yImage = yImage;
	}

	public void draw(Graphic g){
		g.drawImage(ImageLoader.getInstance().getImage(path), x, y, w, h, xImage, yImage, null);
	}
	
}
