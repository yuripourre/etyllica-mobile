package br.com.etyllica.core.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.i18n.Language;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.util.Log;

public class ImageLoader extends Loader {
		
	private static ImageLoader instance = null;
	
	private Map<String, Bitmap> images = new HashMap<String, Bitmap>();
			
	private ImageLoader() {
		super();
		
		folder = "images/";
	}
	
	public static ImageLoader getInstance() {
		if(instance==null) {
			instance = new ImageLoader();
		}

		return instance;
	}
	
	public Bitmap getTile(String path, int w, int h, int xImage, int yImage) {
		
		float scaleX = Configuration.getInstance().getScaleX();
		float scaleY = Configuration.getInstance().getScaleY();
		
		Bitmap source = getImage(path);
				
		return Bitmap.createBitmap(source, (int)(xImage*scaleX), (int)(yImage*scaleY), (int)Math.ceil(w*scaleX), (int)Math.ceil(h*scaleY));
		
	}
	
	public Bitmap getImage(String path) {
				
		if(images.containsKey(path)) {
			return images.get(path);
		}else{
			return loadImage(path);
		}
	}
		
	private Bitmap loadImage(String path) {
	
		final String language = Configuration.getInstance().getLanguage().getCharsetName();
		
		final String defaultLanguage = Language.ENGLISH_USA.getCharsetName();
		
		final String DIR = folder+path;
		final String LANG_DIR = folder+language+"/"+path;
		final String DEFAULT_LANG_DIR = folder+defaultLanguage+"/"+path;
			
		Bitmap bitmap = null;
		
		try {
						
			if(assetExists(DIR)) {
				
				bitmap = loadBitmap(DIR);
				
			}else if(assetExists(LANG_DIR)) {
				
				bitmap = loadBitmap(LANG_DIR);
				
			}else if(assetExists(DEFAULT_LANG_DIR)) {
		        
				bitmap = loadBitmap(DEFAULT_LANG_DIR);
		        
			}else{				
				Log.e("IMAGE_LOADER", "File not found: "+LANG_DIR);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return bitmap;
		
	}
	
	private Bitmap loadBitmap(String path) throws IOException {
		
		InputStream inputStream = assets.open(path);
		
	    Bitmap bmp = null;
		
	    final float scaleX = Configuration.getInstance().getScaleX();
	    final float scaleY = Configuration.getInstance().getScaleY();
	    
		if(scaleX != 1 || scaleY != 1) {
			bmp = getResizedBitmap(inputStream);
		} else {
			bmp = BitmapFactory.decodeStream(inputStream);	
		}
		
		inputStream.close();

        return bmp;		
	}
	
	private Bitmap getResizedBitmap(InputStream inputStream) {

		Options options = new BitmapFactory.Options();
	    options.inScaled = false;
	    
	    Bitmap bmp = BitmapFactory.decodeStream(inputStream, null, options);
	    
	    final int width = bmp.getWidth();
	    final int height = bmp.getHeight();
	    
	    final float scaleX = Configuration.getInstance().getScaleX();
	    final float scaleY = Configuration.getInstance().getScaleY();
	    
	    Matrix matrix = new Matrix();
	    matrix.postScale(scaleX, scaleY);

	    Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true);
	    return resizedBitmap;
	}
		
}
