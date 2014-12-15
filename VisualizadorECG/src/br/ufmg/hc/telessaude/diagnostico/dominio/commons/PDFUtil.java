/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.diagnostico.dominio.commons;

import br.ufmg.hc.telessaude.commons.pdf.Header;
import br.ufmg.hc.telessaude.commons.pdf.PDF;
import br.ufmg.hc.telessaude.commons.util.StringUtil;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.ConteudoExame;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.Exame;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.HistoricoClinico;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.Laudo;
import br.ufmg.hc.telessaude.exception.PDFException;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.CONTEUDOEXAME;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.DADOCLINICO;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.GRUPO;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.HISTORICOCLINICO;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.LAUDO;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.gerador.XMLFactory;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.FontFactoryImp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor.santos
 */
public class PDFUtil extends PDF {

    // listas que serao trabalhadas
    private List historicos;
    private List conteudos;
    private List laudos;
    private Long modelo;

    public PDFUtil(Exame exame, int scale, float speed) throws PDFException {
        /////--- construtor PDF, cria e abre document, e gera cabe�alho
        super(exame.getPaciente().getNome().replace(" ", "") + ".pdf",
                new Header(exame.getPontoRemoto().getNome(),
                        StringUtil.format(exame.getDatarealizacao(), "dd/MM/yyyy HH:mm:ss"),
                        exame.getPaciente().getNome(),
                        String.valueOf(exame.getPaciente().getSexo()),
                        StringUtil.format(exame.getPaciente().getDatanascimento(), "dd/MM/yyyy")));

        /// comencando a trabalhar com historico clinico ///
        historicos = new ArrayList<>();
        for (HistoricoClinico historicoClinico : exame.getHistoricosClinicos()) {
            XMLFactory<HISTORICOCLINICO> historicoClinicoFactory = new XMLFactory<>();
            if (historicoClinico.getModeloHistoricoClinico().getId() == 1L) {
                modelo = 1L;
                historicos.add(historicoClinicoFactory.gerarHistoricoClinicoXMLAntigo(historicoClinico.getConteudo(), "UTF-8"));
            } else {
                modelo = 2L;
                historicos.add(historicoClinicoFactory.gerarEstrutura(historicoClinico.getConteudo(), "UTF-8"));
            }
        }

        /// comecando a trabalhar com conteudo exame ///
        conteudos = new ArrayList<>();
        for (ConteudoExame conteudoExame : exame.getConteudosExames()) {
            XMLFactory<CONTEUDOEXAME> conteudoExameFactory = new XMLFactory<>();
            if (conteudoExame.getModeloConteudoExame().getId() == 1L) {
                if (conteudoExame.getConteudo().contains("<REGISTRO>")) {
                    conteudos.add(conteudoExameFactory.gerarConteudoExameXMLAntigo(conteudoExame.getConteudo(), "UTF-8"));
                } else {
                    throw new PDFException("Traçado corrompido, verificar exame com a Central !");
                }
            } else {
                conteudos.add(conteudoExameFactory.gerarEstrutura(conteudoExame.getConteudo(), "UTF-8"));
            }
        }

        /// comecando a trabalhar com laudo ///
        laudos = new ArrayList<>();
        if (exame.getLaudos() != null) {
            for (Laudo laudo : exame.getLaudos()) {
                XMLFactory<LAUDO> laudoFactory = new XMLFactory<>();
                laudos.add(laudoFactory.gerarEstrutura(laudo.getConteudo(), "UTF-8"));
            }
        }

        /////--- escreve historico clinico
        for (int i = 0; i < historicos.size(); i++) {
            if (modelo == 1L) {
                writeHistoricoClinico((br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.HistoricoClinico) historicos.get(i));
            } else {
                writeHistoricoClinico((HISTORICOCLINICO) historicos.get(i));
            }
        }

        /////--- escreve laudo
        if (!laudos.isEmpty()) {
            for (int i = 0; i < laudos.size(); i++) {
                writeLaudo((LAUDO) laudos.get(i));
            }
        } else {
            writeLaudo(null);
        }

        /////--- desenha tra�ado no pdf
        for (int i = 0; i < conteudos.size(); i++) {
            printTracing((CONTEUDOEXAME) conteudos.get(i), exame.getPaciente().getNome(), exame.getDatarealizacao(), scale, speed);
        }

        /////--- necessario fechar o documento
        closeDocument();
    }

