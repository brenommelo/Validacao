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
@XStreamAlias("DORNOEXAME")
public class DorNoExame implements Serializable{
    
    private String DATAULTIMOEPISODIO;
    
    private String DURACAOULTIMOEPISODIO;
       
    private String HAQUANTOTEMPOAPRESENTAVASINTOMAS;

    /**
     * @return the dataUltimoEpisodio
     */
    public String getDATAULTIMOEPISODIO() {
        return DATAULTIMOEPISODIO;
    }

    /**
     * @param dataUltimoEpisodio the dataUltimoEpisodio to set
     */
    public void setDATAULTIMOEPISODIO(String DATAULTIMOEPISODIO) {
        this.DATAULTIMOEPISODIO = DATAULTIMOEPISODIO;
    }

    /**
     * @return the duracaoUltimoEpisodio
     */
    public String getDURACAOULTIMOEPISODIO() {
        return DURACAOULTIMOEPISODIO;
    }

    /**
     * @param duracaoUltimoEpisodio the duracaoUltimoEpisodio to set
     */
    public void setDURACAOULTIMOEPISODIO(String DURACAOULTIMOEPISODIO) {
        this.DURACAOULTIMOEPISODIO = DURACAOULTIMOEPISODIO;
    }

    /**
     * @return the haQuantoTempoApresentavaSintomas
     */
    public String getHAQUANTOTEMPOAPRESENTAVASINTOMAS() {
        return HAQUANTOTEMPOAPRESENTAVASINTOMAS;
    }

    /**
     * @param haQuantoTempoApresentavaSintomas the haQuantoTempoApresentavaSintomas to set
     */
    public void setHAQUANTOTEMPOAPRESENTAVASINTOMAS(String HAQUANTOTEMPOAPRESENTAVASINTOMAS) {
        this.HAQUANTOTEMPOAPRESENTAVASINTOMAS = HAQUANTOTEMPOAPRESENTAVASINTOMAS;
    }

    
}
