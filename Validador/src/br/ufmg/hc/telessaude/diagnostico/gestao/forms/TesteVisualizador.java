/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.diagnostico.gestao.forms;

import br.ufmg.hc.telessaude.component.ecg.plot.ECG12PlotCustomPanel;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author paulo.gomes
 */
public class TesteVisualizador extends javax.swing.JFrame {

    /**
     * Creates new form TesteVisualizador
     */
    public TesteVisualizador() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTeste = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout pnlTesteLayout = new javax.swing.GroupLayout(pnlTeste);
        pnlTeste.setLayout(pnlTesteLayout);
        pnlTesteLayout.setHorizontalGroup(
            pnlTesteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 984, Short.MAX_VALUE)
        );
        pnlTesteLayout.setVerticalGroup(
            pnlTesteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTeste, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTeste, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TesteVisualizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TesteVisualizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TesteVisualizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TesteVisualizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TesteVisualizador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlTeste;
    // End of variables declaration//GEN-END:variables

    private void init() {
//        ECG12PlotPanel ecg1 = new ECG12PlotPanel();
        ECG12PlotCustomPanel ecg1 = new ECG12PlotCustomPanel();
//        ECG12PlotPanel ecg2 = new ECG12PlotPanel();
        ECG12PlotCustomPanel ecg2 = new ECG12PlotCustomPanel();
//        movimentar(ecg1);
//        movimentar(ecg2);
//        ecg2.setImprimirGri(false);
//        ecg1.setBackground(new Color(0, 0, 0, 5));
        ecg2.setBackground(new Color(0, 0, 0, 5));
//        ecg1.setOpaque(false);
        ecg2.setOpaque(false);
//        pnlTeste.add(ecg2);
        pnlTeste.add(ecg1);
    }

    Point pPoint;
    MouseEvent pressed;

//    public void movimentar(final ECG12PlotCustomPanel p) {
//
////        p.setSize(900, 600);
//        p.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent e) {
//                 if (p.isChild(e.getSource())) {
//                    pressed = e;
//                }
//            }
//        });
//
//        p.addMouseMotionListener(new MouseMotionAdapter() {
//            public void mouseDragged(MouseEvent e) {
//                if (p.isChild(e.getSource())) {
//                    pPoint = p.getLocation(pPoint);
//                    int x = pPoint.x - pressed.getX() + e.getX();
//                    int y = pPoint.y - pressed.getY() + e.getY();
//                    p.setLocation(x, y);
//                }
//            }
//        });

//        p.setBorder(new LineBorder(Color.black));
//        setVisible(true);
//    }
}