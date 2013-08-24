package br.com.etyllica;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class MainActivity extends Activity {

	private Graphic graphic;
	private ImageLayer layer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		ImageLoader.getInstance().setAssets(getAssets());

		graphic = new Graphic();
		
		layer = new ImageLayer(20, 50, "weka.png");
		
		setContentView(new BitmapView(this));
	}

	class BitmapView extends View {
		public BitmapView(Context context) {
			super(context);
		}

		@Override
		public void onDraw(Canvas canvas) {
						
			graphic.setCanvas(canvas);
			canvas.drawColor(Color.WHITE);
						
			layer.draw(graphic);
			
		}
	}

}
