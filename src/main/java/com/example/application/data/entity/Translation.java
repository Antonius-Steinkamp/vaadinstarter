package com.example.application.data.entity;

import jakarta.persistence.Entity;

@Entity
public class Translation extends AbstractEntity {

    private String key;
    private String locale;
    private String translated;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getLocale() {
        return locale;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }
    public String getTranslated() {
        return translated;
    }
    public void setTranslated(String translated) {
        this.translated = translated;
    }

}
