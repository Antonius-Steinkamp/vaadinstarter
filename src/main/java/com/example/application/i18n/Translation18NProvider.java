package com.example.application.i18n;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.application.Utils;
import com.example.application.data.entity.Translation;
import com.example.application.data.service.TranslationService;
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
public class Translation18NProvider implements I18NProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4726383865139769509L;
	/*
	 * Use no-country versions, so that e.g. both en_US and en_GB work.
	 */
	public static final java.util.Locale FINNISH = new Locale("fi");

	private final JdbcTemplate jdbcTemplate;
	private final TranslationService translationService;

	public Translation18NProvider(JdbcTemplate jdbcTemplate, TranslationService translationService) {
		this.jdbcTemplate = jdbcTemplate;
		this.translationService = translationService;
	}

	@Override
	public List<Locale> getProvidedLocales() {
		final List<Locale> result = Collections.unmodifiableList(Arrays.asList(Locale.ENGLISH, Locale.FRENCH, FINNISH));
		log.info("Locales: " + result);
		return result;
	}

	@Override
	public String getTranslation(String key, Locale locale, Object... params) {
		String sql = "SELECT id from TRANSLATION WHERE KEY = '" + key + "' AND LOCALE = '" + locale.getLanguage() + "'";
		Long id;
		Translation translation = null;
		try {
			id = jdbcTemplate.queryForObject(sql, Long.class);
			translation = translationService.get(id).get();
		} catch (EmptyResultDataAccessException ex) {
			// No such Row
		}
		if (translation != null) {
			final String result = MessageFormat.format(translation.getTranslated(), params);
			log.info("Translate '" + key + "/" + locale + "/" + " to '" + result + "'");
			translation.updateRdate();
			translationService.update(translation);
			return result;
		} else {
			translation = new Translation();
			translation.setKey(key);
			translation.setLocale(locale.getLanguage());
			translation.setTranslated(untranslated(key));
			translationService.update(translation);
			final String result = MessageFormat.format(untranslated(key), params);
			log.info("Translate '" + key + "/" + locale + "/" + " to '" + result + "'");
			return result;
		}
	}
	private String untranslated(String key) {
		return "<" + key + ">";
	}
}
