/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor.santos
 */
public class REGISTRO implements Serializable {

    private int NUMERO;
    private String DATA;
    private String HORA;
    private String VELOCIDADE;
    private String FREQUENCIACARDIACA;
    private String FILTRO60HZ;
    private String FILTROMUSCULAR;
    private List<CANAL> DERIVACOES;

    public REGISTRO() {
    }

    public REGISTRO(int NUMERO, String DATA, String HORA, String VELOCIDADE, String FREQUENCIACARDIACA, String FILTRO60HZ, String FILTROMUSCULAR, List<CANAL> DERIVACOES) {
        this.NUMERO = NUMERO;
        this.DATA = DATA;
        this.HORA = HORA;
        this.VELOCIDADE = VELOCIDADE;
        this.FREQUENCIACARDIACA = FREQUENCIACARDIACA;
        this.FILTRO60HZ = FILTRO60HZ;
        this.FILTROMUSCULAR = FILTROMUSCULAR;
        this.DERIVACOES = DERIVACOES;
    }

    public int getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(int NUMERO) {
        this.NUMERO = NUMERO;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getHORA() {
        return HORA;
    }

    public void setHORA(String HORA) {
        this.HORA = HORA;
    }

    public String getVELOCIDADE() {
        return VELOCIDADE;
    }

    public void setVELOCIDADE(String VELOCIDADE) {
        this.VELOCIDADE = VELOCIDADE;
    }

    public String getFREQUENCIACARDIACA() {
        return FREQUENCIACARDIACA;
    }

    public void setFREQUENCIACARDIACA(String FREQUENCIACARDIACA) {
        this.FREQUENCIACARDIACA = FREQUENCIACARDIACA;
    }

    public String getFILTRO60HZ() {
        return FILTRO60HZ;
    }

    public void setFILTRO60HZ(String FILTRO60HZ) {
        this.FILTRO60HZ = FILTRO60HZ;
    }

    public String getFILTROMUSCULAR() {
        return FILTROMUSCULAR;
    }

    public void setFILTROMUSCULAR(String FILTROMUSCULAR) {
        this.FILTROMUSCULAR = FILTROMUSCULAR;
    }

    public List<CANAL> getDERIVACOES() {
        if (DERIVACOES == null) {
            this.DERIVACOES = new ArrayList<CANAL>();
        }
        return DERIVACOES;
    }

    public void setDERIVACOES(List<CANAL> DERIVACOES) {
        this.DERIVACOES = DERIVACOES;
    }
}
