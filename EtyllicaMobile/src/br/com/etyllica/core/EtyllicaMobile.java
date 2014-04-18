package br.com.etyllica.core;

import java.util.Locale;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.loader.ImageLoader;

public abstract class EtyllicaMobile extends Activity {

	private Core core;

	private int windowWidth;
	private int windowHeight;

	protected int width = 1;
	protected int height = 1;

	protected float xScale = 1;
	protected float yScale = 1;

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

	public EtyllicaMobile(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	private Point getDisplaySize(final Display display) {

		final Point point = new Point();

		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){   //API LEVEL 13
			display.getSize(point);
		}else{    
			point.x = display.getWidth();
			point.y = display.getHeight();
		}

		return point;
	}

	private void computeSize() {

		Display display = getWindowManager().getDefaultDisplay();
		Point size = getDisplaySize(display);

		windowWidth = size.x;
		windowHeight = size.y;

		if(orientationHorizontal) {
			xScale = (float)windowWidth/(float)width;
			yScale = (float)windowHeight/(float)height;
		}else{
			xScale = (float)windowWidth/(float)height;
			yScale = (float)windowHeight/(float)width;
		}

		ImageLoader.getInstance().setxScale(xScale);
		ImageLoader.getInstance().setyScale(yScale);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		computeSize();

		ImageLoader.getInstance().setAssets(getAssets());

		Log.d("MOBILE INIT", "Screen Width: "+windowWidth+" "+width);

		Log.d("MOBILE INIT", "Screen Height: "+windowHeight+" "+height);


		core = new Core(this, xScale, yScale);

		startGame();

		if(multilanguage){
			ImageLoader.getInstance().setLanguage(Locale.getDefault().getLanguage());			
		}

		core.init();

		setContentView(core);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onPause() {
		super.onPause();  // Always call the superclass method first

		startGame();
	}

	public void startGame() {
		core.setApplication(startApplication());
	}

	public abstract Application startApplication();

}
