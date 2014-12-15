/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.commons.pdf;

import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.CANAL;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.CONTEUDOEXAME;
import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.REGISTRO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfTemplate;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author igor.santos
 */
public class PrintProtocol {

    private String identification;
    private Color c1 = new Color(255, 204, 204);
    private Color c2 = new Color(255, 128, 128);
    private int DI[] = new int[50000];
    private int DII[] = new int[50000];
    private int DIII[] = new int[50000];
    private int aVR[] = new int[50000];
    private int aVL[] = new int[50000];
    private int aVF[] = new int[50000];
    private int V1_10[] = new int[50000];
    private int V2_10[] = new int[50000];
    private int V3_10[] = new int[50000];
    private int V4_10[] = new int[50000];
    private int V5_10[] = new int[50000];
    private int V6_10[] = new int[50000];
    private String[][][] samples;
    private String[][] gain;
    private int numberRegisters;
    private int zoom = 1;
    private float scale = (float) 10;
    private float sensibilty = (float) 3.9;
     private int sampling = 600; //Hertz
    private static final double ppi_to_mm_print = 72.0 / 25.4; //fator de conversão 72 PPI para PPM (72 Pixels por polegada para pixels por milímetro).
    //private static final double ppi_to_mm_view = 96.0/25.4; //fator de conversão 72 PPI para PPM (72 Pixels por polegada para pixels por milímetro).
    private static final double speed = 25.0;
    int marginX = 0;
    int marginY = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public void createProtocol(CONTEUDOEXAME tracing, Document document) {
        //- verifica se sensibilidade � 5, caso n�o ela
        //- mantem valor default 3.9
        if (tracing.getSENSIBILIDADE().startsWith("5")) {
            sensibilty = 5f;
        }
        //- registros existentes
        numberRegisters = tracing.getREGISTROS().size();
        //- gera as amostragens
        generateSamples(tracing.getREGISTROS(), tracing.getQUANTIDADE());
    }

    private void generateSamples(List<REGISTRO> records, int amount) {
        Iterator<REGISTRO> iteratorRegister = records.iterator();

        samples = new String[12][amount][2200];
        gain = new String[12][amount];

        int contRegister = 0;
        //- percorrer registros
        while (iteratorRegister.hasNext()) {
            REGISTRO register = iteratorRegister.next();
            List<CANAL> channels = register.getDERIVACOES();
            //- comeca a preencher o registro
            Iterator<CANAL> iteratorChannel = channels.iterator();
            int contChannel = 0;
            while (iteratorChannel.hasNext()) {
                CANAL channel = iteratorChannel.next();
                gain[contChannel][contRegister] = channel.getGANHO();
                samples[contChannel][contRegister] = channel.getAMOSTRAS()
                        .replace("<![CDATA[", "").replace("]]>", "").split(";");
                contChannel++;
            }
            contRegister++;
        }

    }

    public Graphics2D print(PdfTemplate template, Rectangle rect, int numberRegister, Date date, int scale,float speed, int sampling) {
        Graphics2D graphics = template.createGraphics(rect.getWidth(), rect.getHeight());
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(0.5f));
        //- seta o fundo branco
        graphics.setColor(Color.WHITE);
        graphics.fillRect(marginX, marginY, (int) rect.getWidth(), (int) rect.getHeight());

        correct(numberRegister);
        printGrid(graphics);
        printIdentification(graphics, numberRegister,date,scale, speed);
        drawIdentify(graphics, numberRegister);
        printTracing(graphics, scale, speed, sampling);

