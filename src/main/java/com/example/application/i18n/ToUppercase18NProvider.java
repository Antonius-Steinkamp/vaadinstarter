package com.example.application.i18n;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.vaadin.flow.i18n.I18NProvider;

import lombok.extern.java.Log;

/**
 * Simple Implementation for {@link I18NProvider}.
 * 
 * @author Antonius
 *
 */
@Component
@Log
public class ToUppercase18NProvider implements I18NProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4726383865139769509L;
	/*
	 * Use no-country versions, so that e.g. both en_US and en_GB work.
	 */
	public static final java.util.Locale FINNISH = new Locale("fi");
	public static final java.util.Locale ENGLISH = new Locale("en");
	public static final java.util.Locale FRENCH = new Locale("fr");

	@Override
	public List<Locale> getProvidedLocales() {
		final List<Locale> result = Collections.unmodifiableList(Arrays.asList(ENGLISH, FRENCH, FINNISH));
		log.info("Locales: " + result);
		return result;
	}

	@Override
	public String getTranslation(String key, Locale locale, Object... params) {
		final String result = MessageFormat.format(key, params).toUpperCase();
		log.info("Translate '" + key + "/" + locale + "/" + " parms to '" + result + "'");
		return result;
	}
}
