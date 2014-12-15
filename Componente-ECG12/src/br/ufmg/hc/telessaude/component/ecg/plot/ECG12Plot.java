package br.ufmg.hc.telessaude.component.ecg.plot;

import br.ufmg.hc.telessaude.component.ecg.pojos.ConteudoECG12;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.print.*;
import java.text.NumberFormat;
import javax.swing.*;

public class ECG12Plot extends javax.swing.JInternalFrame implements KeyListener {

    private ECG12Config config;
    private TracadoECG tracado;
    int mx, my, mxa, mya, mxb, myb, mxs1, mys1, mxs2, mys2, Xa, Ya, X = 0, Y = 0;
    boolean isButtonPressed = false;
    boolean Selecao = false;
    String[] amostras[][];
    private JScrollPane scroll;
    String ganho[][];
    int Zoom = 1;
    int count = 0;
    int[] media = null;
    private float ppi_to_mm_print = (float) (72.0 / 25.4); //fator de conversão 72 PPI para PPM (72 Pixels por polegada para pixels por milímetro).
    private float ppi_to_mm_view = (float) (96. / 25.4); //fator de conversão 72 PPI para PPM (72 Pixels por polegada para pixels por milímetro).
    private float velocidade = 25;
    private String identificacao = null;

    public ECG12Plot() {
        ConteudoECG12 conteudo = new ConteudoECG12(null, null, 0, (float) 3.9, 1200, 1);
        initLaudoECG(conteudo);
        ganho = new String[12][1];
        ganho[0][0] = "10";
        ganho[1][0] = "10";
        ganho[2][0] = "10";
        ganho[3][0] = "10";
        ganho[4][0] = "10";
        ganho[5][0] = "10";
        ganho[6][0] = "10";
        ganho[7][0] = "10";
        ganho[8][0] = "10";
        ganho[9][0] = "10";
        ganho[10][0] = "10";
        ganho[11][0] = "10";
    }

    public ECG12Plot(JDesktopPane desktop, ConteudoECG12 conteudoECG12, String identificacao) {
        desktop.add(this);
        initLaudoECG(conteudoECG12);
        this.identificacao = identificacao;

        //this.Amostragem = conteudoExameECG12.getAmostragem();
        preencherTracadoEMedia();
    }

    private void preencherTracadoEMedia() {
        media = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < amostras[0][0].length; i++) {
            // Convertendo os valores
            tracado.DI()[i] = parseInt(amostras[0][0][i]);
            tracado.DII()[i] = parseInt(amostras[1][0][i]);
            tracado.DIII()[i] = parseInt(amostras[2][0][i]);
            tracado.aVR()[i] = parseInt(amostras[3][0][i]);
            tracado.aVL()[i] = parseInt(amostras[4][0][i]);
            tracado.aVF()[i] = parseInt(amostras[5][0][i]);
            tracado.V1_10()[i] = parseInt(amostras[6][0][i]);
            tracado.V2_10()[i] = parseInt(amostras[7][0][i]);
            tracado.V3_10()[i] = parseInt(amostras[8][0][i]);
            tracado.V4_10()[i] = parseInt(amostras[9][0][i]);
            tracado.V5_10()[i] = parseInt(amostras[10][0][i]);
            tracado.V6_10()[i] = parseInt(amostras[11][0][i]);

            // Somando valores para media
            media[0] += tracado.DI()[i];
            media[1] += tracado.DII()[i];
            media[2] += tracado.DIII()[i];
            media[3] += tracado.aVR()[i];
            media[4] += tracado.aVL()[i];
            media[5] += tracado.aVF()[i];
            media[6] += tracado.V1_10()[i];
            media[7] += tracado.V2_10()[i];
            media[8] += tracado.V3_10()[i];
            media[9] += tracado.V4_10()[i];
            media[10] += tracado.V5_10()[i];
            media[11] += tracado.V6_10()[i];
        }

