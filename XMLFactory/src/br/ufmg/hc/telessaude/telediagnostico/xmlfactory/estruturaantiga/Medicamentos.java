package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga;

import java.io.Serializable;

/**
 * @author weslley.matos
 */
public class Medicamentos implements Serializable {

    private String DIURETICOS;
    private String DIGITAL;
    private String BETABLOQUEADORES;
    private String INIBIDORESDAENZIMACONVERSORA;
    private String AMIODARONA;
    private String BLOQUEADORESDECALCIO;
    private String OUTROS;
    private String NENHUM;

    public String getDIURETICOS() {
        return DIURETICOS;
    }

    public void setDIURETICOS(String DIURETICOS) {
        this.DIURETICOS = DIURETICOS;
    }

    public String getDIGITAL() {
        return DIGITAL;
    }

    public void setDIGITAL(String DIGITAL) {
        this.DIGITAL = DIGITAL;
    }

    public String getBETABLOQUEADORES() {
        return BETABLOQUEADORES;
    }

    public void setBETABLOQUEADORES(String BETABLOQUEADORES) {
        this.BETABLOQUEADORES = BETABLOQUEADORES;
    }

    public String getINIBIDORESDAENZIMACONVERSORA() {
        return INIBIDORESDAENZIMACONVERSORA;
    }

    public void setINIBIDORESDAENZIMACONVERSORA(String INIBIDORESDAENZIMACONVERSORA) {
        this.INIBIDORESDAENZIMACONVERSORA = INIBIDORESDAENZIMACONVERSORA;
    }

    public String getAMIODARONA() {
        return AMIODARONA;
    }

    public void setAMIODARONA(String AMIODARONA) {
        this.AMIODARONA = AMIODARONA;
    }

    public String getBLOQUEADORESDECALCIO() {
        return BLOQUEADORESDECALCIO;
    }

    public void setBLOQUEADORESDECALCIO(String BLOQUEADORESDECALCIO) {
        this.BLOQUEADORESDECALCIO = BLOQUEADORESDECALCIO;
    }

    public String getOUTROS() {
        return OUTROS;
    }

    public void setOUTROS(String OUTROS) {
        this.OUTROS = OUTROS;
    }

    public String getNENHUM() {
        return NENHUM;
    }

    public void setNENHUM(String NENHUM) {
        this.NENHUM = NENHUM;
    }
}
