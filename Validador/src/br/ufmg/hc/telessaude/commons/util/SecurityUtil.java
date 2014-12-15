/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.commons.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author weslley.matos
 */
public class SecurityUtil {
    
    private static final String MD5 = "MD5";
    
    public static String createPassword(String password) {
        MessageDigest md = null;
        
        try {
            md = MessageDigest.getInstance(MD5);
            md.update(password.getBytes());
            password = encrypt(md.digest());
            
        } catch (NoSuchAlgorithmException e) {
            
        }
        
        return password;
    }

    
    private static String encrypt(byte[] password) {
        StringBuilder s = new StringBuilder();
        String psw = "";
        
        for (int i = 0; i < password.length; i++) {
            int parteAlta = ((password[i] >> 4) & 0xf) << 4;
            int parteBaixa = password[i] & 0xf;
            if (parteAlta == 0) {
                s.append('0');
            }
            
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        
        psw = s.toString().toUpperCase();
        
        return psw;
    }
}
