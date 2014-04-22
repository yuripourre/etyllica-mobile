package br.com.etyllica.gui;

public interface GUIComponent {

	/**
	 * 
	 * @param mx mouse coordinate x
	 * @param my mouse coordinate y
	 * @return
	 */
	public boolean onMouse(int mx, int my);
	
}
