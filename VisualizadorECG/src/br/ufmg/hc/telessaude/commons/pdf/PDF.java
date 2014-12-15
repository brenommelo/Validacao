/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.commons.pdf;

import br.ufmg.hc.telessaude.telediagnostico.xmlfactory.estrutura.CONTEUDOEXAME;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author igor.santos
 */
public class PDF extends PdfPageEventHelper {

    private Header h;
    protected final String NAO_INFORMADO = "Não Informado";
    private FileOutputStream file;
    private Document document;
    private PdfWriter writer;
    private int pageNumber;
    private String nameFile;
    private String space = " ";
    private String tab = "      ";
    private String linebreak = "\n";
    private static Font[] SUBTITLE = new Font[2];
    private static Font[] UNDEFINED = new Font[2];
    protected final Font fontTitle = new Font(FontFamily.UNDEFINED, 13, Font.BOLD);
    private List<Line> lines = new ArrayList<>();
    int sampling = 600;

    static {
        SUBTITLE[0] = new Font(FontFamily.UNDEFINED, 11, Font.BOLD);
        SUBTITLE[1] = new Font(FontFamily.UNDEFINED, 9);
        UNDEFINED[0] = new Font(FontFamily.UNDEFINED, 9, Font.BOLD);
        UNDEFINED[1] = new Font(FontFamily.UNDEFINED, 9);
    }

    public PDF() {
        super();
    }

    public PDF(String path, Header header) {
        try {
            document = new Document(PageSize.A4, 50, 50, 50, 50);
            nameFile = path;
            file = new FileOutputStream(nameFile);
            writer = PdfWriter.getInstance(document, file);
            writer.setPageEvent(this);
            h = header;
            document.open();
            clearLines();
        } catch (FileNotFoundException | DocumentException ex) {
        }
    }

    protected void setPDF(String path, Header header) {
        try {
            document = new Document(PageSize.A4, 50, 50, 50, 50);
            nameFile = path;
            file = new FileOutputStream(nameFile);
            writer = PdfWriter.getInstance(document, file);
            writer.setPageEvent(this);
            h = header;
            document.open();
            clearLines();
        } catch (FileNotFoundException | DocumentException ex) {
            ex.printStackTrace();
        }
    }

    protected void createContent(String title) {
        if (document == null || !document.isOpen()) {
            System.out.println("Documento nao foi aberto.");
            return;
        }
        try {
            /////--- cria a primeira linha, titulo
            Paragraph header = new Paragraph(title, fontTitle);
            header.setSpacingAfter(3);
            document.add(header);
            /////--- faz um loop para criar linhas do content
            Paragraph content = new Paragraph();
            content.setIndentationLeft(10);
            content.setSpacingAfter(5);
            Phrase phrase;
            for (Line line : lines) {
                /////--- label
                phrase = new Phrase(line.getLabel(), line.getFont()[0]);
                content.add(phrase);
                /////--- caso conteudo seja um texto
                if (line.isText()) {
                    PdfPTable box = new PdfPTable(1);
                    PdfPCell cell = new PdfPCell(new Phrase(line.getContent(), line.getFont()[1]));
                    cell.setPadding(10);
                    box.addCell(cell);
                    box.setWidthPercentage(100);
                    content.add(box);
                    break;
                }
                /////--- conteudo
                phrase = new Phrase(line.getContent(), line.getFont()[1]);
                content.add(phrase);
            }
            document.add(content);
            clearLines();
        } catch (DocumentException ex) {
        }
    }

    protected void addLine(boolean nextLine, String... args) {
        if (nextLine) {
            lines.add(new Line(args[0] + space, (args[1] == null ? "" : args[1]) + linebreak, UNDEFINED, false));
        } else {
            lines.add(new Line(args[0] + space, (args[1] == null ? "" : args[1]) + tab, UNDEFINED, false));
        }
    }

    protected void printTracing(CONTEUDOEXAME conteudo, String nomePaciente, Date date, int scale, float speed) {
        document.setPageSize(new Rectangle(PageSize.A4.rotate()));
        document.setMargins(50, 50, 20, 50);
        if (conteudo.getTAXAAMOSTRAGEM().equals("300 AMOSTRAS POR SEGUNDO")) {
            sampling = 150;
        }

        PrintProtocol printProtocolo = new PrintProtocol();
        //- identifica��o do paciente
        printProtocolo.setIdentification(nomePaciente);
        printProtocolo.createProtocol(conteudo, document);

        PdfContentByte content;
        for (int register = 0; register < printProtocolo.getRegisters(); register++) {
            document.newPage();
            content = writer.getDirectContent();
            content.saveState();
            PdfTemplate template = content.createTemplate(document.getPageSize().getWidth(), document.getPageSize().getHeight());
            Graphics2D graphics;
            graphics = printProtocolo.print(template, document.getPageSize(), register, date, scale, speed, sampling);
            content.addTemplate(template, document.leftMargin(), -(document.topMargin()));
            graphics.dispose();
            content.restoreState();
        }
    }

