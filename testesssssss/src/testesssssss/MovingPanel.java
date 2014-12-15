/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testesssssss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author breno.melo
 */
public class MovingPanel extends JFrame
{
 JPanel p;
 Point pPoint;
 MouseEvent pressed;

 public MovingPanel()
 {
   p = new JPanel();
//   p =  new DisplayPanel(new File("C:\\Users\\breno.melo\\Desktop\\Nova pasta\\RISCO-CARDIOVASCULAR - Cópia.jpg"));
//   p = new DisplayPanel(new File("C:\\Users\\breno.melo\\Desktop\\LEO.pdf"));
//   p.setBackground(Color.cyan);
//   p.setBorder(new LineBorder(Color.black));
   p.setSize(900, 600);

   p.addMouseListener(new MouseAdapter()
     {public void mousePressed(MouseEvent e)
     {
       if (e.getSource() == p)
       {
         pressed = e;
       }
     }
     });

   p.addMouseMotionListener(new MouseMotionAdapter()
     {public void mouseDragged(MouseEvent e)
     {
       if (e.getSource() == p)
       {
         pPoint = p.getLocation(pPoint);
         int x = pPoint.x - pressed.getX() + e.getX();
         int y = pPoint.y - pressed.getY() + e.getY();
         p.setLocation(x,y);
       }
     }
     });


   p.setBorder(new LineBorder(Color.black));
//   p.add("Center", new JLabel("To Move"));
 

   JPanel mainPanel = new JPanel();
//   mainPanel.setBackground(Color.blue);
   mainPanel.add(p);

//   JPanel mainPanel2 = new JPanel();
//   mainPanel2.add(new JLabel("TEst"));

   getContentPane().add("Center", mainPanel);
//   getContentPane().add("East", mainPanel2);
   setSize(900,600);
   setVisible(true);
 }

 public static void main (String[] args)
 {
   MovingPanel mainPanel = new MovingPanel();
   mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
}

