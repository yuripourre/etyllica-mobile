package br.com.etyllica.core;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
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

	protected int w = 1;
	protected int h = 1;
	
	private float xScale = 1;
	private float yScale = 1;

	protected boolean orientationHorizontal = true;

	public EtyllicaMobile(int width, int height) {
		super();
		this.w = width;
		this.h = height;
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
			xScale = (float)windowWidth/(float)w;
			yScale = (float)windowHeight/(float)h;
		}else{
			xScale = (float)windowWidth/(float)h;
			yScale = (float)windowHeight/(float)w;
		}

		Configuration.getInstance().setScaleX(xScale);
		Configuration.getInstance().setScaleY(yScale);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		computeSize();
		
		configure();

		Log.d("MOBILE INIT", "Screen Width: "+windowWidth+" "+w);

		Log.d("MOBILE INIT", "Screen Height: "+windowHeight+" "+h);

		core = new Core(this, w, h);

		startGame();

		core.init();

		setContentView(core);
	}
	
	private void configure() {
		
		Configuration.getInstance().setLocale(Locale.getDefault());
		
		Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
		
		Configuration.getInstance().setVibrator(vibrator);
		
		ImageLoader.getInstance().setAssets(getAssets());
		
	}

	@Override
	public void onConfigurationChanged(android.content.res.Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onPause() {
		super.onPause();  // Always call the superclass method first
		
		core.setPause(true);
		
	}
	
	@Override
	public void onResume() {
		super.onCreate(null);
	}

	public void startGame() {
		core.setApplication(startApplication());
	}

	public abstract Application startApplication();

}
