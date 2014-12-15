/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.auditoria;

import java.io.Serializable;

/**
 *
 * @author igor.santos
 */
public class BloqueioAV implements Serializable {

    private boolean bav1grau;
    private boolean bav2grau;
    private boolean bavt;
    private boolean mobitzI;
    private boolean bav21;
    private boolean mobitzII;
    private boolean bavParoxistico;
    private boolean bavAvancado;

    public boolean isBav1grau() {
        return bav1grau;
    }

    public void setBav1grau(boolean bav1grau) {
        this.bav1grau = bav1grau;
    }

    public boolean isBav2grau() {
        return bav2grau;
    }

    public void setBav2grau(boolean bav2grau) {
        this.bav2grau = bav2grau;
    }

    public boolean isBavt() {
        return bavt;
    }

    public void setBavt(boolean bavt) {
        this.bavt = bavt;
    }

    public boolean isMobitzI() {
        return mobitzI;
    }

    public void setMobitzI(boolean mobitzI) {
        this.mobitzI = mobitzI;
    }

    public boolean isBav21() {
        return bav21;
    }

    public void setBav21(boolean bav21) {
        this.bav21 = bav21;
    }

    public boolean isMobitzII() {
        return mobitzII;
    }

    public void setMobitzII(boolean mobitzII) {
        this.mobitzII = mobitzII;
    }

    public boolean isBavParoxistico() {
        return bavParoxistico;
    }

    public void setBavParoxistico(boolean bavParoxistico) {
        this.bavParoxistico = bavParoxistico;
    }

    public boolean isBavAvancado() {
        return bavAvancado;
    }

    public void setBavAvancado(boolean bavAvancado) {
        this.bavAvancado = bavAvancado;
    }
}
