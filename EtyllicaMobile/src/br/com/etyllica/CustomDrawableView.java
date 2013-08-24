package br.com.etyllica;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;
import br.com.etyllica.core.loader.ImageLoader;

public class CustomDrawableView extends View {
	
	private ShapeDrawable mDrawable;
	private Bitmap d;

	public CustomDrawableView(Context context) {
		super(context);

		int x = 10;
		int y = 10;
		int width = 300;
		int height = 50;

		mDrawable = new ShapeDrawable(new OvalShape());
		mDrawable.getPaint().setColor(0xff74AC23);
		//mDrawable.getPaint().setColor(0xffff0000);
		mDrawable.setBounds(x, y, x + width, y + height);
		
		d = ImageLoader.getInstance().getImage("weka.png");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		mDrawable.draw(canvas);
		canvas.drawBitmap(d, 0, 0, null);
	}

}