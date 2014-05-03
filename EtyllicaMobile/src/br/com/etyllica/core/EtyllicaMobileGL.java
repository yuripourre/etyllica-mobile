package br.com.etyllica.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public abstract class EtyllicaMobileGL extends EtyllicaMobile {

	private GLSurfaceView mSurfaceView;
	
	private GLSurfaceView mGLView;
	
	public EtyllicaMobileGL(int width, int height) {
		super(width, height);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (hasGLES20()) {
			mGLView = new GLSurfaceView(this);
			mGLView.setEGLContextClientVersion(2);
			mGLView.setPreserveEGLContextOnPause(true);
			//mGLView.setRenderer(new GLES20Renderer());
		} else {
			// OpenGL ES not supported
		}

		setContentView(mGLView);
	}

	@Override
	public void onResume() {
		super.onResume();
		/*
		 * The activity must call the GL surface view's
		 * onResume() on activity onResume().
		 */
		if (mSurfaceView != null) {
			mSurfaceView.onResume();
		}
	}

	@Override
	public void onPause() {
		super.onPause();

		/*
		 * The activity must call the GL surface view's
		 * onPause() on activity onPause().
		 */
		if (mSurfaceView != null) {
			mSurfaceView.onPause();
		}
	}

	private boolean hasGLES20() {
		ActivityManager am = (ActivityManager)
				getSystemService(Context.ACTIVITY_SERVICE);
		ConfigurationInfo info = am.getDeviceConfigurationInfo();
		return info.reqGlEsVersion >= 0x20000;
	}

}
