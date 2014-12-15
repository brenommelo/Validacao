package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga;

import java.io.Serializable;

/**
 * @author weslley.matos
 */
public class Solicitante implements Serializable {

    private String NOME;
    private String CRM;
    private String FUNCAO;

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public String getFUNCAO() {
        return FUNCAO;
    }

    public void setFUNCAO(String FUNCAO) {
        this.FUNCAO = FUNCAO;
    }
}
