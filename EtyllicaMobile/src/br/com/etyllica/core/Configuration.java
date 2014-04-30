package br.com.etyllica.core;

import java.util.Locale;

import android.os.Vibrator;
import br.com.etyllica.i18n.Language;
import br.com.etyllica.i18n.LanguageAdapter;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Configuration {

	private static Configuration instance = null;

	private LanguageAdapter adapter = new LanguageAdapter();
	
	private Locale locale = null;
	
	private Language language = Language.ENGLISH_USA;
	
	private float scaleX = 1;

	private float scaleY = 1;
	
	private boolean vibration = false;
	
	private Vibrator vibrator;

	private Configuration(){
		super();
	}

	public static Configuration getInstance() {
		if(instance==null){
			instance = new Configuration();
		}

		return instance;
	}

	public Language getLanguage() {
		return language;
	}

	public float getScaleX() {
		return scaleX;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}

	public void setLocale(Locale locale) {
		
		this.locale = locale;
		
		this.language = adapter.getLanguage(locale.getLanguage());
	}

	public Locale getLocale() {
		return locale;
	}

	public void vibrate(long milliseconds) {
		if(vibration&&vibrator!=null)
			vibrator.vibrate(milliseconds);
	}

	public void enableVibration() {
		this.vibration = true;
	}
	
	public void disableVibration() {
		this.vibration = false;
	}
	
	public void setVibrator(Vibrator vibrator) {
		this.vibrator = vibrator;
	}	
		
}
