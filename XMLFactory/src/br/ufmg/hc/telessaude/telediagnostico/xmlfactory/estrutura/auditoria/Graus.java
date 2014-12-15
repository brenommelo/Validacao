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
public class Graus implements Serializable {

    private boolean positivo;
    private boolean negativo;

    public boolean isPositivo() {
        return positivo;
    }

    public void setPositivo(boolean positivo) {
        this.positivo = positivo;
    }

    public boolean isNegativo() {
        return negativo;
    }

    public void setNegativo(boolean negativo) {
        this.negativo = negativo;
    }
}
