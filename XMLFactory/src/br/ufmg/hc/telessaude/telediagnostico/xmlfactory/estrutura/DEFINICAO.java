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
public class DEFINICAO {

    private String ID;
    private String ID_STYLE;
    private String LABEL;
    private String TIPO;
    private String DEFAULT;
    private List<DEFINICAO> COMPONENTES;
    private List<VALOR> VALORES;

    public DEFINICAO() {
    }

    public DEFINICAO(String ID, String ID_STYLE, String LABEL, String TIPO, String DEFAULT, List<DEFINICAO> COMPONENTES, List<VALOR> VALORES) {
        this.ID = ID;
        this.ID_STYLE = ID_STYLE;
        this.LABEL = LABEL;
        this.TIPO = TIPO;
        this.DEFAULT = DEFAULT;
        this.COMPONENTES = COMPONENTES;
        this.VALORES = VALORES;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID_STYLE() {
        return ID_STYLE;
    }

    public void setID_STYLE(String ID_STYLE) {
        this.ID_STYLE = ID_STYLE;
    }

    public String getLABEL() {
        return LABEL;
    }

    public void setLABEL(String LABEL) {
        this.LABEL = LABEL;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public String getDEFAULT() {
        return DEFAULT;
    }

    public void setDEFAULT(String DEFAULT) {
        this.DEFAULT = DEFAULT;
    }

    public List<DEFINICAO> getCOMPONENTES() {
        if (COMPONENTES == null) {
            COMPONENTES = new ArrayList<DEFINICAO>();
        }
        return COMPONENTES;
    }

    public void setCOMPONENTES(List<DEFINICAO> COMPONENTES) {
        this.COMPONENTES = COMPONENTES;
    }

    public List<VALOR> getVALORES() {
        if (VALORES == null) {
            VALORES = new ArrayList<VALOR>();
        }
        return VALORES;
    }

    public void setVALORES(List<VALOR> VALORES) {
        this.VALORES = VALORES;
    }
}
