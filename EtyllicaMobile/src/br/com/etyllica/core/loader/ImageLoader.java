package br.com.etyllica.core.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

public class ImageLoader extends Loader{
	
	private String language = "";
	private String defaultLanguage = "en";
	
	private static ImageLoader instance = null;
	
	private Map<String, Bitmap> images = new HashMap<String, Bitmap>();
	
	private float xScale = 1;
	private float yScale = 1;
		
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
	
	public float getxScale() {
		return xScale;
	}

	public void setxScale(float xScale) {
		this.xScale = xScale;
	}

	public float getyScale() {
		return yScale;
	}

	public void setyScale(float yScale) {
		this.yScale = yScale;
	}

	public Bitmap getTile(String path, int w, int h, int xImage, int yImage){
		
		Bitmap source = getImage(path);
				
		return Bitmap.createBitmap(source, (int)(xImage*xScale), (int)(yImage*yScale), (int)Math.ceil(w*xScale), (int)Math.ceil(h*yScale));
		
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
		final String DEFAULT_LANG_DIR = folder+defaultLanguage+"/"+path;
				
		try {
						
			if(assetExists(DIR)){
				
				return loadBitmap(DIR);
				
			}else if(assetExists(LANG_DIR)){
				
				return loadBitmap(LANG_DIR);
				
			}else if(assetExists(DEFAULT_LANG_DIR)){
		        
				return loadBitmap(DEFAULT_LANG_DIR);
		        
			}else{				
				Log.e("", "File not found: "+LANG_DIR);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	private Bitmap loadBitmap(String path) throws IOException{
		
		InputStream inputStream = assets.open(path);
		
		Bitmap bmp = BitmapFactory.decodeStream(inputStream);
		
		if(xScale!=1||yScale!=1){
			bmp = getResizedBitmap(bmp);
		}
		
		inputStream.close();

        return bmp;		
	}
	
	public Bitmap getResizedBitmap(Bitmap bmp) {
				
	    int width = bmp.getWidth();
	    int height = bmp.getHeight();
	    
	    Matrix matrix = new Matrix();
	    matrix.postScale(xScale, yScale);

	    Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, false);
	    return resizedBitmap;
	}
		
}
