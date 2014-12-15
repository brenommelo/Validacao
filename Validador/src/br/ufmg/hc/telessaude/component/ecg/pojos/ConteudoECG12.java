/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.component.ecg.pojos;

import java.io.Serializable;

/**
 *
 * @author william.carvalho
 */
public class ConteudoECG12 implements Serializable{
    private String amostras[][][];//amostras[canal][registro][amostra]
    private String ganho[][];
    private int N_Registros;
    private float sensibilidade;
    private int amostragem;
    private int modo;
    
    public ConteudoECG12(){   
    }

    public ConteudoECG12(String[][][] amostras, String[][] ganho, int N_Registros, float sensibilidade, int amostragem, int modo) {
        this.amostras = amostras;
        this.ganho = ganho;
        this.N_Registros = N_Registros;
        this.sensibilidade = sensibilidade;
        this.amostragem = amostragem;
        this.modo = modo;
    }

    public String[][][] getAmostras() {
        return amostras;
    }

    public void setAmostras(String[][][] amostras) {
        this.amostras = amostras;
    }

    public String[][] getGanho() {
        return ganho;
    }

    public void setGanho(String[][] ganho) {
        this.ganho = ganho;
    }

    public int getN_Registros() {
        return N_Registros;
    }

    public void setN_Registros(int n_registros) {
        this.N_Registros = n_registros;
    }

    public float getSensibilidade() {
        return sensibilidade;
    }

    public void setSensibilidade(float sensibilidade) {
        this.sensibilidade = sensibilidade;
    }

    public int getAmostragem() {
        return amostragem;
    }

    public void setAmostragem(int amostragem) {
        this.amostragem = amostragem;
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

}
