package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga;

import java.io.Serializable;

/**
 * @author weslley.matos
 */
public class DorCardiacaIsquemica implements Serializable {

    private String INFARTO;
    private String ANGINAINSTAVEL;
    private String ANGINAESTAVEL;

    public String getINFARTO() {
        return INFARTO;
    }

    public void setINFARTO(String INFARTO) {
        this.INFARTO = INFARTO;
    }

    public String getANGINAINSTAVEL() {
        return ANGINAINSTAVEL;
    }

    public void setANGINAINSTAVEL(String ANGINAINSTAVEL) {
        this.ANGINAINSTAVEL = ANGINAINSTAVEL;
    }

    public String getANGINAESTAVEL() {
        return ANGINAESTAVEL;
    }

    public void setANGINAESTAVEL(String ANGINAESTAVEL) {
        this.ANGINAESTAVEL = ANGINAESTAVEL;
    }
}
