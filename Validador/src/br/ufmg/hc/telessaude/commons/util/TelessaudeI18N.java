/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.commons.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Felipe
 */
public class TelessaudeI18N {

    private static final TelessaudeI18N INSTANCE = new TelessaudeI18N();
    private static final ResourceBundle I18N = ResourceBundle.getBundle("ApplicationResources", Locale.getDefault());

    public static TelessaudeI18N getInstance() {
        return INSTANCE;
    }

    public String getText(final String key) {
        String text = key;
        if (I18N.containsKey(key)) {
            text = I18N.getString(key);
        }
        return text;
    }

    public String getText(final String key, final Object... params) {
        String text = getText(key);
        if (params != null) {
            int count = 0;
            for (final Object object : params) {
                text = text.replace("{" + count + "}", (String) object);
                ++count;
            }
        }
        return text;
    }

}
