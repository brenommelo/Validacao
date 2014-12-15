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
public class BloqueioIV implements Serializable {

    private BloqueioRamoEsquerdo bloqueioRamoEsquerdo;
    private BloqueioRamoDireito bloqueioRamoDireito;

    public BloqueioRamoEsquerdo getBloqueioRamoEsquerdo() {
        return bloqueioRamoEsquerdo;
    }

    public void setBloqueioRamoEsquerdo(BloqueioRamoEsquerdo bloqueioRamoEsquerdo) {
        this.bloqueioRamoEsquerdo = bloqueioRamoEsquerdo;
    }

    public BloqueioRamoDireito getBloqueioRamoDireito() {
        return bloqueioRamoDireito;
    }

    public void setBloqueioRamoDireito(BloqueioRamoDireito bloqueioRamoDireito) {
        this.bloqueioRamoDireito = bloqueioRamoDireito;
    }
}
