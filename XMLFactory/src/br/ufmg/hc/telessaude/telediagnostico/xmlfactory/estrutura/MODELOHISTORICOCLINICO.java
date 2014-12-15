/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Igor
 */
public class MODELOHISTORICOCLINICO {

    private String ID;
    private String VERSION;
    private List<DEFINICAO> DEFINICOES;
    private List<STYLE> STYLES;

    public MODELOHISTORICOCLINICO() {
    }

    public MODELOHISTORICOCLINICO(String ID, String VERSION, List<DEFINICAO> DEFINICOES, List<STYLE> STYLES) {
        this.ID = ID;
        this.VERSION = VERSION;
        this.DEFINICOES = DEFINICOES;
        this.STYLES = STYLES;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public List<DEFINICAO> getDEFINICOES() {
        if (DEFINICOES == null) {
            DEFINICOES = new ArrayList<DEFINICAO>();
        }
        return DEFINICOES;
    }

    public void setDEFINICOES(List<DEFINICAO> DEFINICOES) {
        this.DEFINICOES = DEFINICOES;
    }

    public List<STYLE> getSTYLES() {
        if (STYLES == null) {
            STYLES = new ArrayList<STYLE>();
        }
        return STYLES;
    }

    public void setSTYLES(List<STYLE> STYLES) {
        this.STYLES = STYLES;
    }
}
