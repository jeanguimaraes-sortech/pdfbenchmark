package br.com.sortech.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sortech.service.PDFMergeUtilityService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pdf")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PDFMergeUtilityController {

	private final PDFMergeUtilityService service;

	@PostMapping(value = "/joinWithoutMarkWaterPdfbox")
	public ResponseEntity<Void> joinWithoutMarkWater() throws IOException {
		service.joinWithoutMarkWater();
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/putMarkWater")
	public ResponseEntity<Void> putMarkWater() throws IOException {
		service.putMarkWater();
		return ResponseEntity.ok().build();
	}

}
