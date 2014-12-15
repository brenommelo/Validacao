package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga;

import java.io.Serializable;

/**
 * @author weslley.matos
 */
public class HistoricoClinico implements Serializable {

    private DadosClinicos DADOSCLINICOS;
    private DadosEstatisticos DADOSESTATISTICOS;
    private Solicitante SOLICITANTE;
    private Medicamentos MEDICAMENTOSEMUSO;
    private Comorbidades COMORBIDADESEFATORESEMRISCO;
    private Investigacao INVESTIGACAODADOR;
    private Hipotese HIPOTESEDIAGNOSTICA;

    public DadosClinicos getDADOSCLINICOS() {
        return DADOSCLINICOS;
    }

    public void setDADOSCLINICOS(DadosClinicos DADOSCLINICOS) {
        this.DADOSCLINICOS = DADOSCLINICOS;
    }

    public DadosEstatisticos getDADOSESTATISTICOS() {
        return DADOSESTATISTICOS;
    }

    public void setDADOSESTATISTICOS(DadosEstatisticos DADOSESTATISTICOS) {
        this.DADOSESTATISTICOS = DADOSESTATISTICOS;
    }

    public Solicitante getSOLICITANTE() {
        return SOLICITANTE;
    }

    public void setSOLICITANTE(Solicitante SOLICITANTE) {
        this.SOLICITANTE = SOLICITANTE;
    }

    public Medicamentos getMEDICAMENTOSEMUSO() {
        return MEDICAMENTOSEMUSO;
    }

    public void setMEDICAMENTOSEMUSO(Medicamentos MEDICAMENTOSEMUSO) {
        this.MEDICAMENTOSEMUSO = MEDICAMENTOSEMUSO;
    }

    public Comorbidades getCOMORBIDADESEFATORESEMRISCO() {
        return COMORBIDADESEFATORESEMRISCO;
    }

    public void setCOMORBIDADESEFATORESEMRISCO(Comorbidades COMORBIDADESEFATORESEMRISCO) {
        this.COMORBIDADESEFATORESEMRISCO = COMORBIDADESEFATORESEMRISCO;
    }

    public Investigacao getINVESTIGACAODADOR() {
        return INVESTIGACAODADOR;
    }

    public void setINVESTIGACAODADOR(Investigacao INVESTIGACAODADOR) {
        this.INVESTIGACAODADOR = INVESTIGACAODADOR;
    }

    public Hipotese getHIPOTESEDIAGNOSTICA() {
        return HIPOTESEDIAGNOSTICA;
    }

    public void setHIPOTESEDIAGNOSTICA(Hipotese HIPOTESEDIAGNOSTICA) {
        this.HIPOTESEDIAGNOSTICA = HIPOTESEDIAGNOSTICA;
    }
}
