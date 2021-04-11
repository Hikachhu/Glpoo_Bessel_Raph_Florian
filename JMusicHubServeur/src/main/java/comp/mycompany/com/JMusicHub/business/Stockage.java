package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.business.*;
import java.io.Serializable;

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


/**
 * Classe mere de toutes les classes de stockages
 */
public abstract class Stockage implements Serializable{

  protected String Titre;
  protected int ID;
  protected String Contenu;
  /**
   * Informations communes à Playlist,Album,Chanson et LivreAudio
   * @param Titre [Titre ou nom du Stockage]
   * @param ID    [ID unique pour se repérer]
   */
  public Stockage(String Titre,int ID,String Contenu){
    this.Titre=Titre;
    this.ID=ID;
    this.Contenu=Contenu;
  }
  /**
   * Accesseur du Titre
   * @return Renvoi Titre
   */
  public String getTitre(){
    return this.Titre;
  }

  public String getContenu(){
   return this.Contenu;
  }

  /**
   * Accesseur de l'ID
   * @return Renvoi l'ID
   */
  public int getID(){
    return this.ID;
  }

  /**
   * Genere et renvoi un Element contenant les informations de la structure
   * @param  document Document dans lequel nous écrivons
   * @return          Retour de l'Element
   */
  public Element getElement(Document document){
    Element client = document.createElement("Stockage");

      Element Titre = document.createElement("Titre");
      Titre.appendChild(document.createTextNode(getTitre()));
      client.appendChild(Titre);

      Element ID = document.createElement("ID");
      ID.appendChild(document.createTextNode(Integer.toString(getID())));
      client.appendChild(ID);

      return client;
  }
}
