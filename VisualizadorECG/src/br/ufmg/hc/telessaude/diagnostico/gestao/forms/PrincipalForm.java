/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.diagnostico.gestao.forms;

import br.ufmg.hc.telessaude.diagnostico.dominio.commons.Acao;
import br.ufmg.hc.telessaude.diagnostico.dominio.commons.PDFUtil;
import br.ufmg.hc.telessaude.diagnostico.dominio.commons.StringUtils;
import br.ufmg.hc.telessaude.diagnostico.dominio.dao.EcgAnalysDAOImpl;
import br.ufmg.hc.telessaude.diagnostico.dominio.dao.ExameDAOLocal;
import br.ufmg.hc.telessaude.diagnostico.dominio.dao.LaudoDAOLocal;
import br.ufmg.hc.telessaude.diagnostico.dominio.daoImpl.ConteudoExameDAOImpl;
import br.ufmg.hc.telessaude.diagnostico.dominio.daoImpl.HistoricoClinicoDAOImpl;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.Exame;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.Laudo;
import br.ufmg.hc.telessaude.diagnostico.dominio.exceptions.DAOException;
import br.ufmg.hc.telessaude.diagnostico.dominio.glasgow.EcgAnalys;
import br.ufmg.hc.telessaude.diagnostico.dominio.glasgow.ReportEcgAnalys;
import br.ufmg.hc.telessaude.exception.PDFException;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.LAUDO;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.gerador.XMLFactory;
import java.awt.Desktop;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paulo.gomes
 */
public class PrincipalForm extends javax.swing.JFrame {

    Exame exameSelecionado;
    List<Exame> exames;
    LaudoForm laudoForm;
    ExameDAOLocal dao;

    /**
     * Creates new form PrincipalForm
     */
    public PrincipalForm() {
        initComponents();
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        mountGrid(exames);
        dao = new ExameDAOLocal();
    }

