package br.com.etyllica.core.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageLoader extends Loader{

	private AssetManager assets;
	
	private static ImageLoader instance = null;
	
	private Map<String, Bitmap> images = new HashMap<String, Bitmap>();
	
	private ImageLoader(){
		super();
		
		folder = "images/";
	}
	
	public static ImageLoader getInstance() {
		if(instance==null){
			instance = new ImageLoader();
		}

		return instance;
	}
		
	public AssetManager getAssets() {
		return assets;
	}

	public void setAssets(AssetManager assets) {
		this.assets = assets;
	}

	public Bitmap getImage(String path){
				
		if(images.containsKey(path)){
			return images.get(path);
		}else{
			return loadImage(path);
		}
	}
		
	private Bitmap loadImage(String path){
	
		InputStream inputStream;
		try {
			inputStream = assets.open(folder+path);
			Bitmap bmp = BitmapFactory.decodeStream(inputStream);
	        inputStream.close();
	        
	        return bmp;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
}
