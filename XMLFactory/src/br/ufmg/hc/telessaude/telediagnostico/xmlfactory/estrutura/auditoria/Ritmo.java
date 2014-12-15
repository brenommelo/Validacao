/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.auditoria;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;

/**
 *
 * @author igor.santos
 */
@XStreamAlias(value = "RITMO")
public class Ritmo implements Serializable {

    @XStreamAlias("RITMO_SINUSAL")
    private boolean ritmoSinusal;
    @XStreamAlias("BRACARDIA_SINUSAL")
    private boolean bracardiaSinusal;
    @XStreamAlias("RITMO_ATRIAL_ECTOPICO")
    private boolean ritmoAtrialEctopico;
    @XStreamAlias("BSA")
    private boolean bsa;
    @XStreamAlias("RITMO_IDIOVENT_ACELERADO")
    private boolean ritmoIdioventAcelerado;
    @XStreamAlias("RITMO_JUNCIONAL")
    private boolean ritmoJuncional;
    @XStreamAlias("TAQUICARDIA_SV")
    private boolean taquicardiaSV;
    @XStreamAlias("FRIBILACAO_ATRIAL")
    private boolean fribilacaoAtrial;
    @XStreamAlias("PARA_SISTOLICA")
    private boolean paraSistolica;
    @XStreamAlias("ESCAPE_VENTRICULAR")
    private boolean escapeVentricular;
    @XStreamAlias("RITMO_MARCAPASSO")
    private boolean ritmoMarcapasso;
    @XStreamAlias("TAQUICARDIA_SINUSAL")
    private boolean taquicardiaSinusal;
    @XStreamAlias("RITMO_ATRIAL_MULTIFOCAL")
    private boolean ritmoAtrialMultifocal;
    @XStreamAlias("PARADA_SINUSAL")
    private boolean paradaSinusal;
    @XStreamAlias("RITMO_IDIOVENT_ESCAPE")
    private boolean ritmoIdioventEscape;
    @XStreamAlias("TAQUICARDIA_VENTRICULAR")
    private boolean taquicardiaVentricular;
    @XStreamAlias("FLUTTER")
    private boolean flutter;
    @XStreamAlias("ESCAPE_ATRIAL")
    private boolean escapeAtrial;

    public boolean isRitmoSinusal() {
        return ritmoSinusal;
    }

    public void setRitmoSinusal(boolean ritmoSinusal) {
        this.ritmoSinusal = ritmoSinusal;
    }

    public boolean isBracardiaSinusal() {
        return bracardiaSinusal;
    }

    public void setBracardiaSinusal(boolean bracardiaSinusal) {
        this.bracardiaSinusal = bracardiaSinusal;
    }

    public boolean isRitmoAtrialEctopico() {
        return ritmoAtrialEctopico;
    }

    public void setRitmoAtrialEctopico(boolean ritmoAtrialEctopico) {
        this.ritmoAtrialEctopico = ritmoAtrialEctopico;
    }

    public boolean isBsa() {
        return bsa;
    }

    public void setBsa(boolean bsa) {
        this.bsa = bsa;
    }

    public boolean isRitmoIdioventAcelerado() {
        return ritmoIdioventAcelerado;
    }

    public void setRitmoIdioventAcelerado(boolean ritmoIdioventAcelerado) {
        this.ritmoIdioventAcelerado = ritmoIdioventAcelerado;
    }

    public boolean isRitmoJuncional() {
        return ritmoJuncional;
    }

    public void setRitmoJuncional(boolean ritmoJuncional) {
        this.ritmoJuncional = ritmoJuncional;
    }

    public boolean isTaquicardiaSV() {
        return taquicardiaSV;
    }

    public void setTaquicardiaSV(boolean taquicardiaSV) {
        this.taquicardiaSV = taquicardiaSV;
    }

    public boolean isFribilacaoAtrial() {
        return fribilacaoAtrial;
    }

    public void setFribilacaoAtrial(boolean fribilacaoAtrial) {
        this.fribilacaoAtrial = fribilacaoAtrial;
    }

    public boolean isParaSistolica() {
        return paraSistolica;
    }

    public void setParaSistolica(boolean paraSistolica) {
        this.paraSistolica = paraSistolica;
    }

    public boolean isEscapeVentricular() {
        return escapeVentricular;
    }

    public void setEscapeVentricular(boolean escapeVentricular) {
        this.escapeVentricular = escapeVentricular;
    }

    public boolean isRitmoMarcapasso() {
        return ritmoMarcapasso;
    }

    public void setRitmoMarcapasso(boolean ritmoMarcapasso) {
        this.ritmoMarcapasso = ritmoMarcapasso;
    }

    public boolean isTaquicardiaSinusal() {
        return taquicardiaSinusal;
    }

    public void setTaquicardiaSinusal(boolean taquicardiaSinusal) {
        this.taquicardiaSinusal = taquicardiaSinusal;
    }

    public boolean isRitmoAtrialMultifocal() {
        return ritmoAtrialMultifocal;
    }

    public void setRitmoAtrialMultifocal(boolean ritmoAtrialMultifocal) {
        this.ritmoAtrialMultifocal = ritmoAtrialMultifocal;
    }

    public boolean isParadaSinusal() {
        return paradaSinusal;
    }

    public void setParadaSinusal(boolean paradaSinusal) {
        this.paradaSinusal = paradaSinusal;
    }

    public boolean isRitmoIdioventEscape() {
        return ritmoIdioventEscape;
    }

    public void setRitmoIdioventEscape(boolean ritmoIdioventEscape) {
        this.ritmoIdioventEscape = ritmoIdioventEscape;
    }

    public boolean isTaquicardiaVentricular() {
        return taquicardiaVentricular;
    }

    public void setTaquicardiaVentricular(boolean taquicardiaVentricular) {
        this.taquicardiaVentricular = taquicardiaVentricular;
    }

    public boolean isFlutter() {
        return flutter;
    }

    public void setFlutter(boolean flutter) {
        this.flutter = flutter;
    }

    public boolean isEscapeAtrial() {
        return escapeAtrial;
    }

    public void setEscapeAtrial(boolean escapeAtrial) {
        this.escapeAtrial = escapeAtrial;
    }
}
