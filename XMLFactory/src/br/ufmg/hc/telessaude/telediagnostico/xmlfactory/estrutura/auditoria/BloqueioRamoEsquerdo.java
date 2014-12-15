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
public class BloqueioRamoEsquerdo implements Serializable {

    private boolean bloqueioIncompletoRamoEsquerdo;
    private boolean bloqueioRamoEsquerdo;
    private boolean bloqueioDivisionalRamoEsquerdo;
    private boolean bdas;
    private boolean bdam;
    private boolean bdpi;

    public boolean isBloqueioIncompletoRamoEsquerdo() {
        return bloqueioIncompletoRamoEsquerdo;
    }

    public void setBloqueioIncompletoRamoEsquerdo(boolean bloqueioIncompletoRamoEsquerdo) {
        this.bloqueioIncompletoRamoEsquerdo = bloqueioIncompletoRamoEsquerdo;
    }

    public boolean isBloqueioRamoEsquerdo() {
        return bloqueioRamoEsquerdo;
    }

    public void setBloqueioRamoEsquerdo(boolean bloqueioRamoEsquerdo) {
        this.bloqueioRamoEsquerdo = bloqueioRamoEsquerdo;
    }

    public boolean isBloqueioDivisionalRamoEsquerdo() {
        return bloqueioDivisionalRamoEsquerdo;
    }

    public void setBloqueioDivisionalRamoEsquerdo(boolean bloqueioDivisionalRamoEsquerdo) {
        this.bloqueioDivisionalRamoEsquerdo = bloqueioDivisionalRamoEsquerdo;
    }

    public boolean isBdas() {
        return bdas;
    }

    public void setBdas(boolean bdas) {
        this.bdas = bdas;
    }

    public boolean isBdam() {
        return bdam;
    }

    public void setBdam(boolean bdam) {
        this.bdam = bdam;
    }

    public boolean isBdpi() {
        return bdpi;
    }

    public void setBdpi(boolean bdpi) {
        this.bdpi = bdpi;
    }
}
