package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.*;
import java.io.Serializable;

public class PlaylistVolatile implements Serializable{
  public ArrayList<Playlist> Ensemble = new ArrayList<Playlist>();

  public void addUser(){

    Scanner clavier = new Scanner(System.in);
    System.out.println("Entrez le Nom:");
    String Nom=clavier.nextLine();

    Playlist nouveau= new Playlist(Nom,Ensemble.size()+1);
    Ensemble.add(nouveau);
  }


  public void addContenu(LivreAudioVolatile livreaudiovolatile,ChansonVolatile chansonvolatile){

    Serveur serveur = new Serveur();
    MutableInt ChoixClient = new MutableInt(0,0);
    int number, c;
    Scanner clavier = new Scanner(System.in);
    System.out.println("___________________________________________________");
    System.out.println("Titre de la playlist:");
    String Titre ="";
    try{
      Titre= serveur.ReceptionString();
    }catch (Exception e) {

    }
    Playlist nouvelle = new Playlist(Titre,Ensemble.size()+1);

    System.out.println("Contenu que vous pouvez ajouter:");
    System.out.println("Chanson:");
    System.out.println(chansonvolatile);

    System.out.println("Livre audio:");
    System.out.println(livreaudiovolatile);

    do {
      System.out.println("c ajouter chanson\nl ajouter livreaudio\nEntrez une commande:");
      try{serveur.ChoixUser(ChoixClient);}catch (Exception e) {

      }
      c=ChoixClient.getValue();
      System.out.println("selectionnez l'id à ajouter:");
      switch (c) {
        case 1:
          try{serveur.ChoixUser(ChoixClient);}catch (Exception e) {

          }
          number=ChoixClient.getValue();
          if(number>=0&&number<=(chansonvolatile.getEnsemble()).size()) nouvelle.add(chansonvolatile.get(number));
          break;

        case 2:
          try{serveur.ChoixUser(ChoixClient);}catch (Exception e) {

          }
          number=ChoixClient.getValue();
          if(number>=0&&number<=livreaudiovolatile.getEnsemble().size()) nouvelle.add(livreaudiovolatile.get(number));
          else System.out.println("Id inconnu");
          break;
      }
    } while (c!=3);
    Ensemble.add(nouvelle);
  }

  public String toString(){
    String s="";
    for (Playlist Courant : Ensemble ) {
      s+=(Courant+"\n");
    }
    return s;
  }

  public ArrayList<Playlist> getEnsemble(){
    return Ensemble;
  }

  public Playlist get(int number){
    return Ensemble.get(number);
  }

  public void add(Playlist Aajouter){
    Ensemble.add(Aajouter);
  }

  public void suppression(){
    int Number;
    Serveur serveur = new Serveur();
    MutableInt ChoixClient = new MutableInt(0,0);
    try{serveur.ChoixUser(ChoixClient);}catch (Exception e) {

    }
    Number=ChoixClient.getValue();
    Ensemble.remove(Number-1);
  }

  public String Trie(){
    ArrayList<Playlist> Trier= new ArrayList<Playlist>(Ensemble);
    Trier.sort((p1, p2) -> p1.getTitre().compareTo(p2.getTitre()));
    String s="";
    for (Playlist playlist : Trier) {
      s+="\t"+playlist+"\n";
    }
    return s;
  }

}
