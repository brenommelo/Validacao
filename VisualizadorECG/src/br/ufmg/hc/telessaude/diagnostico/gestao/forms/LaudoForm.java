/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.diagnostico.gestao.forms;

import br.ufmg.hc.telessaude.component.ecg.plot.ECG12Plot;
import br.ufmg.hc.telessaude.component.ecg.pojos.ConteudoECG12;
import br.ufmg.hc.telessaude.diagnostico.dominio.commons.Acao;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.DadosClinicos;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.Exame;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.Laudo;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.CONTEUDOEXAME;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.REGISTRO;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.gerador.XMLFactory;
import java.util.Date;
import javax.swing.JDesktopPane;

/**
 *
 * @author William
 */
public class LaudoForm {

    private ConteudoLaudoForm conteudoLaudo;
    private GlasgowForm glassgowLaudo;
    private DadosClinicosForm dadosClinicos;
    private ECG12Plot ecgPlot;
    protected Laudo laudo;
    private final JDesktopPane desktop;
    public PrincipalForm referencia;
    private final Date tempoLatenciaInicial;
    private boolean visible;
    

    public LaudoForm(final JDesktopPane desktop,
            final Acao acao,
            final Laudo laudo,
            final Date tempoLatenciaInicial,
            final PrincipalForm principal) throws Exception {
        this.desktop = desktop;
        this.laudo = laudo;
        this.tempoLatenciaInicial = tempoLatenciaInicial;
        referencia = principal;

//        if (laudo == null) {
//            return;
//        }
        visible = true;
        iniciarFormsLaudo();
    }

