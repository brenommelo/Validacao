/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;

/**
 *
 * @author weslley.matos
 */
@XStreamAlias("LOCALIZACAOIRRADIACAODADOR")
public class LocalizacaoIrradiacao implements Serializable{
    
    private String MEMBROSUPERIORDIREITO;
    
    private String MEMBROSUPERIORESQUERDO;
    
    private String PESCOCO;
    
    private String DORSO;
    
    private String REGIAOPRECORDIAL;
    
    private String REGIAOTORACICADIREITA;
    
    private String EPIGASTRIO;

    /**
     * @return the membroSuperiorDireito
     */
    public String getMEMBROSUPERIORDIREITO() {
        return MEMBROSUPERIORDIREITO;
    }

    /**
     * @param membroSuperiorDireito the membroSuperiorDireito to set
     */
    public void setMEMBROSUPERIORDIREITO(String MEMBROSUPERIORDIREITO) {
        this.MEMBROSUPERIORDIREITO = MEMBROSUPERIORDIREITO;
    }

    /**
     * @return the membroSuperiorEsquerdo
     */
    public String getMEMBROSUPERIORESQUERDO() {
        return MEMBROSUPERIORESQUERDO;
    }

    /**
     * @param membroSuperiorEsquerdo the membroSuperiorEsquerdo to set
     */
    public void setMEMBROSUPERIORESQUERDO(String MEMBROSUPERIORESQUERDO) {
        this.MEMBROSUPERIORESQUERDO = MEMBROSUPERIORESQUERDO;
    }

    /**
     * @return the pescoco
     */
    public String getPESCOCO() {
        return PESCOCO;
    }

    /**
     * @param pescoco the pescoco to set
     */
    public void setPESCOCO(String PESCOCO) {
        this.PESCOCO = PESCOCO;
    }

    /**
     * @return the dorso
     */
    public String getDORSO() {
        return DORSO;
    }

    /**
     * @param dorso the dorso to set
     */
    public void setDORSO(String DORSO) {
        this.DORSO = DORSO;
    }

    /**
     * @return the regiaoPrecordial
     */
    public String getREGIAOPRECORDIAL() {
        return REGIAOPRECORDIAL;
    }

    /**
     * @param regiaoPrecordial the regiaoPrecordial to set
     */
    public void setREGIAOPRECORDIAL(String REGIAOPRECORDIAL) {
        this.REGIAOPRECORDIAL = REGIAOPRECORDIAL;
    }

    /**
     * @return the regiaoToraxicaDireita
     */
    public String getREGIAOTORACICADIREITA() {
        return REGIAOTORACICADIREITA;
    }

    /**
     * @param regiaoToraxicaDireita the regiaoToraxicaDireita to set
     */
    public void setREGIAOTORACICADIREITA(String REGIAOTORACICADIREITA) {
        this.REGIAOTORACICADIREITA = REGIAOTORACICADIREITA;
    }

    /**
     * @return the epigastrio
     */
    public String getEPIGASTRIO() {
        return EPIGASTRIO;
    }

    /**
     * @param epigastrio the epigastrio to set
     */
    public void setEPIGASTRIO(String EPIGASTRIO) {
        this.EPIGASTRIO = EPIGASTRIO;
    }
    
}
