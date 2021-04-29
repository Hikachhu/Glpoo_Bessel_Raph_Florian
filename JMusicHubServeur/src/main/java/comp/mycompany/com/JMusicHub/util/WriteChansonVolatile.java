package comp.mycompany.com.JMusicHub.util;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import java.io.IOException;
import java.io.File;


public class WriteChansonVolatile extends WriteVolatile{

	public StockageVolatile readXML(String Fichier) {

    FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
		StockageVolatile          nouvelle    = FactoryStockageVolatile.Generate("Chanson");
		NodeList nodes = this.parseXMLFile(Fichier);
		if (nodes != null){
			for (int i = 0; i<nodes.getLength(); i++) {	 // pour toutes les nodes de NodeList nodes
				if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE)   { // Si la node est un element
					Element currentElement = (Element) nodes.item(i); 			// Cast de la node en element
					if (currentElement.getNodeName().equals("Chanson")) 	{ // verif du nom de l'elem
						try {
							String Titre = 								 currentElement.getElementsByTagName("Titre").item(0).getTextContent();
							int Duree = 	Integer.parseInt(currentElement.getElementsByTagName("Duree").item(0).getTextContent());
							int ID = 			Integer.parseInt(currentElement.getElementsByTagName("ID").item(0).getTextContent());
							String Artiste = 							 currentElement.getElementsByTagName("Artiste").item(0).getTextContent();
							String Contenu = 							 currentElement.getElementsByTagName("Contenu").item(0).getTextContent();
							int Genre = 	Integer.parseInt(currentElement.getElementsByTagName("Genre").item(0).getTextContent());
							Chanson lu = new Chanson(Titre,Duree,ID,Artiste,Contenu,Genre);
							nouvelle.add(lu);
						} catch (Exception ex) {
							System.out.println("Something is wrong with the XML client element");
						}
					}
				}
			}
		}
		return nouvelle;
	}

	public void writeXML(String Fichier,StockageVolatile EnsembleAecrire) {


		LivreAudioVolatile  ListeLivreAudio = new LivreAudioVolatile();

		WriteLivreAudioVolatile FichierLivreAudio = new WriteLivreAudioVolatile();
		ListeLivreAudio  = FichierLivreAudio.readXML("files/Element.xml");

		Document document = this.createXMLDocument();
		if (document == null) return;

		Element root = document.createElement("Chansons");
		document.appendChild(root);


		for (Stockage Ecrire : ListeLivreAudio.getEnsemble() ) {
			root.appendChild(Ecrire.getElement(document));
		}

		for (Stockage Aecrire : EnsembleAecrire.getEnsemble() ) {
			root.appendChild(Aecrire.getElement(document));
		}
		this.createXMLFile(document, Fichier);
	}

}
