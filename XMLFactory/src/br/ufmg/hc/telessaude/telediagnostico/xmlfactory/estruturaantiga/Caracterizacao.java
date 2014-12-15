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
@XStreamAlias("CARACTERIZACAODADORTORACICA")
public class Caracterizacao implements Serializable{
    
    private String PROVOCADAPORESFORCOEOUEMOCAO;
    
    private String ALIVIADAPORREPOUSOOUNITRATO;
    
    private String DESCONFORTORETROESTERNAL;

    public String getPROVOCADAPORESFORCOEOUEMOCAO() {
        return PROVOCADAPORESFORCOEOUEMOCAO;
    }

    public void setPROVOCADAPORESFORCOEOUEMOCAO(String PROVOCADAPORESFORCOEOUEMOCAO) {
        this.PROVOCADAPORESFORCOEOUEMOCAO = PROVOCADAPORESFORCOEOUEMOCAO;
    }

    public String getALIVIADAPORREPOUSOOUNITRATO() {
        return ALIVIADAPORREPOUSOOUNITRATO;
    }

    public void setALIVIADAPORREPOUSOOUNITRATO(String ALIVIADAPORREPOUSOOUNITRATO) {
        this.ALIVIADAPORREPOUSOOUNITRATO = ALIVIADAPORREPOUSOOUNITRATO;
    }

    public String getDESCONFORTORETROESTERNAL() {
        return DESCONFORTORETROESTERNAL;
    }

    public void setDESCONFORTORETROESTERNAL(String DESCONFORTORETROESTERNAL) {
        this.DESCONFORTORETROESTERNAL = DESCONFORTORETROESTERNAL;
    }
    
    
}
