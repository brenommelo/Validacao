/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufmg.hc.telessaude.diagnostico.dominio.dao;

import br.ufmg.hc.telessaude.diagnostico.dominio.exceptions.DAOException;
import br.ufmg.hc.telessaude.diagnostico.dominio.glasgow.EcgAnalys;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paulo.gomes
 */
public class EcgAnalysDAOImplTest {
    
    public EcgAnalysDAOImplTest() {
    }

    @Test
    public void testConsultar() {
        try {
            EcgAnalysDAOImpl dao = new EcgAnalysDAOImpl();
            EcgAnalys ecg = dao.findById(347035);
            assertNotNull(ecg);
        } catch (DAOException ex) {
            Logger.getLogger(EcgAnalysDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
