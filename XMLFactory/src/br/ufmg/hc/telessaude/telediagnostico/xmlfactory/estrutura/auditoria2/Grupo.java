/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.auditoria2;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author igor.santos
 */
public class Grupo extends Informacao implements Serializable {

    private List<Informacao> informacoes;

    public List<Informacao> getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(List<Informacao> informacoes) {
        this.informacoes = informacoes;
    }
}
