package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.business.*;
import comp.mycompany.com.JMusicHub.util.*;

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


public class Chanson extends Stockage implements Listing,Serializable{
  private String Artiste;
  protected int Duree;
  Genre genre;
  public Chanson(String Titre,int Duree,int ID,String Artiste,String Contenu,int genre){
    super(Titre,ID,Contenu);
    this.Artiste=Artiste;
    this.Contenu=Contenu;
    this.Duree=Duree;
    this.genre=Genre.values()[genre];
  }
  ////////////////////////////////////////////////////////////////////////////////////////
  //Ensemble des Accesseurs directs
  ////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Accesseur d'Artiste
   * @return Renvoi le String Artiste
   */
  public String getArtiste(){
    return this.Artiste;
  }

  /**
   * Accesseur de Contenu
   * @return String avec le contenu
   */
  public String getContenu(){
    return this.Contenu;
  }

  /**
   * Accesseur du Genre
   * @return Renvoi le nom du Genre
   */
  public Genre getGenre(){
    return this.genre;
  }

  /**
   * Accesseur du numero du genre
   * @return renvoi le numero du genre
   */
  public int getGenreNumber(){
    return (this.genre).ordinal();
  }

  /**
   * Renvoi la valeur direct de Duree
   * @return valeur de Duree
   */
  public int getDureeSec(){
    return this.Duree;
  }

  /**
   * Convertit Duree en Minutes
   * @return String contenant le formatage de Duree
   */
  public String getDureeMin(){
    int min=(this.Duree)/60;
    int secondes=((this.Duree)%60);
    return String.valueOf(min)+"m"+String.valueOf(secondes)+"s";
  }

  /**
   * Affichage d'une chanson formaté
   * @return une ligne avec toutes les informations sur la chanson
   */
  public String toString(){
    return "Titre ="+getTitre()+" | Duree ="+getDureeMin()+" | ID= "+getID()+" | Artiste = "+Artiste+" | Contenu = "+ Contenu+ " | genre = "+ getGenre();
  }

  /**
   * Recuperation d'un Element crée à partir des informations de chansons
   * @param  document Document où l'on écris
   * @return          renvoi un element unique d'une chanson
   */
  public Element getElement(Document document){
    Element client = document.createElement("Chanson");

      Element Titre = document.createElement("Titre");
      Titre.appendChild(document.createTextNode(getTitre()));
      client.appendChild(Titre);

      Element Duree = document.createElement("Duree");
      Duree.appendChild(document.createTextNode(Integer.toString(getDureeSec())));
      client.appendChild(Duree);

      Element ID = document.createElement("ID");
      ID.appendChild(document.createTextNode(Integer.toString(getID())));
      client.appendChild(ID);

      Element Artiste = document.createElement("Artiste");
      Artiste.appendChild(document.createTextNode(getArtiste()));
      client.appendChild(Artiste);

      Element Contenu = document.createElement("Contenu");
      Contenu.appendChild(document.createTextNode(getContenu()));
      client.appendChild(Contenu);

      Element Genre = document.createElement("Genre");
      Genre.appendChild(document.createTextNode(Integer.toString(getGenreNumber())));
      client.appendChild(Genre);
      return client;
  }

}