        return graphics;
    }

    private void printTracing(Graphics2D g2d, int scale, float speed, int sampling) {
        int limit = (int) (360 * (ppi_to_mm_print / 4) * (sampling / (speed * ppi_to_mm_print)));
        
        int alturaD = 70 + filtroTracado(DI, DII, DIII);
        int alturaAv = 210 + filtroTracado(aVR, aVL, aVF);;
        int alturaV1 = 350 + filtroTracado(V1_10, V2_10, V3_10);
        int alturaV4 = 490 + filtroTracado(V4_10, V5_10, V6_10);
        int alturaDL = 635 + filtroTracado(DII, DII, DII);

        for (int i = 0; i < limit; i++) {
            //- plotando DI
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX, (-DI[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaD * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX, (-DI[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaD * (ppi_to_mm_print / 4) * zoom));
            //- plotando DII
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 360 * (ppi_to_mm_print / 4) * zoom, (-DII[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaD * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 360 * (ppi_to_mm_print / 4) * zoom, (-DII[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaD * (ppi_to_mm_print / 4) * zoom));
            //- plotando DIII
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 720 * (ppi_to_mm_print / 4) * zoom, (-DIII[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaD * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 720 * (ppi_to_mm_print / 4) * zoom, (-DIII[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaD * (ppi_to_mm_print / 4) * zoom));
            //- plotando aVR
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX, (-aVR[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaAv * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX, (-aVR[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaAv * (ppi_to_mm_print / 4) * zoom));
            //- plotando aVL
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 360 * (ppi_to_mm_print / 4) * zoom, (-aVL[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaAv * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 360 * (ppi_to_mm_print / 4) * zoom, (-aVL[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaAv * (ppi_to_mm_print / 4) * zoom));
            //- plotando aVF
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 720 * (ppi_to_mm_print / 4) * zoom, (-aVF[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaAv * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 720 * (ppi_to_mm_print / 4) * zoom, (-aVF[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaAv * (ppi_to_mm_print / 4) * zoom));
            //- plotando V1
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX, (-V1_10[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaV1 * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX, (-V1_10[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaV1 * (ppi_to_mm_print / 4) * zoom));
            //- plotando V2
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 360 * (ppi_to_mm_print / 4) * zoom, (-V2_10[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaV1 * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 360 * (ppi_to_mm_print / 4) * zoom, (-V2_10[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaV1 * (ppi_to_mm_print / 4) * zoom));
            //- plotando V3
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 720 * (ppi_to_mm_print / 4) * zoom, (-V3_10[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaV1 * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 720 * (ppi_to_mm_print / 4) * zoom, (-V3_10[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaV1 * (ppi_to_mm_print / 4) * zoom));
            //- plotando V4
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX, (-V4_10[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaV4 * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX, (-V4_10[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaV4 * (ppi_to_mm_print / 4) * zoom));
            //- plotando V5
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 360 * (ppi_to_mm_print / 4) * zoom, (-V5_10[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaV4 * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 360 * (ppi_to_mm_print / 4) * zoom, (-V5_10[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaV4 * (ppi_to_mm_print / 4) * zoom));
            //- plotando V6
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 720 * (ppi_to_mm_print / 4) * zoom, (-V6_10[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaV4 * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX + 720 * (ppi_to_mm_print / 4) * zoom, (-V6_10[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaV4 * (ppi_to_mm_print / 4) * zoom));
            //- plotando DII Longo - part. 1
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX, (-DII[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaDL * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX, (-DII[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaDL * (ppi_to_mm_print / 4) * zoom));
        }
        //- plotando DII Longo - part. 2
        int rescue = limit;
        limit = (int) (1080 * (ppi_to_mm_print / 4) * (sampling / (speed * ppi_to_mm_print)));
        for (int i = rescue; i < limit; i++) {
            g2d.draw(new Line2D.Double(((i * zoom * speed * ppi_to_mm_print) / sampling) + marginX, (-DII[i] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaDL * (ppi_to_mm_print / 4) * zoom, (((i + 1) * zoom * speed * ppi_to_mm_print) / sampling) + marginX, (-DII[i + 1] * sensibilty * ppi_to_mm_print * zoom * scale / 1000) + marginY + alturaDL * (ppi_to_mm_print / 4) * zoom));
        }
    }

    private void drawIdentify(Graphics2D g2d, int register) {
        drawFillRect(g2d, 20, new int[]{25, 27, 29});
        drawFillRect(g2d, 160, new int[]{25, 25, 25});
        drawFillRect(g2d, 300, new int[]{25, 25, 25});
        drawFillRect(g2d, 440, new int[]{25, 25, 25});
        drawFillRect(g2d, 580, new int[]{40});

        drawString(g2d, new String[]{"DI ", "DII ", "DIII "},
                new int[]{0, 1, 2}, 20, register,
                new int[]{25, 27, 29});
        drawString(g2d, new String[]{"aVR ", "aVL ", "aVF "},
                new int[]{3, 4, 5}, 160, register,
                new int[]{25, 25, 25});
        drawString(g2d, new String[]{"V1 ", "V2 ", "V3 "},
                new int[]{6, 7, 8}, 300, register,
                new int[]{25, 25, 25});
        drawString(g2d, new String[]{"V4 ", "V5 ", "V6 "},
                new int[]{9, 10, 11}, 440, register,
                new int[]{25, 25, 25});
        drawString(g2d, new String[]{"DII_L "},
                new int[]{1}, 580, register,
                new int[]{40});
    }

    private void drawFillRect(Graphics2D g2d, int y, int[] z) {
        g2d.setColor(Color.WHITE);
        int[] x = new int[]{20, 380, 740};
        for (int i = 0; i < z.length; i++) {
            g2d.fillRect(marginX + (int) (x[i] * (ppi_to_mm_print / 4) * zoom) - 4, marginY + (int) (y * (ppi_to_mm_print / 4) * zoom) - 11, z[i], 13);
        }
    }

    private void drawString(Graphics2D g2d, String[] txt, int[] address, int pos, int register, int[] x) {
        g2d.setColor(Color.BLACK);
        int[] position = new int[]{15, 380, 740};
        for (int i = 0; i < x.length; i++) {
            g2d.drawRect(marginX + (int) (position[i] * (ppi_to_mm_print / 4) * zoom) - 4, marginY + (int) (pos * (ppi_to_mm_print / 4) * zoom) - 11, x[i], 13);
            g2d.drawString(txt[i] /*+ gain[address[i]][register]*/, marginX + (int) (position[i] * (ppi_to_mm_print / 4) * zoom), marginY + (int) (pos * (ppi_to_mm_print / 4) * zoom));
        }
    }

    private void printGrid(Graphics2D g2d) {
        g2d.setColor(c1);
        
        for (int i = 0; i < 270; ++i) {
            g2d.draw(new Line2D.Double(calc(marginX, i), marginY, calc(marginX, i), calc(marginY, 175)));
            
        }
        for (int i = 0; i < 175; ++i) {
            g2d.draw(new Line2D.Double(marginX, calc(marginY, i), calc(marginX, 270), calc(marginY, i)));
        }
        g2d.setColor(c2);
        for (int i = 0; i < 55; ++i) {
            g2d.draw(new Line2D.Double(calc(marginX, (i * 5)), marginY, calc(marginX, (i * 5)), calc(marginY, 175)));
        }
        for (int i = 0; i < 36; ++i) {
            g2d.draw(new Line2D.Double(marginX, calc(marginY, (i * 5)), calc(marginX, 270), calc(marginY, (i * 5))));
        }

        g2d.setColor(Color.RED);
        g2d.drawLine(marginX, marginY + (99 * zoom), 765 * zoom, marginY + 99 * zoom);
        g2d.drawLine(marginX, marginY + 198 * zoom, 765 * zoom, marginY + 198 * zoom);
        g2d.drawLine(marginX, marginY + 298 * zoom, 765 * zoom, marginY + 298 * zoom);
        g2d.drawLine(marginX, marginY + 397 * zoom, 765 * zoom, marginY + 397 * zoom);
        g2d.drawLine(marginX + 255 * zoom, marginY, marginX + 255 * zoom, 397 * zoom);
        g2d.drawLine(marginX + 510 * zoom, marginY, marginX + 510 * zoom, 397 * zoom);

        g2d.drawLine(marginX, marginY + (99 * zoom), 765 * zoom, marginY + 99 * zoom);
        g2d.drawLine(marginX, marginY + 198 * zoom, 765 * zoom, marginY + 198 * zoom);
        g2d.drawLine(marginX, marginY + 298 * zoom, 765 * zoom, marginY + 298 * zoom);
        g2d.drawLine(marginX, marginY + 397 * zoom, 765 * zoom, marginY + 397 * zoom);
        g2d.drawLine(marginX + 255 * zoom, marginY, marginX + 255 * zoom, 397 * zoom);
        g2d.drawLine(marginX + 510 * zoom, marginY, marginX + 510 * zoom, 397 * zoom);
    }

    private double calc(int xOrY, double value) {
        return (xOrY + (value * ppi_to_mm_print * zoom));
    }

    private void printIdentification(Graphics2D g2d, int register, Date data, int scale, float speed) {
        g2d.setColor(Color.BLACK);
        g2d.drawRect(marginX, marginY, calc(1080) - 1, calc(700) - 1);
        g2d.drawRect(marginX - 1, marginY - 1, calc(1080) + 1, calc(700) + 1);
        if (identification != null || !identification.isEmpty()) {
            g2d.drawString("Registro n� " + (register + 1) + " de " + numberRegisters + "    Paciente: " + identification, marginX,
                    marginY + (int) (700 * (ppi_to_mm_print / 4) * zoom) + 12);
        } else {
            g2d.drawString("Registro n� " + (register + 1) + " de " + numberRegisters, marginX,
                    marginY + (int) (700 * (ppi_to_mm_print / 4) * zoom) + 12);
        }
           g2d.drawString("Escala: "+speed+"mm/s" +"  "+ scale +"mm/mv"+"       Data da realização: "+sdf.format(data), marginX,
                marginY + (int) (720 * (ppi_to_mm_print / 4) * zoom) + 12);
    }

    private int calc(int value) {
        return (int) (value * (ppi_to_mm_print / 4) * zoom);
    }

    private void correct(int register) {
        int average[] = fillsAverage(register);
        for (int i = 0; i < samples[0][register].length; i++) {
            DI[i] = Integer.parseInt(samples[0][register][i]) - average[0];
            DII[i] = Integer.parseInt(samples[1][register][i]) - average[1];
            DIII[i] = Integer.parseInt(samples[2][register][i]) - average[2];
            aVR[i] = Integer.parseInt(samples[3][register][i]) - average[3];
            aVL[i] = Integer.parseInt(samples[4][register][i]) - average[4];
            aVF[i] = Integer.parseInt(samples[5][register][i]) - average[5];
            V1_10[i] = Integer.parseInt(samples[6][register][i]) - average[6];
            V2_10[i] = Integer.parseInt(samples[7][register][i]) - average[7];
            V3_10[i] = Integer.parseInt(samples[8][register][i]) - average[8];
            V4_10[i] = Integer.parseInt(samples[9][register][i]) - average[9];
            V5_10[i] = Integer.parseInt(samples[10][register][i]) - average[10];
            V6_10[i] = Integer.parseInt(samples[11][register][i]) - average[11];
        }
    }

    private int[] fillsAverage(int register) {
        int average[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < samples[0][register].length; i++) {
            average[0] += Integer.parseInt(samples[0][register][i]);
            average[1] += Integer.parseInt(samples[1][register][i]);
            average[2] += Integer.parseInt(samples[2][register][i]);
            average[3] += Integer.parseInt(samples[3][register][i]);
            average[4] += Integer.parseInt(samples[4][register][i]);
            average[5] += Integer.parseInt(samples[5][register][i]);
            average[6] += Integer.parseInt(samples[6][register][i]);
            average[7] += Integer.parseInt(samples[7][register][i]);
            average[8] += Integer.parseInt(samples[8][register][i]);
            average[9] += Integer.parseInt(samples[9][register][i]);
            average[10] += Integer.parseInt(samples[10][register][i]);
            average[11] += Integer.parseInt(samples[11][register][i]);
        }
        average[0] /= samples[11][register].length;
        average[1] /= samples[11][register].length;
        average[2] /= samples[11][register].length;
        average[3] /= samples[11][register].length;
        average[4] /= samples[11][register].length;
        average[5] /= samples[11][register].length;
        average[6] /= samples[11][register].length;
        average[7] /= samples[11][register].length;
        average[8] /= samples[11][register].length;
        average[9] /= samples[11][register].length;
        average[10] /= samples[11][register].length;
        average[11] /= samples[11][register].length;
        return average;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getRegisters() {
        return numberRegisters;
    }
//procura o maior e o menor elemento
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
            return +22;
        } else if (verificaMaior(amplitude(x, 0, x.length - 1)[0] + amplitude(x, 0, x.length - 1)[1], amplitude(y, 0, y.length - 1)[0] + amplitude(y, 0, y.length - 1)[1], amplitude(z, 0, z.length - 1)[0] + amplitude(z, 0, z.length - 1)[1]) > 400) {
            return +42;
        }
        if (verificaMaior(amplitude(x, 0, x.length - 1)[0] + amplitude(x, 0, x.length - 1)[1], amplitude(y, 0, y.length - 1)[0] + amplitude(y, 0, y.length - 1)[1], amplitude(z, 0, z.length - 1)[0] + amplitude(z, 0, z.length - 1)[1]) < -250 && verificaMaior(amplitude(x, 0, x.length - 1)[0] + amplitude(x, 0, x.length - 1)[1], amplitude(y, 0, y.length - 1)[0] + amplitude(y, 0, y.length - 1)[1], amplitude(z, 0, z.length - 1)[0] + amplitude(z, 0, z.length - 1)[1]) > -400) {
            return -22;
        } else if (verificaMaior(amplitude(x, 0, x.length - 1)[0] + amplitude(x, 0, x.length - 1)[1], amplitude(y, 0, y.length - 1)[0] + amplitude(y, 0, y.length - 1)[1], amplitude(z, 0, z.length - 1)[0] + amplitude(z, 0, z.length - 1)[1]) < -400) {
            return -42;
        }
        return 0;
    }

}
