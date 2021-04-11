package comp.mycompany.com.JMusicHub.business;

import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class ChansonVolatile implements Serializable{
  public ArrayList<Chanson> Ensemble = new ArrayList<Chanson>();

  /**
   * Ajout d'une chanson dans la list des chansons en lui demandant des informations clavier
   */
  public void addUser(){
    Client client = new Client();
    MutableInt mutableInt =new MutableInt(0,0);
    try{
      Scanner clavier = new Scanner(System.in);
      System.out.println("Entrez le titre:");
      String Titre=clavier.nextLine();
      try{
        client.EnvoiString(Titre);
      }catch (Exception e) {

      }
      System.out.println("Duree:");
      int Duree=clavier.nextInt();
      mutableInt.setValue(Duree);
      try{
        client.EnvoiChoix(mutableInt);
      }catch (Exception e) {

      }
      clavier.nextLine();
      System.out.println("Artiste:");
      String Artiste=clavier.nextLine();
      try{
        client.EnvoiString(Artiste);
      }catch (Exception e) {

      }
      System.out.println("Contenu:");
      String Contenu=clavier.nextLine();
      try{
        client.EnvoiString(Contenu);
      }catch (Exception e) {

      }
      System.out.println("Genre ? (Veillez entrer le numero du genre voulu)"+Genre.allGenre());
      int genre=clavier.nextInt();

      mutableInt.setValue(genre);
      try{
        client.EnvoiChoix(mutableInt);
      }catch (Exception e) {

      }
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
