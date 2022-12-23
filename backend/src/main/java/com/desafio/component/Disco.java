package com.desafio.component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Component
public class Disco {
	
	@Value("${contato.disco.raiz}")
	private String raiz;
	
	@Value("${contato.disco.diretorio-arquivos}")
	private String diretorioArquivos;
	
	public void salvarArquivo(MultipartFile arquivo) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		this.salvar(this.diretorioArquivos, arquivo);
	}
	
	public void salvar(String diretorio, MultipartFile arquivo) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		Path diretorioPath = Paths.get(this.raiz, diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
				
			Files.createDirectories(diretorioPath);
			File xmlFile = arquivoPath.toFile();		
			
			arquivo.transferTo(xmlFile);

			if (xmlFile.length() > 0){
				
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				
				System.out.println("Arquivo: "+xmlFile.getName());
				System.out.println("Caminho: "+xmlFile.getPath());

				Document document = builder.parse(xmlFile);

				// Tratando preço médio
				NodeList precoMedioElements = document.getElementsByTagName("precoMedio");
				if (precoMedioElements.getLength() > 0) {
					for (int i = 0; i < precoMedioElements.getLength(); i++) {
						Element precoMedio = (Element) precoMedioElements.item(i);
						NodeList valorElements = precoMedio.getElementsByTagName("valor");
						
						for (int j = 0; j < valorElements.getLength(); j++) {
							Element valor = (Element) valorElements.item(j);
							valor.setTextContent(" ");
						}


					 }
				}

				// Obtenha o elemento "codigo" do documento XML
				NodeList codeElements = document.getElementsByTagName("codigo");
				if (codeElements.getLength() > 0) {
					Element codeElement = (Element) codeElements.item(0);
					String code = ((Node) codeElement).getTextContent();
					System.out.println(
						"Código: "+code
					);
				}
				// Create a transformer.
				Transformer transformer = TransformerFactory.newInstance().newTransformer();

				// Transform the document.
				transformer.transform(new DOMSource(document), new StreamResult(xmlFile));
			}
			  
	}
}
