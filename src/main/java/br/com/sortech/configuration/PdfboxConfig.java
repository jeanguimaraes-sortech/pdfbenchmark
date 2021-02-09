package br.com.sortech.configuration;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.PDFMergerUtility.DocumentMergeMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class PdfboxConfig {

	@Bean
	@RequestScope
	public PDFMergerUtility configure() {
		PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
		pdfMergerUtility.setDocumentMergeMode(DocumentMergeMode.OPTIMIZE_RESOURCES_MODE);
		return pdfMergerUtility;
	}
	
}
