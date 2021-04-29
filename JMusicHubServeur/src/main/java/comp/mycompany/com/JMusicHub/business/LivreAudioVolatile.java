package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;
import java.io.*;

public class LivreAudioVolatile extends StockageVolatile implements Serializable{
  // public ArrayList<LivreAudio> Ensemble = new ArrayList<LivreAudio>();

  public void addUser(){
    Scanner clavier = new Scanner(System.in);
    System.out.println("Entrez le Titre:");
    String Titre=clavier.nextLine();
    System.out.println("Entrez la Duree:");
    int Duree=clavier.nextInt();
    System.out.println("Entrez l'auteur :");
    clavier.nextLine();
    String Auteur=clavier.nextLine();
    System.out.println("Entrez le Contenu:");
    String Contenu=clavier.nextLine();
    System.out.println("Entrez la langue:(Veillez entrer le numero du genre voulu)"+Langues.allLangues());
    int langue=clavier.nextInt();
    System.out.println("Entrez la categorie :(Veillez entrer le numero du genre voulu)"+Categorie.allCategorie());
    int categorie=clavier.nextInt();

    LivreAudio nouveau =  new LivreAudio(Titre,Duree,Ensemble.size()+1,Auteur,Contenu,langue,categorie);
    Ensemble.add(nouveau);
  }

  public String toString(){
    String s="";
    for (Stockage Courant : Ensemble ) {
      s+=(Courant+"\n");
    }
    return s;
  }

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

}
