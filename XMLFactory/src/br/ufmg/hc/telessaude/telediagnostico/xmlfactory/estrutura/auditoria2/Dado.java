/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.auditoria2;

import java.io.Serializable;

/**
 *
 * @author igor.santos
 */
public class Dado extends Informacao implements Serializable {

    public Dado(String descricao, int valor, String tipo) {
        super(descricao, valor, tipo);
    }
}
