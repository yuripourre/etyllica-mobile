package br.com.etyllica.layer;

import android.graphics.Bitmap;
import android.graphics.Paint;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.layer.ImageLayer;

public class TileLayer extends ImageLayer{

	private Bitmap tile;
	
	public TileLayer(int x, int y, int w, int h, int xImage, int yImage, String path) {
		super(x, y, w, h, path);
		
		tile = ImageLoader.getInstance().getTile(path,w,h,xImage,yImage);
	}
	
	public void draw(Graphic g) {
		
		if(!visible) {
			return;
		}
		
		Paint paint = null;
		
		if(opacity!=255){
			paint = new Paint();
			paint.setAlpha(opacity);
		}
		
		g.drawBitmap(tile, 0, 0, w, h, 0, 0, paint);
	}

}
