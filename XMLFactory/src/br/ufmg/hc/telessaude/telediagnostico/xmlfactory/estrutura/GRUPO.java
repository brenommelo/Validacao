/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura;

/**
 *
 * @author igor.santos
 */
public class GRUPO {

    private String ID;
    private String DESCRICAO;
    private String DEFINICAO_ID;

    public GRUPO() {
    }

    public GRUPO(String ID, String DESCRICAO, String DEFINICAO_ID) {
        this.ID = ID;
        this.DESCRICAO = DESCRICAO;
        this.DEFINICAO_ID = DEFINICAO_ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDESCRICAO() {
        return DESCRICAO;
    }

    public void setDESCRICAO(String DESCRICAO) {
        this.DESCRICAO = DESCRICAO;
    }

    public String getDEFINICAO_ID() {
        return DEFINICAO_ID;
    }

    public void setDEFINICAO_ID(String DEFINICAO_ID) {
        this.DEFINICAO_ID = DEFINICAO_ID;
    }
}
