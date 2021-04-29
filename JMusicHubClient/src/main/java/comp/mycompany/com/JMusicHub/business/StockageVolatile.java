package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.*;
import java.io.Serializable;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import java.io.IOException;
import java.io.*;

public class StockageVolatile implements Serializable {
  public volatile ArrayList<Stockage> Ensemble = new ArrayList<Stockage>();

   public void add(Stockage nouveau){
     Ensemble.add(nouveau);
   }

  public String toString(){
    String s="";
    for (Stockage Courant : Ensemble ) {
      s+=("\t"+Courant+"\n");
    }
    return s;
  }

   public ArrayList<Stockage> getEnsemble(){
     return Ensemble;
   }


  public Stockage get(int number){
    if(number>=0&&number<=Ensemble.size()){
      return Ensemble.get(number);
    }
    return Ensemble.get(0);
  }

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

  public void addUser(){}

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
