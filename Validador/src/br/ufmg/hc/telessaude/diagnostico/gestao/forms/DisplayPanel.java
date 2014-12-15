/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.diagnostico.gestao.forms;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.apache.pdfbox.PDFToImage;

/**
 *
 * @author paulo.gomes
 */
public class DisplayPanel extends JPanel implements MouseListener, MouseMotionListener {

    int x, y, sX, sY;
    int curX = -1, curY = -1;
    int sCurX = -1, sCurY = -1;
    Point pPoint;
    MouseEvent pressed;
    List<Medida> medidas;

    boolean dragging = false;
    boolean draggingSecond = false;

    private Color corPadrao, corMedida;

    private BufferedImage displayImage;

    private BufferedImage biSrc, biDest, bi;

    private Graphics2D big;

// Valores padrao
    private float scaleFactor = 1.0f;

    private float offset = 0;

    private RescaleOp rescaleOp;

    private boolean reverse;

    private int width, height;

    private LookupTable lookupTable;

    double scale = 1;

    boolean transparencia;

    public DisplayPanel(File file, boolean transparencia) {
        this.transparencia = transparencia;
        setBackground(Color.black); // panel background color
        //file = new File("C:\\Projetos\\tools\\ColorApp\\src\\images\\BrightnessIncreaseDemo.PNG");
        if (file != null) {
            loadImage(file);
            setSize(displayImage.getWidth(this), displayImage.getHeight(this)); // panel
            width = getSize().width;
            height = getSize().height;
            createBufferedImage();
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        reverse = false;
        corPadrao = Color.red;
        corMedida = Color.BLACK;
        medidas = new ArrayList();
        setBackground(new Color(0, 0, 0, 125));
        setOpaque(false);
    }

    public void changeImage(File file) {
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 125));
//        setBackground(Color.black); // panel background color
        //file = new File("C:\\Projetos\\tools\\ColorApp\\src\\images\\BrightnessIncreaseDemo.PNG");
        loadImage(file);
        setSize(displayImage.getWidth(this), displayImage.getWidth(this)); // panel
        width = getSize().width;
        height = getSize().height;
        createBufferedImage();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        reverse = false;
        corPadrao = Color.red;
        corMedida = Color.GREEN;
        medidas = new ArrayList();
        repaint();
    }

    public void loadImage(File file) {
        if (file == null || !file.exists()) {
            String fileName = "/images/logo_mt.png";
            URL url = getClass().getResource(fileName);
            try {

                displayImage = ImageIO.read(url);

            } catch (IOException ex) {
                Logger.getLogger(DisplayPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                converter(file.getAbsolutePath(), "imagem", 2);
                File f = new File("imagem"+2+".jpg");
                if (f.exists()) {

                    displayImage = ImageIO.read(f);
                    file.delete();

                }
            } catch (Exception e) {
                System.out.println("Exception while loading.");
            }
        }
        try {

            MediaTracker mt = new MediaTracker(this);
            mt.addImage(displayImage, 1);
            mt.waitForAll();
        } catch (Exception e) {
            System.out.println("Exception while loading.");
        }

        if (displayImage.getWidth(this) == -1) {
            System.out.println("No jpg file");
            System.exit(0);
        }
    }

    public void createBufferedImage() {

        biSrc = new BufferedImage(displayImage.getWidth(this),
                displayImage.getHeight(this),
                BufferedImage.TYPE_INT_RGB);

        big = biSrc.createGraphics();
        big.drawImage(displayImage, 0, 0, this);

        biDest = new BufferedImage(displayImage.getWidth(this),
                displayImage.getHeight(this),
                BufferedImage.TYPE_INT_RGB);
        bi = biSrc;
    }

    /**
     * Metodo responsavel pelo zoom Valores vao de 0.0 a 2.0
     *
     * @param s
     */
    public void setScale(double s) {
        scale = s;
        int zoom_x = Math.round(width * (float) s);
        int zoom_y = Math.round(height * (float) s);
        this.setSize(new Dimension(zoom_x, zoom_y));
        this.revalidate();
        repaint();
    }

    public double getScale() {
        return scale;
    }

    /**
     * Valores de -255 a +255
     *
     * @param value
     */
    public void managerBrighten(float offset) {
        this.offset = offset;
        this.rescale();
    }

    /**
     * Valores de 0.0 a 2.0
     *
     * @param scaleFact
     */
    public void managerContrast(float scaleFact) {
        this.scaleFactor = scaleFact;
        this.rescale();
    }

    /**
     * Chama o efeito de 'Negative'
     *
     * @param reverse
     */
    public void setReverse(boolean reverse) {
        if (reverse) {
            this.reverseLUT();
        }
        this.reverse = reverse;
        this.applyFilter();
    }

    /**
     * Efeito de 'Negative'
     *
     * @param reverse
     */
    public void reverseLUT() {
        byte reverse[] = new byte[256];
        for (int i = 0; i < 256; i++) {
            reverse[i] = (byte) (255 - i);
        }
        lookupTable = new ByteLookupTable(0, reverse);
    }

    /**
     * Retorna a imagem ao estado original
     */
    public void reset() {
        big.setColor(Color.black);
        createBufferedImage();
        setScale(1);
        setLocation(0, 0);
        limparMedidas();
        this.repaint();
    }

    /**
     * Utilizado para aplicar o filtro de reverse
     */
    public void applyFilter() {
        LookupOp lop = new LookupOp(lookupTable, null);
        lop.filter(bi, bi);
    }

    @Override
    public void update(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        paintComponent(g);
    }

    Graphics2D g2D;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2D = (Graphics2D) g;
//
//        //g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final int x_zoom = (int) (getWidth() - scale * displayImage.getWidth()) / 2;
        final int y_zoom = (int) (getHeight() - scale * displayImage.getHeight()) / 2;
        final AffineTransform at = AffineTransform.getTranslateInstance(x_zoom, y_zoom);
        at.scale(scale, scale);
        if (transparencia) {
            g2D.setComposite(AlphaComposite.SrcOver.derive(0.2f));
        }
        g2D.drawRenderedImage(bi, at);
        g2D.drawImage(bi, at, this);

        if (x < 0 || y < 0) {
            return;
        }
//        System.out.println("Rect[" + sX + "," + sY
//                + "] size " + w + "x" + h);
//        graphic.
        if (transparencia) {
            g2D.setComposite(AlphaComposite.SrcOver.derive(1f));
        }
        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(corPadrao);
//        graphic.fillRect(sX, sY, w, h);
        g2D.drawLine(x, y, curX, curY);
        double med = calcularMedida(x, y, curX, curY);
        g2D.drawString(String.format("     %.2f", med), curX, curY);
        g2D.setColor(corMedida);
//        graphic.fillRect(sX, sY, w, h);
        g2D.drawLine(sX, sY, sCurX, sCurY);
        g2D.drawString(String.format("  %.2f ( %.2f)", calcularMedida(sX, sY, sCurX, sCurY), calcularMedida(sX, sY, sCurX, sCurY) / med), sCurX, sCurY);
        for (Medida medida : medidas) {
//            g2D.drawLine(sX, sY, sCurX, sCurY);
//            g2D.drawString(String.format("     %.2f", calcularMedida(sX, sY, sCurX, sCurY)), sCurX, sCurY);
            g2D.setColor(medida.cor);
            g2D.drawLine(medida._x, medida._y, medida._cX, medida._cY);
            double med_linha = calcularMedida(medida._x, medida._y, medida._cX, medida._cY);
            g2D.drawString(String.format("  %.2f ( %.2f)", med_linha, med_linha / med), medida._cX, medida._cY);
        }
    }

    public int getImageWidth() {
        return displayImage.getWidth();
    }

    public int getImageHeight() {
        return displayImage.getHeight();
    }

    /**
     * Aplica o ajuste de brilho e contraste da imagem
     */
    public void rescale() {
        rescaleOp = new RescaleOp(scaleFactor, offset, null);
        rescaleOp.filter(biSrc, biDest);
        bi = biDest;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Point point = event.getPoint();
        pressed = event;
        // Evento do botao para a medida padrao
        if (event.getButton() == MouseEvent.BUTTON3) {
//            x = point.x;
//            y = point.y;
            dragging = true;

//             moveLabel(event.getX(), event.getY());  
            // Evento do botao para a medida secundaria
        } else if (event.getButton() == MouseEvent.BUTTON1) {
            sX = point.x;
            sY = point.y;
            draggingSecond = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (draggingSecond) {
            medidas.add(new Medida(sX, sY, sCurX, sCurY, corMedida));
        }
        dragging = false;
        draggingSecond = false;
//        moveLabel(e.getX(), e.getY());  
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        Point p = event.getPoint();
        if (dragging) {
//          if (e.getSource() == p)
            {
                pPoint = this.getLocation(pPoint);

                int x = pPoint.x - pressed.getX() + event.getX();
                int y = pPoint.y - pressed.getY() + event.getY();
                this.setLocation(x, y);
            }
        }
        if (draggingSecond) {
            sCurX = p.x;
            sCurY = p.y;
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    private static double calcularMedida(int x1, int y1, int x2, int y2) {
        int x = x2 - x1;
        int y = y2 - y1;
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double getMedidaPadrao() {
        return calcularMedida(x, y, curX, curY);
    }

    public double getMedidaSecundaria() {
        return calcularMedida(sX, sY, sCurX, sCurY);
    }

    public Color getCorPadrao() {
        return corPadrao;
    }

    public void setCorPadrao(Color corPadrao) {
        this.corPadrao = corPadrao;
        this.repaint();
    }

    public Color getCorMedida() {
        return corMedida;
    }

    public void setfCorMedida(Color corMedida) {
        this.corMedida = corMedida;
        this.repaint();
    }

    private void limparMedidas() {
        x = y = sX = sY = 0;
        curX = -1;
        curY = -1;
        sCurX = -1;
        sCurY = -1;
        medidas.clear();
//        repaint();
    }

    public List<Medida> getMedidas() {
        return medidas;
    }

    public void removerMedida(int index) {
        if (index >= 0 && index < medidas.size()) {
            sX = sY = 0;
            sCurX = -1;
            sCurY = -1;
            medidas.remove(index);
            this.repaint();
        }
    }

    class Medida {

        public int _x;
        public int _y;
        public int _cX;
        public int _cY;
        public Color cor;

        public Medida(int x, int y, int cX, int cY, Color cor) {
            this._x = x;
            this._y = y;
            this._cX = cX;
            this._cY = cY;
            this.cor = cor;
        }

        @Override
        public String toString() {
            double med = calcularMedida(this._x, this._y, this._cX, this._cY);
            return String.format("Tamanho: %4.2f  - Propor��o: %4.2f", med, med / calcularMedida(x, y, curX, curY));
        }

    }
//     public void moveLabel(int x, int y) {  
//            this.setLocation(x,y);  
//            this.repaint();  
//     }  

    public void converter(String pdfPath, String nome, int pagina) {
//        String pdfPath = "/path/to/file.pdf";
//config option 1:convert all document to image
        String[] args_1 = new String[3];
        args_1[0] = "-outputPrefix";
        args_1[1] = "my_image_1";
        args_1[2] = pdfPath;

//config option 2:convert page 1 in pdf to image
        String[] args_2 = new String[7];
        args_2[0] = "-startPage";
        args_2[1] = String.valueOf(1);
        args_2[2] = "-endPage";
        args_2[3] = String.valueOf(2);
        args_2[4] = "-outputPrefix";
        args_2[5] = nome;
        args_2[6] = pdfPath;

        try {
// will output "my_image_1.jpg"
//            PDFToImage.main(args_1);
// will output "my_image_2.jpg" 
            PDFToImage.main(args_2);
        } catch (Exception e) {
        }

    }

}
