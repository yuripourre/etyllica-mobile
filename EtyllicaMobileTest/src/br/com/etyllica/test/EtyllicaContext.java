package br.com.etyllica.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.test.mock.MockContext;

public class EtyllicaContext extends MockContext{
	
	private Context mDelegatedContext;
    private static final String PREFIX = "test.";

    public EtyllicaContext(Context context) {
         mDelegatedContext = context;
    }

    @Override
    public String getPackageName(){
        return PREFIX;
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
    	return mDelegatedContext.getSharedPreferences(name, mode);
    }
    
    public Resources getResources(){
    	return mDelegatedContext.getResources();
    }
    
    
    
    
}
