package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga;

import java.io.Serializable;

/**
 * @author weslley.matos
 */
public class DadosClinicos implements Serializable {

    private String PRESSAOARTERIAL;
    private String ALTURA;
    private String PESO;
    private String IMC;
    private String OBSERVACOES;

    public String getPRESSAOARTERIAL() {
        return PRESSAOARTERIAL;
    }

    public void setPRESSAOARTERIAL(String PRESSAOARTERIAL) {
        this.PRESSAOARTERIAL = PRESSAOARTERIAL;
    }

    public String getALTURA() {
        return ALTURA;
    }

    public void setALTURA(String ALTURA) {
        this.ALTURA = ALTURA;
    }

    public String getPESO() {
        return PESO;
    }

    public void setPESO(String PESO) {
        this.PESO = PESO;
    }

    public String getIMC() {
        return IMC;
    }

    public void setIMC(String IMC) {
        this.IMC = IMC;
    }

    public String getOBSERVACOES() {
        return OBSERVACOES;
    }

    public void setOBSERVACOES(String OBSERVACOES) {
        this.OBSERVACOES = OBSERVACOES;
    }
}
