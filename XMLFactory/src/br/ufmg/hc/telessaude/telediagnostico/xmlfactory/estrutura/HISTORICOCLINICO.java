/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor.santos
 */
public class HISTORICOCLINICO {

    private String VERSION;
    private String MODELOHISTORICOCLINICO;
    private List<DADOCLINICO> DADOSCLINICOS;
    private List<GRUPO> GRUPOS;

    public HISTORICOCLINICO() {
    }

    public HISTORICOCLINICO(String VERSION, String MODELOHISTORICOCLINICO, List<DADOCLINICO> DADOSCLINICOS, List<GRUPO> GRUPOS) {
        this.VERSION = VERSION;
        this.MODELOHISTORICOCLINICO = MODELOHISTORICOCLINICO;
        this.DADOSCLINICOS = DADOSCLINICOS;
        this.GRUPOS = GRUPOS;
    }

    public List<DADOCLINICO> getDADOSCLINICOS() {
        if (DADOSCLINICOS == null) {
            DADOSCLINICOS = new ArrayList<DADOCLINICO>();
        }
        return DADOSCLINICOS;
    }

    public void setDADOSCLINICOS(List<DADOCLINICO> DADOSCLINICOS) {
        this.DADOSCLINICOS = DADOSCLINICOS;
    }

    public List<GRUPO> getGRUPOS() {
        if (GRUPOS == null) {
            GRUPOS = new ArrayList<GRUPO>();
        }
        return GRUPOS;
    }

    public void setGRUPOS(List<GRUPO> GRUPOS) {
        this.GRUPOS = GRUPOS;
    }

    public String getMODELOHISTORICOCLINICO() {
        return MODELOHISTORICOCLINICO;
    }

    public void setMODELOHISTORICOCLINICO(String MODELOHISTORICOCLINICO) {
        this.MODELOHISTORICOCLINICO = MODELOHISTORICOCLINICO;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }
}
