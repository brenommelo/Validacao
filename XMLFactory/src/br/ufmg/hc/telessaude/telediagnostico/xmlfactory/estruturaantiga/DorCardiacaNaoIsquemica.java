package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga;

import java.io.Serializable;

/**
 * @author weslley.matos
 */
public class DorCardiacaNaoIsquemica implements Serializable {

    private String MIOCARDITE;
    private String PERICARDITE;
    private String DOENCAVALVULAR;
    private String OUTRO;

    public String getMIOCARDITE() {
        return MIOCARDITE;
    }

    public void setMIOCARDITE(String MIOCARDITE) {
        this.MIOCARDITE = MIOCARDITE;
    }

    public String getPERICARDITE() {
        return PERICARDITE;
    }

    public void setPERICARDITE(String PERICARDITE) {
        this.PERICARDITE = PERICARDITE;
    }

    public String getDOENCAVALVULAR() {
        return DOENCAVALVULAR;
    }

    public void setDOENCAVALVULAR(String DOENCAVALVULAR) {
        this.DOENCAVALVULAR = DOENCAVALVULAR;
    }

    public String getOUTRO() {
        return OUTRO;
    }

    public void setOUTRO(String OUTRO) {
        this.OUTRO = OUTRO;
    }
}
