/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura;

import java.io.Serializable;

/**
 *
 * @author weslley.matos
 */
public class LAUDO implements Serializable{
    
    private String DATAINICIO;
    private String DATAFIM;
    private String ESPECIALISTA;
    private String CONTEUDO;
    
    public LAUDO() {
        
    }

    public LAUDO(String DATAINICIO, String DATAFIM, String ESPECIALISTA, String CONTEUDO) {
        this.DATAINICIO = DATAINICIO;
        this.DATAFIM = DATAFIM;
        this.ESPECIALISTA = ESPECIALISTA;
        this.CONTEUDO = CONTEUDO;
    }
    /**
     * @return the DATAINICIO
     */
    public String getDATAINICIO() {
        return DATAINICIO;
    }

    /**
     * @param DATAINICIO the DATAINICIO to set
     */
    public void setDATAINICIO(String DATAINICIO) {
        this.DATAINICIO = DATAINICIO;
    }

    /**
     * @return the DATAFIM
     */
    public String getDATAFIM() {
        return DATAFIM;
    }

    /**
     * @param DATAFIM the DATAFIM to set
     */
    public void setDATAFIM(String DATAFIM) {
        this.DATAFIM = DATAFIM;
    }

    /**
     * @return the ESPECIALISTA
     */
    public String getESPECIALISTA() {
        return ESPECIALISTA;
    }

    /**
     * @param ESPECIALISTA the ESPECIALISTA to set
     */
    public void setESPECIALISTA(String ESPECIALISTA) {
        this.ESPECIALISTA = ESPECIALISTA;
    }

    /**
     * @return the CONTEUDO
     */
    public String getCONTEUDO() {
        return CONTEUDO;
    }

    /**
     * @param CONTEUDO the CONTEUDO to set
     */
    public void setCONTEUDO(String CONTEUDO) {
        this.CONTEUDO = CONTEUDO;
    }
}
