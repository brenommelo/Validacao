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
public class AlteracaoPrimariaRepolorizacao implements Serializable {

    private int infra;
    private boolean ondaTnegativaSimetricaPontiaguda;
    private int supra;
    private boolean ondaTpositivaSimetricaPontiaguda;

    public int getInfra() {
        return infra;
    }

    public void setInfra(int infra) {
        this.infra = infra;
    }

    public boolean isOndaTnegativaSimetricaPontiaguda() {
        return ondaTnegativaSimetricaPontiaguda;
    }

    public void setOndaTnegativaSimetricaPontiaguda(boolean ondaTnegativaSimetricaPontiaguda) {
        this.ondaTnegativaSimetricaPontiaguda = ondaTnegativaSimetricaPontiaguda;
    }

    public int getSupra() {
        return supra;
    }

    public void setSupra(int supra) {
        this.supra = supra;
    }

    public boolean isOndaTpositivaSimetricaPontiaguda() {
        return ondaTpositivaSimetricaPontiaguda;
    }

    public void setOndaTpositivaSimetricaPontiaguda(boolean ondaTpositivaSimetricaPontiaguda) {
        this.ondaTpositivaSimetricaPontiaguda = ondaTpositivaSimetricaPontiaguda;
    }
}
