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
public class CONTEUDOEXAME implements Serializable {

    private int QUANTIDADE;
    private String TAXAAMOSTRAGEM;
    private String SENSIBILIDADE;
    private List<REGISTRO> REGISTROS;

    public CONTEUDOEXAME() {
    }

    public CONTEUDOEXAME(int QUANTIDADE, String TAXAAMOSTRAGEM, String SENSIBILIDADE, List<REGISTRO> REGISTROS) {
        this.QUANTIDADE = QUANTIDADE;
        this.TAXAAMOSTRAGEM = TAXAAMOSTRAGEM;
        this.SENSIBILIDADE = SENSIBILIDADE;
        this.REGISTROS = REGISTROS;
    }

    public int getQUANTIDADE() {
        return QUANTIDADE;
    }

    public void setQUANTIDADE(int QUANTIDADE) {
        this.QUANTIDADE = QUANTIDADE;
    }

    public String getTAXAAMOSTRAGEM() {
        return TAXAAMOSTRAGEM;
    }

    public void setTAXAAMOSTRAGEM(String TAXAAMOSTRAGEM) {
        this.TAXAAMOSTRAGEM = TAXAAMOSTRAGEM;
    }

    public String getSENSIBILIDADE() {
        return SENSIBILIDADE;
    }

    public void setSENSIBILIDADE(String SENSIBILIDADE) {
        this.SENSIBILIDADE = SENSIBILIDADE;
    }

    public List<REGISTRO> getREGISTROS() {
        if (REGISTROS == null) {
            this.REGISTROS = new ArrayList<REGISTRO>();
        }
        return REGISTROS;
    }

    public void setREGISTROS(List<REGISTRO> REGISTROS) {
        this.REGISTROS = REGISTROS;
    }
}