    public void imprimirPDF(Exame exam) {
        try {
            int scale = Integer.parseInt(cbxScala.getSelectedItem().toString());

            float speed = Float.parseFloat(cbxVelocidade.getSelectedItem().toString());

            final PDFUtil util = new PDFUtil(exam, scale, speed);
            //  final PDFUtil util = new PDFUtil(exam);
            final File file = new File(util.getNameFile());
            file.deleteOnExit();
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (PDFException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        txtIdExame = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        cbxScala = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxVelocidade = new javax.swing.JComboBox();
        btnImprimir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNomePaciente = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        dtcFim = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        dtcInicio = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        btnFecharVisualizador = new javax.swing.JButton();
        cbxGlasgow = new javax.swing.JCheckBox();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        splitPane.setDividerSize(10);
        splitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPane.setOneTouchExpandable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 255)));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        cbxScala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "20" }));

        jLabel1.setText("Escala:");

        jLabel2.setText("Velocidade:");

        cbxVelocidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12.5", "25", "50" }));

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel3.setText("ID: ");

        jLabel4.setText("Nome:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data inclusão", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 255)));

        jLabel6.setText("FIM: ");

        jLabel5.setText("INÍCIO:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(dtcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dtcFim, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(dtcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtcFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setText("Limpar Filtros");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnFecharVisualizador.setText("Fechar Visualizador");
        btnFecharVisualizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharVisualizadorActionPerformed(evt);
            }
        });

        cbxGlasgow.setText("inserir glasgow");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxScala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxGlasgow)
                        .addGap(14, 14, 14)
                        .addComponent(btnImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addComponent(btnFecharVisualizador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomePaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(txtIdExame))
                        .addGap(35, 35, 35)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtIdExame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxScala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cbxVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnImprimir)
                    .addComponent(btnBuscar)
                    .addComponent(jButton1)
                    .addComponent(btnFecharVisualizador)
                    .addComponent(cbxGlasgow))
                .addContainerGap())
        );

        splitPane.setTopComponent(jPanel1);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(table);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1.setLayer(scrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        splitPane.setRightComponent(jDesktopPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(splitPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (laudoForm != null && laudoForm.isVisible()) {
            JOptionPane.showMessageDialog(this, "Feche o visualizador para buscar novos exames");
            return;
        }
        try {
            if (txtIdExame.getText().isEmpty() && txtNomePaciente.getText().isEmpty() && dtcInicio.getDate() == null && dtcFim.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Informe ao menos um critério de busca");
                return;
            } else if (!txtNomePaciente.getText().isEmpty() && txtNomePaciente.getText().trim().length() <= 3) {
                JOptionPane.showMessageDialog(this, "Consultas por nome devem ter ao menos 3 caracteres");
                return;
            }
            exames = dao.consultar(txtIdExame.getText().isEmpty() ? null : Integer.parseInt(txtIdExame.getText()), txtNomePaciente.getText().trim(), dtcInicio.getDate(), dtcFim.getDate());
            mountGrid(exames);
        } catch (DAOException ex) {
            Logger.getLogger(PrincipalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            carregarExameSelecionado();
            if (exameSelecionado != null) {
//                exameSelecionado.setLaudos(null);
                if (cbxGlasgow.isSelected()) {
                    inserirGlasgowLaudo();
                }
                imprimirPDF(exameSelecionado);
            }
        } catch (Exception ex) {
            Logger.getLogger(PrincipalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (laudoForm != null && laudoForm.isVisible()) {
            JOptionPane.showMessageDialog(this, "Feche o visualizador primeiro para abrir novos exames");
            return;
        }
        if (evt.getClickCount() >= 2) {
            carregarExameSelecionado();
            abrirExame();
        }
    }//GEN-LAST:event_tableMouseClicked

    private void btnFecharVisualizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharVisualizadorActionPerformed
        if (laudoForm != null && laudoForm.isVisible()) {
            laudoForm.dispose();
        }
        this.tooglePanel();
    }//GEN-LAST:event_btnFecharVisualizadorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txtIdExame.setText("");
        txtNomePaciente.setText("");
        dtcInicio.setDate(null);
        dtcFim.setDate(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void carregarExameSelecionado() {
        try {
            final int position = table.getSelectedRow();
            if (position >= 0) {
                exameSelecionado = (Exame) table.getValueAt(position, 0);
                exameSelecionado = dao.findById(exameSelecionado.getId());
                if (exameSelecionado != null) {
                    exameSelecionado.setConteudosExames(new ConteudoExameDAOImpl().consultarPorIdExame(exameSelecionado.getId()));
                    exameSelecionado.setHistoricosClinicos(new HistoricoClinicoDAOImpl().consultarPorIdExame(exameSelecionado.getId()));
                    exameSelecionado.setLaudos(new LaudoDAOLocal().consultarPorIdExame(exameSelecionado.getId()));
                    for (Laudo laudo : exameSelecionado.getLaudos()) {
                        laudo.setExame(exameSelecionado);
                    }
                }
            }
        } catch (DAOException ex) {
            Logger.getLogger(PrincipalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void abrirExame() {
        try {
            if (exameSelecionado != null) {
                this.tooglePanel();
                Laudo laudo = null;
                if (exameSelecionado.getLaudos() != null && exameSelecionado.getLaudos().size() > 0) {
                    laudo = exameSelecionado.getLaudos().get(0);
                }else{
                    laudo = new Laudo();
                    laudo.setExame(exameSelecionado);
                }
                laudoForm = new LaudoForm(jDesktopPane1, Acao.NULA, laudo, Calendar.getInstance().getTime(), this);
//                laudoForm.setReferencia(this);
            }
        } catch (Exception ex) {
            Logger.getLogger(PrincipalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mountGrid(List<Exame> exames) {

        final DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "", "NOME", "DATA DE NASCIMENTO", "DATA INCLUSÃO", "STATUS"}) {
                    Class[] types = new Class[]{
                        java.lang.Object.class, java.lang.String.class, java.lang.String.class,
                        java.lang.String.class, java.lang.String.class,};
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false
                    };

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return false;
                    }
                };

        if (exames == null) {
            exames = new ArrayList<>();
        }

        if (exames.size() > 0) {
            for (final Exame exame : exames) {
                model.addRow(new Object[]{exame,
                    exame.getPaciente().getNome(),
                    StringUtils.format(exame.getPaciente().getDatanascimento(), StringUtils.DD_MM_YYYY),
                    StringUtils.formatDateWithTime(exame.getDatainclusao()),
                    exame.getStatus().getNome()});
            }
        }

        table.setModel(model);
        hideObjectColumn();
    }

    private void hideObjectColumn() {
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

    }

    public String exibirConteudo() {
        if (exameSelecionado != null) {
            try {
                EcgAnalys ecg = new EcgAnalysDAOImpl().findById(exameSelecionado.getId());
                if (ecg != null) {
                    ReportEcgAnalys.report(ecg);
                    List<String> saida = ReportEcgAnalys.getStrings();
                    StringBuilder build = new StringBuilder();
                    for (String string : saida) {
                        build.append(string);
                        build.append("\n");
                    }
                    return build.toString();
                }
            } catch (DAOException ex) {
                Logger.getLogger(GlasgowForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

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
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalForm().setVisible(true);
            }
        });
    }

    public void inserirGlasgowLaudo() {
        if (exameSelecionado != null && exameSelecionado.getLaudos().size() > 0) {
            XMLFactory<LAUDO> laudoFactory = new XMLFactory<>();
            LAUDO ld = laudoFactory.gerarEstrutura(exameSelecionado.getLaudos().get(0).getConteudo(), "UTF-8");
            String conteudo = ld.getCONTEUDO();
            conteudo += "\n\n\n Análise Glasgow:";
            conteudo += "\n\n" + this.exibirConteudo();
            ld.setCONTEUDO(conteudo);
            exameSelecionado.getLaudos().get(0).setConteudo(laudoFactory.gerarXML(ld, "UTF-8"));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnFecharVisualizador;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JCheckBox cbxGlasgow;
    private javax.swing.JComboBox cbxScala;
    private javax.swing.JComboBox cbxVelocidade;
    private com.toedter.calendar.JDateChooser dtcFim;
    private com.toedter.calendar.JDateChooser dtcInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtIdExame;
    private javax.swing.JTextField txtNomePaciente;
    // End of variables declaration//GEN-END:variables

    public void tooglePanel() {
        scrollPane.setVisible(!scrollPane.isVisible());
    }
}