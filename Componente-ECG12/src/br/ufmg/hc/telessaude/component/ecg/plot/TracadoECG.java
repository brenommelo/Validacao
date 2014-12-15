/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.component.ecg.plot;

import java.util.Arrays;

/**
 *
 * @author paulo.gomes
 */
public class TracadoECG {

    int tamanho;
    private int DI[];
    private int DII[];
    private int DIII[];
    private int aVR[];
    private int aVL[];
    private int aVF[];
    private int V1_10[];
    private int V2_10[];
    private int V3_10[];
    private int V4_10[];
    private int V5_10[];
    private int V6_10[];

    public TracadoECG(int amostragem) {
        // quantidade de amostragens( Hertz ) em 12 amostras
        tamanho = amostragem * 12;
        tamanho +=1;
        init();
    }

    public TracadoECG(int amostragem, int quantAmostras) {
        // amostragens( Hertz ) vezes a quantidade de amostras
        tamanho = amostragem * quantAmostras;
        tamanho +=1;
        init();
    }

    public void init() {
        DI = new int[tamanho];
        DII = new int[tamanho];
        DIII = new int[tamanho];
        aVR = new int[tamanho];
        aVL = new int[tamanho];
        aVF = new int[tamanho];
        V1_10 = new int[tamanho];
        V2_10 = new int[tamanho];
        V3_10 = new int[tamanho];
        V4_10 = new int[tamanho];
        V5_10 = new int[tamanho];
        V6_10 = new int[tamanho];
    }

    public void preencherAmostras(int[][] Derivacoes){
        int tamanhoAux = Derivacoes[0].length;
        DI = Arrays.copyOf(Derivacoes[0], tamanhoAux);
        DII = Arrays.copyOf(Derivacoes[1], tamanhoAux);
        DIII = Arrays.copyOf(Derivacoes[2], tamanhoAux);
        aVR = Arrays.copyOf(Derivacoes[3], tamanhoAux);
        aVL = Arrays.copyOf(Derivacoes[4], tamanhoAux);
        aVF = Arrays.copyOf(Derivacoes[5], tamanhoAux);
        V1_10 = Arrays.copyOf(Derivacoes[6], tamanhoAux);
        V2_10 = Arrays.copyOf(Derivacoes[7], tamanhoAux);
        V3_10 = Arrays.copyOf(Derivacoes[8], tamanhoAux);
        V4_10 = Arrays.copyOf(Derivacoes[9], tamanhoAux);
        V5_10 = Arrays.copyOf(Derivacoes[10], tamanhoAux);
        V6_10 = Arrays.copyOf(Derivacoes[11], tamanhoAux);
    }
    
    public int getTamanho() {
        return tamanho;
    }

    public int[] DI() {
        return DI;
    }

    public int[] DII() {
        return DII;
    }

    public int[] DIII() {
        return DIII;
    }

    public int[] aVR() {
        return aVR;
    }

    public int[] aVL() {
        return aVL;
    }

    public int[] aVF() {
        return aVF;
    }

    public int[] V1_10() {
        return V1_10;
    }

    public int[] V2_10() {
        return V2_10;
    }

    public int[] V3_10() {
        return V3_10;
    }

    public int[] V4_10() {
        return V4_10;
    }

    public int[] V5_10() {
        return V5_10;
    }

    public int[] V6_10() {
        return V6_10;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setDI(int[] DI) {
        this.DI = DI;
    }

    public void setDII(int[] DII) {
        this.DII = DII;
    }

    public void setDIII(int[] DIII) {
        this.DIII = DIII;
    }

    public void setaVR(int[] aVR) {
        this.aVR = aVR;
    }

    public void setaVL(int[] aVL) {
        this.aVL = aVL;
    }

    public void setaVF(int[] aVF) {
        this.aVF = aVF;
    }

    public void setV1_10(int[] V1_10) {
        this.V1_10 = V1_10;
    }

    public void setV2_10(int[] V2_10) {
        this.V2_10 = V2_10;
    }

    public void setV3_10(int[] V3_10) {
        this.V3_10 = V3_10;
    }

    public void setV4_10(int[] V4_10) {
        this.V4_10 = V4_10;
    }

    public void setV5_10(int[] V5_10) {
        this.V5_10 = V5_10;
    }

    public void setV6_10(int[] V6_10) {
        this.V6_10 = V6_10;
    }
}
