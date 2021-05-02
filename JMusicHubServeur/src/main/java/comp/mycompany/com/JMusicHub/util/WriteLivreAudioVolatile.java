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
import java.util.UUID;

/**
 * Classe ecrivant/lisant les informations d'une liste de LivreAudio dans un fichier XML
 */
public class WriteLivreAudioVolatile extends WriteVolatile{

	/**
	* Lis une liste de LivreAudio depuis un fichier passé en parametre
	* @param  Fichier Fichier contenant la liste des LivreAudio
	* @return         Liste lu
	*/
	public LivreAudioVolatile readXML(String Fichier) {
		LivreAudioVolatile nouvelle = new LivreAudioVolatile();
		NodeList nodes = this.parseXMLFile(Fichier);
		if (nodes != null){
			for (int i = 0; i<nodes.getLength(); i++) {
				if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE)   {
					Element currentElement = (Element) nodes.item(i);
					if (currentElement.getNodeName().equals("LivreAudio")) 	{
						try {
							String Titre = 										 currentElement.getElementsByTagName("Titre").item(0).getTextContent();
							int Duree = 			Integer.parseInt(currentElement.getElementsByTagName("Duree").item(0).getTextContent());
							int ID = 					Integer.parseInt(currentElement.getElementsByTagName("ID").item(0).getTextContent());
							String Auteur = 									 currentElement.getElementsByTagName("Auteur").item(0).getTextContent();
							String Contenu =	  							 currentElement.getElementsByTagName("Contenu").item(0).getTextContent();
							int Langue = 			Integer.parseInt(currentElement.getElementsByTagName("Langue").item(0).getTextContent());
							int Categorie = 	Integer.parseInt(currentElement.getElementsByTagName("Categorie").item(0).getTextContent());
							LivreAudio lu = new LivreAudio(Titre,Duree,ID,Auteur,Contenu,Langue,Categorie);
							nouvelle.add(lu);
						} catch (Exception ex) {
							System.out.println("Something is wrong with the XML client element"+ex.getMessage());
						}
					}
				}
			}
		}
		return nouvelle;
	}

	public void writeXML(String Fichier,StockageVolatile EnsembleAecrire) {
		Document document = this.createXMLDocument();
		if (document == null) return;

		Element root = document.createElement("LivreAudios");
		document.appendChild(root);

		FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
		StockageVolatile  ListeChanson = FactoryStockageVolatile.Generate("Chanson");

		WriteChansonVolatile FichierChansons = new WriteChansonVolatile();
		ListeChanson  = FichierChansons.readXML(Fichier);

		for (Stockage Aecrire : EnsembleAecrire.getEnsemble() ) {
			root.appendChild(Aecrire.getElement(document));
		}

		for (Stockage Aecrire : ListeChanson.getEnsemble() ) {
			root.appendChild(Aecrire.getElement(document));
		}

		this.createXMLFile(document, Fichier);
	}

}
