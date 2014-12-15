/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testesssssss;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.MediaTracker;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComponentDragger extends MouseAdapter {

     private Component target;

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Container container = (Container) e.getComponent();
        for (Component c : container.getComponents()) {
            if (c.getBounds().contains(e.getPoint())) {
                target = c;
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (target != null) {
            target.setBounds(e.getX(), e.getY(), target.getWidth(), target.getHeight());
            e.getComponent().repaint();
            }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        target = null;
    }


    public static void main(String[] args) {
//         DisplayPanel dis = new DisplayPanel(new File("C:\\Users\\breno.melo\\Desktop\\Nova pasta\\RISCO-CARDIOVASCULAR - Cópia.jpg"));
        JLabel label = new JLabel("Drag Me");
        JLabel label2 = new JLabel("Drag Me 2");
        JPanel panel = new JPanel();
        panel.add(label);
        ComponentDragger dragger = new ComponentDragger();
        panel.addMouseListener(dragger);
        panel.addMouseMotionListener(dragger);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1024, 768);
        f.add(panel);
        f.setVisible(true);
        panel.setLayout(null);
        f.setState(Frame.MAXIMIZED_BOTH);
    }
}