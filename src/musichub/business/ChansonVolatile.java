package musichub.business;
import musichub.util.*;
import musichub.business.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ChansonVolatile{
  public ArrayList<Chanson> Ensemble = new ArrayList<Chanson>();

  /**
   * Ajout d'une chanson dans la list des chansons en lui demandant des informations clavier
   */
  public void addUser(){
    try{
      Scanner clavier = new Scanner(System.in);
      System.out.println("Entrez le titre:");
      String Titre=clavier.nextLine();
      System.out.println("Duree:");
      int Duree=clavier.nextInt();
      clavier.nextLine();
      System.out.println("Artiste:");
      String Artiste=clavier.nextLine();
      System.out.println("Contenu:");
      String Contenu=clavier.nextLine();
      System.out.println("Genre ? (Veillez entrer le numero du genre voulu)"+Genre.allGenre());
      int genre=clavier.nextInt();

      Chanson nouveau= new Chanson(Titre,Duree,Ensemble.size()+1,Artiste,Contenu,genre);
      Ensemble.add(nouveau);
    }catch (Exception e) {
      System.out.println("Erreur lors des entrées utilisateurs");
    }
    System.out.println("Chanson ajoutée");
  }

  /**
   * Ajoute une chanson dans la list des chansons
   * @param nouveau Chanson à ajouter
   */
  public void add(Chanson nouveau){
    Ensemble.add(nouveau);
  }

  /**
   * Renvoi la list des chansons de la Liste
   * @return String avec sur chaque ligne une Chanson
   */
  public String toString(){
    String s="";
    for (Chanson Courant : Ensemble ) {
      s+=("\t"+Courant+"\n");
    }
    return s;
  }

  /**
   * Accesseur de l'ArrayList des Chansons
   * @return renvoi l'ArrayList des Chansons
   */
  public ArrayList<Chanson> getEnsemble(){
    return Ensemble;
  }

  /**
   * Accès d'une chansons de l'ArrayList des Chansons
   * @param  number Numéro de la chanson à récupérer
   * @return        Chanson selectionnée
   */
  public Chanson get(int number){
    if(number>=0&&number<=Ensemble.size()){
      return Ensemble.get(number);
    }
    return Ensemble.get(0);
  }
}
