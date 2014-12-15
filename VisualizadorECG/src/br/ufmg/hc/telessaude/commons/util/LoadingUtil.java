/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.commons.util;

import br.ufmg.hc.telessaude.commons.util.TelessaudeI18N;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Felipe
 */
public class LoadingUtil {
    
    private static final LoadingUtil INSTANCE = new LoadingUtil();
    
    protected TelessaudeI18N i18n = TelessaudeI18N.getInstance();

    public static LoadingUtil getInstance() {
        return INSTANCE;
    }
    
    public void configureLoading(JDialog dialog, JPanel panel, Component parent) {
        final JLabel loadingImage = new JLabel();
        loadingImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/ufmg/hc/telessaude/diagnostico/pontoremoto/images/loading.gif")));
        panel.add(loadingImage, new GridBagConstraints());
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, i18n.getText("label.action.loading"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 204)));
        panel.setBackground(Color.white);
        dialog.setTitle(i18n.getText("label.action.wait"));
        dialog.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        dialog.getContentPane().add(panel);
        dialog.setSize(200, 100);
        dialog.setLocationRelativeTo(parent);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setModal(true);
    }
    
}
