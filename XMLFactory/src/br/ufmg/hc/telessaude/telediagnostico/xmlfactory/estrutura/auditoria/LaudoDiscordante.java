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
public class LaudoDiscordante implements Serializable {

    private boolean laudo1Correto;
    private boolean laudo2Correto;
    private boolean ambosIncorretos;
    private boolean possivelAlteracaoCondutaMedica;
    private boolean semAlteracaoCondutaMedica;

    public boolean isLaudo1Correto() {
        return laudo1Correto;
    }

    public void setLaudo1Correto(boolean laudo1Correto) {
        this.laudo1Correto = laudo1Correto;
    }

    public boolean isLaudo2Correto() {
        return laudo2Correto;
    }

    public void setLaudo2Correto(boolean laudo2Correto) {
        this.laudo2Correto = laudo2Correto;
    }

    public boolean isAmbosIncorretos() {
        return ambosIncorretos;
    }

    public void setAmbosIncorretos(boolean ambosIncorretos) {
        this.ambosIncorretos = ambosIncorretos;
    }

    public boolean isPossivelAlteracaoCondutaMedica() {
        return possivelAlteracaoCondutaMedica;
    }

    public void setPossivelAlteracaoCondutaMedica(boolean possivelAlteracaoCondutaMedica) {
        this.possivelAlteracaoCondutaMedica = possivelAlteracaoCondutaMedica;
    }

    public boolean isSemAlteracaoCondutaMedica() {
        return semAlteracaoCondutaMedica;
    }

    public void setSemAlteracaoCondutaMedica(boolean semAlteracaoCondutaMedica) {
        this.semAlteracaoCondutaMedica = semAlteracaoCondutaMedica;
    }
}