    public PDFUtil(String str) throws PDFException {
//        Gson gson = new Gson();
//        Exame exame = gson.fromJson(str, Exame.class);
        Exame exame = null;

        /////--- construtor PDF, cria e abre document, e gera cabe�alho
        super.setPDF(exame.getPaciente().getNome().replace(" ", "") + ".pdf",
                new Header(exame.getPontoRemoto().getNome(),
                        StringUtil.format(exame.getDatarealizacao(), "dd/MM/yyyy HH:mm:ss"),
                        exame.getPaciente().getNome(),
                        String.valueOf(exame.getPaciente().getSexo()),
                        StringUtil.format(exame.getPaciente().getDatanascimento(), "dd/MM/yyyy")));

        /// comencando a trabalhar com historico clinico ///
        historicos = new ArrayList<>();
        for (HistoricoClinico historicoClinico : exame.getHistoricosClinicos()) {
            XMLFactory<HISTORICOCLINICO> historicoClinicoFactory = new XMLFactory<>();
            if (historicoClinico.getModeloHistoricoClinico().getId() == 1L) {
                modelo = 1L;
                historicos.add(historicoClinicoFactory.gerarHistoricoClinicoXMLAntigo(historicoClinico.getConteudo(), "UTF-8"));
            } else {
                modelo = 2L;
                historicos.add(historicoClinicoFactory.gerarEstrutura(historicoClinico.getConteudo(), "UTF-8"));
            }
        }

        /// comecando a trabalhar com conteudo exame ///
        conteudos = new ArrayList<>();
        for (ConteudoExame conteudoExame : exame.getConteudosExames()) {
            XMLFactory<CONTEUDOEXAME> conteudoExameFactory = new XMLFactory<>();
            if (conteudoExame.getModeloConteudoExame().getId() == 1L) {
                if (conteudoExame.getConteudo().contains("<REGISTRO>")) {
                    conteudos.add(conteudoExameFactory.gerarConteudoExameXMLAntigo(conteudoExame.getConteudo(), "UTF-8"));
                } else {
                    throw new PDFException("Traçado corrompido, verificar exame com a Central !");
                }
            } else {
                conteudos.add(conteudoExameFactory.gerarEstrutura(conteudoExame.getConteudo(), "UTF-8"));
            }
        }

        /// comecando a trabalhar com laudo ///
        laudos = new ArrayList<>();
        if (exame.getLaudos() != null) {
            for (Laudo laudo : exame.getLaudos()) {
                XMLFactory<LAUDO> laudoFactory = new XMLFactory<>();
                laudos.add(laudoFactory.gerarEstrutura(laudo.getConteudo(), "UTF-8"));
            }
        }

        /////--- escreve historico clinico
        for (int i = 0; i < historicos.size(); i++) {
            if (modelo == 1L) {
                writeHistoricoClinico((br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.HistoricoClinico) historicos.get(i));
            } else {
                writeHistoricoClinico((HISTORICOCLINICO) historicos.get(i));
            }
        }

        /////--- escreve laudo
        if (!laudos.isEmpty()) {
            for (int i = 0; i < laudos.size(); i++) {
                writeLaudo((LAUDO) laudos.get(i));
            }
        } else {
            writeLaudo(null);
        }

        /////--- desenha tra�ado no pdf
        for (int i = 0; i < conteudos.size(); i++) {
            printTracing((CONTEUDOEXAME) conteudos.get(i), exame.getPaciente().getNome(), exame.getDatarealizacao(), 10, 25);
        }

        /////--- necessario fechar o documento
        closeDocument();
    }

