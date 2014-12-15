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
public class ExtraSistolia implements Serializable {

    private ExtraSistolesSV extraSistolesSV;
    private ESVentriculares eSVentriculares;
    private boolean polimorfas;

    public ExtraSistolesSV getExtraSistolesSV() {
        return extraSistolesSV;
    }

    public void setExtraSistolesSV(ExtraSistolesSV extraSistolesSV) {
        this.extraSistolesSV = extraSistolesSV;
    }

    public ESVentriculares geteSVentriculares() {
        return eSVentriculares;
    }

    public void seteSVentriculares(ESVentriculares eSVentriculares) {
        this.eSVentriculares = eSVentriculares;
    }

    public boolean isPolimorfas() {
        return polimorfas;
    }

    public void setPolimorfas(boolean polimorfas) {
        this.polimorfas = polimorfas;
    }
}
