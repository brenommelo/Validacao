/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.exception;

/**
 *
 * @author igor.santos
 */
public class ServiceException extends Exception {
    
    /**
     * 
     * @param msg 
     */
    public ServiceException(String msg) {
        super(msg);
    }
}
