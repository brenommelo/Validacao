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
public class AlteracaoSecundariaRepolorizacao implements Serializable {

    private boolean ondaTnegativaAssimetrica;
    private boolean baixaVoltagem;
    private boolean intervaloQTcLongo;
    private boolean intervaloPRCurto;
    private boolean preExcitacaoVentricular;

    public boolean isOndaTnegativaAssimetrica() {
        return ondaTnegativaAssimetrica;
    }

    public void setOndaTnegativaAssimetrica(boolean ondaTnegativaAssimetrica) {
        this.ondaTnegativaAssimetrica = ondaTnegativaAssimetrica;
    }

    public boolean isBaixaVoltagem() {
        return baixaVoltagem;
    }

    public void setBaixaVoltagem(boolean baixaVoltagem) {
        this.baixaVoltagem = baixaVoltagem;
    }

    public boolean isIntervaloQTcLongo() {
        return intervaloQTcLongo;
    }

    public void setIntervaloQTcLongo(boolean intervaloQTcLongo) {
        this.intervaloQTcLongo = intervaloQTcLongo;
    }

    public boolean isIntervaloPRCurto() {
        return intervaloPRCurto;
    }

    public void setIntervaloPRCurto(boolean intervaloPRCurto) {
        this.intervaloPRCurto = intervaloPRCurto;
    }

    public boolean isPreExcitacaoVentricular() {
        return preExcitacaoVentricular;
    }

    public void setPreExcitacaoVentricular(boolean preExcitacaoVentricular) {
        this.preExcitacaoVentricular = preExcitacaoVentricular;
    }
}
