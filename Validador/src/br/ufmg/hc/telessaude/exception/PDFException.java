/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author igor.santos
 */
public class PDFException extends Exception {

    private static final Logger LOG = LoggerFactory.getLogger(PDFException.class);

    public PDFException(String message, Throwable cause) {
        super(message, cause);
        getLogger(message, cause);
    }

    public PDFException(String msg) {
        super(msg);
        getLogger(msg, this);
    }

    private static void getLogger(String msg, Throwable thrwbl) {
        try {
            if (thrwbl != null) {
                LOG.error(msg, thrwbl);
            } else {
                LOG.error(msg);
            }
        } catch (Throwable ex) {
        }
    }
}
