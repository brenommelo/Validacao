package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga;

import java.io.Serializable;

/**
 * @author weslley.matos
 */
public class Hipotese implements Serializable {

    private DorCardiacaIsquemica DORCARDIACAISQUEMICA;
    private DorCardiacaNaoIsquemica DORCARDIACANAOISQUEMICA;
    private DorNaoCardiaca DORNAOCARDIACA;
    private MotivoExameSemDor MOTIVOEXAMESEMDOR;
    private String OBSERVACOES;

    public DorCardiacaIsquemica getDORCARDIACAISQUEMICA() {
        return DORCARDIACAISQUEMICA;
    }

    public void setDORCARDIACAISQUEMICA(DorCardiacaIsquemica DORCARDIACAISQUEMICA) {
        this.DORCARDIACAISQUEMICA = DORCARDIACAISQUEMICA;
    }

    public DorCardiacaNaoIsquemica getDORCARDIACANAOISQUEMICA() {
        return DORCARDIACANAOISQUEMICA;
    }

    public void setDORCARDIACANAOISQUEMICA(DorCardiacaNaoIsquemica DORCARDIACANAOISQUEMICA) {
        this.DORCARDIACANAOISQUEMICA = DORCARDIACANAOISQUEMICA;
    }

    public DorNaoCardiaca getDORNAOCARDIACA() {
        return DORNAOCARDIACA;
    }

    public void setDORNAOCARDIACA(DorNaoCardiaca DORNAOCARDIACA) {
        this.DORNAOCARDIACA = DORNAOCARDIACA;
    }

    public MotivoExameSemDor getMOTIVOEXAMESEMDOR() {
        return MOTIVOEXAMESEMDOR;
    }

    public void setMOTIVOEXAMESEMDOR(MotivoExameSemDor MOTIVOEXAMESEMDOR) {
        this.MOTIVOEXAMESEMDOR = MOTIVOEXAMESEMDOR;
    }

    public String getOBSERVACOES() {
        return OBSERVACOES;
    }

    public void setOBSERVACOES(String OBSERVACOES) {
        this.OBSERVACOES = OBSERVACOES;
    }
}
