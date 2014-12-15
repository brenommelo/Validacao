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
public class BloqueioRamoDireito implements Serializable {

    private boolean bloqueioIncompletoRamoDireito;
    private boolean bloqueioRamoDireito;
    private boolean bloqueioDivisionalRamoDireito;
    private boolean bdsd;
    private boolean bdid;

    public boolean isBloqueioIncompletoRamoDireito() {
        return bloqueioIncompletoRamoDireito;
    }

    public void setBloqueioIncompletoRamoDireito(boolean bloqueioIncompletoRamoDireito) {
        this.bloqueioIncompletoRamoDireito = bloqueioIncompletoRamoDireito;
    }

    public boolean isBloqueioRamoDireito() {
        return bloqueioRamoDireito;
    }

    public void setBloqueioRamoDireito(boolean bloqueioRamoDireito) {
        this.bloqueioRamoDireito = bloqueioRamoDireito;
    }

    public boolean isBloqueioDivisionalRamoDireito() {
        return bloqueioDivisionalRamoDireito;
    }

    public void setBloqueioDivisionalRamoDireito(boolean bloqueioDivisionalRamoDireito) {
        this.bloqueioDivisionalRamoDireito = bloqueioDivisionalRamoDireito;
    }

    public boolean isBdsd() {
        return bdsd;
    }

    public void setBdsd(boolean bdsd) {
        this.bdsd = bdsd;
    }

    public boolean isBdid() {
        return bdid;
    }

    public void setBdid(boolean bdid) {
        this.bdid = bdid;
    }
}
