package br.com.etyllica;

import br.com.etyllica.core.EtyllicaMobile;
import br.com.etyllica.core.application.Application;

public class MainActivity extends EtyllicaMobile {

	public MainActivity() {
		super(800,480);
	}
	
	@Override
	public Application startApplication() {
		return new Example(w, h);
	}
	
}
