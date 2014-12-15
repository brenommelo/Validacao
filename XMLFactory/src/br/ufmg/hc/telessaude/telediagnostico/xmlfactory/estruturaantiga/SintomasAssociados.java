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
@XStreamAlias("SINTOMASASSOCIADOS")
public class SintomasAssociados implements Serializable{
 
    private String DISPNEIA;
    
    private String SUDORESE;
    
    private String NAUSEASEOUVOMITOS;
    
    private String TONTEIRA;
    
    private String SINCOPE;
    
    private String PALPITACAO;

    public String getDISPNEIA() {
        return DISPNEIA;
    }

    public void setDISPNEIA(String DISPNEIA) {
        this.DISPNEIA = DISPNEIA;
    }

    public String getSUDORESE() {
        return SUDORESE;
    }

    public void setSUDORESE(String SUDORESE) {
        this.SUDORESE = SUDORESE;
    }

    public String getNAUSEASEOUVOMITOS() {
        return NAUSEASEOUVOMITOS;
    }

    public void setNAUSEASEOUVOMITOS(String NAUSEASEOUVOMITOS) {
        this.NAUSEASEOUVOMITOS = NAUSEASEOUVOMITOS;
    }

    public String getTONTEIRA() {
        return TONTEIRA;
    }

    public void setTONTEIRA(String TONTEIRA) {
        this.TONTEIRA = TONTEIRA;
    }

    public String getSINCOPE() {
        return SINCOPE;
    }

    public void setSINCOPE(String SINCOPE) {
        this.SINCOPE = SINCOPE;
    }

    public String getPALPITACAO() {
        return PALPITACAO;
    }

    public void setPALPITACAO(String PALPITACAO) {
        this.PALPITACAO = PALPITACAO;
    }
 
}
