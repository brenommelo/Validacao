/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.commons.pdf;

/**
 *
 * @author igor.santos
 */
public class Header {

    private String pontoRemoto;
    private String dataRealizacao;
    private String nomePaciente;
    private String genero;
    private String dataNascimento;

    public Header(String pontoRemoto, String dataRealizacao, String nomePaciente, String genero, String dataNascimento) {
        this.pontoRemoto = pontoRemoto;
        this.dataRealizacao = dataRealizacao;
        this.nomePaciente = nomePaciente;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
    }

    public String getPontoRemoto() {
        return pontoRemoto;
    }

    public String getDataRealizacao() {
        return dataRealizacao;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public String getGenero() {
        return genero;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}
