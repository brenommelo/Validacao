package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga;

import java.io.Serializable;

/**
 * @author weslley.matos
 */
public class Investigacao implements Serializable{
    
    private DorNoExame dorNoExame;
    
    private LocalizacaoIrradiacao localizacaoIrradiacaoDaDor;
    
    private SintomasAssociados sintomasAssociados;
    
    private String CLASSIFICACAODADOR;
    
    private Intensidade intensidadeDaDor;
    
    private Caracterizacao caracterizacaoDaDorToracica;

    /**
     * @return the dorNoExame
     */
    public DorNoExame getDorNoExame() {
        if(dorNoExame == null) {
            dorNoExame = new DorNoExame();
        }
        
        return dorNoExame;
    }

    /**
     * @param dorNoExame the dorNoExame to set
     */
    public void setDorNoExame(DorNoExame dorNoExame) {
        this.dorNoExame = dorNoExame;
    }

    /**
     * @return the localizacaoIrradiacaoDaDor
     */
    public LocalizacaoIrradiacao getLocalizacaoIrradiacaoDaDor() {
        if(localizacaoIrradiacaoDaDor == null) {
            localizacaoIrradiacaoDaDor = new LocalizacaoIrradiacao();
        }
        
        return localizacaoIrradiacaoDaDor;
    }

    /**
     * @param localizacaoIrradiacaoDaDor the localizacaoIrradiacaoDaDor to set
     */
    public void setLocalizacaoIrradiacaoDaDor(LocalizacaoIrradiacao localizacaoIrradiacaoDaDor) {
        this.localizacaoIrradiacaoDaDor = localizacaoIrradiacaoDaDor;
    }

    /**
     * @return the sintomasAssociados
     */
    public SintomasAssociados getSintomasAssociados() {
        if(sintomasAssociados == null) {
            sintomasAssociados = new SintomasAssociados();
        }
        
        return sintomasAssociados;
    }

    /**
     * @param sintomasAssociados the sintomasAssociados to set
     */
    public void setSintomasAssociados(SintomasAssociados sintomasAssociados) {
        this.sintomasAssociados = sintomasAssociados;
    }

    public String getCLASSIFICACAODADOR() {        
        return CLASSIFICACAODADOR;
    }

    public void setCLASSIFICACAODADOR(String CLASSIFICACAODADOR) {
        this.CLASSIFICACAODADOR = CLASSIFICACAODADOR;
    }

    /**
     * @return the intensidadeDaDor
     */
    public Intensidade getIntensidadeDaDor() {
        if(intensidadeDaDor == null) {
            intensidadeDaDor = new Intensidade();
        }
        
        return intensidadeDaDor;
    }

    /**
     * @param intensidadeDaDor the intensidadeDaDor to set
     */
    public void setIntensidadeDaDor(Intensidade intensidadeDaDor) {
        this.intensidadeDaDor = intensidadeDaDor;
    }

    /**
     * @return the caracterizacaoDaDorToracica
     */
    public Caracterizacao getCaracterizacaoDaDorToracica() {
        if(caracterizacaoDaDorToracica == null) {
            caracterizacaoDaDorToracica = new Caracterizacao();
        }
        
        return caracterizacaoDaDorToracica;
    }

    /**
     * @param caracterizacaoDaDorToracica the caracterizacaoDaDorToracica to set
     */
    public void setCaracterizacaoDaDorToracica(Caracterizacao caracterizacaoDaDorToracica) {
        this.caracterizacaoDaDorToracica = caracterizacaoDaDorToracica;
    }
    
    
}
