/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.diagnostico.gestao.forms;

import br.ufmg.hc.telessaude.diagnostico.dominio.commons.Acao;
import br.ufmg.hc.telessaude.diagnostico.dominio.commons.Mensagens;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author igor.santos
 */
public abstract class Form extends JInternalFrame {

    private JDesktopPane desktop;
    private Object objeto;
    private Form referencia;
    private Acao acao;
    // Atributo para ativar se o form se comportara como modal
    private boolean modal = false;

    public Form(JDesktopPane desktop, Acao acao) {
        this.desktop = desktop;
        this.acao = acao;
        init();
    }

    public void redmensionarTela() {
        Dimension screenSize = desktop.getSize();
        Dimension size = this.getSize();
        if (size.getHeight() < (screenSize.getHeight() - 312)) {
            setLocation((int) ((screenSize.getWidth() - size.getWidth()) / 2), (int) ((screenSize.height - size.getHeight() - 312) / 2));
        } else {
            setLocation((int) ((screenSize.getWidth() - size.getWidth()) / 2), (int) ((screenSize.height - size.getHeight()) / 2));
        }
    }

    public void fullScreen() {
        Dimension screenSize = desktop.getSize();
        setSize(screenSize);
        setLocation(0, 0);
    }

    public void toUpperCaseText(javax.swing.JTextField txtField) {
        txtField.setText(txtField.getText().toUpperCase());
    }

    public void centralizarTela() {
        Dimension screenSize = desktop.getSize();
        Dimension size = this.getSize();
        if (size.width > screenSize.width) {
            size.width = screenSize.width;
        }
        if (size.height > screenSize.height) {
            size.height = screenSize.height;
        }
        setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
    }

    public void exibirMensagem(String mensagem, String titulo, int icone) {
        JOptionPane.showMessageDialog(desktop, mensagem, titulo, icone);
    }

    protected int exibirConfirmacao(String mensagem) {
        return JOptionPane.showConfirmDialog(this, mensagem, Mensagens.CONFIRMAR_OPERACAO, JOptionPane.YES_NO_OPTION);
    }

    public void exibirJanelaErro(Exception ex) {
        if (ex != null && ex.getMessage() != null) {
            if (ex.getMessage().contains("ERRO: atualização ou exclusão")) {
                exibirMensagem(Mensagens.EXCLUSAO_RELACIONAMENTO, Mensagens.TITLE_ATENCAO, JOptionPane.ERROR_MESSAGE);
            } else {
                Form form = new ErrorForm(desktop, ex);
                desktop.add(form);
                form.setVisible(true);
            }
        }
        
    }

    public void exibirConfirmacaoCancelar() {
        if (acao == null || !acao.equals(Acao.VISUALIZAR)) {
            int confirm = exibirConfirmacao(Mensagens.CONFIRMAR_CANCELAR);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
            }
        } else {
            dispose();
        }
    }

    public Object getObjeto() {
        return objeto;
    }

    public Form getReferencia() {
        return referencia;
    }

    public void setReferencia(Form referencia) {
        this.referencia = referencia;
    }

    public void transportar(Object objeto) {
        this.objeto = objeto;
    }

    public Acao getAcao() {
        return acao;
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    public abstract void execute(Acao acao);

    //<editor-fold defaultstate="collapsed" desc="Funcoes para trabalhar com modal">
    private Form getChildForm() {
        return this;
    }

    private Form getParentForm() {
        return referencia;
    }

    public boolean isModal() {
        return modal;
    }

    public void setModal(boolean modal) {
        this.modal = modal;
    }
    private static JDesktopPane glassPane = new JDesktopPane();

    private void init() {
        // Add glass pane
//        ModalityInternalGlassPane glassPane = new ModalityInternalGlassPane(this);
        // make JDesktopPane (glassPane) transparent
        glassPane.setOpaque(false);
        // add empty mouse adapter to block events to other components then JInternalFrame
        glassPane.addMouseListener(new MouseAdapter() {
        });
        setGlassPane(glassPane);

        // Add frame listeners
        addFrameListener();
    }

    protected boolean hasParentFrame() {
        return (getParentForm() != null);
    }

    /**
     * Method to control the display of the glasspane, dependant on the frame
     * being active or not
     */
    protected void addFrameListener() {
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
//                glassPane.remove(0);
//                glassPane.setVisible(false);
//                glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    protected void childOpening() {
        glassPane.add(this);
        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        glassPane.setVisible(true);
    }

    /**
     * Glass pane to overlay. Listens for mouse clicks and sets selected on
     * associated modal frame. Also if modal frame has no children make class
     * pane invisible
     */
    class ModalityInternalGlassPane extends JComponent {

        public ModalityInternalGlassPane(Form form) {
            this.add(form);
            form.setVisible(true);
            addMouseListener(new MouseAdapter() {
            });
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g); //To change body of generated methods, choose Tools | Templates.
//            g.setColor(new Color(255, 255, 255, 100));
            setOpaque(true);
            g.setColor(new Color(255, 255, 100, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

//</editor-fold>
    @Override
    public void setVisible(boolean aFlag) {
        if (modal) {
            if (getParentForm() != null) {
                childOpening();
                centralizarTela();
            }
        }
        
        super.setVisible(aFlag); //To change body of generated methods, choose Tools | Templates.
    }
}
