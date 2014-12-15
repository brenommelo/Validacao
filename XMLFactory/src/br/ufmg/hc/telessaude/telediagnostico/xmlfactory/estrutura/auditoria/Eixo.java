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
public class Eixo implements Serializable {
    private boolean desvioDeEixo;
    private Graus graus;

    public boolean isDesvioDeEixo() {
        return desvioDeEixo;
    }

    public void setDesvioDeEixo(boolean desvioDeEixo) {
        this.desvioDeEixo = desvioDeEixo;
    }

    public Graus getGraus() {
        return graus;
    }

    public void setGraus(Graus graus) {
        this.graus = graus;
    }
}
