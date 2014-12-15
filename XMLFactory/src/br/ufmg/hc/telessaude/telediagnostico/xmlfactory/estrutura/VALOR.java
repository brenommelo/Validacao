/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura;

/**
 *
 * @author Igor
 */
public class VALOR {

    private String DESCRICAO;

    public VALOR() {
    }

    public VALOR(String DESCRICAO) {
        this.DESCRICAO = DESCRICAO;
    }

    public String getDESCRICAO() {
        return DESCRICAO;
    }

    public void setDESCRICAO(String DESCRICAO) {
        this.DESCRICAO = DESCRICAO;
    }
}
