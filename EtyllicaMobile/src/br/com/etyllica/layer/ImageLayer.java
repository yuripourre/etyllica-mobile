package br.com.etyllica.layer;

import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.video.Graphic;

public class ImageLayer extends Layer {
		
	private String path;
	
	public ImageLayer(int x, int y, String path){
		super(x,y);
		
		this.path = path;
	}

	public void draw(Graphic g){
		g.drawImage(ImageLoader.getInstance().getImage(path), x, y, null);
	}
	
}
