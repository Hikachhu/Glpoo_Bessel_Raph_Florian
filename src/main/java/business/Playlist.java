package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.*;
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

public class Playlist  implements Listing{
  public ArrayList<Stockage> Ensemble = new ArrayList<Stockage>();
  String Nom;
  Integer ID;

  public Playlist(String Nom,int ID){
    this.Nom=Nom;
    this.ID=ID;
  }

  public String getTitre(){
    return Nom;
  }

  public Integer getID(){
    return ID;
  }

  public void Affiche(){
    for (Stockage Courant : Ensemble ) {
      System.out.println(Courant);
    }
  }

  /**
   * Ajoute un element mis en parametre dans la liste
   * @param stock parametre à rajouter
   */
  public void add(Stockage stock){
    Ensemble.add(stock);
  }

  public ArrayList<Stockage> getEnsemble(){
    return Ensemble;
  }

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


  public void addUser(){
    Scanner clavier = new Scanner(System.in);
    System.out.println("Entrez le titre:");
    String Titre=clavier.nextLine();
    System.out.println("Duree:");
    int Duree=clavier.nextInt();
    System.out.println("Artiste:");
    clavier.nextLine();
    String Artiste=clavier.nextLine();
    System.out.println("Contenu:");
    String Contenu=clavier.nextLine();
    System.out.println("Genre:");
    int genre=clavier.nextInt();

    Chanson nouveau= new Chanson(Titre,Duree,Ensemble.size()+1,Artiste,Contenu,genre);
    Ensemble.add(nouveau);
  }

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
