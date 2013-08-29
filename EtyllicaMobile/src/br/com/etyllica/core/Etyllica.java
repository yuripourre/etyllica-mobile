package br.com.etyllica.core;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.loader.ImageLoader;

public class Etyllica extends Activity {

	private Core core;
	
	private int width;
	private int height;
	
	private int windowWidth;
	private int windowHeight;
	private float density;
	
	protected boolean orientationHorizontal = true;
	
	protected boolean multilanguage = false;
	//cs
	//de(utch)
	//en(glish)
	//es(panol)
	//fr(ench)
	//it(alia)
	//ko(rean)
	//ja(pan)
	//ru(ssian)
	//pt
		
	public Etyllica(int width, int height){
		super();
		this.width = width;
		this.height = height;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
										
		Display display = getWindowManager().getDefaultDisplay();
		
		windowWidth = display.getWidth();
		windowHeight = display.getHeight();
		density = getResources().getDisplayMetrics().density;

				
		ImageLoader.getInstance().setAssets(getAssets());
		
		float xScale = 1;
		float yScale = 1;
		
		if(orientationHorizontal){
			xScale = (float)windowWidth/width;
			yScale = (float)windowHeight/height;
		}else{
			xScale = (float)windowWidth/height;
			yScale = (float)windowHeight/width;
		}
		
		ImageLoader.getInstance().setxScale(xScale);
		ImageLoader.getInstance().setyScale(yScale);
		
		core = new Core(this, xScale, yScale);
		
		startGame();
				
		if(multilanguage){
			ImageLoader.getInstance().setLanguage(Locale.getDefault().getLanguage());			
		}
		
		core.init();
		
		setContentView(core);
	}
	
	public void startGame(){
	
	}
	
	protected void setMainApplication(Application application){
		core.setApplication(application);
	}

}
