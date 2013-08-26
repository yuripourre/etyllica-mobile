package br.com.etyllica;

import br.com.etyllica.core.Etyllica;

public class MainActivity extends Etyllica {

	public MainActivity(){
		super();
	}
	
	@Override
	public void startGame() {
		setMainApplication(new Example());
	}
	
}