        for (int e = 0; e < media.length; e++) {
            media[e] /= amostras[e][0].length;
        }
    }

    private void initLaudoECG(ConteudoECG12 conteudoEcg) {
        config = new ECG12Config();

        // Valores da tela
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
        setResizable(true);
        this.setLocation(0, 0);
        setSize(config.getWidth(), config.getHeight());
        setBackground(Color.WHITE);

        // Configura��o do ECG
        if (conteudoEcg != null) {
            config.setModo(conteudoEcg.getModo());
            config.setN_Registros(conteudoEcg.getN_Registros());
            config.setAmostragem(conteudoEcg.getAmostragem());
            config.setSensibilidade(conteudoEcg.getSensibilidade());
            this.amostras = conteudoEcg.getAmostras();
            this.ganho = conteudoEcg.getGanho();
            conteudoEcg = null;
        }

        setTitle("Traçado - Registro Nº: " + config.getReg_Sel() + "/" + config.getN_Registros() + " - Escala: 25mm/s, 10 mm/mV - Modo: I");

        tracado = new TracadoECG(config.getAmostragem());

//---------------------Panel---------------------//
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        scroll = new JScrollPane(new ImagePanel(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scroll, BorderLayout.CENTER);
        addKeyListener(this);
    }

    // Revisar Metodo!!!!!
    public int[][] registrar() {
        int[][] registro = new int[12][tracado.DI().length];

        int a = 0;

        for (int i = count; i < tracado.DI().length; i++) {
            registro[0][a] = tracado.DI()[i];
            registro[1][a] = tracado.DII()[i];
            registro[2][a] = tracado.DIII()[i];
            registro[3][a] = tracado.aVR()[i];
            registro[4][a] = tracado.aVL()[i];
            registro[5][a] = tracado.aVF()[i];
            registro[6][a] = tracado.V1_10()[i];
            registro[7][a] = tracado.V2_10()[i];
            registro[8][a] = tracado.V3_10()[i];
            registro[9][a] = tracado.V4_10()[i];
            registro[10][a] = tracado.V5_10()[i];
            registro[11][a] = tracado.V6_10()[i];
            a++;
        }

        for (int i = 0; i < count; i++) {
            registro[0][a] = tracado.DI()[i];
            registro[1][a] = tracado.DII()[i];
            registro[2][a] = tracado.DIII()[i];
            registro[3][a] = tracado.aVR()[i];
            registro[4][a] = tracado.aVL()[i];
            registro[5][a] = tracado.aVF()[i];
            registro[6][a] = tracado.V1_10()[i];
            registro[7][a] = tracado.V2_10()[i];
            registro[8][a] = tracado.V3_10()[i];
            registro[9][a] = tracado.V4_10()[i];
            registro[10][a] = tracado.V5_10()[i];
            registro[11][a] = tracado.V6_10()[i];
            a++;
        }

        return registro;
    }

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public void mostrarTracado(int[][] Derivacoes) {
        tracado.preencherAmostras(Derivacoes);
    }

    public void setAmostras1(int[] Derivacoes) {
        tracado.DI()[count] = Derivacoes[0];
        tracado.DII()[count] = Derivacoes[1];
        tracado.DIII()[count] = Derivacoes[2];
        tracado.aVR()[count] = Derivacoes[3];
        tracado.aVL()[count] = Derivacoes[4];
        tracado.aVF()[count] = Derivacoes[5];
        tracado.V1_10()[count] = Derivacoes[6];
        tracado.V2_10()[count] = Derivacoes[7];
        tracado.V3_10()[count] = Derivacoes[8];
        tracado.V4_10()[count] = Derivacoes[9];
        tracado.V5_10()[count] = Derivacoes[10];
        tracado.V6_10()[count] = Derivacoes[11];
        count++;

        if (((config.getAmostragem() == 300) && (count >= 3600)) || ((config.getAmostragem() == 1200) && (count >= 14400))) {
            count = 0;
        }
    }

    public void resetView() {
        tracado.init();
        count = 0;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    private class ImagePanel extends JPanel implements ActionListener, Printable, MouseListener, MouseMotionListener, MouseWheelListener {

        JPopupMenu popupmenu;
        boolean pink = false;
        boolean blue = false;
        boolean ControlPressed = false;
        double RR = 0, QT = 0, QTc = 0, FC = 0;

        public ImagePanel() {
            createMenu();
            addMouseListener(this);
            addMouseMotionListener(this);
            addMouseWheelListener(this);
            setCursor(Cursor.getPredefinedCursor(1));
        }

        private void createMenu() {
            JMenuItem mi;
            setBackground(Color.WHITE);
            popupmenu = new JPopupMenu("Registros");
            for (int i = 0; i < config.getN_Registros(); i++) {
                int x = i + 1;
                mi = new JMenuItem("Registro: " + x);
                mi.addActionListener(this);
                popupmenu.add(mi);
            }
            popupmenu.addSeparator();

            mi = new JMenuItem("Escala: 10 mm/mV");
            mi.addActionListener(this);
            popupmenu.add(mi);

            mi = new JMenuItem("Escala: 5 mm/mV");
            mi.addActionListener(this);
            popupmenu.add(mi);

            mi = new JMenuItem("Escala: 2.5 mm/mV");
            mi.addActionListener(this);
            popupmenu.add(mi);

            popupmenu.addSeparator();

            mi = new JMenuItem("Modo: I");
            mi.addActionListener(this);
            popupmenu.add(mi);

            mi = new JMenuItem("Modo: II");
            mi.addActionListener(this);
            popupmenu.add(mi);

            popupmenu.addSeparator();

            mi = new JMenuItem("Armazenar R-R");
            mi.addActionListener(this);
            popupmenu.add(mi);

            mi = new JMenuItem("Armazenar QT");
            mi.addActionListener(this);
            popupmenu.add(mi);

            mi = new JMenuItem("Limpar Medidas");
            mi.addActionListener(this);
            popupmenu.add(mi);

            popupmenu.addSeparator();

            mi = new JMenuItem("Imprimir");
            mi.addActionListener(this);
            popupmenu.add(mi);
        }

        private boolean getClipValue(Graphics g, int X, int Y) {
            boolean clip = false;
            if (Math.abs(mxs2 - mxs1) > Math.abs(mys2 - mys1)) {
                if (mxs2 > mxs1) {
                    g.setClip(mxs1, 0, (mxs2 - mxs1), config.getHeight());
                    clip = true;
                } else if (mxs2 > 0) {//******************************
                    g.setClip(mxs2, 0, (mxs1 - mxs2), config.getHeight());
                    clip = true;
                }
            } else if (Math.abs(mys2 - mys1) > Math.abs(mxs2 - mxs1)) {
                if (mys2 > mys1) {
                    g.setClip(0, mys1, config.getWidth(), (mys2 - mys1));
                    clip = true;
                } else if (mys2 > 0) {
                    g.setClip(0, mys2, config.getWidth(), (mys1 - mys2));
                    clip = true;
                }
            } else if (mxs2 > mxs1) {
                if (mxs2 > mxs1) {
                    g.setClip(mxs1, 0, (mxs2 - mxs1), config.getHeight());
                    clip = true;
                } else if (mxs2 > 0) {
                    g.setClip(mxs2, 0, (mxs1 - mxs2), config.getHeight());
                    clip = true;
                }
            }
            return clip;
        }

        private void desenharGridPadrao(Graphics g, int X, int Y) {
            int y2 = Y + 720 * Zoom;
            int x2 = X + 1200 * Zoom;

            int zoom4 = 4 * Zoom;
            int zoom20 = 20 * Zoom;

            g.setColor(config.getC1());
            for (int i = 0; i < 300; ++i) {
                g.drawLine(X + (int) (i * zoom4), Y, X + (int) (i * zoom4), y2);
            }
            for (int i = 0; i < 180; ++i) {
                g.drawLine(X, Y + (int) (i * zoom4), x2, Y + (int) (i * zoom4));
            }

            g.setColor(config.getC2());
            for (int i = 0; i < 60; ++i) {
                g.drawLine(X + (int) (i * zoom20), Y, X + (int) (i * zoom20), y2);
            }

            for (int i = 0; i < 37; ++i) {
                g.drawLine(X, Y + (int) (i * zoom20), x2, Y + (int) (i * zoom20));
            }
        }

        private void desenharGridCinza(Graphics g, int X, int Y) {
            int y2 = Y + 720 * Zoom;
            int x2 = X + 1200 * Zoom;

            int zoom4 = 4 * Zoom;
            int zoom20 = 20 * Zoom;

            g.setColor(Color.DARK_GRAY);
            for (int i = 0; i < 300; ++i) {
                g.drawLine(X + (i * zoom4), Y, X + (i * zoom4), Y + 720 * Zoom);
            }
            for (int i = 0; i < 180; ++i) {
                g.drawLine(X, Y + (i * zoom4), X + 1200 * Zoom, Y + (i * zoom4));
            }

            g.setColor(Color.GRAY);
            for (int i = 0; i < 60; ++i) {
                g.drawLine(X + (i * zoom20), Y, X + (i * zoom20), Y + (720 * Zoom));
            }

            for (int i = 0; i < 37; ++i) {
                g.drawLine(X, Y + (i * zoom20), X + (1200 * Zoom), Y + (i * zoom20));
            }
        }

        private void preencherTitulo() {
            String title = "Traçado - Registro Nº: " + config.getReg_Sel() + "/" + config.getN_Registros() + " - Escala: 25mm/s, #ESCALA# mm/mV - Modo: #MODO#";
            title = title.replace("#ESCALA#", String.valueOf((int) config.getEscala()));
            title = title.replace("#MODO#", config.getModo() == 0 ? "I" : "II");
            setTitle(title);
        }

        public void ecgFrame(Graphics g, int X, int Y) {

            g.setClip(0, 0, config.getWidth(), config.getHeight());

            if (Math.abs(mxs2 - mxs1) > Math.abs(mys2 - mys1)) {
                g.setColor(Color.black);
                if (mxs2 > mxs1) {
                    g.fillRect(mxs1, Y, mxs2 - mxs1, 720 * Zoom);
                } else if (mxs2 > 0) {
                    g.fillRect(mxs2, Y, mxs1 - mxs2, 720 * Zoom);
                }

            } else if (Math.abs(mys2 - mys1) > Math.abs(mxs2 - mxs1)) {
                g.setColor(Color.black);
                if (mys2 > mys1) {
                    g.fillRect(X, mys1, 1200 * Zoom, mys2 - mys1);
                } else if (mys2 > 0) {
                    g.fillRect(X, mys2, 1200 * Zoom, mys1 - mys2);
                }
            } else if (mxs2 > mxs1) {
                g.fillRect(mxs1, Y, mxs2 - mxs1, 720 * Zoom);
            } else if (mxs2 > 0) {
                g.fillRect(mxs2, Y, mxs1 - mxs2, 720 * Zoom);
            } else {
                mxs1 = 0;
                mys1 = 0;
                mxs2 = 0;
                mys2 = 0;
                Selecao = false;
            }

            desenharGridPadrao(g, X, Y);

            boolean clip = getClipValue(g, X, Y);

            if (clip) {
                desenharGridCinza(g, X, Y);
            }
            g.setClip(0, 0, config.getWidth(), config.getHeight());

            g.setColor(config.getC2());
            g.drawRect(X, Y, 1200 * Zoom - 1, 720 * Zoom - 1);
            g.drawRect(X - 1, Y - 1, 1200 * Zoom + 1, 720 * Zoom + 2);

            // Desenha grid de divisao das amostras
            if (config.getModo() == 0) {
                g.drawLine(X, Y + (140 * Zoom), 1200 * Zoom, Y + 140 * Zoom);
                g.drawLine(X, Y + 280 * Zoom, 1200 * Zoom, Y + 280 * Zoom);
                g.drawLine(X, Y + 420 * Zoom, 1200 * Zoom, Y + 420 * Zoom);
                g.drawLine(X, Y + 560 * Zoom, 1200 * Zoom, Y + 560 * Zoom);
                g.drawLine(X + 400 * Zoom, Y, X + 400 * Zoom, 560 * Zoom);
                g.drawLine(X + 800 * Zoom, Y, X + 800 * Zoom, 560 * Zoom);

                g.drawLine(X, Y + (140 * Zoom + 1), 1200 * Zoom, Y + 140 * Zoom + 1);
                g.drawLine(X, Y + 280 * Zoom + 1, 1200 * Zoom, Y + 280 * Zoom + 1);
                g.drawLine(X, Y + 420 * Zoom + 1, 1200 * Zoom, Y + 420 * Zoom + 1);
                g.drawLine(X, Y + 560 * Zoom + 1, 1200 * Zoom, Y + 560 * Zoom + 1);
                g.drawLine(X + 400 * Zoom + 1, Y, X + 400 * Zoom + 1, 560 * Zoom);
                g.drawLine(X + 800 * Zoom + 1, Y, X + 800 * Zoom + 1, 560 * Zoom);
            }

            /*            if(Modo==0){
             g.drawLine(X, Y + (180*Zoom), 1200*Zoom, Y + 180*Zoom);
             g.drawLine(X, Y + 360*Zoom, 1200*Zoom, Y + 360*Zoom);
             g.drawLine(X, Y + 540*Zoom, 1200*Zoom, Y + 540*Zoom);
             g.drawLine(X + 400*Zoom, Y, X + 400*Zoom, 720*Zoom);
             g.drawLine(X + 800*Zoom, Y, X + 800*Zoom, 720*Zoom);

             g.drawLine(X, Y + (180*Zoom +1), 1200*Zoom, Y + 180*Zoom + 1);
             g.drawLine(X, Y + 360*Zoom + 1, 1200*Zoom, Y + 360*Zoom + 1);
             g.drawLine(X, Y + 540*Zoom + 1, 1200*Zoom, Y + 540*Zoom + 1);
             g.drawLine(X + 400*Zoom + 1, Y, X + 400*Zoom + 1, 720*Zoom);
             g.drawLine(X + 800*Zoom + 1, Y, X + 800*Zoom + 1, 720*Zoom);
             }*/
        }

        private void imprimeGraficoModo_0(Graphics g, int X, int Y) {
            // Definicao de falores que seram constantes para os calculos de plotagem do grafico
            float sensibilidade = config.getSensibilidade() * 4 * Zoom * config.getEscala() / 1000;
            int percentualAmostragem = config.getAmostragem() / 100;
            int zoomAux = 0;
            int zoomMaisAux = 0;
            int x_400 = X + 400 * Zoom;
            int x_800 = X + 800 * Zoom;
            int y_70 = Y + 70 * Zoom;
            int y_210 = Y + 210 * Zoom;
            int y_350 = Y + 350 * Zoom;
            int y_490 = Y + 490 * Zoom;
            int y_640 = Y + 640 * Zoom;

            for (int i = 0; i < 400 * percentualAmostragem; ++i) {
                zoomAux = i * Zoom / percentualAmostragem;
                zoomMaisAux = (i + 1) * Zoom / percentualAmostragem;
                //------------------------------Plotando DI----------------------------------//
                g.drawLine(zoomAux + X, (int) (-tracado.DI()[i] * sensibilidade) + y_70, zoomMaisAux + X, (int) (-tracado.DI()[i + 1] * sensibilidade) + y_70);
                //------------------------------Plotando DII----------------------------------//
                g.drawLine(zoomAux + x_400, (int) (-tracado.DII()[i] * sensibilidade) + y_70, zoomMaisAux + x_400, (int) (-tracado.DII()[i + 1] * sensibilidade) + y_70);

                //------------------------------Plotando DIII---------------------------------//
                g.drawLine(zoomAux + x_800, (int) (-tracado.DIII()[i] * sensibilidade) + y_70, zoomMaisAux + x_800, (int) (-tracado.DIII()[i + 1] * sensibilidade) + y_70);

                //------------------------------Plotando aVR----------------------------------//
                g.drawLine(zoomAux + X, (int) (-tracado.aVR()[i] * sensibilidade) + y_210, zoomMaisAux + X, (int) (-tracado.aVR()[i + 1] * sensibilidade) + y_210);

                //------------------------------Plotando aVL----------------------------------//
                g.drawLine(zoomAux + x_400, (int) (-tracado.aVL()[i] * sensibilidade) + y_210, zoomMaisAux + x_400, (int) (-tracado.aVL()[i + 1] * sensibilidade) + y_210);

                //------------------------------Plotando aVF---------------------------------//
                g.drawLine(zoomAux + x_800, (int) (-tracado.aVF()[i] * sensibilidade) + y_210, zoomMaisAux + x_800, (int) (-tracado.aVF()[i + 1] * sensibilidade) + y_210);

                //------------------------------Plotando V1-----------------------------------//
                g.drawLine(zoomAux + X, (int) (-tracado.V1_10()[i] * sensibilidade) + y_350, zoomMaisAux + X, (int) (-tracado.V1_10()[i + 1] * sensibilidade) + y_350);

                //------------------------------Plotando V2-----------------------------------//
                g.drawLine(zoomAux + x_400, (int) (-tracado.V2_10()[i] * sensibilidade) + y_350, zoomMaisAux + x_400, (int) (-tracado.V2_10()[i + 1] * sensibilidade) + y_350);

                //------------------------------Plotando V3---------------------------------//
                g.drawLine(zoomAux + x_800, (int) (-tracado.V3_10()[i] * sensibilidade) + y_350, zoomMaisAux + x_800, (int) (-tracado.V3_10()[i + 1] * sensibilidade) + y_350);

                //------------------------------Plotando V4----------------------------------//
                g.drawLine(zoomAux + X, (int) (-tracado.V4_10()[i] * sensibilidade) + y_490, zoomMaisAux + X, (int) (-tracado.V4_10()[i + 1] * sensibilidade) + y_490);

                //------------------------------Plotando V5----------------------------------//
                g.drawLine(zoomAux + x_400, (int) (-tracado.V5_10()[i] * sensibilidade) + y_490, zoomMaisAux + x_400, (int) (-tracado.V5_10()[i + 1] * sensibilidade) + y_490);

                //------------------------------Plotando V6---------------------------------//
                g.drawLine(zoomAux + x_800, (int) (-tracado.V6_10()[i] * sensibilidade) + y_490, zoomMaisAux + x_800, (int) (-tracado.V6_10()[i + 1] * sensibilidade) + y_490);
            }

            //---------------------------Plotando DII Longo-------------------------------//                                        
            for (int i = 0; i < 1200 * percentualAmostragem; ++i) {
                g.drawLine((i * Zoom / percentualAmostragem) + X, (int) (-tracado.DII()[i] * sensibilidade) + y_640, ((i + 1) * Zoom / percentualAmostragem) + X, (int) (-tracado.DII()[i + 1] * sensibilidade) + y_640);
            }
        }

        private void imprimeGraficoModo_1(Graphics g, int X, int Y) {
            ///////////////////////////PLOTANDO AMOSTRAGEM COMPLETA/////////////////////////
            // Definicao de falores que seram constantes para os calculos de plotagem do grafico
            float sensibilidade = config.getSensibilidade() * 4 * Zoom * config.getEscala() / 1000;
            int percentualAmostragem = config.getAmostragem() / 100;
            int zoomAux = 0;
            int zoomMaisAux = 0;
            int y_40 = Y + 40 * Zoom;
            int y_100 = Y + 100 * Zoom;
            int y_160 = Y + 160 * Zoom;
            int y_220 = Y + 220 * Zoom;
            int y_280 = Y + 280 * Zoom;
            int y_340 = Y + 340 * Zoom;
            int y_400 = Y + 400 * Zoom;
            int y_460 = Y + 460 * Zoom;
            int y_520 = Y + 520 * Zoom;
            int y_580 = Y + 580 * Zoom;
            int y_640 = Y + 640 * Zoom;
            int y_700 = Y + 700 * Zoom;
            for (int i = 0; i < 1200 * percentualAmostragem; ++i) {
                zoomAux = (i * Zoom / percentualAmostragem) + X;
                zoomMaisAux = ((i + 1) * Zoom / percentualAmostragem) + X;
                //------------------------------Plotando DI----------------------------------//
                g.drawLine(zoomAux, (int) (-tracado.DI()[i] * sensibilidade) + y_40, zoomMaisAux, (int) (-tracado.DI()[i + 1] * sensibilidade) + y_40);

                //------------------------------Plotando DII----------------------------------//
                g.drawLine(zoomAux, (int) (-tracado.DII()[i] * sensibilidade) + y_100, zoomMaisAux, (int) (-tracado.DII()[i + 1] * sensibilidade) + y_100);

                //------------------------------Plotando DIII---------------------------------//
                g.drawLine(zoomAux, (int) (-tracado.DIII()[i] * sensibilidade) + y_160, zoomMaisAux, (int) (-tracado.DIII()[i + 1] * sensibilidade) + y_160);

                //------------------------------Plotando aVR----------------------------------//
                g.drawLine(zoomAux, (int) (-tracado.aVR()[i] * sensibilidade) + y_220, zoomMaisAux, (int) (-tracado.aVR()[i + 1] * sensibilidade) + y_220);

                //------------------------------Plotando aVL----------------------------------//
                g.drawLine(zoomAux, (int) (-tracado.aVL()[i] * sensibilidade) + y_280, zoomMaisAux, (int) (-tracado.aVL()[i + 1] * sensibilidade) + y_280);

                //------------------------------Plotando aVF---------------------------------//
                g.drawLine(zoomAux, (int) (-tracado.aVF()[i] * sensibilidade) + y_340, zoomMaisAux, (int) (-tracado.aVF()[i + 1] * sensibilidade) + y_340);

                //------------------------------Plotando V1-----------------------------------//
                g.drawLine(zoomAux, (int) (-tracado.V1_10()[i] * sensibilidade) + y_400, zoomMaisAux, (int) (-tracado.V1_10()[i + 1] * sensibilidade) + y_400);

                //------------------------------Plotando V2-----------------------------------//
                g.drawLine(zoomAux, (int) (-tracado.V2_10()[i] * sensibilidade) + y_460, zoomMaisAux, (int) (-tracado.V2_10()[i + 1] * sensibilidade) + y_460);

                //------------------------------Plotando V3---------------------------------//
                g.drawLine(zoomAux, (int) (-tracado.V3_10()[i] * sensibilidade) + y_520, zoomMaisAux, (int) (-tracado.V3_10()[i + 1] * sensibilidade) + y_520);

                //------------------------------Plotando V4----------------------------------//
                g.drawLine(zoomAux, (int) (-tracado.V4_10()[i] * sensibilidade) + y_580, zoomMaisAux, (int) (-tracado.V4_10()[i + 1] * sensibilidade) + y_580);

                //------------------------------Plotando V5----------------------------------//
                g.drawLine(zoomAux, (int) (-tracado.V5_10()[i] * sensibilidade) + y_640, zoomMaisAux, (int) (-tracado.V5_10()[i + 1] * sensibilidade) + y_640);

                //------------------------------Plotando V6---------------------------------//
                g.drawLine(zoomAux, (int) (-tracado.V6_10()[i] * sensibilidade) + y_700, zoomMaisAux, (int) (-tracado.V6_10()[i + 1] * sensibilidade) + y_700);
            }
        }

        private void imprimeGrafico(Graphics2D g2d, int Margin_X, int Margin_Y) {
            float sensibilidade = config.getSensibilidade() * ppi_to_mm_print * Zoom * config.getEscala() / 1000;
            float ppi_vel_zoom = Zoom * velocidade * ppi_to_mm_print;
            float ppi_to_mm_por_4 = ppi_to_mm_print / 4;
            float ppi_por_4_x_Zoom = ppi_to_mm_por_4 * Zoom;
            float ppi_360 = 360 * ppi_por_4_x_Zoom;
            float ppi_720 = 720 * ppi_por_4_x_Zoom;
            float y_ppi_70 = Margin_Y + 70 * ppi_por_4_x_Zoom;
            float y_ppi_210 = Margin_Y + 210 * ppi_por_4_x_Zoom;
            float y_ppi_350 = Margin_Y + 350 * ppi_por_4_x_Zoom;
            float y_ppi_490 = Margin_Y + 490 * ppi_por_4_x_Zoom;
            float limiteFor = (ppi_to_mm_por_4) * (config.getAmostragem() / (velocidade * ppi_to_mm_print));

            for (int i = 0; i < 360 * limiteFor; i++) {
                float zoomAux = ((i * ppi_vel_zoom) / config.getAmostragem()) + Margin_X;
                float zoomMaisAux = ((i + 1) * ppi_vel_zoom) / config.getAmostragem() + Margin_X;

//-------------------------------Plotando DI----------------------------------//           
                g2d.draw(new Line2D.Double(zoomAux, (-tracado.DI()[i] * sensibilidade) + y_ppi_70, zoomMaisAux, (-tracado.DI()[i + 1] * sensibilidade) + y_ppi_70));

//------------------------------Plotando DII----------------------------------//                  
                g2d.draw(new Line2D.Double(zoomAux + ppi_360, (-tracado.DII()[i] * sensibilidade) + y_ppi_70, zoomMaisAux + ppi_360, (-tracado.DII()[i + 1] * sensibilidade) + y_ppi_70));

//------------------------------Plotando DIII---------------------------------//
                g2d.draw(new Line2D.Double(zoomAux + ppi_720, (-tracado.DIII()[i] * sensibilidade) + y_ppi_70, zoomMaisAux + ppi_720, (-tracado.DIII()[i + 1] * sensibilidade) + y_ppi_70));

//------------------------------Plotando aVR----------------------------------//
                g2d.draw(new Line2D.Double(zoomAux, (-tracado.aVR()[i] * sensibilidade) + y_ppi_210, zoomMaisAux, (-tracado.aVR()[i + 1] * sensibilidade) + y_ppi_210));

//------------------------------Plotando aVL----------------------------------//
                g2d.draw(new Line2D.Double(zoomAux + ppi_360, (-tracado.aVL()[i] * sensibilidade) + y_ppi_210, zoomMaisAux + ppi_360, (-tracado.aVL()[i + 1] * sensibilidade) + y_ppi_210));

//-------------------------------Plotando aVF---------------------------------//
                g2d.draw(new Line2D.Double(zoomAux + ppi_720, (-tracado.aVF()[i] * sensibilidade) + y_ppi_210, zoomMaisAux + ppi_720, (-tracado.aVF()[i + 1] * sensibilidade) + y_ppi_210));

//------------------------------Plotando V1-----------------------------------//
                g2d.draw(new Line2D.Double(zoomAux, (-tracado.V1_10()[i] * sensibilidade) + y_ppi_350, zoomMaisAux, (-tracado.V1_10()[i + 1] * sensibilidade) + y_ppi_350));

//------------------------------Plotando V2-----------------------------------//
                g2d.draw(new Line2D.Double(zoomAux + ppi_360, (-tracado.V2_10()[i] * sensibilidade) + y_ppi_350, zoomMaisAux + ppi_360, (-tracado.V2_10()[i + 1] * sensibilidade) + y_ppi_350));

//--------------------------------Plotando V3---------------------------------//
                g2d.draw(new Line2D.Double(zoomAux + ppi_720, (-tracado.V3_10()[i] * sensibilidade) + y_ppi_350, zoomMaisAux + ppi_720, (-tracado.V3_10()[i + 1] * sensibilidade) + y_ppi_350));

//-------------------------------Plotando V4----------------------------------//
                g2d.draw(new Line2D.Double(zoomAux, (-tracado.V4_10()[i] * sensibilidade) + y_ppi_490, zoomMaisAux, (-tracado.V4_10()[i + 1] * sensibilidade) + y_ppi_490));

//-------------------------------Plotando V5----------------------------------//
                g2d.draw(new Line2D.Double(zoomAux + ppi_360, (-tracado.V5_10()[i] * sensibilidade) + y_ppi_490, zoomMaisAux + ppi_360, (-tracado.V5_10()[i + 1] * sensibilidade) + y_ppi_490));

//--------------------------------Plotando V6---------------------------------//
                g2d.draw(new Line2D.Double(zoomAux + ppi_720, (-tracado.V6_10()[i] * sensibilidade) + y_ppi_490, zoomMaisAux + ppi_720, (-tracado.V6_10()[i + 1] * sensibilidade) + y_ppi_490));
            }
//---------------------------Plotando DII Longo-------------------------------//                                        
            for (int i = 0; i < 1200 * limiteFor; i++) {
                float zoomAux = (i * ppi_vel_zoom) / config.getAmostragem() + Margin_X;
                float zoomMaisAux = ((i + 1) * ppi_vel_zoom) / config.getAmostragem() + Margin_X;
                g2d.draw(new Line2D.Double(zoomAux,
                        (-tracado.DII()[i] * sensibilidade) + Margin_Y + 620 * ppi_por_4_x_Zoom,
                        zoomMaisAux,
                        (-tracado.DII()[i + 1] * sensibilidade) + Margin_Y + 620 * ppi_por_4_x_Zoom));
            }
        }

        private void imprimeGanho(Graphics g, int x_1, int x_2, int x_3,
                int y_1, int y_2, int y_3, int y_4, int y_5, int y_6, int y_7, int y_8, int y_9, int y_10, int y_11, int y_12) {
            g.setColor(Color.WHITE);
            g.fillRect(X + x_1 * Zoom - 4, Y + y_1 * Zoom - 11, 40, 13);
            g.fillRect(X + x_2 * Zoom - 4, Y + y_2 * Zoom - 11, 42, 13);
            g.fillRect(X + x_3 * Zoom - 4, Y + y_3 * Zoom - 11, 44, 13);
            g.fillRect(X + x_1 * Zoom - 4, Y + y_4 * Zoom - 11, 49, 13);
            g.fillRect(X + x_2 * Zoom - 4, Y + y_5 * Zoom - 11, 47, 13);
            g.fillRect(X + x_3 * Zoom - 4, Y + y_6 * Zoom - 11, 47, 13);
            g.fillRect(X + x_1 * Zoom - 4, Y + y_7 * Zoom - 11, 40, 13);
            g.fillRect(X + x_2 * Zoom - 4, Y + y_8 * Zoom - 11, 40, 13);
            g.fillRect(X + x_3 * Zoom - 4, Y + y_9 * Zoom - 11, 40, 13);
            g.fillRect(X + x_1 * Zoom - 4, Y + y_10 * Zoom - 11, 40, 13);
            g.fillRect(X + x_2 * Zoom - 4, Y + y_11 * Zoom - 11, 40, 13);
            g.fillRect(X + x_3 * Zoom - 4, Y + y_12 * Zoom - 11, 40, 13);

            g.setColor(Color.BLACK);

            g.drawRect(X + x_1 * Zoom - 4, Y + y_1 * Zoom - 11, 40, 13);
            g.drawString("DI " + ganho[0][config.getReg_Sel() - 1], X + x_1 * Zoom, Y + y_1 * Zoom);
            g.drawRect(X + x_2 * Zoom - 4, Y + y_2 * Zoom - 11, 42, 13);
            g.drawString("DII " + ganho[1][config.getReg_Sel() - 1], X + x_2 * Zoom, Y + y_2 * Zoom);
            g.drawRect(X + x_3 * Zoom - 4, Y + y_3 * Zoom - 11, 44, 13);
            g.drawString("DIII " + ganho[2][config.getReg_Sel() - 1], X + x_3 * Zoom, Y + y_3 * Zoom);

            g.drawRect(X + x_1 * Zoom - 4, Y + y_4 * Zoom - 11, 49, 13);
            g.drawString("aVR " + ganho[3][config.getReg_Sel() - 1], X + x_1 * Zoom, Y + y_4 * Zoom);
            g.drawRect(X + x_2 * Zoom - 4, Y + y_5 * Zoom - 11, 47, 13);
            g.drawString("aVL " + ganho[4][config.getReg_Sel() - 1], X + x_2 * Zoom, Y + y_5 * Zoom);
            g.drawRect(X + x_3 * Zoom - 4, Y + y_6 * Zoom - 11, 47, 13);
            g.drawString("aVF " + ganho[5][config.getReg_Sel() - 1], X + x_3 * Zoom, Y + y_6 * Zoom);

            g.drawRect(X + x_1 * Zoom - 4, Y + y_7 * Zoom - 11, 40, 13);
            g.drawString("V1 " + ganho[6][config.getReg_Sel() - 1], X + x_1 * Zoom, Y + y_7 * Zoom);
            g.drawRect(X + x_2 * Zoom - 4, Y + y_8 * Zoom - 11, 40, 13);
            g.drawString("V2 " + ganho[7][config.getReg_Sel() - 1], X + x_2 * Zoom, Y + y_8 * Zoom);
            g.drawRect(X + x_3 * Zoom - 4, Y + y_9 * Zoom - 11, 40, 13);
            g.drawString("V3 " + ganho[8][config.getReg_Sel() - 1], X + x_3 * Zoom, Y + y_9 * Zoom);

            g.drawRect(X + x_1 * Zoom - 4, Y + y_10 * Zoom - 11, 40, 13);
            g.drawString("V4 " + ganho[9][config.getReg_Sel() - 1], X + x_1 * Zoom, Y + y_10 * Zoom);
            g.drawRect(X + x_2 * Zoom - 4, Y + y_11 * Zoom - 11, 40, 13);
            g.drawString("V5 " + ganho[10][config.getReg_Sel() - 1], X + x_2 * Zoom, Y + y_11 * Zoom);
            g.drawRect(X + x_3 * Zoom - 4, Y + y_12 * Zoom - 11, 40, 13);
            g.drawString("V6 " + ganho[11][config.getReg_Sel() - 1], X + x_3 * Zoom, Y + y_12 * Zoom);
        }

        public void plot(Graphics g, int X, int Y) {

            g.setClip(0, 0, config.getWidth(), config.getHeight());

            g.setColor(Color.BLACK);

            if (config.getModo() == 0) {
                imprimeGraficoModo_0(g, X, Y);

                boolean clip = getClipValue(g, X, Y);

                if (clip) {
                    g.setColor(Color.WHITE);
                    imprimeGraficoModo_0(g, X, Y);
                }
            } else if (config.getModo() == 1) {
                g.setColor(Color.BLACK);

///////////////////////////PLOTANDO AMOSTRAGEM COMPLETA/////////////////////////
                imprimeGraficoModo_1(g, X, Y);

                boolean clip = getClipValue(g, X, Y);

                if (clip) {
                    g.setColor(Color.WHITE);
                    imprimeGraficoModo_1(g, X, Y);
                }
            }

            g.setClip(0, 0, config.getWidth(), config.getHeight());

            if (config.getModo() == 0) {
                imprimeGanho(g, 20, 420, 820, 20, 20, 20, 160, 160, 160, 300, 300, 300, 440, 440, 440);
                g.setColor(Color.WHITE);
                g.fillRect(X + 20 * Zoom - 4, Y + 580 * Zoom - 11, 80, 13);
                g.setColor(Color.BLACK);

                g.drawRect(X + 20 * Zoom - 4, Y + 580 * Zoom - 11, 80, 13);
                g.drawString("DII Longo " + ganho[1][config.getReg_Sel() - 1], X + 20 * Zoom, Y + 580 * Zoom);

            } else if (config.getModo() == 1) {
                imprimeGanho(g, 20, 20, 20, 20, 80, 140, 200, 260, 320, 380, 440, 500, 560, 620, 680);
            }
        }

        public void Cursor(Graphics g, int X, int Y) {
            g.setClip(0, 0, config.getWidth(), config.getHeight());
            g.setColor(Color.BLACK);
            if ((mx <= getWidth()) && (my <= getHeight())) {
                g.drawLine(mx - 1, 0, mx - 1, config.getHeight());
                g.drawLine(0, my - 1, config.getWidth(), my - 1);
            }

            boolean clip = getClipValue(g, X, Y);

            if (clip) {
                g.setColor(Color.WHITE);
                if ((mx <= getWidth()) && (my <= getHeight())) {
                    g.drawLine(mx - 1, 0, mx - 1, config.getHeight());
                    g.drawLine(0, my - 1, config.getWidth(), my - 1);
                }
            }
        }

        public void Medidas(Graphics g, int X, int Y) {
            if ((RR > 0) || (QT > 0) || (QTc > 0) || (FC > 0)) {
                g.setClip(mx + 20, my + 55, 140, 120);
                g.setColor(Color.WHITE);
                g.fillRect(mx + 20, my + 55, 140, 120);
                g.setColor(Color.BLACK);
                g.drawRect(mx + 20, my + 55, 139, 119);
                g.drawString("RR: " + NumberFormat.getNumberInstance().format(RR) + " ms", mx + 30, my + 73);
                g.drawString("QT: " + NumberFormat.getNumberInstance().format(QT) + " ms", mx + 30, my + 103);
                g.drawString("QTc: " + NumberFormat.getNumberInstance().format(QTc) + " ms", mx + 30, my + 133);
                g.drawString("FC: " + NumberFormat.getNumberInstance().format(FC) + " bpm", mx + 30, my + 163);
            }

            g.setColor(Color.BLACK);
            if (Math.abs(mxs2 - mxs1) > Math.abs(mys2 - mys1)) {
                if ((mxs2 > mxs1) || (mxs2 > 0)) {
                    g.setClip(mx + 20, my + 20, 140, 30);
                    g.setColor(Color.WHITE);
                    g.fillRect(mx + 20, my + 20, 140, 30);
                    g.setColor(Color.BLACK);
                    g.drawRect(mx + 20, my + 20, 139, 29);
                    g.drawString("Medida: " + NumberFormat.getNumberInstance().format((double) ((mxs2 > mxs1) ? (mxs2 - mxs1) : (mxs1 - mxs2)) * 1000 / (4 * 25 * (double) Zoom)) + " ms", mx + 30, my + 40);
                }
            } else if (Math.abs(mys2 - mys1) > Math.abs(mxs2 - mxs1)) {
                if ((mys2 > mys1) || (mys2 > 0)) {
                    g.setClip(mx + 20, my + 20, 120, 30);
                    g.setColor(Color.WHITE);
                    g.fillRect(mx + 20, my + 20, 120, 30);
                    g.setColor(Color.BLACK);
                    g.drawRect(mx + 20, my + 20, 119, 29);
                    g.drawString("Medida: " + NumberFormat.getNumberInstance().format((double) ((double) ((mys2 > mys1) ? (mys2 - mys1) : (mys1 - mys2)) / (4 * (double) Zoom * (double) config.getEscala()))) + " mv", mx + 30, my + 40);

                }
            } else if (mxs2 > mxs1) {
                g.setClip(mx + 20, my + 20, 140, 30);
                g.setColor(Color.WHITE);
                g.fillRect(mx + 20, my + 20, 140, 30);
                g.setColor(Color.BLACK);
                g.drawRect(mx + 20, my + 20, 139, 29);
                g.drawString("Medida: " + NumberFormat.getNumberInstance().format((double) (mxs2 - mxs1) * 1000 / (4 * 25 * (double) Zoom)) + " ms", mx + 30, my + 40);

            }
            if (Zoom > 1) {
                g.setClip(getWidth() - 150, getHeight() - 40, 150, 30);
                g.setColor(Color.RED);
                Font minhaFonte = new Font("Arial", Font.PLAIN, 20);
                g.setFont(minhaFonte);
                g.drawString("Zoom: " + Zoom + "x", getWidth() - 120, getHeight() - 20);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (ui != null) {
                Graphics scratchGraphics = (g == null) ? null : g.create();
                try {
                    ui.update(scratchGraphics, this);
                } finally {
                    scratchGraphics.dispose();
                }
            }

//////////////////////////Calcula dimensões máximas/////////////////////////////
            if (getWidth() < (X + 1200 * Zoom)) {
                config.setWidth(getWidth());
            } else {
                config.setWidth(X + 1200 * Zoom + 1);
            }

            if (getHeight() < (Y + 720 * Zoom)) {
                config.setHeight(getHeight());
            } else {
                config.setHeight(Y + 720 * Zoom + 1);
            }

////////////////////////////////////////////////////////////////////////////////
//            Graphics2D g2d = (Graphics2D) g;
//            //g2d.translate (pageFormat.getImageableX (), pageFormat.getImageableY ());
////            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
//
//            g2d.setStroke(new BasicStroke(1.05f));

//            ecgFrame(g2d, X, Y);
//            plot(g2d, X, Y);
//            Cursor(g2d, X, Y);
//            Medidas(g2d, X, Y);
            ecgFrame(g, X, Y);
            plot(g, X, Y);
            Cursor(g, X, Y);
            Medidas(g, X, Y);
            repaint();
        }

        protected void ECGPDF() {
//            Document document = new Document();
//            PdfWriter writer = null;
//            try {
//                document = new Document();
//                document.setPageSize(PageSize.A4);
//                String nameFile = identificacao.replace(" ", "") + ".pdf";
//                writer = PdfWriter.getInstance(document, new FileOutputStream(nameFile));
//
//            } catch (Exception ex) {
//                Logger.getLogger(ECG12Plot.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            document.open();
//            DefaultFontMapper mapper = new DefaultFontMapper();
//            mapper.insertDirectory("c:\\winnt\\fonts");
//
//            int w = 780; // i want the whole page!
//            int h = 523;
//            int x = 15;
//            int y = 40;
//
//            PdfContentByte cb = writer.getDirectContent();
//            PdfTemplate tp = cb.createTemplate(w, h);
//            Graphics2D g2d = tp.createGraphics(w, h);
//
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2d.setStroke(new BasicStroke(0.5f));
//            int Margin_X = 15;
//            int Margin_Y = 15;
//
//            g2d.setColor(c1);
//
//            for (int i = 0; i < 300; ++i) {
//
//                g2d.draw(new Line2D.Double(Margin_X + (i * ppi_to_mm_print * Zoom), Margin_Y, Margin_X + (i * ppi_to_mm_print * Zoom), Margin_Y + 175 * ppi_to_mm_print * Zoom));
//            }
//            for (int i = 0; i < 175; ++i) {
//                g2d.draw(new Line2D.Double(Margin_X, Margin_Y + (i * ppi_to_mm_print * Zoom), Margin_X + (1080 * ppi_to_mm_print * Zoom), Margin_Y + (i * ppi_to_mm_print * Zoom)));
//            }
//
//            g2d.setColor(c2);
//            for (int i = 0; i < 60; ++i) {
//                g2d.draw(new Line2D.Double(Margin_X + (i * 5 * ppi_to_mm_print * Zoom), Margin_Y, Margin_X + (i * 5 * ppi_to_mm_print * Zoom), Margin_Y + (175 * ppi_to_mm_print * Zoom)));
//            }
//
//            for (int i = 0; i < 35; ++i) {
//                g2d.draw(new Line2D.Double(Margin_X, Margin_Y + (i * 5 * ppi_to_mm_print * Zoom), Margin_X + (1080 * ppi_to_mm_print * Zoom), Margin_Y + (i * 5 * ppi_to_mm_print * Zoom)));
//            }
//
//            g2d.setColor(Color.BLACK);
//
//            g2d.drawRect(Margin_X, Margin_Y, (int) (1080 * (ppi_to_mm_print / 4) * Zoom) - 1, (int) (700 * (ppi_to_mm_print / 4) * Zoom) - 1);
//            g2d.drawRect(Margin_X - 1, Margin_Y - 1, (int) (1080 * (ppi_to_mm_print / 4) * Zoom) + 1, (int) (700 * (ppi_to_mm_print / 4) * Zoom) + 1);
//
//            g2d.drawString("Identificação: " + identificacao + " - Registro Nº: " + Reg_Sel, Margin_X, Margin_Y + (int) (700 * (ppi_to_mm_print / 4) * Zoom) + 12);
//            g2d.drawString("Identificação: " + identificacao + " - Registro Nº: " + Reg_Sel, Margin_X, Margin_Y + (int) (700 * (ppi_to_mm_print / 4) * Zoom) + 12);
//
//            g2d.drawLine(Margin_X, Margin_Y + (int) (140 * (ppi_to_mm_print / 4) * Zoom), Margin_X + (int) (1080 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (140 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawLine(Margin_X, Margin_Y + (int) (280 * (ppi_to_mm_print / 4) * Zoom), Margin_X + (int) (1080 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (280 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawLine(Margin_X, Margin_Y + (int) (420 * (ppi_to_mm_print / 4) * Zoom), Margin_X + (int) (1080 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (420 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawLine(Margin_X, Margin_Y + (int) (560 * (ppi_to_mm_print / 4) * Zoom), Margin_X + (int) (1080 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (560 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawLine(Margin_X + (int) (360 * (ppi_to_mm_print / 4) * Zoom), Margin_Y, Margin_X + (int) (360 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (560 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawLine(Margin_X + (int) (720 * (ppi_to_mm_print / 4) * Zoom), Margin_Y, Margin_X + (int) (720 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (560 * (ppi_to_mm_print / 4) * Zoom));
//
//            g2d.drawLine(Margin_X, Margin_Y + (int) (140 * (ppi_to_mm_print / 4) * Zoom) + 1, Margin_X + (int) (1080 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (140 * (ppi_to_mm_print / 4) * Zoom) + 1);
//            g2d.drawLine(Margin_X, Margin_Y + (int) (280 * (ppi_to_mm_print / 4) * Zoom) + 1, Margin_X + (int) (1080 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (280 * (ppi_to_mm_print / 4) * Zoom) + 1);
//            g2d.drawLine(Margin_X, Margin_Y + (int) (420 * (ppi_to_mm_print / 4) * Zoom) + 1, Margin_X + (int) (1080 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (420 * (ppi_to_mm_print / 4) * Zoom) + 1);
//            g2d.drawLine(Margin_X, Margin_Y + (int) (560 * (ppi_to_mm_print / 4) * Zoom) + 1, Margin_X + (int) (1080 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (560 * (ppi_to_mm_print / 4) * Zoom) + 1);
//            g2d.drawLine(Margin_X + (int) (360 * (ppi_to_mm_print / 4) * Zoom) + 1, Margin_Y, Margin_X + (int) (360 * (ppi_to_mm_print / 4) * Zoom) + 1, Margin_Y + (int) (560 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawLine(Margin_X + (int) (720 * (ppi_to_mm_print / 4) * Zoom) + 1, Margin_Y, Margin_X + (int) (720 * (ppi_to_mm_print / 4) * Zoom) + 1, Margin_Y + (int) (560 * (ppi_to_mm_print / 4) * Zoom));
//
//
////-------------------------------Plotando DI----------------------------------//           
//            for (int i = 0; i < 360 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X, (-DI[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 70 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X, (-DI[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 70 * (ppi_to_mm_print / 4) * Zoom));
//            }
////------------------------------Plotando DII----------------------------------//                  
//            for (int i = 0; i < 360 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 360 * (ppi_to_mm_print / 4) * Zoom, (-DII[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 70 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 360 * (ppi_to_mm_print / 4) * Zoom, (-DII[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 70 * (ppi_to_mm_print / 4) * Zoom));
//            }
////------------------------------Plotando DIII---------------------------------//
//            for (int i = 0; i < 360 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 720 * (ppi_to_mm_print / 4) * Zoom, (-DIII[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 70 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 720 * (ppi_to_mm_print / 4) * Zoom, (-DIII[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 70 * (ppi_to_mm_print / 4) * Zoom));
//            }
////------------------------------Plotando aVR----------------------------------//
//            for (int i = 0; i < 360 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X, (-aVR[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 210 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X, (-aVR[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 210 * (ppi_to_mm_print / 4) * Zoom));
//            }
////------------------------------Plotando aVL----------------------------------//
//            for (int i = 0; i < 360 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 360 * (ppi_to_mm_print / 4) * Zoom, (-aVL[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 210 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 360 * (ppi_to_mm_print / 4) * Zoom, (-aVL[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 210 * (ppi_to_mm_print / 4) * Zoom));
//            }
////-------------------------------Plotando aVF---------------------------------//
//            for (int i = 0; i < 360 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 720 * (ppi_to_mm_print / 4) * Zoom, (-aVF[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 210 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 720 * (ppi_to_mm_print / 4) * Zoom, (-aVF[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 210 * (ppi_to_mm_print / 4) * Zoom));
//            }
////------------------------------Plotando V1-----------------------------------//
//            for (int i = 0; i < 360 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X, (-V1_10[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 350 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X, (-V1_10[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 350 * (ppi_to_mm_print / 4) * Zoom));
//            }
////------------------------------Plotando V2-----------------------------------//
//            for (int i = 0; i < 360 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 360 * (ppi_to_mm_print / 4) * Zoom, (-V2_10[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 350 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 360 * (ppi_to_mm_print / 4) * Zoom, (-V2_10[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 350 * (ppi_to_mm_print / 4) * Zoom));
//            }
////--------------------------------Plotando V3---------------------------------//
//            for (int i = 0; i < 360 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 720 * (ppi_to_mm_print / 4) * Zoom, (-V3_10[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 350 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 720 * (ppi_to_mm_print / 4) * Zoom, (-V3_10[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 350 * (ppi_to_mm_print / 4) * Zoom));
//            }
////-------------------------------Plotando V4----------------------------------//
//            for (int i = 0; i < 360 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X, (-V4_10[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 490 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X, (-V4_10[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 490 * (ppi_to_mm_print / 4) * Zoom));
//            }
////-------------------------------Plotando V5----------------------------------//
//            for (int i = 0; i < 360 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 360 * (ppi_to_mm_print / 4) * Zoom, (-V5_10[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 490 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 360 * (ppi_to_mm_print / 4) * Zoom, (-V5_10[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 490 * (ppi_to_mm_print / 4) * Zoom));
//            }
////--------------------------------Plotando V6---------------------------------//
//            for (int i = 0; i < 360 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 720 * (ppi_to_mm_print / 4) * Zoom, (-V6_10[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 490 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X + 720 * (ppi_to_mm_print / 4) * Zoom, (-V6_10[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 490 * (ppi_to_mm_print / 4) * Zoom));
//            }
////---------------------------Plotando DII Longo-------------------------------//                                        
//            for (int i = 0; i < 1200 * (ppi_to_mm_print / 4) * (Amostragem / (velocidade * ppi_to_mm_print)); i++) {
//                g2d.draw(new Line2D.Double(((i * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X, (-DII[i] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 620 * (ppi_to_mm_print / 4) * Zoom, (((i + 1) * Zoom * velocidade * ppi_to_mm_print) / Amostragem) + Margin_X, (-DII[i + 1] * Sensibilidade * ppi_to_mm_print * Zoom * Escala / 1000) + Margin_Y + 620 * (ppi_to_mm_print / 4) * Zoom));
//            }
//
//            g2d.setColor(Color.WHITE);
//            g2d.fillRect(Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.fillRect(Margin_X + (int) (380 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 11, 42, 13);
//            g2d.fillRect(Margin_X + (int) (740 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 11, 44, 13);
//            g2d.fillRect(Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (160 * (ppi_to_mm_print / 4) * Zoom) - 11, 49, 13);
//            g2d.fillRect(Margin_X + (int) (380 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (160 * (ppi_to_mm_print / 4) * Zoom) - 11, 47, 13);
//            g2d.fillRect(Margin_X + (int) (740 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (160 * (ppi_to_mm_print / 4) * Zoom) - 11, 47, 13);
//            g2d.fillRect(Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (300 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.fillRect(Margin_X + (int) (380 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (300 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.fillRect(Margin_X + (int) (740 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (300 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.fillRect(Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (440 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.fillRect(Margin_X + (int) (380 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (440 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.fillRect(Margin_X + (int) (740 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (440 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.fillRect(Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (580 * (ppi_to_mm_print / 4) * Zoom) - 11, 80, 13);
//
//            g2d.setColor(Color.BLACK);
//
//            g2d.drawRect(Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.drawString("DI " + ganho[0][Reg_Sel - 1], Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (20 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawRect(Margin_X + (int) (380 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 11, 42, 13);
//            g2d.drawString("DII " + ganho[1][Reg_Sel - 1], Margin_X + (int) (380 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (20 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawRect(Margin_X + (int) (740 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 11, 44, 13);
//            g2d.drawString("DIII " + ganho[2][Reg_Sel - 1], Margin_X + (int) (740 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (20 * (ppi_to_mm_print / 4) * Zoom));
//
//            g2d.drawRect(Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (160 * (ppi_to_mm_print / 4) * Zoom) - 11, 49, 13);
//            g2d.drawString("aVR " + ganho[3][Reg_Sel - 1], Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (160 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawRect(Margin_X + (int) (380 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (160 * (ppi_to_mm_print / 4) * Zoom) - 11, 47, 13);
//            g2d.drawString("aVL " + ganho[4][Reg_Sel - 1], Margin_X + (int) (380 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (160 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawRect(Margin_X + (int) (740 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (160 * (ppi_to_mm_print / 4) * Zoom) - 11, 47, 13);
//            g2d.drawString("aVF " + ganho[5][Reg_Sel - 1], Margin_X + (int) (740 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (160 * (ppi_to_mm_print / 4) * Zoom));
//
//            g2d.drawRect(Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (300 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.drawString("V1 " + ganho[6][Reg_Sel - 1], Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (300 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawRect(Margin_X + (int) (380 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (300 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.drawString("V2 " + ganho[7][Reg_Sel - 1], Margin_X + (int) (380 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (300 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawRect(Margin_X + (int) (740 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (300 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.drawString("V3 " + ganho[8][Reg_Sel - 1], Margin_X + (int) (740 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (300 * (ppi_to_mm_print / 4) * Zoom));
//
//            g2d.drawRect(Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (440 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.drawString("V4 " + ganho[9][Reg_Sel - 1], Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (440 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawRect(Margin_X + (int) (380 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (440 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.drawString("V5 " + ganho[10][Reg_Sel - 1], Margin_X + (int) (380 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (440 * (ppi_to_mm_print / 4) * Zoom));
//            g2d.drawRect(Margin_X + (int) (740 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (440 * (ppi_to_mm_print / 4) * Zoom) - 11, 40, 13);
//            g2d.drawString("V6 " + ganho[11][Reg_Sel - 1], Margin_X + (int) (740 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (440 * (ppi_to_mm_print / 4) * Zoom));
//
//            g2d.drawRect(Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom) - 4, Margin_Y + (int) (580 * (ppi_to_mm_print / 4) * Zoom) - 11, 80, 13);
//            g2d.drawString("DII Longo " + ganho[1][Reg_Sel - 1], Margin_X + (int) (20 * (ppi_to_mm_print / 4) * Zoom), Margin_Y + (int) (580 * (ppi_to_mm_print / 4) * Zoom));
//
//            // Finish the PDF-Document
//            g2d.dispose();
//            cb.addTemplate(tp, x, y);
//            document.close();
        }

        protected void Imprimir(Graphics g2d) {

//            Graphics2D g2d = (Graphics2D) g;
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2d.setStroke(new BasicStroke(0.5f));
            int Margin_X = 15;
            int Margin_Y = 40;

            g2d.setColor(config.getC1());

            int ppi_mm_zoom = (int) ppi_to_mm_print * Zoom;

            for (int i = 0; i < 300; ++i) {
                g2d.drawLine(Margin_X + (i * ppi_mm_zoom), Margin_Y, Margin_X + (i * ppi_mm_zoom), Margin_Y + 175 * ppi_mm_zoom);
            }
            for (int i = 0; i < 175; ++i) {
                g2d.drawLine(Margin_X, Margin_Y + (i * ppi_mm_zoom), Margin_X + (1080 * ppi_mm_zoom), Margin_Y + (i * ppi_mm_zoom));
            }

            g2d.setColor(config.getC2());
            for (int i = 0; i < 60; ++i) {
                g2d.drawLine(Margin_X + (i * 5 * ppi_mm_zoom), Margin_Y, Margin_X + (i * 5 * ppi_mm_zoom), Margin_Y + (175 * ppi_mm_zoom));
            }

            for (int i = 0; i < 35; ++i) {
                g2d.drawLine(Margin_X, Margin_Y + (i * 5 * ppi_mm_zoom), Margin_X + (1080 * ppi_mm_zoom), Margin_Y + (i * 5 * ppi_mm_zoom));
            }

            g2d.setColor(Color.BLACK);

            float ppi_Zoom = (ppi_to_mm_print / 4) * Zoom;
            float ppi_Zoom_1080 = ppi_Zoom * 1080;

            g2d.drawRect(Margin_X, Margin_Y, (int) (ppi_Zoom_1080) - 1, (int) (700 * ppi_Zoom) - 1);
            g2d.drawRect(Margin_X - 1, Margin_Y - 1, (int) (ppi_Zoom_1080) + 1, (int) (700 * ppi_Zoom) + 1);

            g2d.drawString("Identifica��o: " + identificacao + " - Registro N�: " + config.getReg_Sel(), Margin_X, Margin_Y + (int) (700 * ppi_Zoom) + 12);

            g2d.drawLine(Margin_X, Margin_Y + (int) (140 * ppi_Zoom), Margin_X + (int) (ppi_Zoom_1080), Margin_Y + (int) (140 * ppi_Zoom));
            g2d.drawLine(Margin_X, Margin_Y + (int) (280 * ppi_Zoom), Margin_X + (int) (ppi_Zoom_1080), Margin_Y + (int) (280 * ppi_Zoom));
            g2d.drawLine(Margin_X, Margin_Y + (int) (420 * ppi_Zoom), Margin_X + (int) (ppi_Zoom_1080), Margin_Y + (int) (420 * ppi_Zoom));
            g2d.drawLine(Margin_X, Margin_Y + (int) (560 * ppi_Zoom), Margin_X + (int) (ppi_Zoom_1080), Margin_Y + (int) (560 * ppi_Zoom));
            g2d.drawLine(Margin_X + (int) (360 * ppi_Zoom), Margin_Y, Margin_X + (int) (360 * ppi_Zoom), Margin_Y + (int) (560 * ppi_Zoom));
            g2d.drawLine(Margin_X + (int) (720 * ppi_Zoom), Margin_Y, Margin_X + (int) (720 * ppi_Zoom), Margin_Y + (int) (560 * ppi_Zoom));

            g2d.drawLine(Margin_X, Margin_Y + (int) (140 * ppi_Zoom) + 1, Margin_X + (int) (ppi_Zoom_1080), Margin_Y + (int) (140 * ppi_Zoom) + 1);
            g2d.drawLine(Margin_X, Margin_Y + (int) (280 * ppi_Zoom) + 1, Margin_X + (int) (ppi_Zoom_1080), Margin_Y + (int) (280 * ppi_Zoom) + 1);
            g2d.drawLine(Margin_X, Margin_Y + (int) (420 * ppi_Zoom) + 1, Margin_X + (int) (ppi_Zoom_1080), Margin_Y + (int) (420 * ppi_Zoom) + 1);
            g2d.drawLine(Margin_X, Margin_Y + (int) (560 * ppi_Zoom) + 1, Margin_X + (int) (ppi_Zoom_1080), Margin_Y + (int) (560 * ppi_Zoom) + 1);
            g2d.drawLine(Margin_X + (int) (360 * ppi_Zoom) + 1, Margin_Y, Margin_X + (int) (360 * ppi_Zoom) + 1, Margin_Y + (int) (560 * ppi_Zoom));
            g2d.drawLine(Margin_X + (int) (720 * ppi_Zoom) + 1, Margin_Y, Margin_X + (int) (720 * ppi_Zoom) + 1, Margin_Y + (int) (560 * ppi_Zoom));

            imprimeGrafico((Graphics2D) g2d, Margin_X, Margin_Y);

            g2d.setColor(Color.WHITE);
            g2d.fillRect(Margin_X + (int) (20 * ppi_Zoom) - 4, Margin_Y + (int) (20 * ppi_Zoom) - 11, 40, 13);
            g2d.fillRect(Margin_X + (int) (380 * ppi_Zoom) - 4, Margin_Y + (int) (20 * ppi_Zoom) - 11, 42, 13);
            g2d.fillRect(Margin_X + (int) (740 * ppi_Zoom) - 4, Margin_Y + (int) (20 * ppi_Zoom) - 11, 44, 13);
            g2d.fillRect(Margin_X + (int) (20 * ppi_Zoom) - 4, Margin_Y + (int) (160 * ppi_Zoom) - 11, 49, 13);
            g2d.fillRect(Margin_X + (int) (380 * ppi_Zoom) - 4, Margin_Y + (int) (160 * ppi_Zoom) - 11, 47, 13);
            g2d.fillRect(Margin_X + (int) (740 * ppi_Zoom) - 4, Margin_Y + (int) (160 * ppi_Zoom) - 11, 47, 13);
            g2d.fillRect(Margin_X + (int) (20 * ppi_Zoom) - 4, Margin_Y + (int) (300 * ppi_Zoom) - 11, 40, 13);
            g2d.fillRect(Margin_X + (int) (380 * ppi_Zoom) - 4, Margin_Y + (int) (300 * ppi_Zoom) - 11, 40, 13);
            g2d.fillRect(Margin_X + (int) (740 * ppi_Zoom) - 4, Margin_Y + (int) (300 * ppi_Zoom) - 11, 40, 13);
            g2d.fillRect(Margin_X + (int) (20 * ppi_Zoom) - 4, Margin_Y + (int) (440 * ppi_Zoom) - 11, 40, 13);
            g2d.fillRect(Margin_X + (int) (380 * ppi_Zoom) - 4, Margin_Y + (int) (440 * ppi_Zoom) - 11, 40, 13);
            g2d.fillRect(Margin_X + (int) (740 * ppi_Zoom) - 4, Margin_Y + (int) (440 * ppi_Zoom) - 11, 40, 13);
            g2d.fillRect(Margin_X + (int) (20 * ppi_Zoom) - 4, Margin_Y + (int) (580 * ppi_Zoom) - 11, 80, 13);

            g2d.setColor(Color.BLACK);

            g2d.drawRect(Margin_X + (int) (20 * ppi_Zoom) - 4, Margin_Y + (int) (20 * ppi_Zoom) - 11, 40, 13);
            g2d.drawString("DI " + ganho[0][config.getReg_Sel() - 1], Margin_X + (int) (20 * ppi_Zoom), Margin_Y + (int) (20 * ppi_Zoom));
            g2d.drawRect(Margin_X + (int) (380 * ppi_Zoom) - 4, Margin_Y + (int) (20 * ppi_Zoom) - 11, 42, 13);
            g2d.drawString("DII " + ganho[1][config.getReg_Sel() - 1], Margin_X + (int) (380 * ppi_Zoom), Margin_Y + (int) (20 * ppi_Zoom));
            g2d.drawRect(Margin_X + (int) (740 * ppi_Zoom) - 4, Margin_Y + (int) (20 * ppi_Zoom) - 11, 44, 13);
            g2d.drawString("DIII " + ganho[2][config.getReg_Sel() - 1], Margin_X + (int) (740 * ppi_Zoom), Margin_Y + (int) (20 * ppi_Zoom));

            g2d.drawRect(Margin_X + (int) (20 * ppi_Zoom) - 4, Margin_Y + (int) (160 * ppi_Zoom) - 11, 49, 13);
            g2d.drawString("aVR " + ganho[3][config.getReg_Sel() - 1], Margin_X + (int) (20 * ppi_Zoom), Margin_Y + (int) (160 * ppi_Zoom));
            g2d.drawRect(Margin_X + (int) (380 * ppi_Zoom) - 4, Margin_Y + (int) (160 * ppi_Zoom) - 11, 47, 13);
            g2d.drawString("aVL " + ganho[4][config.getReg_Sel() - 1], Margin_X + (int) (380 * ppi_Zoom), Margin_Y + (int) (160 * ppi_Zoom));
            g2d.drawRect(Margin_X + (int) (740 * ppi_Zoom) - 4, Margin_Y + (int) (160 * ppi_Zoom) - 11, 47, 13);
            g2d.drawString("aVF " + ganho[5][config.getReg_Sel() - 1], Margin_X + (int) (740 * ppi_Zoom), Margin_Y + (int) (160 * ppi_Zoom));

            g2d.drawRect(Margin_X + (int) (20 * ppi_Zoom) - 4, Margin_Y + (int) (300 * ppi_Zoom) - 11, 40, 13);
            g2d.drawString("V1 " + ganho[6][config.getReg_Sel() - 1], Margin_X + (int) (20 * ppi_Zoom), Margin_Y + (int) (300 * ppi_Zoom));
            g2d.drawRect(Margin_X + (int) (380 * ppi_Zoom) - 4, Margin_Y + (int) (300 * ppi_Zoom) - 11, 40, 13);
            g2d.drawString("V2 " + ganho[7][config.getReg_Sel() - 1], Margin_X + (int) (380 * ppi_Zoom), Margin_Y + (int) (300 * ppi_Zoom));
            g2d.drawRect(Margin_X + (int) (740 * ppi_Zoom) - 4, Margin_Y + (int) (300 * ppi_Zoom) - 11, 40, 13);
            g2d.drawString("V3 " + ganho[8][config.getReg_Sel() - 1], Margin_X + (int) (740 * ppi_Zoom), Margin_Y + (int) (300 * ppi_Zoom));

            g2d.drawRect(Margin_X + (int) (20 * ppi_Zoom) - 4, Margin_Y + (int) (440 * ppi_Zoom) - 11, 40, 13);
            g2d.drawString("V4 " + ganho[9][config.getReg_Sel() - 1], Margin_X + (int) (20 * ppi_Zoom), Margin_Y + (int) (440 * ppi_Zoom));
            g2d.drawRect(Margin_X + (int) (380 * ppi_Zoom) - 4, Margin_Y + (int) (440 * ppi_Zoom) - 11, 40, 13);
            g2d.drawString("V5 " + ganho[10][config.getReg_Sel() - 1], Margin_X + (int) (380 * ppi_Zoom), Margin_Y + (int) (440 * ppi_Zoom));
            g2d.drawRect(Margin_X + (int) (740 * ppi_Zoom) - 4, Margin_Y + (int) (440 * ppi_Zoom) - 11, 40, 13);
            g2d.drawString("V6 " + ganho[11][config.getReg_Sel() - 1], Margin_X + (int) (740 * ppi_Zoom), Margin_Y + (int) (440 * ppi_Zoom));

            g2d.drawRect(Margin_X + (int) (20 * ppi_Zoom) - 4, Margin_Y + (int) (580 * ppi_Zoom) - 11, 80, 13);
            g2d.drawString("DII Longo " + ganho[1][config.getReg_Sel() - 1], Margin_X + (int) (20 * ppi_Zoom), Margin_Y + (int) (580 * ppi_Zoom));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            String x = command.substring(0, command.length());
            String title = "Tra�ado - Registro N�: " + config.getReg_Sel() + "/" + config.getN_Registros() + " - Escala: 25mm/s, #ESCALA# mm/mV - Modo: #MODO#";
            if (x.contains("Escala:")) {
                // deixando apenas numeros e ponto
                String aux = x.replaceAll("[^\\d\\.]", "");
                if (aux != null && !aux.isEmpty()) {
                    config.setEscala(Float.parseFloat(aux));
                }
                preencherTitulo();
            } else if (x.contains("Modo:")) {
                config.setModo(x.contains("II") ? 1 : 0);
                preencherTitulo();
            } else if (x.equals("Imprimir")) {
                ECGPDF();

                // Obtem um job de impressao
                PrinterJob job = PrinterJob.getPrinterJob();
                Book book = new Book();

                PageFormat documentPageFormat = new PageFormat();
                documentPageFormat.setOrientation(PageFormat.LANDSCAPE);

                Paper x1 = documentPageFormat.getPaper();
                x1.setImageableArea(0, 0, 220 * ppi_to_mm_print, 310 * ppi_to_mm_print);
                documentPageFormat.setPaper(x1);
                book.append(this, documentPageFormat);

                job.setPageable(book);

                if (job.printDialog()) {
                    try {
                        job.print();
                    } catch (PrinterException e2) {
                        e2.printStackTrace();
                    }
                }

            } else if (x.equals("Armazenar R-R")) {
                RR = (double) (mxs2 - mxs1) * 1000 / (4 * 25 * (double) Zoom);
                FC = (double) 60000 / (double) RR;

                if ((RR > 0) && (QT > 0)) {
                    QTc = QT / Math.sqrt(RR / 1000);
                } else {
                    QTc = 0;
                }
            } else if (x.equals("Armazenar QT")) {
                QT = (double) (mxs2 - mxs1) * 1000 / (4 * 25 * (double) Zoom);
                if ((RR > 0) && (QT > 0)) {
                    QTc = QT / Math.sqrt(RR / 1000);
                } else {
                    QTc = 0;
                }
            } else if (x.equals("Limpar Medidas")) {
                RR = 0;
                QT = 0;
                QTc = 0;
                FC = 0;
            } else {
                x = command.substring(0, 10);
                if (x.equals("Registro: ")) {
                    x = command.substring(10, command.length());
                    int y = parseInt(x);
                    if ((y > 0) && (y < (config.getN_Registros() + 1))) {
                        config.setReg_Sel(y);

                        for (int i = 0; i < amostras[0][y - 1].length; i++) {
                            tracado.DI()[i] = parseInt(amostras[0][y - 1][i]);
                            tracado.DII()[i] = parseInt(amostras[1][y - 1][i]);
                            tracado.DIII()[i] = parseInt(amostras[2][y - 1][i]);
                            tracado.aVR()[i] = parseInt(amostras[3][y - 1][i]);
                            tracado.aVL()[i] = parseInt(amostras[4][y - 1][i]);
                            tracado.aVF()[i] = parseInt(amostras[5][y - 1][i]);
                            tracado.V1_10()[i] = parseInt(amostras[6][y - 1][i]);
                            tracado.V2_10()[i] = parseInt(amostras[7][y - 1][i]);
                            tracado.V3_10()[i] = parseInt(amostras[8][y - 1][i]);
                            tracado.V4_10()[i] = parseInt(amostras[9][y - 1][i]);
                            tracado.V5_10()[i] = parseInt(amostras[10][y - 1][i]);
                            tracado.V6_10()[i] = parseInt(amostras[11][y - 1][i]);
                        }
                        preencherTitulo();
                    }
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 3) {
                popupmenu.show(this, e.getX(), e.getY());
                popupmenu.setVisible(true);
                Selecao = false;
            } else {
                popupmenu.setVisible(false);
            }
            if (e.getButton() == 2) {
                isButtonPressed = true;
                setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                Xa = X;
                Ya = Y;
                mxb = mx;
                myb = my;
                mxs2 = 0;
                mys2 = 0;
                Selecao = false;
            }
            if ((e.getButton() == 1)) {
                mxs1 = mx;
                mys1 = my;
                mxs2 = 0;
                mys2 = 0;
                Selecao = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == 2) {
                isButtonPressed = false;
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                mxb = mx;
                myb = my;
                if (Zoom > 1) {
                    mxa = X / (1 - Zoom);
                    mya = Y / (1 - Zoom);
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mx = e.getX();
            my = e.getY();

            if (Selecao) {
                mxs2 = mx;
                mys2 = my;
            }

            if (isButtonPressed) {
                if (((Xa - (mxb - mx)) >= (-1200 * Zoom + getWidth())) && ((Xa - (mxb - mx)) <= 0)) {
                    X = Xa - (mxb - mx);
                }
                if (((Ya - (myb - my)) >= (-720 * Zoom + getHeight())) && ((Ya - (myb - my)) <= 0)) {
                    Y = Ya - (myb - my);
                }
            }

            repaint();
            e.consume();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mx = e.getX();
            my = e.getY();

            repaint();
            e.consume();
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (!e.isControlDown()) {
                mxs2 = 0;
                mys2 = 0;
                Selecao = false;
                if (!isButtonPressed) {
                    if (Zoom == 1) {
                        mxa = mx;
                        mya = my;
                    }

                    if (e.getWheelRotation() == 1) {
                        Zoom++;
                    } else {
                        if (Zoom > 1) {
                            Zoom--;
                        } else {
                            X = 0;
                            Y = 0;
                        }
                    }

                    X = (1 - Zoom) * mxa;
                    Y = (1 - Zoom) * mya;
                }
            } else {
                if (e.getWheelRotation() == 1) {
                    actionPerformed(new ActionEvent(this, 0, "Registro: " + (config.getReg_Sel() + 1)));
                } else {
                    actionPerformed(new ActionEvent(this, 0, "Registro: " + (config.getReg_Sel() - 1)));
                }
            }
        }

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

            // (caso o pageIndex seja maior que zero, retorna NO_SUCH_PAGE)
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Imprimir(graphics);

            // Indica que foi possível renderizar a página return Printable.PAGE_EXISTS; } }
            return Printable.PAGE_EXISTS;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private int parseInt(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public void dispose() {
        config = null;
        tracado = null;
        amostras = null;
        scroll = null;
        ganho = null;
        media = null;
        identificacao = null;

        super.dispose();
    }

    public int[] amplitude(int v[], int linf, int lsup) {

        int maxMin[] = new int[2];
        if (lsup - linf <= 1) {
            if (v[linf] < v[lsup]) {
                maxMin[0] = v[lsup];
                maxMin[1] = v[linf];
            } else {
                maxMin[0] = v[linf];
                maxMin[1] = v[lsup];
            }
        } else {
            int meio = (linf + lsup) / 2;
            maxMin = amplitude(v, linf, meio);
            int max1 = maxMin[0], min1 = maxMin[1];
            maxMin = amplitude(v, meio + 1, lsup);
            int max2 = maxMin[0], min2 = maxMin[1];
            if (max1 > max2) {
                maxMin[0] = max1;
            } else {
                maxMin[0] = max2;
            }
            if (min1 < min2) {
                maxMin[1] = min1;
            } else {
                maxMin[1] = min2;
            }
        }
        return maxMin;
    }
//verifica qual amplitude é a maior entre o componentes do traçado

    public int verificaMaior(int x, int y, int z) {
        int maior = 0;
        int a = x;
        int b = y;
        int c = z;
        if (a < 0) {
            a = a * -1;
        }
        if (b < 0) {
            b = b * -1;
        }
        if (c < 0) {
            c = c * -1;
        }
        if (a > b) {
            if (a > c) {
                maior = x;
            }
            maior = z;
        } else {
            if (b > c) {
                maior = y;
            } else {
                maior = z;
            }
        }

        return maior;
    }
//desloca impressão do tracado de acordo com a amplitude

    public int filtroTracado(int[] x, int[] y, int[] z) {
        if (verificaMaior(amplitude(x, 0, x.length - 1)[0] + amplitude(x, 0, x.length - 1)[1], amplitude(y, 0, y.length - 1)[0] + amplitude(y, 0, y.length - 1)[1], amplitude(z, 0, z.length - 1)[0] + amplitude(z, 0, z.length - 1)[1]) > 250 && verificaMaior(amplitude(x, 0, x.length - 1)[0] + amplitude(x, 0, x.length - 1)[1], amplitude(y, 0, y.length - 1)[0] + amplitude(y, 0, y.length - 1)[1], amplitude(z, 0, z.length - 1)[0] + amplitude(z, 0, z.length - 1)[1]) < 400) {
            return +17;
        } else if (verificaMaior(amplitude(x, 0, x.length - 1)[0] + amplitude(x, 0, x.length - 1)[1], amplitude(y, 0, y.length - 1)[0] + amplitude(y, 0, y.length - 1)[1], amplitude(z, 0, z.length - 1)[0] + amplitude(z, 0, z.length - 1)[1]) > 400) {
            return +30;
        }

        if (verificaMaior(amplitude(x, 0, x.length - 1)[0] + amplitude(x, 0, x.length - 1)[1], amplitude(y, 0, y.length - 1)[0] + amplitude(y, 0, y.length - 1)[1], amplitude(z, 0, z.length - 1)[0] + amplitude(z, 0, z.length - 1)[1]) < -250 && verificaMaior(amplitude(x, 0, x.length - 1)[0] + amplitude(x, 0, x.length - 1)[1], amplitude(y, 0, y.length - 1)[0] + amplitude(y, 0, y.length - 1)[1], amplitude(z, 0, z.length - 1)[0] + amplitude(z, 0, z.length - 1)[1]) > -400) {
            return -17;
        } else if (verificaMaior(amplitude(x, 0, x.length - 1)[0] + amplitude(x, 0, x.length - 1)[1], amplitude(y, 0, y.length - 1)[0] + amplitude(y, 0, y.length - 1)[1], amplitude(z, 0, z.length - 1)[0] + amplitude(z, 0, z.length - 1)[1]) < -400) {
            return -30;
        }
        return 0;
    }

}
