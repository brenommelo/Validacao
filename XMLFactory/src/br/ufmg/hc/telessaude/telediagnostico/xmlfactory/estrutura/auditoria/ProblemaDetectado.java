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
public class ProblemaDetectado implements Serializable {

    private boolean dadosClinicosIncorretos;
    private boolean posicionamentoIncorretoEletrodos;
    private boolean interferenciasTracadoEletrocardiografico;

    public boolean isDadosClinicosIncorretos() {
        return dadosClinicosIncorretos;
    }

    public void setDadosClinicosIncorretos(boolean dadosClinicosIncorretos) {
        this.dadosClinicosIncorretos = dadosClinicosIncorretos;
    }

    public boolean isPosicionamentoIncorretoEletrodos() {
        return posicionamentoIncorretoEletrodos;
    }

    public void setPosicionamentoIncorretoEletrodos(boolean posicionamentoIncorretoEletrodos) {
        this.posicionamentoIncorretoEletrodos = posicionamentoIncorretoEletrodos;
    }

    public boolean isInterferenciasTracadoEletrocardiografico() {
        return interferenciasTracadoEletrocardiografico;
    }

    public void setInterferenciasTracadoEletrocardiografico(boolean interferenciasTracadoEletrocardiografico) {
        this.interferenciasTracadoEletrocardiografico = interferenciasTracadoEletrocardiografico;
    }
}
