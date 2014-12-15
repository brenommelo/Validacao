/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura;

/**
 *
 * @author Igor
 */
public class STYLE {

    private int COLOR;
    private int WIDTH;
    private int HEIGHT;
    private float SIZE;
    private int FONT;
    private float MARGIN;
    private float PADDING;

    public STYLE() {
    }

    public STYLE(int COLOR, int WIDTH, int HEIGHT, float SIZE, int FONT, float MARGIN, float PADDING) {
        this.COLOR = COLOR;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.SIZE = SIZE;
        this.FONT = FONT;
        this.MARGIN = MARGIN;
        this.PADDING = PADDING;
    }

    public int getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(int COLOR) {
        this.COLOR = COLOR;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public float getSIZE() {
        return SIZE;
    }

    public void setSIZE(float SIZE) {
        this.SIZE = SIZE;
    }

    public int getFONT() {
        return FONT;
    }

    public void setFONT(int FONT) {
        this.FONT = FONT;
    }

    public float getMARGIN() {
        return MARGIN;
    }

    public void setMARGIN(float MARGIN) {
        this.MARGIN = MARGIN;
    }

    public float getPADDING() {
        return PADDING;
    }

    public void setPADDING(float PADDING) {
        this.PADDING = PADDING;
    }
}