    private void writeHistoricoClinico(HISTORICOCLINICO historico) {
        List<DADOCLINICO> dadosClinicos = historico.getDADOSCLINICOS();
        List<DADOCLINICO> dadosClinicosRemove = new ArrayList<>();
        for (GRUPO grupo : historico.getGRUPOS()) {
            addSubtitle(grupo.getDESCRICAO().trim());
            for (DADOCLINICO dado : dadosClinicos) {
                if (grupo.getID().equals(dado.getGRUPO_ID())) {
                    if (dado.getVALOR().equals("TRUE")) {
                        addLine(true, "", dado.getDESCRICAO());
                    } else if (!dado.getVALOR().equals("FALSE")) {
                        if (!dado.getVALOR().isEmpty()) {
                            addLine(true, dado.getDESCRICAO(), dado.getVALOR());
                        }
                    }
                    dadosClinicosRemove.add(dado);
                }
            }
            dadosClinicos.removeAll(dadosClinicosRemove);
            dadosClinicosRemove.clear();
        }
        createContent("HISTÓRICO CLINICO");
    }

    private void writeHistoricoClinico(br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.HistoricoClinico historico) {
        addSubtitle("Dados Clínicos");
        addLine(true, "Pressão Arterial", historico.getDADOSCLINICOS().getPRESSAOARTERIAL());
        addLine(false, "Altura: ", historico.getDADOSCLINICOS().getALTURA());
        addLine(true, "Peso: ", historico.getDADOSCLINICOS().getPESO());
        addLine(true, "IMC: ", historico.getDADOSCLINICOS().getIMC());
        addLine(true, "Observação: ", historico.getDADOSCLINICOS().getOBSERVACOES());

        addSubtitle("Comorbidades e Fatores de Risco");
        addLine(false, "Obesidade: ", historico.getCOMORBIDADESEFATORESEMRISCO().getOBESIDADE());
        addLine(true, "Tabagismo: ", historico.getCOMORBIDADESEFATORESEMRISCO().getTABAGISMO());
        addLine(false, "Hipertensão Arterial: ", historico.getCOMORBIDADESEFATORESEMRISCO().getHIPERTENCAOARTERIAL());
        addLine(true, "Doença Pulmonar Crônica: ", historico.getCOMORBIDADESEFATORESEMRISCO().getDOENCAPULMONARCRONICA());
        addLine(false, "Infarto Miocárdio Prévio: ", historico.getCOMORBIDADESEFATORESEMRISCO().getINFARTOMIOCARDIOPREVIO());
        addLine(true, "Diabetes Mellitus: ", historico.getCOMORBIDADESEFATORESEMRISCO().getDIABETESMELLITUS());
        addLine(false, "Doença Renal Crônica: ", historico.getCOMORBIDADESEFATORESEMRISCO().getDOENCARENALCRONICA());
        addLine(true, "Dislipidemia: ", historico.getCOMORBIDADESEFATORESEMRISCO().getDISLIPIDEMIA());
        addLine(false, "Doença de Chagas: ", historico.getCOMORBIDADESEFATORESEMRISCO().getDOENCADECHAGAS());
        addLine(true, "Histórico Familiar Doença Coronariana: ", historico.getCOMORBIDADESEFATORESEMRISCO().getHISTORICOFAMILIARDOENCACORONARIANA());
        addLine(false, "Revascularização Miocárdica Prévia: ", historico.getCOMORBIDADESEFATORESEMRISCO().getREVASCULARIZACAMIOCARDICAPREVIA());

        addSubtitle("Hipótese Diagnostica");

        addSubtitle("Dor Cardiaca Isquêmica");
        addLine(false, "Infarto: ", historico.getHIPOTESEDIAGNOSTICA().getDORCARDIACAISQUEMICA().getINFARTO());
        addLine(true, "Angina Estável: ", historico.getHIPOTESEDIAGNOSTICA().getDORCARDIACAISQUEMICA().getANGINAESTAVEL());
        addLine(true, "Angina Instável: ", historico.getHIPOTESEDIAGNOSTICA().getDORCARDIACAISQUEMICA().getANGINAINSTAVEL());

        addSubtitle("Dor Cardíaca Não Isquêmica");
        addLine(false, "Miocardite: ", historico.getHIPOTESEDIAGNOSTICA().getDORCARDIACANAOISQUEMICA().getMIOCARDITE());
        addLine(true, "Pericardite: ", historico.getHIPOTESEDIAGNOSTICA().getDORCARDIACANAOISQUEMICA().getPERICARDITE());
        addLine(false, "Doença Valvular: ", historico.getHIPOTESEDIAGNOSTICA().getDORCARDIACANAOISQUEMICA().getDOENCAVALVULAR());
        addLine(true, "Outro: ", historico.getHIPOTESEDIAGNOSTICA().getDORCARDIACANAOISQUEMICA().getOUTRO());

        addSubtitle("Dor Não Cardíaca");
        addLine(false, "Aorta: ", historico.getHIPOTESEDIAGNOSTICA().getDORNAOCARDIACA().getAORTA());
        addLine(true, "Somatização: ", historico.getHIPOTESEDIAGNOSTICA().getDORNAOCARDIACA().getSOMATIZACAO());
        addLine(false, "Gastro Intestinal: ", historico.getHIPOTESEDIAGNOSTICA().getDORNAOCARDIACA().getGASTROINTESTINAL());
        addLine(true, "Medialino: ", historico.getHIPOTESEDIAGNOSTICA().getDORNAOCARDIACA().getMEDIALINO());
        addLine(false, "Cutânea: ", historico.getHIPOTESEDIAGNOSTICA().getDORNAOCARDIACA().getCUTANEA());
        addLine(true, "Pulmonar: ", historico.getHIPOTESEDIAGNOSTICA().getDORNAOCARDIACA().getPULMONAR());
        addLine(true, "Músculo Esquelético: ", historico.getHIPOTESEDIAGNOSTICA().getDORNAOCARDIACA().getMUSCULOESQUELETICO());

        addSubtitle("Motivos Exame Sem Dor");
        addLine(false, "Aorta: ", historico.getHIPOTESEDIAGNOSTICA().getMOTIVOEXAMESEMDOR().getAORTA());
        addLine(true, "Somatização: ", historico.getHIPOTESEDIAGNOSTICA().getMOTIVOEXAMESEMDOR().getSOMATIZACAO());
        addLine(false, "Gastro Intestinal: ", historico.getHIPOTESEDIAGNOSTICA().getMOTIVOEXAMESEMDOR().getGASTROINTESTINAL());
        addLine(true, "Medialino: ", historico.getHIPOTESEDIAGNOSTICA().getMOTIVOEXAMESEMDOR().getMEDIALINO());
        addLine(false, "Cutânea: ", historico.getHIPOTESEDIAGNOSTICA().getMOTIVOEXAMESEMDOR().getCUTANEA());
        addLine(true, "Pulmonar: ", historico.getHIPOTESEDIAGNOSTICA().getMOTIVOEXAMESEMDOR().getPULMONAR());
        addLine(true, "Músculo Esquelético: ", historico.getHIPOTESEDIAGNOSTICA().getMOTIVOEXAMESEMDOR().getMUSCULOESQUELETICO());

        addSubtitle("Observações");
        addLine(true, "Observação: ", historico.getHIPOTESEDIAGNOSTICA().getOBSERVACOES());

        if (historico.getINVESTIGACAODADOR() != null) {
            addSubtitle("Investigação Da Dor");

            addSubtitle("Localização Irradiação Da Dor");
            addLine(false, "Membro Superior Direito: ", historico.getINVESTIGACAODADOR().getLocalizacaoIrradiacaoDaDor().getMEMBROSUPERIORDIREITO());
            addLine(true, "Dorso: ", historico.getINVESTIGACAODADOR().getLocalizacaoIrradiacaoDaDor().getDORSO());
            addLine(false, "Região Precordial: ", historico.getINVESTIGACAODADOR().getLocalizacaoIrradiacaoDaDor().getREGIAOPRECORDIAL());
            addLine(true, "Membro Superior Esquerdo: ", historico.getINVESTIGACAODADOR().getLocalizacaoIrradiacaoDaDor().getMEMBROSUPERIORESQUERDO());
            addLine(false, "Pescoço: ", historico.getINVESTIGACAODADOR().getLocalizacaoIrradiacaoDaDor().getPESCOCO());
            addLine(true, "Epigástrio: ", historico.getINVESTIGACAODADOR().getLocalizacaoIrradiacaoDaDor().getEPIGASTRIO());
            addLine(true, "Região Torácica Direita: ", historico.getINVESTIGACAODADOR().getLocalizacaoIrradiacaoDaDor().getREGIAOTORACICADIREITA());
            addSubtitle("Sintomas Associados");
            addLine(false, "Dispneia: ", historico.getINVESTIGACAODADOR().getSintomasAssociados().getDISPNEIA());
            addLine(true, "Tonteira: ", historico.getINVESTIGACAODADOR().getSintomasAssociados().getTONTEIRA());
            addLine(false, "Palpitação: ", historico.getINVESTIGACAODADOR().getSintomasAssociados().getPALPITACAO());
            addLine(true, "Sudorese: ", historico.getINVESTIGACAODADOR().getSintomasAssociados().getSUDORESE());
            addLine(false, "Sincope: ", historico.getINVESTIGACAODADOR().getSintomasAssociados().getSINCOPE());
            addLine(true, "Nausea ou Vômitos: ", historico.getINVESTIGACAODADOR().getSintomasAssociados().getNAUSEASEOUVOMITOS());

            addSubtitle("Classificação Da Dor");
            addLine(true, "Nível: ", historico.getINVESTIGACAODADOR().getIntensidadeDaDor().getNIVEL());

            addSubtitle("Caracterização Da Dor Torácica");
            addLine(false, "Provocada Por Esforço/Emoção: ", historico.getINVESTIGACAODADOR().getCaracterizacaoDaDorToracica().getPROVOCADAPORESFORCOEOUEMOCAO());
            addLine(true, "Aliviada por Repouso/Unitrato: ", historico.getINVESTIGACAODADOR().getCaracterizacaoDaDorToracica().getALIVIADAPORREPOUSOOUNITRATO());
            addLine(true, "Desconforto Retro Esternal: ", historico.getINVESTIGACAODADOR().getCaracterizacaoDaDorToracica().getDESCONFORTORETROESTERNAL());
        }

        addSubtitle("Medicamentos Em Uso");
        addLine(false, "Diureticos: ", historico.getMEDICAMENTOSEMUSO().getDIURETICOS());
        addLine(true, "Inibidoras Da Enzima: ", historico.getMEDICAMENTOSEMUSO().getINIBIDORESDAENZIMACONVERSORA());
        addLine(false, "Amiodarona: ", historico.getMEDICAMENTOSEMUSO().getAMIODARONA());
        addLine(true, "Digital: ", historico.getMEDICAMENTOSEMUSO().getDIGITAL());
        addLine(false, "Betabloqueadores: ", historico.getMEDICAMENTOSEMUSO().getBETABLOQUEADORES());
        addLine(true, "Betabloqueadores de Calcio: ", historico.getMEDICAMENTOSEMUSO().getBLOQUEADORESDECALCIO());
        addLine(true, "Outros: ", historico.getMEDICAMENTOSEMUSO().getOUTROS());

        createContent("HISTÓRICO CLINICO");
    }

    private void writeLaudo(LAUDO laudo) {
        if (laudo != null) {
            addLine(true, "Especialista:", laudo.getESPECIALISTA());
            addLine(true, "Data / Hora Laudo:", laudo.getDATAFIM());
            addText("Resultado", laudo.getCONTEUDO(), new FontFactoryImp().getFont(FontFactory.COURIER), new FontFactoryImp().getFont(FontFactory.COURIER));
        } else {
            addText("Resultado", "Em processamento.");
        }
        createContent("LAUDO");

    }
}
