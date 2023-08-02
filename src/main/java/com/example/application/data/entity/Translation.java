package com.example.application.data.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.PreUpdate;

@Entity
public class Translation extends AbstractEntity {

    private String key;
    private String locale;
    private String translated;
    private LocalDateTime udate; // last update Date
    private LocalDateTime cdate; // creation Date
    private LocalDateTime rdate; // last read Date

    public Translation() {
    	cdate = LocalDateTime.now();
    	udate = cdate;
    }
    
    @PreUpdate
    public void preUpdateFunction(){
        udate = LocalDateTime.now();
    }
    
    public LocalDateTime getRdate() {
		return rdate;
	}

    public void updateRdate() {
		rdate = LocalDateTime.now();
	}

	public String getKey() {
        return key;
    }
    public LocalDateTime getUdate() {
		return udate;
	}
	public LocalDateTime getCdate() {
		return cdate;
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
