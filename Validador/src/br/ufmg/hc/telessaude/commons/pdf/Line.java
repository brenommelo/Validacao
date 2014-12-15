/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.commons.pdf;

import com.itextpdf.text.Font;

/**
 *
 * @author igor.santos
 */
public class Line {

    private boolean text;
    private String label;
    private String content;
    private Font[] font;

    public Line(String label, String content, Font[] font, boolean text) {
        this.label = label;
        this.content = content;
        this.font = font;
        this.text = text;
    }

    public String getLabel() {
        return label;
    }

    public String getContent() {
        return content;
    }

    public Font[] getFont() {
        return font;
    }

    public boolean isText() {
        return text;
    }
}
