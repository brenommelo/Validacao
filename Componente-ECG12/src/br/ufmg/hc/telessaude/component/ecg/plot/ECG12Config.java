/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.component.ecg.plot;

import java.awt.Color;

/**
 *
 * @author paulo.gomes
 */
public class ECG12Config {

    private Color c1 ;
    private Color c2 ;
    private int N_Registros;
    private int Reg_Sel;
    private float Escala;
    private float Sensibilidade;
    private int Modo;
    private int Amostragem;//Hertz
    private double energia ;
    private double media;
    private boolean interferencia;
    private boolean linhabase;
    private int width, height;

    public ECG12Config() {
        c1 = new Color(255, 204, 204);
        c2 = new Color(255, 128, 128);
        N_Registros = 0;
        Reg_Sel = 1;
        Escala = (float) 10;
        Sensibilidade = (float) 3.9;
        Modo = 0;
        Amostragem = 300; //Hertz
        energia = 0;
        media = 0;
        interferencia = false;
        linhabase = false;
        width = 1215;
        height = 755;
//        height = 855;
    }

    public Color getC1() {
        return c1;
    }

    public void setC1(Color c1) {
        this.c1 = c1;
    }

    public Color getC2() {
        return c2;
    }

    public void setC2(Color c2) {
        this.c2 = c2;
    }

    public int getN_Registros() {
        return N_Registros;
    }

    public void setN_Registros(int N_Registros) {
        this.N_Registros = N_Registros;
    }

    public int getReg_Sel() {
        return Reg_Sel;
    }

    public void setReg_Sel(int Reg_Sel) {
        this.Reg_Sel = Reg_Sel;
    }

    public float getEscala() {
        return Escala;
    }

    public void setEscala(float Escala) {
        this.Escala = Escala;
    }

    public float getSensibilidade() {
        return Sensibilidade;
    }

    public void setSensibilidade(float Sensibilidade) {
        this.Sensibilidade = Sensibilidade;
    }

    public int getModo() {
        return Modo;
    }

    public void setModo(int Modo) {
        this.Modo = Modo;
    }

    public int getAmostragem() {
        return Amostragem;
    }

    public void setAmostragem(int Amostragem) {
        this.Amostragem = Amostragem;
    }

    public double getEnergia() {
        return energia;
    }

    public void setEnergia(double energia) {
        this.energia = energia;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public boolean isInterferencia() {
        return interferencia;
    }

    public void setInterferencia(boolean interferencia) {
        this.interferencia = interferencia;
    }

    public boolean isLinhabase() {
        return linhabase;
    }

    public void setLinhabase(boolean linhabase) {
        this.linhabase = linhabase;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
