package br.com.etyllica.core.loader;

import java.io.File;
import java.io.IOException;

import android.content.res.AssetManager;

public class Loader {
	
	protected AssetManager assets;

	protected String path;
	
	protected String folder;
	
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public AssetManager getAssets() {
		return assets;
	}

	public void setAssets(AssetManager assets) {
		this.assets = assets;
	}
	
	protected boolean assetExists(String name) {
	    try {
	        // using File to extract path / filename
	        // alternatively use name.lastIndexOf("/") to extract the path
	        File f = new File(name);
	        String parent = f.getParent();
	        if (parent == null) parent = "";
	        String fileName = f.getName();
	        // now use path to list all files
	        String[] assetList = assets.list(parent);
	        if (assetList != null && assetList.length > 0) {
	            for (String item : assetList) {
	                if (fileName.equals(item))
	                    return true;
	            }
	        }
	    } catch (IOException e) {
	        // Log.w(TAG, e); // enable to log errors
	    }
	    return false;
	}
			
}
