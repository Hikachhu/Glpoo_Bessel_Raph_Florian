package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;
import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import java.io.IOException;
import java.io.*;

/**
 * Classe manipulant une liste de LivresAudios
 */
public class LivreAudioVolatile  implements StockageVolatile, Serializable{
  public ArrayList<Stockage> Ensemble = new ArrayList<Stockage>();

    /**
     * Ajout d'un element dans la liste des stockages
     * @param nouveau Information à ajouter
     */
     public void add(Stockage nouveau){
       Ensemble.add(nouveau);
     }

     /**
      * Accesseur de la liste des elements
      * @return la liste sans modification
      */
     public ArrayList<Stockage> getEnsemble(){
       return Ensemble;
     }

     /**
      * Accesseur d'un element specifique
      * @param  number Numero de l'element à obtenir
      * @return        Element selectionné
      */
    public Stockage get(int number){
      if(number>=0&&number<=Ensemble.size()){
        return Ensemble.get(number);
      }
      return Ensemble.get(0);
    }

  public String toString(){
    String s="";
    for (Stockage Courant : Ensemble ) {
      s+=(Courant+"\n");
    }
    return s;
  }

  /**
   * Tri de la liste de la manière voulu
   * @param  Choix Choix du mode de triage
   * @return       String contenant la liste formaté
   */
  public String Tri(int Choix){
    if(Choix==0){
      ArrayList<Stockage> Init= new ArrayList<Stockage>(Ensemble);
      ArrayList<LivreAudio> Trier= new ArrayList<LivreAudio>();
      for (Stockage Actuel : Init ) {
        Trier.add((LivreAudio)Actuel);
      }
      Trier.sort((p1, p2) -> (p1.getAuteur().compareTo(p2.getAuteur())));
      String s="";
      for (LivreAudio livre : Trier) {
        s+="\t"+livre+"\n";
      }
      return s;
    }else{
      return "ERROR";
    }
  }
  
  /**
   * Transforme l'objet actuel en un Element
   * @param  document Document auquel rataché l'Element
   * @return          Element formaté d'apres les valeurs contenu dans l'objet
   */
  public Element getElement(Document document){
   Element client = document.createElement("Playlist");

     Element Liste = document.createElement("ListeAudios");

     for (Stockage Actuel : Ensemble ) {
       Liste.appendChild(Actuel.getElement(document));
     }
     client.appendChild(Liste);

     return client;
  }

}
