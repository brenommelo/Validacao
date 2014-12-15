/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura;

/**
 *
 * @author igor.santos
 */
public class DADOCLINICO {

    private String ID;
    private String DESCRICAO;
    private String VALOR;
    private String GRUPO_ID;
    private String DEFINICAO_ID;

    public DADOCLINICO() {
    }

    public DADOCLINICO(String ID, String DESCRICAO, String VALOR, String GRUPO_ID, String DEFINICAO_ID) {
        this.ID = ID;
        this.DESCRICAO = DESCRICAO;
        this.VALOR = VALOR;
        this.GRUPO_ID = GRUPO_ID;
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

    public String getVALOR() {
        return VALOR;
    }

    public void setVALOR(String VALOR) {
        this.VALOR = VALOR;
    }

    public String getGRUPO_ID() {
        return GRUPO_ID;
    }

    public void setGRUPO_ID(String GRUPO_ID) {
        this.GRUPO_ID = GRUPO_ID;
    }

    public String getDEFINICAO() {
        return DEFINICAO_ID;
    }

    public void setDEFINICAO(String DEFINICAO_ID) {
        this.DEFINICAO_ID = DEFINICAO_ID;
    }
}
