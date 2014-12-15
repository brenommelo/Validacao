package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga;

import java.io.Serializable;

/**
 * @author weslley.matos
 */
public class DorNaoCardiaca implements Serializable {

    private String AORTA;
    private String MEDIALINO;
    private String MUSCULOESQUELETICO;
    private String SOMATIZACAO;
    private String CUTANEA;
    private String PULMONAR;
    private String GASTROINTESTINAL;

    public String getAORTA() {
        return AORTA;
    }

    public void setAORTA(String AORTA) {
        this.AORTA = AORTA;
    }

    public String getMEDIALINO() {
        return MEDIALINO;
    }

    public void setMEDIALINO(String MEDIALINO) {
        this.MEDIALINO = MEDIALINO;
    }

    public String getMUSCULOESQUELETICO() {
        return MUSCULOESQUELETICO;
    }

    public void setMUSCULOESQUELETICO(String MUSCULOESQUELETICO) {
        this.MUSCULOESQUELETICO = MUSCULOESQUELETICO;
    }

    public String getSOMATIZACAO() {
        return SOMATIZACAO;
    }

    public void setSOMATIZACAO(String SOMATIZACAO) {
        this.SOMATIZACAO = SOMATIZACAO;
    }

    public String getCUTANEA() {
        return CUTANEA;
    }

    public void setCUTANEA(String CUTANEA) {
        this.CUTANEA = CUTANEA;
    }

    public String getPULMONAR() {
        return PULMONAR;
    }

    public void setPULMONAR(String PULMONAR) {
        this.PULMONAR = PULMONAR;
    }

    public String getGASTROINTESTINAL() {
        return GASTROINTESTINAL;
    }

    public void setGASTROINTESTINAL(String GASTROINTESTINAL) {
        this.GASTROINTESTINAL = GASTROINTESTINAL;
    }
}
