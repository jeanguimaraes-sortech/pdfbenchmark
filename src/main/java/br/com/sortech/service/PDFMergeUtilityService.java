package br.com.sortech.service;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PDFMergeUtilityService {

	private final PDFMergerUtility utility;

	public void joinWithoutMarkWater() throws IOException {
		utility.addSource(PDFMergeUtilityService.class.getClassLoader().getResource("static/1.pdf").getPath());
		utility.addSource(PDFMergeUtilityService.class.getClassLoader().getResource("static/2.pdf").getPath());
		utility.addSource(PDFMergeUtilityService.class.getClassLoader().getResource("static/3.pdf").getPath());
		utility.addSource(PDFMergeUtilityService.class.getClassLoader().getResource("static/4.pdf").getPath());
		utility.addSource(PDFMergeUtilityService.class.getClassLoader().getResource("static/5.pdf").getPath());
		utility.addSource(PDFMergeUtilityService.class.getClassLoader().getResource("static/6.pdf").getPath());
		utility.addSource(PDFMergeUtilityService.class.getClassLoader().getResource("static/7.pdf").getPath());
		utility.addSource(PDFMergeUtilityService.class.getClassLoader().getResource("static/8.pdf").getPath());
		utility.addSource(PDFMergeUtilityService.class.getClassLoader().getResource("static/9.pdf").getPath());
		utility.setDestinationFileName("C:\\Users\\Kelisson\\Desktop\\Teste\\abacate.pdf");
		utility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
	}

	public void putMarkWater() throws IOException {
		try (PDDocument doc = PDDocument.load(new File("C:\\Users\\Kelisson\\Desktop\\Teste\\abacate.pdf"))) {
			PDFont font = PDType1Font.HELVETICA_BOLD;
			float fontSize = 36.0f;
			for (PDPage page : doc.getPages()) {
				PDRectangle pageSize = page.getMediaBox();
				float stringWidth = font.getStringWidth("PÃO COM SALAME") * fontSize / 1000f;
				int rotation = page.getRotation();
				boolean rotate = rotation == 90 || rotation == 270;
				float pageWidth = rotate ? pageSize.getHeight() : pageSize.getWidth();
				float pageHeight = rotate ? pageSize.getWidth() : pageSize.getHeight();
				float centerX = rotate ? pageHeight / 2f : (pageWidth - stringWidth) / 2f;
				float centerY = rotate ? (pageWidth - stringWidth) / 2f : pageHeight / 2f;
				try (PDPageContentStream contentStream = new PDPageContentStream(doc, page, AppendMode.APPEND, true,
						true)) {
					contentStream.beginText();
					contentStream.setFont(font, fontSize);
					contentStream.setNonStrokingColor(Color.red);
					if (rotate) {
						contentStream.setTextMatrix(Matrix.getRotateInstance(Math.PI / 2, centerX, centerY));
					} else {
						contentStream.setTextMatrix(Matrix.getTranslateInstance(centerX, centerY));
					}
					contentStream.showText("PÃO COM SALAME");
					contentStream.endText();
				}
			}
			doc.save("C:\\Users\\Kelisson\\Desktop\\Teste\\abacate_marca.pdf");
		}
	}

}
