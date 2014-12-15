package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga;

import java.io.Serializable;

/**
 * @author weslley.matos
 */
public class Comorbidades implements Serializable {

    private String HIPERTENCAOARTERIAL;
    private String DOENCADECHAGAS;
    private String OBESIDADE;
    private String DIABETESMELLITUS;
    private String TABAGISMO;
    private String REVASCULARIZACAMIOCARDICAPREVIA;
    private String HISTORICOFAMILIARDOENCACORONARIANA;
    private String INFARTOMIOCARDIOPREVIO;
    private String DISLIPIDEMIA;
    private String DOENCARENALCRONICA;
    private String DOENCAPULMONARCRONICA;

    public String getHIPERTENCAOARTERIAL() {
        return HIPERTENCAOARTERIAL;
    }

    public void setHIPERTENCAOARTERIAL(String HIPERTENCAOARTERIAL) {
        this.HIPERTENCAOARTERIAL = HIPERTENCAOARTERIAL;
    }

    public String getDOENCADECHAGAS() {
        return DOENCADECHAGAS;
    }

    public void setDOENCADECHAGAS(String DOENCADECHAGAS) {
        this.DOENCADECHAGAS = DOENCADECHAGAS;
    }

    public String getOBESIDADE() {
        return OBESIDADE;
    }

    public void setOBESIDADE(String OBESIDADE) {
        this.OBESIDADE = OBESIDADE;
    }

    public String getDIABETESMELLITUS() {
        return DIABETESMELLITUS;
    }

    public void setDIABETESMELLITUS(String DIABETESMELLITUS) {
        this.DIABETESMELLITUS = DIABETESMELLITUS;
    }

    public String getTABAGISMO() {
        return TABAGISMO;
    }

    public void setTABAGISMO(String TABAGISMO) {
        this.TABAGISMO = TABAGISMO;
    }

    public String getREVASCULARIZACAMIOCARDICAPREVIA() {
        return REVASCULARIZACAMIOCARDICAPREVIA;
    }

    public void setREVASCULARIZACAMIOCARDICAPREVIA(String REVASCULARIZACAMIOCARDICAPREVIA) {
        this.REVASCULARIZACAMIOCARDICAPREVIA = REVASCULARIZACAMIOCARDICAPREVIA;
    }

    public String getHISTORICOFAMILIARDOENCACORONARIANA() {
        return HISTORICOFAMILIARDOENCACORONARIANA;
    }

    public void setHISTORICOFAMILIARDOENCACORONARIANA(String HISTORICOFAMILIARDOENCACORONARIANA) {
        this.HISTORICOFAMILIARDOENCACORONARIANA = HISTORICOFAMILIARDOENCACORONARIANA;
    }

    public String getINFARTOMIOCARDIOPREVIO() {
        return INFARTOMIOCARDIOPREVIO;
    }

    public void setINFARTOMIOCARDIOPREVIO(String INFARTOMIOCARDIOPREVIO) {
        this.INFARTOMIOCARDIOPREVIO = INFARTOMIOCARDIOPREVIO;
    }

    public String getDISLIPIDEMIA() {
        return DISLIPIDEMIA;
    }

    public void setDISLIPIDEMIA(String DISLIPIDEMIA) {
        this.DISLIPIDEMIA = DISLIPIDEMIA;
    }

    public String getDOENCARENALCRONICA() {
        return DOENCARENALCRONICA;
    }

    public void setDOENCARENALCRONICA(String DOENCARENALCRONICA) {
        this.DOENCARENALCRONICA = DOENCARENALCRONICA;
    }

    public String getDOENCAPULMONARCRONICA() {
        return DOENCAPULMONARCRONICA;
    }

    public void setDOENCAPULMONARCRONICA(String DOENCAPULMONARCRONICA) {
        this.DOENCAPULMONARCRONICA = DOENCAPULMONARCRONICA;
    }
}
