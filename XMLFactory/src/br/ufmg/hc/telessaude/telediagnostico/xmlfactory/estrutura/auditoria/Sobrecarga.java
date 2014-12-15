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
public class Sobrecarga implements Serializable {

    private boolean sae;
    private boolean svd;
    private boolean sve;
    private boolean sba;
    private boolean sad;
    private boolean sbv;

    public boolean isSae() {
        return sae;
    }

    public void setSae(boolean sae) {
        this.sae = sae;
    }

    public boolean isSvd() {
        return svd;
    }

    public void setSvd(boolean svd) {
        this.svd = svd;
    }

    public boolean isSve() {
        return sve;
    }

    public void setSve(boolean sve) {
        this.sve = sve;
    }

    public boolean isSba() {
        return sba;
    }

    public void setSba(boolean sba) {
        this.sba = sba;
    }

    public boolean isSad() {
        return sad;
    }

    public void setSad(boolean sad) {
        this.sad = sad;
    }

    public boolean isSbv() {
        return sbv;
    }

    public void setSbv(boolean sbv) {
        this.sbv = sbv;
    }
}
