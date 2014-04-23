package br.com.etyllica.layer;

import android.graphics.Bitmap;
import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.loader.ImageLoader;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class StaticLayer extends Layer {

	protected String path = "";

	public StaticLayer(){
		super();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public StaticLayer(int x, int y){
		super(x,y);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public StaticLayer(int x, int y, int w, int h){
		super(x,y,w,h);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param path
	 */
	public StaticLayer(int x, int y, int w, int h, String path){
		super(x,y);

		this.path = path;
		load();
		
		this.w = w;
		this.h = h;
	}

	/**
	 * 
	 * @param path
	 */
	public StaticLayer(String path) {
		this.path = path;
		load();
	}

	public String getPath(){
		return path;
	}

	/**
	 * 
	 * @param path
	 */
	public void setPath(String path){
		this.path = path;
	}

	/**
	 * 
	 * @param w
	 * @param h
	 */
	public void setSize(int w , int h) {
		this.w = w;
		this.h = h;
	}

	/**
	 * 
	 * @param path
	 */
	public void cloneLayer(String path) {
		this.path = path;
	}

	/**
	 * 
	 * @param layer
	 */
	public void cloneLayer(StaticLayer layer) {
		this.path = layer.path;
		w = layer.getW();
		h = layer.getH();
	}

	private void load() {
		
		Bitmap bitmap = ImageLoader.getInstance().getImage(path);
		
		float xScale = Configuration.getInstance().getScaleX();
		float yScale = Configuration.getInstance().getScaleY();
		
		//Set the Original Size		
		this.w = (int)(bitmap.getWidth()/xScale);
		this.h = (int)(bitmap.getHeight()/yScale);
		
	}

}