    private void ExibirTracado() throws Exception {
        if (ecgPlot == null) {
            ecgPlot = new ECG12Plot(desktop, processarConteudoExame(laudo.getExame()), laudo.getExame().getPaciente().getNome());
        } else if (ecgPlot.isShowing()) {
            try {
                ecgPlot.setSelected(true);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        } else if (ecgPlot.isIcon()) {
            try {
                ecgPlot.setIcon(false);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
        ecgPlot.setSize((desktop.getWidth() - 600), desktop.getHeight() - 200);
        ecgPlot.setLocation(0, 0);
        ecgPlot.setClosable(false);
        ecgPlot.setMaximizable(true);
        ecgPlot.setVisible(true);
    }

    private void ExibirDadosCLinicos() throws Exception {
        if (dadosClinicos == null) {
            dadosClinicos = new DadosClinicosForm(desktop, DadosClinicos.preencherDadosClinicos(laudo.getExame()));
            //dadosClinicos = new DadosClinicosForm(desktop, dados);
            //dadosClinicos.setReferencia(this);
        } else if (dadosClinicos.isShowing()) {
            try {
                dadosClinicos.setSelected(true);
            } catch (Exception ex) {
            }
        } else if (dadosClinicos.isIcon()) {
            try {
                dadosClinicos.setIcon(false);
            } catch (Exception ex) {
            }
        }

        dadosClinicos.setSize(600, desktop.getHeight() / 2);
        dadosClinicos.setLocation((desktop.getWidth() - 600), 0);
        dadosClinicos.setVisible(true);
        if (laudo.getExame().getPrioridade().getNome().equals("URGENCIA")) {
            //JOptionPane.showMessageDialog(null, "Este Exame � um Exame de Urg�ncia.", "Exame de Urg�ncia", JOptionPane.INFORMATION_MESSAGE);
            dadosClinicos.setUrgente(true);
        }
    }

    private void ExibirCadastroLaudo() {
        if (conteudoLaudo == null) {
            conteudoLaudo = new ConteudoLaudoForm(desktop, laudo, Acao.NULA, this);
            //cadastroLaudo.setReferencia(this);
            conteudoLaudo.execute(Acao.NULA);
        } else if (conteudoLaudo.isShowing()) {
            try {
                conteudoLaudo.setSelected(true);
            } catch (Exception ex) {
            }
        } else if (conteudoLaudo.isIcon()) {
            try {
                conteudoLaudo.setIcon(false);
            } catch (Exception ex) {
            }
        }
        conteudoLaudo.setSize(600, desktop.getHeight() / 2);
        conteudoLaudo.setLocation((desktop.getWidth() - 600), desktop.getHeight() / 2);
        conteudoLaudo.setVisible(true);
    }

    private void ExibirGlasgow() {
        if (glassgowLaudo == null) {
            glassgowLaudo = new GlasgowForm(desktop, this);
            //cadastroLaudo.setReferencia(this);
            glassgowLaudo.execute(Acao.NULA);
        } else if (glassgowLaudo.isShowing()) {
            try {
                glassgowLaudo.setSelected(true);
            } catch (Exception ex) {
            }
        } else if (glassgowLaudo.isIcon()) {
            try {
                glassgowLaudo.setIcon(false);
            } catch (Exception ex) {
            }
        }
        glassgowLaudo.setSize((desktop.getWidth() - 600), 200);
        glassgowLaudo.setLocation(0, desktop.getHeight() - 200);
        glassgowLaudo.setVisible(true);
    }

    private void iniciarFormsLaudo() throws Exception {
        //this.setSize(desktop.getWidth(), desktop.getHeight());
        ExibirTracado();
        ExibirDadosCLinicos();
        ExibirCadastroLaudo();
        ExibirGlasgow();
    }

    public void fecharJanela() {
        System.gc();

        conteudoLaudo.dispose();
        dadosClinicos.dispose();
        glassgowLaudo.dispose();
        ecgPlot.dispose();
        referencia.tooglePanel();

        conteudoLaudo = null;
        dadosClinicos = null;
        ecgPlot = null;
        laudo = null;
        referencia = null;
        visible = false;

        System.gc();
    }

    private ConteudoECG12 processarConteudoExame(Exame exame) throws Exception {
        if (exame.getConteudosExames() != null && exame.getConteudosExames().size() > 0) {
            ConteudoECG12 conteudoEcg = new ConteudoECG12();
            String b[][][] = null;
            String ganho[][] = null;

            XMLFactory<CONTEUDOEXAME> factory = new XMLFactory<>();
            CONTEUDOEXAME conteudo = null;
            Integer versaoModeloConteudoExame = exame.getConteudosExames().get(0).getModeloConteudoExame().getId();
            if (versaoModeloConteudoExame == 1) {
                conteudo = factory.gerarConteudoExameXMLAntigo(exame.getConteudosExames().get(0).getConteudo(), "UTF-8");
            } else if (versaoModeloConteudoExame == 2) {
                conteudo = factory.gerarEstrutura(exame.getConteudosExames().get(0).getConteudo(), "UTF-8");
            }

            conteudoEcg.setN_Registros(conteudo.getQUANTIDADE());
            b = new String[12][conteudo.getQUANTIDADE()][2200];
            ganho = new String[12][conteudo.getQUANTIDADE()];

            if (!conteudo.getTAXAAMOSTRAGEM().isEmpty()) {
                conteudoEcg.setAmostragem(Integer.parseInt(retornaNumero(conteudo.getTAXAAMOSTRAGEM())));
            }

            if (!conteudo.getSENSIBILIDADE().isEmpty()) {
                conteudoEcg.setSensibilidade(Float.parseFloat(retornaNumero(conteudo.getSENSIBILIDADE())));
            }

            int cont = 0;
            String[] str;

            // Ideia para substituir o codigo abaixo
            for (REGISTRO r : conteudo.getREGISTROS()) {
                for (int i = 0; i < b.length; i++) {
                    str = formatString(r.getDERIVACOES().get(i).getAMOSTRAS()).split(";");
                    b[i][cont] = str;
                    ganho[i][cont] = r.getDERIVACOES().get(i).getGANHO();
                }
                cont++;
            }

            conteudoEcg.setAmostras(b);
            conteudoEcg.setGanho(ganho);
            return conteudoEcg;
        } else {
            return null;
        }
    }

    private String formatString(String string) {
        return string.replaceAll("[^0-9\\-\\;]", "");
    }

    private String retornaNumero(String string) {
        return string.replaceAll("[^0-9\\.]", "");
    }

    public void dispose() {
        if (ecgPlot != null) {
            ecgPlot.dispose();
        }
        if (dadosClinicos != null) {
            dadosClinicos.dispose();
        }
        if (conteudoLaudo != null) {
            conteudoLaudo.dispose();
        }
        if (glassgowLaudo != null) {
            glassgowLaudo.dispose();
        }
        if (referencia != null) {
            referencia.tooglePanel();
        }
        visible = false;
    }

    public void setReferencia(PrincipalForm referencia) {
        this.referencia = referencia;
    }

    public boolean isVisible() {
        return visible;
    }

    
}
