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
public class InterpretacaoEletrocardiograma implements Serializable {

    private LaudoDiscordante laudoDiscordante;
    private boolean laudosConcordantes;

    public LaudoDiscordante getLaudoDiscordante() {
        return laudoDiscordante;
    }

    public void setLaudoDiscordante(LaudoDiscordante laudoDiscordante) {
        this.laudoDiscordante = laudoDiscordante;
    }

    public boolean isLaudosConcordantes() {
        return laudosConcordantes;
    }

    public void setLaudosConcordantes(boolean laudosConcordantes) {
        this.laudosConcordantes = laudosConcordantes;
    }
}
