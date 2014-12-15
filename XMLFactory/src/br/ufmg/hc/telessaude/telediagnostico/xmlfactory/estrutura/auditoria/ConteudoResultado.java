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
@XStreamAlias(value = "CONTEUDO_EXAME")
public class ConteudoResultado implements Serializable {

    private Ritmo ritmo;
    private Eixo eixo;
    private ExtraSistolia extraSistolia;
    private BloqueioAV bloqueioAV;
    private BloqueioIV bloqueioIV;
    private Sobrecarga sobrecarga;
    @XStreamAlias("INATIVIDADE_ELETRICA")
    private boolean inatividadeEletrica;
    @XStreamAlias("VARIANTES_REPOLORIZACAO_VENTRICULAR_NORMAL")
    private boolean variantesRepolorizacaoVentricularNormal;
    @XStreamAlias("REPOLORIZACAO_PRECOCE")
    private boolean repolorizacaoPrecoce;
    private AlteracaoPrimariaRepolorizacao alteracaoPrimariaRepolorizacao;
    private AlteracaoSecundariaRepolorizacao alteracaoSecundariaRepolorizacao;
    private InterpretacaoEletrocardiograma interpretacaoEletrocargiograma;
    private ProblemaDetectado problemaDetectado;

    public Ritmo getRitmo() {
        return ritmo;
    }

    public void setRitmo(Ritmo ritmo) {
        this.ritmo = ritmo;
    }

    public Eixo getEixo() {
        return eixo;
    }

    public void setEixo(Eixo eixo) {
        this.eixo = eixo;
    }

    public ExtraSistolia getExtraSistolia() {
        return extraSistolia;
    }

    public void setExtraSistolia(ExtraSistolia extraSistolia) {
        this.extraSistolia = extraSistolia;
    }

    public BloqueioAV getBloqueioAV() {
        return bloqueioAV;
    }

    public void setBloqueioAV(BloqueioAV bloqueioAV) {
        this.bloqueioAV = bloqueioAV;
    }

    public BloqueioIV getBloqueioIV() {
        return bloqueioIV;
    }

    public void setBloqueioIV(BloqueioIV bloqueioIV) {
        this.bloqueioIV = bloqueioIV;
    }

    public Sobrecarga getSobrecarga() {
        return sobrecarga;
    }

    public void setSobrecarga(Sobrecarga sobrecarga) {
        this.sobrecarga = sobrecarga;
    }

    public boolean isInatividadeEletrica() {
        return inatividadeEletrica;
    }

    public void setInatividadeEletrica(boolean inatividadeEletrica) {
        this.inatividadeEletrica = inatividadeEletrica;
    }

    public boolean isVariantesRepolorizacaoVentricularNormal() {
        return variantesRepolorizacaoVentricularNormal;
    }

    public void setVariantesRepolorizacaoVentricularNormal(boolean variantesRepolorizacaoVentricularNormal) {
        this.variantesRepolorizacaoVentricularNormal = variantesRepolorizacaoVentricularNormal;
    }

    public boolean isRepolorizacaoPrecoce() {
        return repolorizacaoPrecoce;
    }

    public void setRepolorizacaoPrecoce(boolean repolorizacaoPrecoce) {
        this.repolorizacaoPrecoce = repolorizacaoPrecoce;
    }

    public AlteracaoPrimariaRepolorizacao getAlteracaoPrimariaRepolorizacao() {
        return alteracaoPrimariaRepolorizacao;
    }

    public void setAlteracaoPrimariaRepolorizacao(AlteracaoPrimariaRepolorizacao alteracaoPrimariaRepolorizacao) {
        this.alteracaoPrimariaRepolorizacao = alteracaoPrimariaRepolorizacao;
    }

    public AlteracaoSecundariaRepolorizacao getAlteracaoSecundariaRepolorizacao() {
        return alteracaoSecundariaRepolorizacao;
    }

    public void setAlteracaoSecundariaRepolorizacao(AlteracaoSecundariaRepolorizacao alteracaoSecundariaRepolorizacao) {
        this.alteracaoSecundariaRepolorizacao = alteracaoSecundariaRepolorizacao;
    }

    public InterpretacaoEletrocardiograma getInterpretacaoEletrocargiograma() {
        return interpretacaoEletrocargiograma;
    }

    public void setInterpretacaoEletrocargiograma(InterpretacaoEletrocardiograma interpretacaoEletrocargiograma) {
        this.interpretacaoEletrocargiograma = interpretacaoEletrocargiograma;
    }

    public ProblemaDetectado getProblemaDetectado() {
        return problemaDetectado;
    }

    public void setProblemaDetectado(ProblemaDetectado problemaDetectado) {
        this.problemaDetectado = problemaDetectado;
    }
    
}
