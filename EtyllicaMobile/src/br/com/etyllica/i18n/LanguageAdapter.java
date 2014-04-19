package br.com.etyllica.i18n;

import java.util.HashMap;
import java.util.Map;

public class LanguageAdapter {

	private Map<String, Language> languages;
	
	public LanguageAdapter() {
		super();
		
		languages = new HashMap<String, Language>();
		
		languages.put("en", Language.ENGLISH_USA);
		languages.put("pt", Language.PORTUGUESE_BRAZIL);
		languages.put("ja", Language.JAPANESE);
		
		//Display Languages
		//cs
		//de(utch)
		//en(glish)
		//es(panol)
		//fr(ench)
		//it(alia)
		//ko(rean)
		//ja(pan)
		//ru(ssian)
		//pt
		
	}
	
	public Language getLanguage(String locale) {
		
		if(languages.containsKey(locale)){
			
			return languages.get(locale);
			
		} else {
		
			return Language.ENGLISH_USA;
		}
	}
	
	
}
