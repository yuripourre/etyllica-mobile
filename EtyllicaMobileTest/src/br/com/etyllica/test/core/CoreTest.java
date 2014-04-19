package br.com.etyllica.test.core;

import android.content.Context;
import android.test.AndroidTestCase;
import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.Core;
import br.com.etyllica.test.EtyllicaContext;
import br.com.etyllica.test.MockEtyllicaApplication;

public class CoreTest extends AndroidTestCase{
	
	private Context context;
	private Core core;
	
	public void setUp(){
		context = new EtyllicaContext(getContext());
		
		Configuration.getInstance().setScaleX(2);
		Configuration.getInstance().setScaleY(3);
		
		core = new Core(context);
		core.setApplication(new MockEtyllicaApplication(100,100)); 
	}
	
	public void testCoreConstructorParams() {
			
		assertNotNull(core.getContext());
		assertNotNull(core.getGraphic());
		
		assertEquals(core.getGraphic().getxScale(),2);
		assertEquals(core.getGraphic().getyScale(),3);		

	}
	
	public void testApplicationMethods(){
		assertNotNull(core.getApplication());
	}
	
	public void testCoreInit() {
		
		core.init();
		
		assertEquals(core.getApplication().getLoading(),100);
		
	}

}
