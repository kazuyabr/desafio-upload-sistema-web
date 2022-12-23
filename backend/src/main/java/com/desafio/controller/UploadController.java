package com.desafio.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.desafio.component.Disco;

@RestController
@RequestMapping("/upload")
@CrossOrigin("*")
public class UploadController {
	
	@Autowired
	private Disco disco;
	
	@PostMapping
	public void upload(@RequestParam("files") MultipartFile[] files){
			
		
		for (MultipartFile file : files) {			

			try {
				disco.salvarArquivo(file);
				
			} catch (DOMException | IOException | ParserConfigurationException | SAXException | TransformerException e) {
				System.out.println("Ocorreram uma ou mais excessões!");				
				System.out.println("Messagem: "+e.getMessage());
				System.out.println("------------ TRACE --------------");				
				e.printStackTrace();
				System.out.println("----------------------------------");
			}
		}
	}

}