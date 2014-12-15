/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.gerador;

import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.*;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.Caracterizacao;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.Comorbidades;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.DadosClinicos;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.DorCardiacaIsquemica;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.DorCardiacaNaoIsquemica;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.DorNaoCardiaca;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.DorNoExame;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.Hipotese;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.HistoricoClinico;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.Intensidade;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.Investigacao;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.LocalizacaoIrradiacao;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.Medicamentos;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.MotivoExameSemDor;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.SintomasAssociados;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estruturaantiga.Solicitante;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 *
 * @author Igor
 */
public class XMLFactory<T> {

    /**
     * Método da clase XMLFactory utilizado para gerar um xml a partir de uma
     * estrutura pré-definida em seu fonte.
     *
     * @param estrutura objeto de estrutura do xml, modelos disponiveis -
     * MODELOHISTORICOCLINICO.class, HISTORICOCLINICO.class, CONTEUDOEXAME.class
     * @param encoding formato em que será codificado o xml.
     * @return um objeto String com o conteudo gerado em forma de xml.
     */
    public String gerarXML(T estrutura, String encoding) {
        XStream stream = new XStream(new DomDriver(encoding));

        // MODELO HISTORICO CLINICO
        stream.alias("MODELOHISTORICOCLINICO", MODELOHISTORICOCLINICO.class);
        stream.alias("DEFINICAO", DEFINICAO.class);
        stream.alias("VALOR", VALOR.class);
        stream.alias("STYLE", STYLE.class);
        // HISTORICO CLINICO
        stream.alias("HISTORICOCLINICO", HISTORICOCLINICO.class);
        stream.alias("DADOCLINICO", DADOCLINICO.class);
        stream.alias("GRUPO", GRUPO.class);
        // CONTEUDO EXAME
        stream.alias("CONTEUDOEXAME", CONTEUDOEXAME.class);
        stream.alias("REGISTRO", REGISTRO.class);
        stream.alias("CANAL", CANAL.class);
        //LAUDO
        stream.alias("LAUDO", LAUDO.class);
        
        return stream.toXML(estrutura);
    }

    /**
     * Método da classe XMLFacttory utilizado para gerar uma estrutura a partir
     * de um xml.
     *
     * @param XML xml em formato de String, obdecer modelo de estrutura
     * MODELOHISTORICOCLINICO.class, HISTORICOCLINICO.class, CONTEUDOEXAME.class
     * @param encoding formato em que será decodificado o xml
     * @return retorna um objeto especifico de acordo com um tipo definido na
     * instância;
     */
    public T gerarEstrutura(String XML, String encoding) {
        XStream stream = new XStream(new DomDriver(encoding));

        // MODELO HISTORICO CLINICO
        stream.alias("MODELOHISTORICOCLINICO", MODELOHISTORICOCLINICO.class);
        stream.alias("DEFINICAO", DEFINICAO.class);
        stream.alias("VALOR", VALOR.class);
        stream.alias("STYLE", STYLE.class);
        // HISTORICO CLINICO
        stream.alias("HISTORICOCLINICO", HISTORICOCLINICO.class);
        stream.alias("DADOCLINICO", DADOCLINICO.class);
        stream.alias("GRUPO", GRUPO.class);
        // CONTEUDO EXAME
        stream.alias("CONTEUDOEXAME", CONTEUDOEXAME.class);
        stream.alias("REGISTRO", REGISTRO.class);
        stream.alias("CANAL", CANAL.class);
        //LAUDO
        stream.alias("LAUDO", LAUDO.class);
        
        T object = null;
        object = (T) stream.fromXML(XML);

        return object;
    }

    /**
     * Método da classe XMLFacttory utilizado para gerar uma estrutura a partir
     * de um xml.
     *
     * @param XML xml em formato de File, obdecer modelo de estrutura
     * MODELOHISTORICOCLINICO.class, HISTORICOCLINICO.class, CONTEUDOEXAME.class
     * @param encoding formato em que será decodificado o xml
     * @return retorna um objeto especifico de acordo com um tipo definido na
     * instância;
     */
    public T gerarEstrutura(File XML, String encoding) {
        XStream stream = new XStream(new DomDriver(encoding));

        // MODELO HISTORICO CLINICO
        stream.alias("MODELOHISTORICOCLINICO", MODELOHISTORICOCLINICO.class);
        stream.alias("DEFINICAO", DEFINICAO.class);
        stream.alias("VALOR", VALOR.class);
        stream.alias("STYLE", STYLE.class);
        // HISTORICO CLINICO
        stream.alias("HISTORICOCLINICO", HISTORICOCLINICO.class);
        stream.alias("DADOCLINICO", DADOCLINICO.class);
        stream.alias("GRUPO", GRUPO.class);
        // CONTEUDO EXAME
        stream.alias("CONTEUDOEXAME", CONTEUDOEXAME.class);
        stream.alias("REGISTRO", REGISTRO.class);
        stream.alias("CANAL", CANAL.class);
        //LAUDO
        stream.alias("LAUDO", LAUDO.class);
        
        try {
            Reader reader = new FileReader(XML);
            T object = (T) stream.fromXML(reader);

            return object;
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    public HistoricoClinico gerarHistoricoClinicoXMLAntigo(String XML, String encoding) {
        XStream stream = new XStream(new DomDriver(encoding));
        stream.alias("HISTORICOCLINICO", HistoricoClinico.class);
        stream.alias("DADOSCLINICOS", DadosClinicos.class);
        stream.alias("SOLICITANTE", Solicitante.class);
        stream.alias("MEDICAMENTOSEMUSO", Medicamentos.class);
        stream.alias("COMORBIDADESEFATORESEMRISCO", Comorbidades.class);
        stream.alias("HIPOTESEDIAGNOSTICA", Hipotese.class);
        stream.alias("INVESTIGACAODADOR", Investigacao.class);
        stream.alias("DORNOEXAME", DorNoExame.class);
        stream.alias("LOCALIZACAOIRRADIACAODADOR", LocalizacaoIrradiacao.class);
        stream.alias("SINTOMASASSOCIADOS", SintomasAssociados.class);
        stream.alias("INTENSIDADEDADOR", Intensidade.class);
        stream.alias("CARACTERIZACAODADORTORACICA", Caracterizacao.class);       
        stream.alias("DORCARDIACAISQUEMICA", DorCardiacaIsquemica.class);
        stream.alias("DORCARDIACANAOISQUEMICA", DorCardiacaNaoIsquemica.class);
        stream.alias("DORNAOCARDIACA", DorNaoCardiaca.class);
        stream.alias("MOTIVOEXAMESEMDOR", MotivoExameSemDor.class);

        return (HistoricoClinico) stream.fromXML(XML);
    }

    public CONTEUDOEXAME gerarConteudoExameXMLAntigo(String XML, String encoding) {
        XML = XML.replaceAll("</SENSIBILIDADE>", "</SENSIBILIDADE><REGISTROS>");
        XML = XML.replaceAll("</CONTEUDOEXAME>", "</REGISTROS></CONTEUDOEXAME>");
        return (CONTEUDOEXAME) gerarEstrutura(XML, encoding);
    }
}
