/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.diagnostico.dominio.glasgow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe com metodos estaticos apenas para formatar a saida do EcgResult
 *
 * @author paulo.gomes
 */
public class ReportEcgAnalys {

    //<editor-fold defaultstate="collapsed" desc="Constantes">
    private static final int GRI_FLAG_DERIVEDXYZ = 0x00020000;	/* Input lead buffers contain derived XYZ */

    private static final int GRI_FLAG_QTC_HODGE = 0x00000200;	/* Indicate use of Hodges QTc */

    private static final int GRI_FLAG_QTC_BAZETT = 0x00000400;	/* Indicate use of Bazetts QTc */

    private static final int GRI_FLAG_QTC_FRIDERICIA = 0x00000800;	/* Indicate use of Fridericias QTc */

    private static final int GRI_FLAG_QTC_FRAMINGHAM = 0x00001000;	/* Indicate use of Framinghams QTc */

    private static final int GRI_FLAG_15LEADPROCESSING = 0x00010000;	/* Input lead buffers contain additional 3 leads */

    private static final String TITLE = "Ecg_Result_#.txt";

    //</editor-fold>
    private static List<String> strings;
    private static StdOut stdOut = StdOut.CONSOLE;
    private static String title = TITLE;

    private static void printString(String value) {
        strings.add(value);
    }

    private static void printReport() {
        switch (stdOut) {
            case FILE:
                try {
                    FileOutputStream file = new FileOutputStream(new File(title));
                    for (String string : strings) {
                        file.write((string + "\n").getBytes());
                    }
                    file.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ReportEcgAnalys.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ReportEcgAnalys.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case CONSOLE:
                for (String string : strings) {
                    System.out.println(string);
                }
                break;
            default:
                break;
        }
    }

    public static List<String> getStrings() {
        return strings;
    }

    /**
     * Altera o padrao de saida segundo o enum StdOut dentro da classe
     * ReportEcgAnalys
     *
     * @param stdOut
     */
    public static void setStdOut(StdOut stdOut) {
        ReportEcgAnalys.stdOut = stdOut;
    }

    /**
     * Estrutura o result de entrada para uma saida formatada exibindo a saida
     * de acordo com o valor definido em StdOut que por padrao � CONSOLE.
     *
     * @param result EcgResult sendo diferente de nulo e com id exame n�o nulo
     */
    public static void report(EcgAnalys result) {
        if (result == null || result.getIdExame() == null) {
            return;
        }
        strings = new ArrayList();
        title = TITLE.replace("#", result.getIdExame().toString());
        int Flags = result.getFlags().intValue();
        String aux = "";
        printString("Global Measurements");
        printString("===================");

        printString(String.format("QrsFrontalAxis       %5d   PFrontalAxis %5d\nSTFrontalAxis        %5d   TFrontalAxis %5d",
                result.getQrsFrontalAxis(),
                result.getpFrontalAxis(),
                result.getStFrontalAxis(),
                result.gettFrontalAxis()));

        printString(String.format("Display Heart Rate   %5d", result.getHeartRate()));
        printString(String.format("Sinus Rate           %5d   Average RR   %5d", result.getSinusRate(), result.getSinusAverageRR()));
        printString(String.format("Ventricular Rate     %5d   Average RR   %5d", result.getVentRate(), result.getVentAverageRR()));

        printString(String.format("Heart Rate Variability                    %5d", result.getHeartRateVariability()));
        printString(String.format("StdDev Normal RR Intervals                %5d", result.getStdDevNormRRIntervals()));

        printString(String.format("LVH Score            %5d   LV Strain    %5d", result.getLvHscore(), result.getLvStrain()));
        printString(String.format("ST Duration          %5d", result.getOverallSTDuration()));
        if ((Flags & GRI_FLAG_15LEADPROCESSING) == 1) {
            if ((Flags & GRI_FLAG_DERIVEDXYZ) == 1) {
                printString(String.format("QRST spatial angle from XYZ      %5d", result.gettAngles()));
            }
        }
        printString(String.format("PR Interval          %5d", result.getOverallPRInterval()));
        printString(String.format("QT Interval          %5d", result.getOverallQTInterval()));

        printString(String.format("QT Dispersion        %5d", result.getQtDispersion()));
        if ((Flags & GRI_FLAG_QTC_BAZETT) == GRI_FLAG_QTC_BAZETT) {
            aux = " [BAZETT]";
        } else if ((Flags & GRI_FLAG_QTC_FRIDERICIA) == GRI_FLAG_QTC_FRIDERICIA) {
            aux = " [FRIDERICIA]";
        } else if ((Flags & GRI_FLAG_QTC_FRAMINGHAM) == GRI_FLAG_QTC_FRAMINGHAM) {
            aux = " [FRAMINGHAM]";
        } else {
            aux = " [HODGE]";
        }
        printString(String.format("QT Corrected (used)  %5d %s", result.getQtc(), aux));
        printString(String.format("P Terminal (V1)      %5d", result.getpTerminalForceInV1()));
        printString(" ");
        printString("QTc Measurements:");
        printString(String.format("    Hodge            %5d", result.getQtcHodge()));
        printString(String.format("    Bazett           %5d", result.getQtcBazett()));
        printString(String.format("    Fridericia       %5d", result.getQtcFridericia()));
        printString(String.format("    Framingham       %5d", result.getQtcFramingham()));
        printString(" ");

        printString(String.format("Flags        %d", result.getFlags()));
        printString(String.format("  - P waves found           %s", ((result.ispWaves() ? "Sim" : "Não"))));
        printString(String.format("  - Indeterminate QRS axis  %s", ((result.isInderterminateQRSAxis() ? "Sim" : "Não"))));
        printString(String.format("  - Default gender used     %s", ((result.isDefaultGender() ? "Sim" : "Não"))));
        printString(String.format("  - Default race used       %s", ((result.isDefaultRace() ? "Sim" : "Não"))));
        printString(String.format("  - Default age used        %s", ((result.isDefaultAge() ? "Sim" : "Não"))));

        printString(" ");
        printString("Overall Onsets, Terminations and Durations:");
        printString("------------------------------------------");
        printString("           Onset       Termination    Duration");
        printString(String.format(" P        %5d          %5d         %5d",
                result.getOverallPOnset(),
                result.getOverallPTermination(),
                result.getOverallPDuration()));
        printString(String.format(" QRS      %5d          %5d         %5d",
                result.getOverallQrsOnset(),
                result.getOverallQrsTermination(),
                result.getOverallQrsDuration()));
        printString(String.format(" T        %5d          %5d         %5d",
                result.getOverallTOnset(),
                result.getOverallTTermination(),
                result.getOverallTDuration()));

        printString("");
        printString("Interpretation");
        printString("==============");
        printString(result.getDescription());
        printString("");
        printString(String.format("Summary Code: %d ", result.getSummaryCode()));
        printString("");
        printReport();
    }

    public static enum StdOut {

        CONSOLE, FILE, STRING;
    }
}
