package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import java.io.IOException;
import java.io.*;

/**
 * Classe permettant de manipuler une playlist, stockant une liste de livre audio ou Chansons
 */
public class Playlist extends StockageVolatile  implements Listing,Serializable{
  // public ArrayList<Stockage> Ensemble = new ArrayList<Stockage>();
  String Nom;
  Integer ID;

  /**
   * constructeur de Playlist
   * @param Nom Nom de la Playlist
   * @param ID  Numero de la playlist
   */
  public Playlist(String Nom,int ID){
    this.Nom=Nom;
    this.ID=ID;
  }

  /**
   * Accesseur du titre
   * @return le titre
   */
  public String getTitre(){
    return Nom;
  }

  /**
   * Accesseur de l'ID
   * @return L'Id de la playlist
   */
  public Integer getID(){
    return ID;
  }

  /**
   * Affiche l'ensemble des LivreAudio et Chansons stockées
   */
  public void Affiche(){
    for (Stockage Courant : Ensemble ) {
      System.out.println(Courant);
    }
  }

  /**
   * Ajoute un element mis en parametre dans la liste
   * @param stock parametre à rajouter
   */
  // public void add(Stockage stock){
  //   Ensemble.add(stock);
  // }
  //
  // /**
  //  * Accesseur de la liste des données stockées
  //  * @return Retourne la liste
  //  */
  // public ArrayList<Stockage> getEnsemble(){
  //   return Ensemble;
  // }

  /**
   * Fonction d'affichage par defaut
   * @return Renvoi la chaine de texte à afficher
   */
  public String toString(){
    String s="Nom: "+getTitre()+" ID: "+getID()+"\n";
    for (Stockage Courant : Ensemble ) {
      s+="\t\t"+Courant+"\n";
    }
    return s;
  }

  /**
   * Renvoi un Element en fonction du des elements stockées dans playlist
   * @param  document document où écrire
   * @return          Renvoi l'element aaaaaaaaaaaaa
   */
  public Element getElement(Document document){
    Element client = document.createElement("Playlist");

      Element Titre = document.createElement("Titre");
      Titre.appendChild(document.createTextNode(getTitre()));
      client.appendChild(Titre);

      Element ID = document.createElement("ID");
      ID.appendChild(document.createTextNode(Integer.toString(getID())));
      client.appendChild(ID);

      Element ListeAudios = document.createElement("ListeAudios");

      for (Stockage Actuel : Ensemble ) {
        ListeAudios.appendChild(Actuel.getElement(document));
      }
      client.appendChild(ListeAudios);

      return client;
  }

}