    protected void addText(String label, String content) {
        lines.add(new Line(label, content, UNDEFINED, true));
    }

    protected void addText(String label, String content, Font... font) {
        lines.add(new Line(label, content, font, true));
    }

    protected void addSubtitle(String label) {
        lines.add(new Line(label, linebreak, UNDEFINED, false));
    }

    protected void addLinebreak() {
        lines.add(new Line("", linebreak, UNDEFINED, false));
    }

    private void clearLines() {
        lines.clear();
    }

    protected void closeDocument() {
        if (document != null && document.isOpen()) {
            document.close();
        }
    }

    public String getNameFile() {
        return nameFile;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        /////--- cria o cabecalho com a logo
        try {
            PdfPTable header = new PdfPTable(3);

            //- logo adicionado a esquerda
            Image logo = Image.getInstance(this.getClass().getResource("/br/ufmg/hc/telessaude/commons/pdf/images/logo.png"));
            logo.scaleAbsolute(145, 60);
            logo.setBorder(PdfPCell.NO_BORDER);
            PdfPCell headerLogo = new PdfPCell(logo);
            headerLogo.setRowspan(5);
//            headerLogo.setBorder(PdfPCell.NO_BORDER);
            headerLogo.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            headerLogo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            header.addCell(headerLogo);

            //- titulo do documento
            PdfPCell colunm = new PdfPCell(new Phrase("Centro de Telessaúde - Hospital das Clinicas - UFMG", fontTitle));
//            colunm.setBorder(PdfPCell.NO_BORDER);
            colunm.setColspan(2);
            colunm.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            header.addCell(colunm);

            //- informa��es do exame
            colunm = new PdfPCell(new Phrase("Ponto Remoto:  " + h.getPontoRemoto(), UNDEFINED[1])); //- row[1]col[1-2]
//            colunm.setBorder(PdfPCell.NO_BORDER);
            colunm.setColspan(2);
            colunm.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            header.addCell(colunm);

            colunm = new PdfPCell(new Phrase("Data da Realização do Exame:  " + h.getDataRealizacao(), UNDEFINED[1])); //- row[1]col[1-2]
//            colunm.setBorder(PdfPCell.NO_BORDER);
            colunm.setColspan(2);
            colunm.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            header.addCell(colunm);

            colunm = new PdfPCell(new Phrase("Nome do Paciente:  " + h.getNomePaciente(), UNDEFINED[1])); //- row[2]col[1-2]
//            colunm.setBorder(PdfPCell.NO_BORDER);
            colunm.setColspan(2);
            colunm.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            header.addCell(colunm);

            colunm = new PdfPCell(new Phrase("Genêro:  " + h.getGenero(), UNDEFINED[1])); //- row[3]col[1]
//            colunm.setBorder(PdfPCell.NO_BORDER);
            colunm.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            header.addCell(colunm);

            colunm = new PdfPCell(new Phrase("Nascimento:  " + h.getDataNascimento(), UNDEFINED[1])); //- row[3]col[2]
//            colunm.setBorder(PdfPCell.NO_BORDER);
            colunm.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            header.addCell(colunm);

            if (document.getPageNumber() == 1) {
                colunm = new PdfPCell(new Phrase("Resultado de Exame de Eletrocardiograma", fontTitle));
                colunm.setColspan(3);
                colunm.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                colunm.setBorder(PdfPCell.NO_BORDER);
                colunm.setPaddingTop(10);
                header.addCell(colunm);
            }

            header.setSpacingAfter(10);
            header.setHorizontalAlignment(PdfPTable.ALIGN_MIDDLE);
//            float widthFooter = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
            header.setWidthPercentage(100);

            document.add(header);
        } catch (DocumentException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        try {
            pageNumber++;
            /////--- cria o rodape com informa��o do telessaude
            PdfPTable footer = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(); //- primeira coluna
            cell.setPhrase(new Phrase("Site: http://www.telessaude.hc.ufmg.br\r\n\nTel: (31) 3409-9201")); // escreve o site e o telefone no pdf
            cell.setBorder(1);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            footer.addCell(cell);
            cell = new PdfPCell(); //- segunda coluna
            cell.setPhrase(new Phrase("E-mail: telessaude@hc.ufmg.br\r\n\nPágina " + pageNumber));
            cell.setBorder(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT); // alinha a celula a direita da tabela
            cell.setVerticalAlignment(Element.ALIGN_TOP); // alinha a celula a cima da tabela
            footer.addCell(cell);
            //- escreve no rodape
            float widthFooter = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
            footer.setTotalWidth(widthFooter);
            footer.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
