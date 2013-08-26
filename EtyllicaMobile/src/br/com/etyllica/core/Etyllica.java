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
	
	protected boolean multilanguage = false;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
										
		Display display = getWindowManager().getDefaultDisplay();
		
		width = display.getWidth();
		height = display.getHeight();

		ImageLoader.getInstance().setAssets(getAssets());
		
		core = new Core(this);
		
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
