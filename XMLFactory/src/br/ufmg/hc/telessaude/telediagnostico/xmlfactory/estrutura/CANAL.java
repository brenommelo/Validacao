/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura;

import java.io.Serializable;

/**
 *
 * @author igor.santos
 */
public class CANAL implements Serializable {

    private String NOME;
    private String GANHO;
    private String AMOSTRAS;

    public CANAL() {
    }

    public CANAL(String NOME, String GANHO, String AMOSTRAS) {
        this.NOME = NOME;
        this.GANHO = GANHO;
        this.AMOSTRAS = AMOSTRAS;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getGANHO() {
        return GANHO;
    }

    public void setGANHO(String GANHO) {
        this.GANHO = GANHO;
    }

    public String getAMOSTRAS() {
        return AMOSTRAS;
    }

    public void setAMOSTRAS(String AMOSTRAS) {
        this.AMOSTRAS = AMOSTRAS;
    }
}
