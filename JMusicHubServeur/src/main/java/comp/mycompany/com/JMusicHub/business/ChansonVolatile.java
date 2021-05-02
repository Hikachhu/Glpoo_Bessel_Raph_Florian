package comp.mycompany.com.JMusicHub.business;

import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import java.io.Serializable;
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

/**
 * Classe destinée au stockage de Chansons par le stockage d'une liste de Stockage
 */
public class ChansonVolatile implements StockageVolatile, Serializable{
  public ArrayList<Stockage> Ensemble = new ArrayList<Stockage>();

  /**
   * Formatage spécifique de la liste
   * @return Chaine formaté
   */
  public String toString(){
    String s="";
    for (Stockage Courant : Ensemble ) {
      s+=("\t"+Courant+"\n");
    }
    return s;
  }

  /**
   * Ajout d'une chanson dans la liste des chansons
   * @param nouveau Chanson à ajouter
   */
   public void add(Stockage nouveau){
     Ensemble.add(nouveau);
   }

   /**
    * Accesseur de la liste
    * @return ArrayList des Elements stockées sans traitement
    */
   public ArrayList<Stockage> getEnsemble(){
     return Ensemble;
   }

   /**
    * Obtention d'un element de la liste
    * @param  number Numero de l'element à obtenir
    * @return        Element obtenu
    */
  public Stockage get(int number){

    //Verification de l'accessibilité de l'element
    if(number>=0&&number<=Ensemble.size()){
      return Ensemble.get(number);
    }
    return Ensemble.get(0);
  }

  /**
   * Tri de la liste de la manière voulu
   * @param  Choix Choix du mode de triage
   * @return       String contenant la liste formaté
   */
  public String Tri(int Choix){
    if(Choix==0){
      String s="";
      for (Stockage Actuel : Ensemble) {
        s+=Actuel;
      }
      return s;
    }else{
      return null;
    }
  }

  /**
   * Transforme l'objet actuel en un Element
   * @param  document Document auquel rataché l'Element
   * @return          Element formaté d'apres les valeurs contenu dans l'objet
   */

  public Element getElement(Document document){
   Element client = document.createElement("Playlist");

     Element Liste = document.createElement("Chanson");

     /**
      * Ajout de tout les element de la liste
      */
     for (Stockage Actuel : Ensemble ) {
       Liste.appendChild(Actuel.getElement(document));
     }
     client.appendChild(Liste);

     return client;
  }
}
