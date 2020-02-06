package com.gsafety.dawn.community.backend.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Locale;

/**
 * Created by numsg
 */
public class AppInit implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${app.language}")
    private String language;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent()==null)
        {
            setLocale();
        }
    }

    /**
     * Sets locale by config language.
     *
     * @return the locale
     */
    public String setLocale() {
        Locale locale = new Locale("en", "US");
        String[] lan = language.split("_");
        if (lan.length > 1) {
            locale = new Locale(lan[0], lan[1]);
        }
        Locale.setDefault(locale);
        return null;
    }
}
