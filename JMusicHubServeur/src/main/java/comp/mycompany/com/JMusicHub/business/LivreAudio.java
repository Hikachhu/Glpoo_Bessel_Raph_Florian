package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
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
import java.io.*;

/**
 * Classe manipulant les informations d'un livre audio
 */
public class LivreAudio extends Stockage implements Listing,Serializable{
  private String Auteur;
  private int Duree;
  private Langues langue;
  private Categorie categorie;

  public LivreAudio(String Titre,int Duree,int ID,String Auteur,String Contenu,int langue,int categorie){
    super(Titre,ID,Contenu,"LivresAudios");
    this.Auteur=Auteur;
    this.Contenu=Contenu;
    this.Duree=Duree;
    this.categorie=Categorie.values()[categorie];
    this.langue=Langues.values()[langue];
  }

  /**
   * Accesseur de la durée d'un livre audio
   * @return La valeur de temps
   */
  public int getDureeSec(){
    return this.Duree;
  }

  /**
   * Genere le temps sous le format XmXs pour min et seconde
   * @return renvoi une chaine formaté
   */
  public String getDureeMin(){
    int min=(this.Duree)/60;
    int secondes=((this.Duree)%60);
    return String.valueOf(min)+"m"+String.valueOf(secondes)+"s";
  }

  /**
   * Accesseur de l'auteur
   * @return le nom de l'auteur
   */
  public String getAuteur(){
    return this.Auteur;
  }

  /**
   * Accesseur du contenu
   * @return Renvoi le contenu
   */
  public String getContenu(){
    return this.Contenu;
  }

  /**
   * Accesseur du numero de la categorie
   * @return Numero de la categorie
   */
  public int getCategorieNumber(){
    return (this.categorie).ordinal();
  }

  /**
   * Accesseur du numero de la langue
   * @return Numero de la langue
   */
  public int getLangueNumber(){
    return (this.langue).ordinal();
  }

  /**
   * Affichage formaté des information d'un livre audio
   * @return [description]
   */
  public String toString(){
    return "Titre="+Titre +" Duree="+getDureeMin()+" ID="+ID+" Auteur="+ Auteur+" Contenu="+ Contenu+" langue="+ langue+" categorie=" +categorie;
  }



  /**
   * Recuperation d'un Element crée à partir des informations d'un livre audio
   * @param  document Document où l'on écris
   * @return          renvoi un element unique d'une chanson
   */
  public Element getElement(Document document){
    Element client = document.createElement("LivreAudio");

      Element Titre = document.createElement("Titre");
      Titre.appendChild(document.createTextNode(getTitre()));
      client.appendChild(Titre);

      Element Duree = document.createElement("Duree");
      Duree.appendChild(document.createTextNode(Integer.toString(getDureeSec())));
      client.appendChild(Duree);

      Element ID = document.createElement("ID");
      ID.appendChild(document.createTextNode(Integer.toString(getID())));
      client.appendChild(ID);

      Element Artiste = document.createElement("Auteur");
      Artiste.appendChild(document.createTextNode(getAuteur()));
      client.appendChild(Artiste);

      Element Contenu = document.createElement("Contenu");
      Contenu.appendChild(document.createTextNode(getContenu()));
      client.appendChild(Contenu);

      Element Langue = document.createElement("Langue");
      Langue.appendChild(document.createTextNode(Integer.toString(getCategorieNumber())));
      client.appendChild(Langue);

      Element Categorie = document.createElement("Categorie");
      Categorie.appendChild(document.createTextNode(Integer.toString(getLangueNumber())));
      client.appendChild(Categorie);

      return client;
  }
}
