package br.com.etyllica.core.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageLoader extends Loader{
	
	private String language = "";
	
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
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Bitmap getTile(String path, int w, int h, int xImage, int yImage){
		
		Bitmap source = getImage(path);
		
		return Bitmap.createBitmap(source, xImage, yImage, w, h);				
		
	}
	
	public Bitmap getImage(String path){
				
		if(images.containsKey(path)){
			return images.get(path);
		}else{
			return loadImage(path);
		}
	}
		
	private Bitmap loadImage(String path){
	
		final String DIR = folder+path;
		final String LANG_DIR = folder+language+"/"+path;
		
		InputStream inputStream;
		
		try {
						
			if(assetExists(DIR)){
				inputStream = assets.open(DIR);
				Bitmap bmp = BitmapFactory.decodeStream(inputStream);
		        inputStream.close();
		        return bmp;
			}else if(assetExists(LANG_DIR)){
				
				inputStream = assets.open(LANG_DIR);
				Bitmap bmp = BitmapFactory.decodeStream(inputStream);
		        inputStream.close();
		        return bmp;
			}else{
				Log.w("", "File not found: "+LANG_DIR);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
		
}
