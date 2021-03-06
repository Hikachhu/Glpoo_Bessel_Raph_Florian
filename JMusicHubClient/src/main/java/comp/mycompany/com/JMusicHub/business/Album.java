package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import java.io.IOException;
import java.io.*;
import org.apache.log4j.Logger;

/**
 * Classe permettant de stocker les informations sur un album, contient une liste de Stockage
 */

public class Album implements StockageVolatile, Listing,Serializable{
  private String Artiste;
  private int DateSortie;
  protected int Duree;
  private String Titre;
  private int ID;
  /**
   * Stocke l'ensemble des stockages
   */
  public ArrayList<Stockage> Ensemble = new ArrayList<Stockage>();

  /**
   * Classe Album
   * @param Titre      Titre de l'album
   * @param Duree      Duree de l'album en secondes
   * @param ID         ID de l'album
   * @param Artiste    Nom de l'artiste
   * @param DateSortie Date de sortie entre 0 et la date actuel
   */
  public Album(String Titre,int Duree,int ID,String Artiste,int DateSortie){
    this.Titre=Titre;
    this.ID=ID;
    this.Artiste=Artiste;
    this.DateSortie=DateSortie;
    this.Duree=Duree;
  }


  /**
   * Accesseur du Titre
   * @return Renvoi Titre
   */
  public String getTitre(){
    return this.Titre;
  }


  /**
   * Accesseur de l'ID
   * @return Renvoi l'ID
   */
  public int getID(){
    return this.ID;
  }

  /**
   * Accesseur de Duree en seconde
   * @return renvoi la Duree en brut
   */
  public int getDureeSec(){
    return this.Duree;
  }

  /**
   * Obtient un String contenant la Duree en minute
   * @return Un string contenant de format 'Minutes m Secondes s'
   */
  public String getDureeMin(){
    int min=(this.Duree)/60;
    int secondes=((this.Duree)%60);
    return String.valueOf(min)+"m"+String.valueOf(secondes)+"s";
  }

  /**
   * Ajoute une chanson dans la liste des chansons
   * @param stock Chanson à ajouter
   */
  public void add(Stockage stock){
    Ensemble.add(stock);
  }

  /**
   * Accesseur de l'ArrayList des chansons
   * @return ArrayList contenant les chansons
   */
  public ArrayList<Stockage> getEnsemble(){
    return Ensemble;
  }

  /**
   * Accesseur de la date de sortie
   * @return int contenant la date de sortie
   */
  public int getDateSortie(){
    return this.DateSortie;
  }

  /**
   * Renvoi une chaine avec au début les informations sur l'album puis l'ensemble des chansons
   * @return String avec toutes les informations sur un album
   */
  public String toString(){
    String s="Album: "+getTitre()+" ID: "+getID()+" Artiste: "+Artiste+" DateSortie: "+DateSortie+" Duree: "+getDureeMin()+"\n";
    for (Stockage Courant : Ensemble ) {
      s+=("\t\t"+Courant+"\n");
    }
    return s;
  }

  /**
   * Renvoi un element contenu dans la liste des Stockages
   * @param  Numero Numero de l'element voulu
   * @return        Element voulu
   */
  public Stockage get(int Numero){
    return Ensemble.get(Numero);
  }

  /**
   * Accesseur d'artiste
   * @return String contenant le nom de l'artiste
   */
  public String getArtiste(){
    return this.Artiste;
  }

  /**
   * Creation d'un element avec toutes les informations sur l'album avec les chansons contenus
   * @param  document Document où l'element sera écris
   * @return          Renvoi l'Element
   */
  public Element getElement(Document document){

      Element AlbumElem = document.createElement("Album");
        Element Titre = document.createElement("Titre");
        Titre.appendChild(document.createTextNode(getTitre()));
        AlbumElem.appendChild(Titre);

        Element Duree = document.createElement("Duree");
        Duree.appendChild(document.createTextNode(Integer.toString(getDureeSec())));
        AlbumElem.appendChild(Duree);

        Element ID = document.createElement("ID");
        ID.appendChild(document.createTextNode(Integer.toString(getID())));
        AlbumElem.appendChild(ID);

        Element DateSortie = document.createElement("DateSortie");
        DateSortie.appendChild(document.createTextNode(Integer.toString(getDateSortie())));
        AlbumElem.appendChild(DateSortie);

        Element Artiste = document.createElement("Artiste");
        Artiste.appendChild(document.createTextNode(getArtiste()));
        AlbumElem.appendChild(Artiste);

        Element ListeChanson = document.createElement("ListeChanson");

        for(Stockage  ChansonEcrire : Ensemble){
          ListeChanson.appendChild(ChansonEcrire.getElement(document));
        }

        AlbumElem.appendChild(ListeChanson);
    return AlbumElem;
  }

  /**
   * Genere un ArrayList contenant les chansons triée selon les genres
   * @return l'ArrayList avec les chansons
   */
  public ArrayList<Chanson> TriAlbum(){
   ArrayList<Stockage> Init= new ArrayList<Stockage>(Ensemble);
   ArrayList<Chanson> Trier= new ArrayList<Chanson>();
   for (Stockage init : Init ) {
     Trier.add((Chanson)init);
   }

   Trier.sort((p1, p2) -> p1.getGenre().compareTo(p2.getGenre()));
   return Trier;
  }

  /**
   * Tri la liste des Stockage selon un choix
   * @param  Selection Numero du tri voulu
   * @return           Renvoi la liste formaté selon le tri voulu
   */
 public String Tri(int Selection){
   if(Selection==0){
      ArrayList<Stockage> Init= new ArrayList<Stockage>(Ensemble);
      ArrayList<Chanson> Trier= new ArrayList<Chanson>();
      for (Stockage init : Init ) {
        Trier.add((Chanson)init);
      }
      Trier.sort((p1, p2) -> p1.getGenre().compareTo(p2.getGenre()));
      return Trier.toString();
    }else{
      return "ERROR";
    }
 }

}
