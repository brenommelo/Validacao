package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga;

import java.io.Serializable;

/**
 * @author weslley.matos
 */
public class DadosEstatisticos implements Serializable {

    private String ESTADOCIVIL;
    private String ESCOLARIDADE;
    private String RENDA;
    private String ENCAMINHARPACIENTE;

    public String getESTADOCIVIL() {
        return ESTADOCIVIL;
    }

    public void setESTADOCIVIL(String ESTADOCIVIL) {
        this.ESTADOCIVIL = ESTADOCIVIL;
    }

    public String getESCOLARIDADE() {
        return ESCOLARIDADE;
    }

    public void setESCOLARIDADE(String ESCOLARIDADE) {
        this.ESCOLARIDADE = ESCOLARIDADE;
    }

    public String getRENDA() {
        return RENDA;
    }

    public void setRENDA(String RENDA) {
        this.RENDA = RENDA;
    }

    public String getENCAMINHARPACIENTE() {
        return ENCAMINHARPACIENTE;
    }

    public void setENCAMINHARPACIENTE(String ENCAMINHARPACIENTE) {
        this.ENCAMINHARPACIENTE = ENCAMINHARPACIENTE;
    }
}
